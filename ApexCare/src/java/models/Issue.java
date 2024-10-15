package models;

import java.util.Date;

public class Issue {
    // Issue attributes
    private String issueID;
    private int clientID;
    private int callID;
    private String priority;
    private String status;
    private Date startDate;
    private Date endDate;
    private String description; // Description based on issue type

    // Constructor for creating an issue with parameters
    public Issue(String issueID, int clientID, int callID, String priority, String status, Date startDate, Date endDate, String description) {
        this.issueID = issueID;
        this.clientID = clientID;
        this.callID = callID;
        this.priority = priority;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    // Default constructor
    public Issue() {
    }

    // Getters and Setters
    public String getIssueID() {
        return issueID;
    }

    public void setIssueID(String issueID) {
        this.issueID = issueID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getCallID() {
        return callID;
    }

    public void setCallID(int callID) {
        this.callID = callID;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Method to determine the issue description
    public String determineIssueDescription() {
        return this.description;
    }

    public String getContractID() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
