package controller;

import java.time.LocalDate;
import java.sql.*;
import models.*;  // Ensure models are imported correctly

public class DBConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/ApexCareDB";
    private static final String USER = "Tester";
    private static final String PASSWORD = "5432";
    private static final String DRIVER = "org.postgresql.Driver";

    // Getting connection with exception handling
    public Connection getConnection() throws ClassNotFoundException {
        try {
            Class.forName(DRIVER); // Load the JDBC driver
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            if (con != null) {
                System.out.println("Connected to DB");
            }
            return con; // Return the connection
        } catch (SQLException ex) {
            System.out.println("Could not connect: " + ex.getMessage());
            throw new RuntimeException("Database connection failed!", ex); // Re-throw as unchecked exception
        }
    }

    public User getUserDetails(String username) {
        User user = null;
        String query = "CALL sp_getuser(?)";  // Assuming a stored procedure that returns user details

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
             
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(rs.getString(1), rs.getString(2), rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Handle class not found
        }

        return user;
    }

    public Clients getClientDetails(String username) {
        Clients client = null;
        String query = "SELECT * FROM sp_getclientdetails(?)";  // Assuming a stored procedure

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
             
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                client = new Clients();
                client.setClientID(rs.getInt("ClientID"));
                client.setUsername(rs.getString("Username"));
                client.setFirstName(rs.getString("FirstName"));
                client.setLastName(rs.getString("LastName"));
                client.setPhone(rs.getString("Phone"));
                client.setEmail(rs.getString("Email"));
                client.setAddress(rs.getString("Address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Handle class not found
        }

        return client;
    }

    public void addIssue(Issue issue) {
        String query = "CALL sp_addissue(?, ?, ?, ?, ?, ?, ?)";  // Assuming this is a stored procedure

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
             
            ps.setInt(1, issue.getClientID());
            ps.setString(2, issue.getContractID());
            ps.setInt(3, issue.getCallID());
            ps.setString(4, issue.getPriority());
            ps.setString(5, issue.getStatus());
            ps.setDate(6, new java.sql.Date(issue.getStartDate().getTime()));
            ps.setString(7, issue.getDescription());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Handle class not found
        }
    }

    public void addComplaint(Complaint complaint) {
        String query = "CALL sp_addcomplaint(?, ?, ?, ?, ?)";  // Assuming this is a stored procedure

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
             
            ps.setInt(1, complaint.getClientID());
            ps.setString(2, complaint.getIssueID());
            ps.setDate(3, new java.sql.Date(complaint.getDateReported().getTime()));
            ps.setDate(4, new java.sql.Date(complaint.getDateResolved().getTime()));
            ps.setString(5, complaint.getDescription());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Handle class not found
        }
    }

    public void addFeedback(Feedback feedback) {
        String query = "CALL sp_addfeedback(?, ?, ?, ?)";  // Assuming this is a stored procedure

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
             
            ps.setString(1, feedback.getIssueID());
            ps.setInt(2, feedback.getRating());
            ps.setDate(3, new java.sql.Date(feedback.getDateProvided().getTime()));
            ps.setString(4, feedback.getComments());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Handle class not found
        }
    }

    public void insertUser(User user, Clients client) {
        String query = "CALL sp_insertclient(?, ?, ?, ?, ?, ?)";  // Assuming this is a stored procedure

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
             
            ps.setString(1, client.getFirstName());
            ps.setString(2, client.getLastName());
            ps.setString(3, client.getPhone());
            ps.setString(4, client.getEmail());
            ps.setString(5, client.getAddress());
            ps.setString(6, user.getPassword());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Handle class not found
        }
    }

    public ServiceAgent getAgentById(String agentID) {
        ServiceAgent serviceAgent = null;
        String query = "SELECT * FROM sp_getagents(?)";  // Assuming this is a stored procedure

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
             
            ps.setString(1, agentID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                serviceAgent = new ServiceAgent();
                serviceAgent.setAgentID(rs.getString("agentID"));
                serviceAgent.setUsername(rs.getString("username"));
                serviceAgent.setFirstName(rs.getString("firstname"));
                serviceAgent.setLastName(rs.getString("lastname"));
                serviceAgent.setPhone(rs.getString("phone"));
                serviceAgent.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Handle class not found
        }

        return serviceAgent;
    }
}
