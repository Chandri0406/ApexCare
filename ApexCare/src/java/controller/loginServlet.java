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
import models.*;

/**
 *
 * @author chanb
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    Connection conn;
    private DBConnection dbconn = new DBConnection();
    User user;
    private Clients client;
    private ServiceAgent agent;
    private Technician tech;

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
                    agent = getAgentByUsername(username);

                    session.setAttribute("firstname", agent.getFirstName());
                    session.setAttribute("lastname", agent.getLastName());
                    session.setAttribute("phone", agent.getPhone());
                    session.setAttribute("email", agent.getEmail());
                    
                    response.sendRedirect(request.getContextPath() + "/profileAgent.jsp");
                } else if ("Client".equalsIgnoreCase(role)) {
                    client = getClientByUsername(username);
                    
                    session.setAttribute("clientID", client.getClientID());
                    session.setAttribute("firstname", client.getFirstName());
                    session.setAttribute("lastname", client.getLastName());
                    session.setAttribute("phone", client.getPhone());
                    session.setAttribute("email", client.getEmail());
                    session.setAttribute("address", client.getAddress()); 
                    response.sendRedirect(request.getContextPath() + "/profileClient.jsp");
                } else if ("Technician".equalsIgnoreCase(role)) {
                    tech = getTechnicianByUsername(username);

                    session.setAttribute("firstname", tech.getFirstName());
                    session.setAttribute("lastname", tech.getLastName());
                    session.setAttribute("phone", tech.getPhone());
                    session.setAttribute("email", tech.getEmail());
                    
                    
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
    
    public Clients getClientByUsername(String username) throws ClassNotFoundException {
        conn = dbconn.getConnection();
        client = null;
        String query = "SELECT * FROM \"tb_Client\" WHERE \"Username\" = ?" ;

        try (
                PreparedStatement stmt = conn.prepareStatement(query)) 
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
    
    public ServiceAgent getAgentByUsername(String username) throws ClassNotFoundException {
        conn = dbconn.getConnection();
        client = null;
        String query = "SELECT * FROM \"tb_ServiceAgent\" WHERE \"Username\" = ?" ;

        try (
                PreparedStatement stmt = conn.prepareStatement(query)) 
        {
            stmt.setString(1, username);
            try (
                    ResultSet rs = stmt.executeQuery()) 
            {
                if (rs.next()) 
                {
                    agent = new ServiceAgent();
                    agent.setAgentID(rs.getString("AgentID"));
                    agent.setUsername(rs.getString("Username"));
                    agent.setFirstName(rs.getString("FirstName"));
                    agent.setLastName(rs.getString("LastName"));
                    agent.setPhone(rs.getString("Phone"));
                    agent.setEmail(rs.getString("Email"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return agent;
    }
    
    public Technician getTechnicianByUsername(String username) throws ClassNotFoundException {
        conn = dbconn.getConnection();
        client = null;
        String query = "SELECT * FROM \"tb_Technician\" WHERE \"Username\" = ?" ;

        try (
                PreparedStatement stmt = conn.prepareStatement(query)) 
        {
            stmt.setString(1, username);
            try (
                    ResultSet rs = stmt.executeQuery()) 
            {
                if (rs.next()) 
                {
                    tech = new Technician();
                    tech.setTechnicianID(rs.getString("TechnicianID"));
                    tech.setUsername(rs.getString("Username"));
                    tech.setFirstName(rs.getString("FirstName"));
                    tech.setLastName(rs.getString("LastName"));
                    tech.setPhone(rs.getString("Phone"));
                    tech.setEmail(rs.getString("Email"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tech;
    }
}