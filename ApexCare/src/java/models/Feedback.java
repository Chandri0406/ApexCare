package models;

import java.time.LocalDate;

public class Feedback {
    
    private int feedbackID;
    private int clientID;
    private String issueID;
    private int rating;
    private String comments;
    private LocalDate dateProvided;

    // Default constructor
    public Feedback() {}

    // Parameterized constructor
    public Feedback(int clientID, String issueID, int rating, String comments, LocalDate dateProvided) 
    {
        this.clientID = clientID;
        this.issueID = issueID;
        this.rating = rating;
        this.comments = comments;
        this.dateProvided = dateProvided;
    }

    // Getters and setters
    public int getFeedbackID() 
    {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) 
    {
        this.feedbackID = feedbackID;
    }

    public int getClientID() 
    {
        return clientID;
    }

    public void setClientID(int clientID) 
    {
        this.clientID = clientID;
    }

    public String getIssueID() 
    {
        return issueID;
    }

    public void setIssueID(String issueID) 
    {
        this.issueID = issueID;
    }

    public int getRating() 
    {
        return rating;
    }

    public void setRating(int rating) 
    {
        this.rating = rating;
    }

    public String getComments() 
    {
        return comments;
    }

    public void setComments(String comments) 
    {
        this.comments = comments;
    }

    public LocalDate getDateProvided() {
        return dateProvided;
    }

    public void setDateProvided(LocalDate dateProvided) {
        this.dateProvided = dateProvided;
    }

    // Validation method
    public boolean isValid() {
        return rating >= 1 && rating <= 5 && (comments == null || comments.length() <= 500);
    }

    @Override
    public String toString() 
    {
        return "Feedback{" +
               "feedbackID=" + feedbackID +
               ", clientID=" + clientID +
               ", issueID='" + issueID + '\'' +
               ", rating=" + rating +
               ", comments='" + comments + '\'' +
               ", dateProvided=" + dateProvided +
                '}';
    }

}