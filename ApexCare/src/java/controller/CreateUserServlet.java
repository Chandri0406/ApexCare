package controller;

import controller.DBConnection;
import models.Clients;
import models.User;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/createUser")
public class CreateUserServlet extends HttpServlet {
    
    Connection conn;
    private DBConnection dbconn = new DBConnection();  // Assuming you have a DBConnection class to manage the database
    User user;
    Clients client;
    
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Display login page
        request.getRequestDispatcher("/createUser.jsp").forward(request, response);
    }
    
 protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    
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
        request.getRequestDispatcher("/createUser.jsp").forward(request, response);
        return;
    }

    // Insert user into the database if validation passes
    try {
        insertClient(username, password, firstName, lastName, phone, email, address);

        // Set success message and redirect to login.jsp
        request.getSession().setAttribute("successMessage", "User registered successfully! Please login.");
        response.sendRedirect(request.getContextPath() + "/login.jsp");

    } catch (Exception ex) {
        request.setAttribute("errorMessage", "An error occurred while creating the user.");
        request.getRequestDispatcher("/createUser.jsp").forward(request, response);
    }
}


    
    public boolean usernameExists(String username) throws SQLException {
        String query = "SELECT EXISTS (SELECT 1 FROM \"tb_Client\" WHERE \"Username\" = ?)";
        
        try (
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);  

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next())  {
                    return rs.getBoolean(1);
                }
            }
        }
        return false;
    }
    
    public void insertClient(String username, String password, String firstName, String lastName, String phone, String email, String address) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement userStmt = null;
        PreparedStatement clientStmt = null;

        // SQL statements for inserting into tb_User and tb_Client
        String userSql = "INSERT INTO public.\"tb_User\" (\"Username\", \"Password\", \"Role\") VALUES (?, ?, 'Client')";
        String clientSql = "INSERT INTO public.\"tb_Client\" (\"Username\", \"FirstName\", \"LastName\", \"Phone\", \"Email\", \"Address\") VALUES (?, ?, ?, ?, ?, ?)";

        try {
            conn = dbconn.getConnection();
            conn.setAutoCommit(false);  // Start transaction

            // Insert into tb_User
            userStmt = conn.prepareStatement(userSql);
            userStmt.setString(1, username);   // Username
            userStmt.setString(2, password);   // Password
            userStmt.executeUpdate();

            // Insert into tb_Client
            clientStmt = conn.prepareStatement(clientSql);
            clientStmt.setString(1, username);   // Username
            clientStmt.setString(2, firstName);  // FirstName
            clientStmt.setString(3, lastName);   // LastName
            clientStmt.setString(4, phone);      // Phone
            clientStmt.setString(5, email);      // Email
            clientStmt.setString(6, address);    // Address
            clientStmt.executeUpdate();

            conn.commit();  // Commit transaction

        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();  // Rollback in case of error
            }
            e.printStackTrace();
            throw new SQLException("Error while inserting client into database", e);
        } finally {
            if (userStmt != null) userStmt.close();
            if (clientStmt != null) clientStmt.close();
            if (conn != null) conn.close();
        }
    }

public void insertUserOld (User user, Clients client) throws SQLException, ClassNotFoundException {
        String sql = "{call sp_insertclient(?, ?, ?, ?, ?, ?, ?)}";
        
        try (
                Connection conn = dbconn.getConnection();  // Get the database connection
                CallableStatement stmt = conn.prepareCall(sql)) {

                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getPassword());  
                stmt.setString(3, client.getFirstName());
                stmt.setString(4, client.getLastName());
                stmt.setString(5, client.getPhone());
                stmt.setString(6, client.getEmail());
                stmt.executeUpdate();
        } catch (SQLException e) {
        e.printStackTrace();
        throw new SQLException("Error while inserting user using stored procedure", e);
    }
    }
}
