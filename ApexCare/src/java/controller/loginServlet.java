package controller;

import controller.DBConnection;
import models.User;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class loginServlet extends HttpServlet {
    private DBConnection dbconn = new DBConnection();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Display login page
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        User user = dbconn.getUserDetails(username);

        if (user != null) {
            // Password check (ensure passwords are hashed in production)
            if (user.getPassword().equals(password)) {
                switch (user.getRole()) {
                    case "Agent":
                        response.sendRedirect("Agent/profileAgent.jsp");
                        break;

                    case "Client":
                        response.sendRedirect("Client/profileClient.jsp");
                        break;

                    case "Technician":
                        response.sendRedirect("Technician/profileTechnician.jsp");
                        break;
                }
            } else {
                request.setAttribute("error", "Invalid password.");
                doGet(request, response); // Show login page with error
            }
        } else {
            request.setAttribute("error", "User does not exist.");
            doGet(request, response); // Show login page with error
        }
    }
}