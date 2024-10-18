/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Clients;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author chanb
 */
public class ProfileClientServletTest {
    
    public ProfileClientServletTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of doGet method, of class ProfileClientServlet.
     */
    @Test
    public void testDoGet() throws Exception {
        System.out.println("doGet");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        ProfileClientServlet instance = new ProfileClientServlet();
        instance.doGet(request, response);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doPost method, of class ProfileClientServlet.
     */
    @Test
    public void testDoPost() throws Exception {
        System.out.println("doPost");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        ProfileClientServlet instance = new ProfileClientServlet();
        instance.doPost(request, response);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClientByUsername method, of class ProfileClientServlet.
     */
    @Test
    public void testGetClientByUsername() throws Exception {
        System.out.println("getClientByUsername");
        String username = "";
        ProfileClientServlet instance = new ProfileClientServlet();
        Clients expResult = null;
        Clients result = instance.getClientByUsername(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateClient2 method, of class ProfileClientServlet.
     */
    @Test
    public void testUpdateClient2() {
        System.out.println("updateClient2");
        Clients client = null;
        ProfileClientServlet instance = new ProfileClientServlet();
        instance.updateClient2(client);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateClient method, of class ProfileClientServlet.
     */
    @Test
    public void testUpdateClient() {
        System.out.println("updateClient");
        Clients client = null;
        ProfileClientServlet instance = new ProfileClientServlet();
        instance.updateClient(client);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
