package controller;

import models.Clients;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/profileClient")
public class ProfileClientServlet extends HttpServlet {

    private Clients client;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Populate test data
        client = new Clients();
        client.setClientID(13);
        client.setUsername("AJones777");
        client.setFirstName("Alex");
        client.setLastName("Jones");
        client.setPhone("011 233 4576");
        client.setEmail("AJones777@gmail.com");
        client.setAddress("776 Port Avenue Space");

        // Set the client data as request attributes to be displayed in the JSP
        request.setAttribute("client", client);
        request.getRequestDispatcher("/profileClient.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data from the POST request
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        Clients updatedClient = new Clients(client.getClientID(), username, firstName, lastName, address, phone, email);

        // For testing purposes, just return the values as a response
        //response.getWriter().write(username + "; " + firstName + "; " + lastName + "; " + phone + "; " + email + "; " + address);
        
        DBConnection db = new DBConnection();
        //db.updateClient(updatedClient);
        db.updateClient(updatedClient);
    }
}