package libraryy;
/**
 * Represents the admin of the library system.
 * Supports login functionality required for performing restricted actions
 * like adding books and unregistering users.
 */

public class Admin {
	private String username;
    private String password;
    private boolean loggedIn = false;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean login(String user, String pass) {
        if (user.equals(username) && pass.equals(password)) {
            loggedIn = true;
            System.out.println(" Login successful!");
            return true;
        } else {
            System.out.println(" Invalid credentials!");
            return false;
        }
    }

    public void logout() {
        loggedIn = false;
        System.out.println("Logged out successfully.");
    }

    public boolean isLoggedIn() { return loggedIn; }
}


