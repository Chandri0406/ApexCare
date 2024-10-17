package controller;

import models.User;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    Connection conn;
    private DBConnection dbconn = new DBConnection();
    User user;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("Username");
        String password = request.getParameter("Password");
        String role = request.getParameter("role"); // Capture the selected role from form

        try {
            boolean loggedIn = fetchData(username, password);

            if (loggedIn) {
                // Login successful, handle redirection based on role
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("role", role); // Store role in session if needed

                if ("Agent".equalsIgnoreCase(role)) {
                    response.sendRedirect(request.getContextPath() + "/profileAgent.jsp");
                } else if ("Client".equalsIgnoreCase(role)) {
                    response.sendRedirect(request.getContextPath() + "/profileClient.jsp");
                } else if ("Technician".equalsIgnoreCase(role)) {
                    response.sendRedirect(request.getContextPath() + "/profileTechnician.jsp");
                } else {
                    // If no role is selected, return an error
                    request.setAttribute("errorMessage", "Please select a role.");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
            } else {
                // Login failed
                request.setAttribute("errorMessage", "Invalid username or password.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            // Handle database exceptions
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean fetchData(String username, String password) throws SQLException, ClassNotFoundException {
       Connection conn = dbconn.getConnection();
        try (
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM \"tb_User\" WHERE \"Username\" = ? AND \"Password\" = ?")) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Check password
                    String dbPassword = rs.getString("Password");
                    if (dbPassword.equals(password)) {
                        return true; // Login successful
                    }
                }
            }
        }
        return false; // Login failed
    }
}