package controller;

import controller.DBConnection;
import models.Clients;
import models.User;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/createUser")
public class CreateUserServlet extends HttpServlet {
    
    Connection conn;
    private DBConnection dbconn = new DBConnection();  // Assuming you have a DBConnection class to manage the database
    User user;
    Clients client;
    
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Display login page
        request.getRequestDispatcher("/createUser.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String confirmEmail = request.getParameter("confirmEmail");
        String address = request.getParameter("address");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        boolean isValid = true;
        String errorMessage = "";

        // Validate email confirmation
        if (!email.equals(confirmEmail)) {
            errorMessage += "Emails do not match!<br/>";
            isValid = false;
        }

        // Validate password confirmation
        if (!password.equals(confirmPassword)) {
            errorMessage += "Passwords do not match!<br/>";
            isValid = false;
        }

        // If validation fails, send back to the JSP with error messages
        if (!isValid) {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("createUser.jsp").forward(request, response);
            return;
        }

        // Insert user into the database if validation passes
        try {
            DBConnection db = new DBConnection();
            user = new User(username, password); // Assuming User class has a constructor
            client = new Clients(firstName, lastName, phone, email, address); // Assuming Clients class has a constructor

            insertUser(user, client);

            // Redirect to login page after successful signup
            response.sendRedirect("/login.jsp");
        } catch (Exception ex) {
            request.setAttribute("errorMessage", "An error occurred while creating the user.");
            request.getRequestDispatcher("createUser.jsp").forward(request, response);
        }
    }
    
    public boolean usernameExists(String username) throws SQLException {
        String query = "SELECT EXISTS (SELECT 1 FROM \"tb_Client\" WHERE \"Username\" = ?)";
        
        try (
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);  

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next())  {
                    return rs.getBoolean(1);
                }
            }
        }
        return false;
    }

    public void insertUser(User user, Clients client) throws SQLException {
        String sql = "{Call sp_insertclient(?, ?, ?, ?, ?, ?, ?)}";
        
        try (
                PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getPassword());  
                stmt.setString(3, client.getFirstName());
                stmt.setString(4, client.getLastName());
                stmt.setString(5, client.getPhone());
                stmt.setString(6, client.getEmail());
                stmt.executeUpdate();
        }
    }
}
