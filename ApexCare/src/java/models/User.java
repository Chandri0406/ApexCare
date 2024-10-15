package models;

public class User {
    // Public attributes
    private String username;
    private String password; // Private attribute
    private String role;

    // Public constructor
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password; // Password hashing can be added later
        this.role = role;
    }

    public User(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Public method to authenticate user
    public boolean authenticateUser(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}
