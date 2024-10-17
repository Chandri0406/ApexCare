package controller;


import models.Clients;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class DBConnection {
    private static final String Username = "Tester";
    private static final String Password = "5432";

    private  static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/ApexCareDB";
    
    Connection con;
    
    public Connection getConnection() throws ClassNotFoundException{
        try{
            Class.forName(DRIVER);
            this.con = DriverManager.getConnection(URL, Username, Password);
            if(this.con != null){
                System.out.println("Connected to DB");
            }
        }
        catch(SQLException ex){
            System.out.println("Could not connect: " + ex.getMessage());
        }
        return con;
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
    

}