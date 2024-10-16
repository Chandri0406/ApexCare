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
public class loginServlet extends HttpServlet {
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
        String agentRole = request.getParameter("Agent");
        String clientRole = request.getParameter("Client");
        String technicianRole = request.getParameter("Technician");

        try {
            boolean loggedIn = fetchData(username, password);

            if (loggedIn) {
                // Login successful
                User user = new User(username, password, clientRole); // Initialize user
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect(request.getContextPath() + "/profileClient.jsp");
            } else {
                // Login failed
                request.setAttribute("errorMessage", "Invalid username or password.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            // Handle database exceptions
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
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