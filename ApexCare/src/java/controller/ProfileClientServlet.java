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

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // Retrieve the logged-in username from the session
            String username = (String) request.getSession().getAttribute("username");

            if (username == null) {
                // Redirect to login page if username is not found in session
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            // Fetch client data from the database based on the username
            DBConnection db = new DBConnection();
            try {
                client = getClientByUsername(username);  // Implement this method to fetch client details
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to retrieve client data.");
                return;
            }

            // Set the client data as request attributes to be displayed in the JSP
            request.setAttribute("client", client);
            request.getRequestDispatcher("/profileClient.jsp").forward(request, response);
        }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data from the POST request
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        Clients updatedClient = new Clients(client.getClientID(), username, firstName, lastName, address, phone, email);

        // For testing purposes, just return the values as a response
        //response.getWriter().write(username + "; " + firstName + "; " + lastName + "; " + phone + "; " + email + "; " + address);
        
        DBConnection db = new DBConnection();
        //db.updateClient(updatedClient);
        db.updateClient2(updatedClient);
    }
    
    public Clients getClientByUsername(String username) throws ClassNotFoundException {
        Connection con = null;
        DBConnection db = new DBConnection();
        con = db.getConnection();
        Clients client = null;
        String query = "SELECT * FROM \"tb_Client\" WHERE \"Username\" = ?";

        try (
             PreparedStatement stmt = con.prepareStatement(query)) {

            // Set the parameter value
            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Map the ResultSet to a Clients object
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
            e.printStackTrace(); // Handle exception or log the error
        }

        return client;
    }

}