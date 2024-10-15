package controller;

import models.Complaint;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;

@WebServlet("/submitComplaint")
public class ComplaintServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Create complaint object from request parameters
        int clientID = Integer.parseInt(request.getParameter("clientID"));
        String issueID = request.getParameter("issueID");
        LocalDate dateReported = LocalDate.parse(request.getParameter("dateReported"));
        LocalDate dateResolved = LocalDate.parse(request.getParameter("dateResolved"));
        String description = request.getParameter("description");

        Complaint complaint = new Complaint(clientID, issueID, dateReported, dateResolved, description);
        
        // Simple validation
        if (!complaint.isValid()) {
            request.setAttribute("error", "Invalid complaint data");
            request.getRequestDispatcher("/complaint.jsp").forward(request, response);
            return;
        }

        try {
            // Assuming you have a DBConnection class with addComplaint method
            DBConnection db = new DBConnection();
            db.addComplaint(complaint);

            // Redirect or forward to the complaint JSP with success message
            request.setAttribute("success", "Complaint sent successfully");
            request.getRequestDispatcher("/complaint.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while sending the complaint.");
            request.getRequestDispatcher("/complaint.jsp").forward(request, response);
        }
    }
}