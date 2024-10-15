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
public class UserController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String confirmEmail = request.getParameter("confirmEmail");
        String address = request.getParameter("address");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        String errorMessage = null;

        // Validate email confirmation
        if (!email.equals(confirmEmail)) {
            errorMessage = "Emails do not match!";
        }

        // Validate password confirmation
        if (!password.equals(confirmPassword)) {
            errorMessage = "Passwords do not match!";
        }

        // If validation passes, insert the user into the database
        if (errorMessage == null) {
            User user = new User(username, password);
            Clients client = new Clients(firstName, lastName, phone, email, address);
            DBConnection db = new DBConnection();
            db.insertUser(user, client);
            response.sendRedirect("login.jsp"); // Redirect to login or success page
        } else {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/createUser.jsp").forward(request, response);
        }
    }
}
