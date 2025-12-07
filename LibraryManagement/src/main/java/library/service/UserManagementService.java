
	package library.service;

import libraryy.User;
import libraryy.Borrow;
import java.util.List;
import libraryy.Admin;


public class UserManagementService {

    public boolean unregisterUser(User user, Admin admin, List<Borrow> loans) {

        if (!admin.isLoggedIn()) {
            System.out.println("Admin must be logged in!");
            return false;
        }

        if (user.getUnpaidFines() > 0) {
            System.out.println("User has unpaid fines!");
            return false;
        }

        for (Borrow b : loans) {
            if (b.getItem() != null && !b.getItem().isAvailable()) {
                System.out.println("User has active borrowed items!");
                return false;
            }
        }

        System.out.println("User unregistered successfully.");
        return true;
    }
    public boolean unregister(User user, List<Borrow> loans, boolean isAdminLoggedIn) {

        if (!isAdminLoggedIn) {
            throw new IllegalStateException("Only admin can unregister users.");
        }

        if (user.getUnpaidFines() > 0) {
            throw new IllegalStateException("User has unpaid fines and cannot be unregistered.");
        }

        if (!loans.isEmpty()) {
            throw new IllegalStateException("User has active loans and cannot be unregistered.");
        }

        return true;}
    
}