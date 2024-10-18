package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.*;
import models.Complaint;
import java.util.*;

@WebServlet("/complaintServlet")
public class ComplaintServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/complaint.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Retrieve clientID from session
        HttpSession session = request.getSession();
        Integer clientID = (Integer) session.getAttribute("clientID");

        if (clientID == null) {
            request.setAttribute("errorMessage", "Client is not logged in. Please log in.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;  // Stop execution if client is not logged in
        }

        // Extract form data
        String issueID = request.getParameter("issueID");
        String description = request.getParameter("description");

        // Handle dates and formatting
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateReported = request.getParameter("dateReported") != null ? 
        LocalDate.parse(request.getParameter("dateReported"), formatter) : null;
        
        LocalDate dateResolved = null;
        
        try {
            if (request.getParameter("dateResolved") != null && !request.getParameter("dateResolved").isEmpty()) {
                dateResolved = LocalDate.parse(request.getParameter("dateResolved"), formatter);
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Invalid date format. Please enter a valid date.");
            request.getRequestDispatcher("/complaint.jsp").forward(request, response);
            return;  // Stop execution if there's an invalid date
        }

        boolean isComplaintSent = false;

        try {
            // Create a Complaint object
            Complaint complaint = new Complaint();
            complaint.setClientID(clientID);
            complaint.setIssueID(issueID);
            complaint.setDescription(description);
            complaint.setDateReported(dateReported);
            complaint.setDateResolved(dateResolved);

            // Add the complaint to the database
            addComplaint(complaint);

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

    public void addComplaint(Complaint complaint) throws SQLException, ClassNotFoundException {
        Connection con = null;
        DBConnection dbcon = new DBConnection();

        String sql = "INSERT INTO \"tb_Complaint\"(\"ClientID\", \"IssueID\", \"DateReported\", \"DateResolved\", \"Description\")"
                + " VALUES (?, ?, ?, ?, ?)";

        // Use try-with-resources to ensure proper closing of resources
        try (Connection connection = dbcon.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Set parameters
            pstmt.setInt(1, complaint.getClientID());
            pstmt.setString(2, complaint.getIssueID());
            pstmt.setDate(3, complaint.getDateReported() != null ? java.sql.Date.valueOf(complaint.getDateReported()) : null); 
            pstmt.setDate(4, complaint.getDateResolved() != null ? java.sql.Date.valueOf(complaint.getDateResolved()) : null);
            pstmt.setString(5, complaint.getDescription());

            // Execute the insert query
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error adding complaint: " + ex.getMessage());
            throw new SQLException("Failed to add complaint", ex);
        }
    }
}
