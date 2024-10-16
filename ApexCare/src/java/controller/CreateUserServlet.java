package controller;

import models.Clients;
import models.User;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/createUser")
public class CreateUserServlet extends HttpServlet {
    
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Display login page
        request.getRequestDispatcher("/createUser.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String confirmEmail = request.getParameter("confirmEmail");
        String address = request.getParameter("address");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        boolean isValid = true;
        String errorMessage = "";

        // Validate email confirmation
        if (!email.equals(confirmEmail)) {
            errorMessage += "Emails do not match!<br/>";
            isValid = false;
        }

        // Validate password confirmation
        if (!password.equals(confirmPassword)) {
            errorMessage += "Passwords do not match!<br/>";
            isValid = false;
        }

        // If validation fails, send back to the JSP with error messages
        if (!isValid) {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("createUser.jsp").forward(request, response);
            return;
        }

        // Insert user into the database if validation passes
        try {
            DBConnection db = new DBConnection();
            User user = new User(username, password); // Assuming User class has a constructor
            Clients clients = new Clients(firstName, lastName, phone, email, address); // Assuming Clients class has a constructor

            db.insertUser(user, clients);

            // Redirect to login page after successful signup
            response.sendRedirect("/login.jsp");
        } catch (Exception ex) {
            request.setAttribute("errorMessage", "An error occurred while creating the user.");
            request.getRequestDispatcher("createUser.jsp").forward(request, response);
        }
    }
}