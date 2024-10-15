package controller;

import models.Feedback;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.*;

/**
 *
 * @author chanb
 */
@WebServlet("/submitFeedback")
public class FeedbackServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Create feedback object from request parameters
        int clientID = Integer.parseInt(request.getParameter("clientID"));
        String issueID = request.getParameter("issueID");
        int rating = Integer.parseInt(request.getParameter("rating"));
        String comments = request.getParameter("comments");
        LocalDate dateProvided = LocalDate.parse(request.getParameter("dateProvided"));

        Feedback feedback = new Feedback(clientID, issueID, rating, comments, dateProvided);
        
        // Simple validation
        if (!feedback.isValid()) {
            request.setAttribute("error", "Invalid feedback data");
            request.getRequestDispatcher("/feedback.jsp").forward(request, response);
            return;
        }

        try {
            // Assuming you have a DBConnection class with addFeedback method
            DBConnection db = new DBConnection();
            db.addFeedback(feedback);

            // Redirect or forward to the feedback JSP with success message
            request.setAttribute("success", "Feedback submitted successfully");
            request.getRequestDispatcher("/feedback.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while submitting the feedback.");
            request.getRequestDispatcher("/feedback.jsp").forward(request, response);
        }
    }
}