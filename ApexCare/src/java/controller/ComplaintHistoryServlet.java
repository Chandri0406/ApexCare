package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import models.Complaint;

@WebServlet("/complaintHistory")
public class ComplaintHistoryServlet extends HttpServlet {

    Complaint complaint;
    Connection con;
    DBConnection dbcon = new DBConnection();
    
   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/complaintHistory.jsp").forward(request, response);
    }
}
