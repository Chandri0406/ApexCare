package controller;

import models.Technician;
import controller.DBConnection;


import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/profileTechnician")
public class ProfileTechnicianServlet extends HttpServlet {

    private Technician technician;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve username from session
        String username = (String) request.getSession().getAttribute("username");

        if (username == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Fetch technician data from the database
        DBConnection db = new DBConnection();
        try {
            technician = getTechnician(username);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to retrieve technician data.");
            return;
        }

        // Set technician data as an attribute and forward to JSP
        request.setAttribute("technician", technician);
        request.getRequestDispatcher("/profileTechnician.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get updated technician details from the form
        String technicianID = request.getParameter("technicianID");
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        response.sendRedirect(request.getContextPath() + "/profileTechnician");
    }
    
    public Technician getTechnician(String username) throws ClassNotFoundException {
        Connection con = null;
        DBConnection db = new DBConnection();
        con = db.getConnection();
        Technician technician = null;
        String query = "SELECT * FROM \"tb_Technician\" WHERE \"Username\" = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    technician = new Technician();
                    technician.setTechnicianID(rs.getString("TechnicianID"));
                    technician.setUsername(rs.getString("Username"));
                    technician.setFirstName(rs.getString("FirstName"));
                    technician.setLastName(rs.getString("LastName"));
                    technician.setPhone(rs.getString("Phone"));
                    technician.setEmail(rs.getString("Email"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return technician;
    }
}