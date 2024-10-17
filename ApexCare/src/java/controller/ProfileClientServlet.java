package controller;

import models.Clients;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/profileClient")
public class ProfileClientServlet extends HttpServlet {

    private Clients client;
    Connection con = null;
    DBConnection db = new DBConnection();
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");

        if (username == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            client = getClientByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to retrieve client data.");
            return;
        }

        request.setAttribute("client", client);
        request.getRequestDispatcher("/profileClient.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        Clients updatedClient = new Clients(client.getClientID(), username, firstName, lastName, address, phone, email);

        DBConnection db = new DBConnection();
        updateClient2(updatedClient);
    }
    
    public Clients getClientByUsername(String username) throws ClassNotFoundException {
        con = db.getConnection();
        client = null;
        String query = "SELECT * FROM \"tb_Client\" WHERE \"Username\" = ?" ;

        try (
                PreparedStatement stmt = con.prepareStatement("SELECT * FROM \"tb_Client\" WHERE \"Username\" = ?" + username)) 
        {
            stmt.setString(1, username);
            try (
                    ResultSet rs = stmt.executeQuery()) 
            {
                if (rs.next()) 
                {
                    client = new Clients();
                    client.setClientID(rs.getInt("ClientID"));
                    client.setUsername(rs.getString("Username"));
                    client.setFirstName(rs.getString("FirstName"));
                    client.setLastName(rs.getString("LastName"));
                    client.setPhone(rs.getString("Phone"));
                    client.setEmail(rs.getString("Email"));
                    client.setAddress(rs.getString("Address"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return client;
    }
    
    public void updateClient2(Clients client){
        try{
            con = db.getConnection();
            
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
            con = db.getConnection();
            
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
