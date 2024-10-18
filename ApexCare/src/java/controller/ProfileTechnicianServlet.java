package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Clients;
import models.Technician;

@WebServlet("/profileTechnician")
public class ProfileTechnicianServlet extends HttpServlet {

    private Technician tech;
    Connection conn = null;
    DBConnection db = new DBConnection();
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        String firstname = (String) request.getSession().getAttribute("firstname");
        String lastname = (String) request.getSession().getAttribute("lastname");
        String phone = (String) request.getSession().getAttribute("phone");
        String email = (String) request.getSession().getAttribute("email");
        String address = (String) request.getSession().getAttribute("address");
        

        if (username == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.getRequestDispatcher("/profileTechnician.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        DBConnection db = new DBConnection();
    }
    
public Technician getTechnicianByUsername(String username) throws ClassNotFoundException {
        conn = db.getConnection();
        tech = null;
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
