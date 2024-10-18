package models;

public class Technician {
    // Properties
    private String technicianID;
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    // Constructor
    public Technician(String technicianID, String username, String firstName, String lastName, String phone, String email) {
        this.technicianID = technicianID;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }
    
    public Technician(){}

    // Getters and setters
    public String getTechnicianID() {
        return technicianID;
    }

    public void setTechnicianID(String technicianID) {
        this.technicianID = technicianID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void viewIssue(Issue issue) {
        System.out.println("Handling issue ID: " + issue.getIssueID() + ", Priority: " + issue.getPriority());
    }
}
