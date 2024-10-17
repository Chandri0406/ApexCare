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
import models.Complaint;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/complaintServlet")
public class complaintServlet extends HttpServlet {

    public void addComplaint(Complaint complaint) throws SQLException {
        Connection con = null;
        DBConnection dbcon = new DBConnection();
        try {
            con = dbcon.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(complaintServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "INSERT INTO \"tb_Complaint\"(\"ClientID\", \"IssueID\", \"DateReported\", \"DateResolved\", \"Description\")"
                + " VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            // Set parameters
            pstmt.setInt(1, complaint.getClientID());
            pstmt.setString(2, complaint.getIssueID());
            pstmt.setDate(3, Date.valueOf(complaint.getDateReported()));  // Convert LocalDate to SQL Date
            pstmt.setDate(4, Date.valueOf(complaint.getDateResolved()));  // Convert LocalDate to SQL Date
            pstmt.setString(5, complaint.getDescription());

            // Execute the insert query
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error adding complaint: " + ex.getMessage());
            throw new SQLException("Failed to add complaint", ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int clientID = Integer.parseInt(request.getParameter("clientID"));
        String issueID = request.getParameter("issueID");
        String description = request.getParameter("description");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateReported = LocalDate.parse(request.getParameter("dateReported"), formatter);
        LocalDate dateResolved = LocalDate.parse(request.getParameter("dateResolved"), formatter);

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
    
}