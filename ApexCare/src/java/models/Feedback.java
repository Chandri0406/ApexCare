package models;

import java.util.Date;

public class Feedback {
    private int feedbackID;
    private int clientID;
    private String issueID;
    private int rating;
    private String comments;
    private Date dateProvided;

    // Constructor
    public Feedback(int clientID, String issueID, int rating, String comments, Date dateProvided) {
        this.clientID = clientID;
        this.issueID = issueID;
        this.rating = rating;
        this.comments = comments;
        this.dateProvided = dateProvided;
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

    public Date getDateProvided() {
        return dateProvided;
    }

    public void setDateProvided(Date dateProvided) {
        this.dateProvided = dateProvided;
    }

    // Optional: Validation logic or methods related to feedback
    public boolean isValid() {
        return rating >= 1 && rating <= 5; // For example, ensure rating is between 1 and 5
    }
}
