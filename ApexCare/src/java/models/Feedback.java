package models;

import java.time.LocalDate;

public class Feedback {
    public int feedbackID;
    public int clientID;
    public String issueID;
    public int rating;
    public String comments;
    public LocalDate dateProvided;

    // Constructor
    public Feedback(int clientID, String issueID, int rating, String comments, LocalDate dateProvided) {
        this.clientID = clientID;
        this.issueID = issueID;
        this.rating = rating;
        this.comments = comments;
        this.dateProvided = dateProvided;
    }
    
    public Feedback(){
        
    }

    // Getters and setters
    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDate getDateProvided() {
        return dateProvided;
    }

    public void setDateProvided(LocalDate dateProvided) {
        this.dateProvided = dateProvided;
    }

    // Optional: Validation logic or methods related to feedback
    public boolean isValid() {
        return rating >= 1 && rating <= 5; // For example, ensure rating is between 1 and 5
    }
}
