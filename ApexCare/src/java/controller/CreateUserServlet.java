import controller.DBConnection;
import jakarta.servlet.RequestDispatcher;
import models.User;
import models.Clients;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.*;
@WebServlet("/createUser")
public class CreateUserServlet extends HttpServlet {

   public void addUser(User user, Clients clients) throws SQLException {
        
        Connection con = null;
        DBConnection dbcon = new DBConnection();
        try {
            con = (Connection) dbcon.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "INSERT INTO \"tb_User\"(\"Username\", \"Password\")"
                + " VALUES (?, ?)";

        try (
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            // Set parameters
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, clients.getFirstName());  
            pstmt.setString(4, clients.getLastName());
            pstmt.setString(5, clients.getPhone());
            pstmt.setString(6, clients.getEmail());
            pstmt.setString(7, clients.getAddress());

           // Execute the stored procedure
        pstmt.executeUpdate();
        } catch (SQLException ex) {
            // Log or handle the error appropriately
            System.out.println("Error adding complaint: " + ex.getMessage());
            throw new SQLException("Failed to add complaint", ex);
        }
    }
/*
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

}
*/
}
