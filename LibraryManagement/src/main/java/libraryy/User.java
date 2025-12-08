package libraryy;
/**
 * Represents a library user.
 * Stores basic user information such as name, email, and unpaid fines.
 * Users accumulate fines and can make payments.
 */


public class User {

    private String name;
    private String email;
    private double unpaidFines;

    public User(String name, String email) {
        this.setName(name);
        this.email = email;
        this.unpaidFines = 0;
    }

    public String getEmail() {
        return email;
    }

    public double getUnpaidFines() {
        return unpaidFines;
    }

    public void addFine(double f) {
        unpaidFines += f;
    }

    public void pay(double amount) {
        unpaidFines -= amount;
        if (unpaidFines < 0) unpaidFines = 0;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}