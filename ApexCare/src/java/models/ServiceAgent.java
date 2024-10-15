package models;

public class ServiceAgent {
    // UML ServiceAgentModel
    private String agentID; // int or string
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    // Constructor
    public ServiceAgent() {}

    // Constructor with parameters
    public ServiceAgent(String agentID, String username, String firstName, String lastName, String phone, String email) {
        this.agentID = agentID;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    // Getters and setters
    public String getAgentID() {
        return agentID;
    }

    public void setAgentID(String agentID) {
        this.agentID = agentID;
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
}
