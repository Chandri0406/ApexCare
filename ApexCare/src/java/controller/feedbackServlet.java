package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import models.Feedback;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/feedbackServlet")
public class FeedbackServlet extends HttpServlet {
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("feedback.jsp").forward(request, response);
    }

    public void addFeedback(Feedback feedback) throws SQLException {
        Connection con = null;
        DBConnection dbcon = new DBConnection();
        try {
            con = dbcon.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ComplaintServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "INSERT INTO \"tb_ServiceFeedback\"(\"ClientID\", \"IssueID\", \"Rating\", \"Comments\", \"DateProvided\")"
                + " VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            // Set parameters
            pstmt.setInt(1, feedback.getClientID());
            pstmt.setString(2, feedback.getIssueID());
            pstmt.setInt(3, feedback.getRating());  // Convert LocalDate to SQL Date
            pstmt.setString(4, feedback.getComments());
            pstmt.setDate(5, Date.valueOf(feedback.getDateProvided()));  // Convert LocalDate to SQL Date

            // Execute the insert query
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error submitting feedback: " + ex.getMessage());
            throw new SQLException("Failed to submit feedback", ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int clientID = Integer.parseInt(request.getParameter("clientID"));
        String issueID = request.getParameter("issueID");
        int rating = Integer.parseInt(request.getParameter("rating"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateProvided = LocalDate.parse(request.getParameter("dateProvided"), formatter);
        String comments = request.getParameter("comments");

        boolean isComplaintSent = false;

        try {
            // Create a Complaint object
            Feedback feedback = new Feedback();
            feedback.setClientID(clientID);
            feedback.setIssueID(issueID);
            feedback.setRating(rating);
            feedback.setDateProvided(dateProvided);
            feedback.setComments(comments);
            
            

            // Add the complaint to the database
            addFeedback(feedback);

            isComplaintSent = true;  // Mark as successfully sent
        } catch (Exception ex) {
            ex.printStackTrace();
            // Set an error message in case of failure
            request.setAttribute("errorMessage", "An error occurred while sending the complaint: " + ex.getMessage());
        }

        // Set attributes to send back to JSP
        request.setAttribute("isComplaintSent", isComplaintSent);

        // Forward the request back to the JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/complaint.jsp");
        dispatcher.forward(request, response);
    }
    
}
