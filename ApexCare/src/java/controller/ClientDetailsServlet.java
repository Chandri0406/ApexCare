package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import models.Clients;

@WebServlet("/clientDetails")
public class ClientDetailsServlet extends HttpServlet {

    Clients client;
    Connection con;
    DBConnection dbcon = new DBConnection();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientID = request.getParameter("clientIDC");

        // Fetch client information from the database based on the clientID
        try {
            con = dbcon.getConnection(); // Assume DBConnection has this method to get connection

            String query = "SELECT * FROM \"tb_Client\" WHERE \"ClientID\" = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, clientID);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Populate the session or request attributes with client information
                request.setAttribute("usernameC", rs.getString("usernameC"));
                request.setAttribute("clientIDC", rs.getString("clientIDC"));
                request.setAttribute("firstnameC", rs.getString("firstnameC"));
                request.setAttribute("lastnameC", rs.getString("lastnameC"));
                request.setAttribute("phoneC", rs.getString("phoneC"));
                request.setAttribute("emailC", rs.getString("emailC"));
                request.setAttribute("addressC", rs.getString("addressC"));
            }

            // Forward the request to the JSP page to display client details
            request.getRequestDispatcher("/clientDetails.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("errorPage.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); // Handle both GET and POST in the same way
    }
}
