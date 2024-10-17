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
        String username = (String) request.getSession().getAttribute("username");

        if (username == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        DBConnection db = new DBConnection();
        try {
            client = getClientByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to retrieve client data.");
            return;
        }

        request.setAttribute("client", client);
        request.getRequestDispatcher("/profileClient.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        Clients updatedClient = new Clients(client.getClientID(), username, firstName, lastName, address, phone, email);

        DBConnection db = new DBConnection();
        db.updateClient2(updatedClient);
    }
    
    public Clients getClientByUsername(String username) throws ClassNotFoundException {
        Connection con = null;
        DBConnection db = new DBConnection();
        con = db.getConnection();
        Clients client = null;
        String query = "SELECT * FROM \"tb_Client\" WHERE \"Username\" = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return client;
    }
}
