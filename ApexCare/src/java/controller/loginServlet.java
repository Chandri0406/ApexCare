package controller;

import models.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class loginServlet extends HttpServlet {
    private DBConnection dbconn = new DBConnection();  // Assuming you have a DBConnection class to manage the database

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Display the login page
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("Username");
        String password = request.getParameter("Password");

        // Fetch user details based on the username
        User user = null;
        try {
            user = dbconn.getUserDetails(username);
        } catch (SQLException ex) {
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (user != null) {
            // Check if the password matches
            if (user.getPassword().equals(password)) { // In production, ensure to use password hashing
                // Store user details in session
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("user", user); // You can store the user object if needed
                
                // Redirect based on the user role
                switch (user.getRole()) {
                    case "Agent":
                        response.sendRedirect(request.getContextPath() + "/profileAgent.jsp");
                        break;
                        
                    case "Client":
                        response.sendRedirect(request.getContextPath() + "/profileClient.jsp");
                        break;
                        
                    case "Technician":
                        response.sendRedirect(request.getContextPath() + "/profileTechnician.jsp");
                        break;
                        
                    default:
                        // If no valid role is found, send back to login with an error
                        request.setAttribute("errorMessage", "Invalid role.");
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
            } else {
                // Password doesn't match
                request.setAttribute("errorMessage", "Invalid password.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } else {
            // User not found
            request.setAttribute("errorMessage", "No user found. Please register.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
