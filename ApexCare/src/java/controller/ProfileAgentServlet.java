package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.ServiceAgent;

/**
 *
 * @author chanb
 */
@WebServlet("/profileAgent")
public class ProfileAgentServlet extends HttpServlet {

     private ServiceAgent agent;
    Connection con = null;
    DBConnection db = new DBConnection();
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        String firstname = (String) request.getSession().getAttribute("firstname");
        String lastname = (String) request.getSession().getAttribute("lastname");
        String phone = (String) request.getSession().getAttribute("phone");
        String email = (String) request.getSession().getAttribute("email");
        String address = (String) request.getSession().getAttribute("address");
        

        if (username == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            /*client = getClientByUsername(username);
            System.out.println(client.getClientID());
            System.out.println(client.getFirstName());
            System.out.println(client.getLastName());
            System.out.println(client.getEmail());
            System.out.println(client.getPhone());
            System.out.println(client.getAddress());*/
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to retrieve client data.");
            return;
        }
        request.getRequestDispatcher("/profileClient.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
    }
    
    public ServiceAgent getAgentByUsername(String username) throws ClassNotFoundException {
        con = db.getConnection();
        agent = null;
        String query = "SELECT * FROM \"tb_ServiceAgent\" WHERE \"Username\" = ?" ;

        try (
                PreparedStatement stmt = con.prepareStatement(query)) 
        {
            stmt.setString(1, username);
            try (
                    ResultSet rs = stmt.executeQuery()) 
            {
                while (rs.next()) 
                {
                    agent = new ServiceAgent();
                    agent.setAgentID(rs.getString("AgentID"));
                    agent.setUsername(rs.getString("Username"));
                    agent.setFirstName(rs.getString("FirstName"));
                    agent.setLastName(rs.getString("LastName"));
                    agent.setPhone(rs.getString("Phone"));
                    agent.setEmail(rs.getString("Email"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return agent;
    }
    
}
