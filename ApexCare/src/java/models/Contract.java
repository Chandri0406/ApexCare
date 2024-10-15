package models;

import java.util.*;
 
public class Contract {
    // Contract attributes
    private String contractID;
    private int clientID;
    private Date startDate;
    private Date endDate;
    private String status;
    private String type; // Can be "Commercial", "Private", or "Warranty"
    private String residency;
 
    // Constructor for creating a contract with parameters
    public Contract(String contractID, int clientID, Date startDate, Date endDate, String status, String type, String residency) {
        this.contractID = contractID;
        this.clientID = clientID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.type = type;
        this.residency = residency;
    }
 
    // Default constructor
    public Contract() {
        this.type = "Default"; // Default type
    }
 
    // Getters and setters
    public String getContractID() {
        return contractID;
    }
 
    public void setContractID(String contractID) {
        this.contractID = contractID;
    }
 
    public int getClientID() {
        return clientID;
    }
 
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }
 
    public Date getStartDate() {
        return startDate;
    }
 
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
 
    public Date getEndDate() {
        return endDate;
    }
 
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
 
    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }
 
    public String getType() {
        return type;
    }
 
    public void setType(String type) {
        this.type = type;
    }
 
    public String getResidency() {
        return residency;
    }
 
    public void setResidency(String residency) {
        this.residency = residency;
    }
    
    // Method to determine the type of contract
    public String determineType() {
        return this.type;
    }
    
}