package controller;

import controller.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;

public class ContractHistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        DBConnection dbcon = new DBConnection();
        try {
            con = dbcon.getConnection();

            int contractId = Integer.parseInt(request.getParameter("selectedID"));

            // Prepare and execute the SQL statement to fetch contract details
            try (PreparedStatement preparedStatement = con.prepareStatement("SELECT ClientID, StartDate, EndDate, Status, Type, Residency FROM tb_contracts WHERE ContractID = ?")) {
                preparedStatement.setInt(1, contractId);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int clientId = resultSet.getInt("ClientID");
                    Date startDate = resultSet.getDate("StartDate");
                    Date endDate = resultSet.getDate("EndDate");
                    String status = resultSet.getString("Status");
                    String type = resultSet.getString("Type");
                    String residency = resultSet.getString("Residency");

                    // Set contract details as request attributes
                    request.setAttribute("clientId", clientId);
                    request.setAttribute("startDate", startDate);
                    request.setAttribute("endDate", endDate);
                    request.setAttribute("status", status);
                    request.setAttribute("type", type);
                    request.setAttribute("residency", residency);

                    // Forward the request to the JSP
                    request.getRequestDispatcher("contractHistory.jsp").forward(request, response);
                } else {
                    // Handle the case where no contract is found
                    request.setAttribute("errorMessage", "Contract not found.");
                    request.getRequestDispatcher("contractHistory.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                Logger.getLogger(ContractHistoryServlet.class.getName()).log(Level.SEVERE, null, e);
                // Handle the exception appropriately (e.g., set an error message attribute)
            }
        } catch (ClassNotFoundException e) {
           // Logger.getLogger(.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            // Close the database connection if it's not null
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                //    Logger.getLogger(ContractHistoryServlet..class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
}