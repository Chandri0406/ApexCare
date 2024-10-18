package controller;

import com.sun.jdi.connect.spi.Connection;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Clients;
import controller.DBConnection;
import java.sql.*;

@WebServlet("/clientDetails")
public class ClientDetailsServlet extends HttpServlet {

    DBConnection dbcon = new DBConnection();
    Connection con;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the client ID from the request
        try {
            int clientID = Integer.parseInt(request.getParameter("clientIDS"));

            // Fetch client information using the getClientByID method
            Clients client = getClientByID(clientID);

            if (client != null) {
                // Populate request attributes with client information
                request.setAttribute("clientIDC", client.getClientID());
                request.setAttribute("usernameC", client.getUsername());
                request.setAttribute("firstnameC", client.getFirstName());
                request.setAttribute("lastnameC", client.getLastName());
                request.setAttribute("phoneC", client.getPhone());
                request.setAttribute("emailC", client.getEmail());
                request.setAttribute("addressC", client.getAddress());
            } else {
                // If no client is found, set an error message
                request.setAttribute("errorMessage", "No client found with ID: " + clientID);
            }

            // Forward the request to the clientDetails.jsp page to display client details
            request.getRequestDispatcher("/clientDetails.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            // Handle incorrect input for client ID
            request.setAttribute("errorMessage", "Invalid Client ID. Please enter a numeric value.");
            request.getRequestDispatcher("/clientDetails.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the client ID from the request
        try {
            int clientID = Integer.parseInt(request.getParameter("clientIDC"));

            // Fetch client information using the getClientByID method
            Clients client = getClientByID(clientID);

            if (client != null) {
                // Populate request attributes with client information
                request.setAttribute("clientIDC", client.getClientID());
                request.setAttribute("usernameC", client.getUsername());
                request.setAttribute("firstnameC", client.getFirstName());
                request.setAttribute("lastnameC", client.getLastName());
                request.setAttribute("phoneC", client.getPhone());
                request.setAttribute("emailC", client.getEmail());
                request.setAttribute("addressC", client.getAddress());
            } else {
                // If no client is found, set an error message
                request.setAttribute("errorMessage", "No client found with ID: " + clientID);
            }

            // Forward the request to the clientDetails.jsp page to display client details
            request.getRequestDispatcher("/clientDetails.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            // Handle incorrect input for client ID
            request.setAttribute("errorMessage", "Invalid Client ID. Please enter a numeric value.");
            request.getRequestDispatcher("/clientDetails.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }

    // Utility method to get client details by ID
    public Clients getClientByID(int clientID) throws ClassNotFoundException, SQLException {
        
        java.sql.Connection conn = dbcon.getConnection();
        Clients client = null;

        String query = "SELECT * FROM \"tb_Client\" WHERE \"ClientID\" = ?";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setInt(1, clientID);
        ResultSet rs = pst.executeQuery();

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

        return client;
    }
}
