package models;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    // Private attributes
    private String username;
    private String password; // Store hashed password
    private String role;

    // Default constructor
    public User() {
    }

       // Public constructor
    public User(String Username, String Password, String Role) {
        this.username = Username;
        this.password = Password; // Password hashing can be added later
        this.role = Role;
    }

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
        this.username = username;
    }
       
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Private method to hash password using SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", 0xFF & b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available.", e);
        }
    }

    // Public method to authenticate user (compare hashed passwords)
    public boolean authenticateUser(String username, String password) {
        return this.username.equals(username) && this.password.equals(hashPassword(password));
    }
}
