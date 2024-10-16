package models;

import java.time.LocalDate;
import java.util.Date;



public class Complaint {
    private int complaintID;
    private int clientID;
    private String issueID;
    private Date dateReported;
    private Date dateResolved; // Nullable if not yet resolved
    private String description;

    // Constructor
    public Complaint(int clientID, String issueID, Date dateReported, Date dateResolved, String description) {
        this.clientID = clientID;
        this.issueID = issueID;
        this.dateReported = dateReported;
        this.description = description;
    }
    
    public Complaint(){}

    // Getters and setters
    public int getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(int complaintID) {
        this.complaintID = complaintID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getIssueID() {
        return issueID;
    }

    public void setIssueID(String issueID) {
        this.issueID = issueID;
    }

    public Date getDateReported() {
        return dateReported;
    }

    public void setDateReported(Date dateReported) {
        this.dateReported = dateReported;
    }

    public Date getDateResolved() {
        return dateResolved;
    }

    public void setDateResolved(Date dateResolved) {
        this.dateResolved = dateResolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Optional: Validation logic
    public boolean isResolved() {
        return dateResolved != null;
    }

    public void setDateReported(LocalDate dateReported) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setDateResolved(LocalDate dateResolved) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
