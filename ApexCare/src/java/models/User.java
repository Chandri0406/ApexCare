
package models;

public class User {
    // Public attributes
    private String username;
    private String password; // Private attribute
    private String role;

    // Public constructor
    public User(String Username, String Password, String Role) {
        this.username = Username;
        this.password = Password; // Password hashing can be added later
        this.role = Role;
    }
    
    public User(){}
    
    // Constructor with username and password only
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = "Client"; // Default role can be "User" or set it later
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
}
