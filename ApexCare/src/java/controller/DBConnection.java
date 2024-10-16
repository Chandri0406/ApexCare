package controller;

import models.User;
import models.Clients;
import java.sql.*;

public class DBConnection {
    private static final String USERNAME = "Tester";  // Update with actual DB username
    private static final String PASSWORD = "5432";    // Update with actual DB password
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/ApexCareDB";

    private Connection con;

    // Get connection to the database
    public Connection getConnection() throws ClassNotFoundException{
        try{
            Class.forName(DRIVER);
            this.con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if(this.con != null){
                System.out.println("Connected to DB");
            }
        }
        catch(SQLException ex){
            System.out.println("Could not connect: " + ex.getMessage());
        }
        return con;
    }

    // Close connection
    public void closeConnection() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

    // Insert user and client data using the stored procedure
public void insertUser(User user, Clients clients) throws SQLException, ClassNotFoundException {
    Connection connection = getConnection();
    CallableStatement stmt = null;

    try {
        String sql = "{Call sp_insertclient(?, ?, ?, ?, ?, ?, ?)}";
        stmt = connection.prepareCall(sql);

        // Set parameters for the stored procedure
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        stmt.setString(3, clients.getFirstName());
        stmt.setString(4, clients.getLastName());
        stmt.setString(5, clients.getPhone());
        stmt.setString(6, clients.getEmail());
        stmt.setString(7, clients.getAddress());

        // Print parameters to console for debugging
        System.out.println("Executing stored procedure with parameters:");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());
        System.out.println("First Name: " + clients.getFirstName());
        System.out.println("Last Name: " + clients.getLastName());
        System.out.println("Phone: " + clients.getPhone());
        System.out.println("Email: " + clients.getEmail());
        System.out.println("Address: " + clients.getAddress());

        // Execute the stored procedure
        stmt.executeUpdate();
        System.out.println("Client and user inserted successfully.");
    } catch (SQLException e) {
        System.err.println("Error inserting client: " + e.getMessage());
        throw e;
    } finally {
        if (stmt != null) stmt.close();
        closeConnection();
    }
}


    // Retrieve user details using the stored procedure
    public User getUserDetails(String username) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        CallableStatement stmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            String sql = "{Call sp_getuser(?)}";
            stmt = connection.prepareCall(sql);

            // Set parameter for the stored procedure
            stmt.setString(1, username);

            // Execute the stored procedure and get result set
            rs = stmt.executeQuery();

            // Check if a user is found
            if (rs.next()) {
                String foundUsername = rs.getString("Username");
                String password = rs.getString("Password");
                String role = rs.getString("Role");

                // Create and return a User object with retrieved data
                user = new User(foundUsername, password, role);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving user: " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            closeConnection();
        }

        return user;
    }
    
    public void updateClient(Clients client){
        try{
            con = getConnection();
            
            CallableStatement statement = con.prepareCall("{call sp_updateclient(?, ?, ?, ?, ?, ?, ?)}");

            statement.setInt(1, client.getClientID());
            statement.setString(2, client.getUsername());
            statement.setString(3, client.getFirstName());
            statement.setString(4, client.getLastName());
            statement.setString(5, client.getPhone());
            statement.setString(6, client.getEmail());
            statement.setString(7, client.getAddress());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Client updated successfully.");
            } else {
                System.out.println("No client found with the specified ID.");
            }

            statement.close();
            con.close();
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
    }
    
    public void updateClient2(Clients client){
        try{
            con = getConnection();
            
            String updateQuery = "UPDATE \"public.tb_Client\" SET \"Username\" = ?, \"FirstName\" = ?, \"LastName\" = ?, \"Phone\" = ?, \"Email\" = ?, \"Address\" = ? WHERE \"ClientID\" = ?";

            PreparedStatement statement = con.prepareStatement(updateQuery);

            statement.setString(1, client.getUsername());
            statement.setString(2, client.getFirstName());
            statement.setString(3, client.getLastName());
            statement.setString(4, client.getPhone());
            statement.setString(5, client.getEmail());
            statement.setString(6,client.getAddress());
            statement.setInt(7, client.getClientID());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Client updated successfully.");
            } else {
                System.out.println("No client found with the specified ID.");
            }

            statement.close();
            con.close();
            
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
    }
}