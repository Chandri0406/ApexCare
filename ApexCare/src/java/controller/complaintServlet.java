package controller;

import models.Complaint;

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
/**
 *
 * @author peter
 */

@WebServlet("/complaint")
public class complaintServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/complaint.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Retrieve form data from request
        int clientID = Integer.parseInt(request.getParameter("clientID"));
        String issueID = request.getParameter("issueID");
        String description = request.getParameter("description"); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateReported = LocalDate.parse(request.getParameter("dateReported"), formatter);
        LocalDate dateResolved = LocalDate.parse(request.getParameter("dateResolved"), formatter);
        
        boolean isComplaintSent = false;  // Track if the complaint was successfully sent
        
        try {
            // Create a Complaint object
            Complaint complaint = new Complaint();
            complaint.setClientID(clientID);
            complaint.setIssueID(issueID);
            complaint.setDescription(description);
            complaint.setDateReported(dateReported);  // Assuming a date string, parse if necessary
            complaint.setDateResolved(dateResolved);
            
            // Use your DBConnection to add the complaint to the database
            addComplaint(complaint);

            isComplaintSent = true;  // Mark as successfully sent
        } catch (Exception ex) {
            // Log or handle any errors
            ex.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while sending the complaint.");
        }
        
        // Set attributes to display messages back in JSP
        request.setAttribute("isComplaintSent", isComplaintSent);
        
        // Forward back to the JSP page with success/error feedback
        RequestDispatcher dispatcher = request.getRequestDispatcher("/complaint.jsp");
        dispatcher.forward(request, response);
    }

    
    public void addComplaint(Complaint complaint) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO \"tb_Complaint\"(\"ClientID\", \"IssueID\", \"DateReported\", \"DateResolved\", \"Description\")"
                + " VALUES (?, ?, ?, ?, ?)";
        
        Connection con = null;
        DBConnection dbcon = new DBConnection();
        con = dbcon.getConnection();

        
        try (
             PreparedStatement pstmt = con.prepareStatement(sql)) 
        {
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            // Log or handle the error appropriately
            System.out.println("Error adding complaint: " + ex.getMessage());
            throw new SQLException("Failed to add complaint", ex);
        }
    }

}

