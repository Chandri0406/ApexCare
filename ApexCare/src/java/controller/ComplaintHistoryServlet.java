package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import models.Complaint;

@WebServlet("/complaintHistory")
public class ComplaintHistoryServlet extends HttpServlet {

    DBConnection dbcon = new DBConnection();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Complaint> complaints = new ArrayList<>();
        String clientIdStr = request.getParameter("clientIDS"); // Fetch the parameter

        try {
            // Validate and parse client ID
            if (clientIdStr != null && !clientIdStr.isEmpty()) {
                int clientId = Integer.parseInt(clientIdStr); // Only parse if not null
                complaints = fetchComplaintsByClientId(clientId);
                // Fetch complaints for the client
                for(Complaint com : complaints)
                {
                                
                request.setAttribute("complaintid", com.ComplaintID);
                request.setAttribute("clientid", com.ClientID);
                request.setAttribute("issueid", com.IssueID);
                request.setAttribute("datereported", com.DateReported);
                request.setAttribute("dateresolved", com.DateResolved);
                request.setAttribute("description", com.Description);
                }
                
                request.setAttribute("complaints", complaints);

            } else {
                request.setAttribute("errorMessage", "Client ID is required.");
            }
        } catch (NumberFormatException e) {
            // Handle invalid clientId format
            request.setAttribute("errorMessage", "Invalid Client ID format.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
        }
        

        request.setAttribute("complaints", complaints);
        request.getRequestDispatcher("/complaintHistory.jsp").forward(request, response);
    }

    private List<Complaint> fetchComplaintsByClientId(int clientId) throws ClassNotFoundException {
        List<Complaint> complaints = new ArrayList<>();
        Connection con = null;

        try {
            con = dbcon.getConnection(); // Get database connection
            String query = "SELECT *FROM \"tb_Complaint\" WHERE \"ClientID\" = ?";
            System.out.println("SQL Query: " + query + " with ClientID: " + clientId);
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, clientId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Complaint complaint = new Complaint();
                complaint.setComplaintID(rs.getInt("ComplaintID"));
                complaint.setClientID(rs.getInt("ClientID"));
                complaint.setIssueID(rs.getString("IssueID"));
                complaint.setDateReported(rs.getObject("DateReported", LocalDate.class)); // Retrieve LocalDate
                complaint.setDateResolved(rs.getObject("DateResolved", LocalDate.class)); // Retrieve LocalDate
                complaint.setDescription(rs.getString("Description"));
                
                System.out.println("Fetched complaint: " + complaint); // Log complaint details
                complaints.add(complaint);
                
            }
           

        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions appropriately
        } finally {
            try {
                if (con != null) con.close(); // Close the connection
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
         return complaints;
        
    }
}

    
    
    

