package controller;

import controller.DBConnection;
import models.User;

import java.io.IOException;
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
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Debugging: Log the attempted login
        System.out.println("Attempting to login with username: " + username);

        User user = dbconn.getUserDetails(username);

        if (user != null) {
            // Password check (ensure passwords are hashed in production)
            if (user.getPassword().equals(password)) {  // Replace with hashed password check
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
                        request.setAttribute("error", "Invalid role.");
                        doGet(request, response); // Show login page with error
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
