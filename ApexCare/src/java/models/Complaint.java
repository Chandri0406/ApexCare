package models;

import java.time.LocalDate;

public class Complaint {
    public int ComplaintID;
    public int ClientID;
    public String IssueID;
    public LocalDate DateReported;
    public LocalDate DateResolved; // Nullable if not yet resolved
    public String Description;

    // Constructor
    public Complaint(int clientID, String issueID, LocalDate dateReported, LocalDate dateResolved, String description)
        {
            ClientID = clientID;
            IssueID = issueID;
            DateReported = dateReported;
            DateResolved = dateResolved;
            Description = description;
        }

    public Complaint() {
        
    }

    // Getters and setters
    public int getComplaintID() {
        return ComplaintID;
    }

    public void setComplaintID(int complaintID) {
        this.ComplaintID = complaintID;
    }

    public int getClientID() {
        return ClientID;
    }

    public void setClientID(int clientID) {
        this.ClientID = clientID;
    }

    public String getIssueID() {
        return IssueID;
    }

    public void setIssueID(String issueID) {
        this.IssueID = issueID;
    }

    public LocalDate getDateReported() {
        return DateReported;
    }


    public LocalDate getDateResolved() {
        return DateResolved;
    }


    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    // Optional: Validation logic
    public boolean isResolved() {
        return DateResolved != null;
    }

    public void setDateReported(LocalDate dateReported) {
        this.DateReported = dateReported; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setDateResolved(LocalDate dateResolved) {
        this.DateResolved = dateResolved; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
