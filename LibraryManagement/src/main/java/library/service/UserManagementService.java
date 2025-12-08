
	package library.service;

import libraryy.User;
import libraryy.Borrow;
import java.util.List;
import libraryy.Admin;

/**
 * Handles user management operations such as unregistering users.
 *
 * Rules enforced:
 * - Admin must be logged in.
 * - User must have no unpaid fines.
 * - User must not have active borrowed items.
 */

public class UserManagementService {

    public UserUnregisterStatus unregisterUser(User user, Admin admin, List<Borrow> loans) {
/* I  Used an enum to reduce repetitive print statements
   since excessive printing is considered a bad smell.*/
        if (!admin.isLoggedIn()) {
           // System.out.println("Admin must be logged in!");
            return UserUnregisterStatus.ADMIN_NOT_LOGGED_IN; 
        }

        if (user.getUnpaidFines() > 0) {
         //   System.out.println("User has unpaid fines!");
            return UserUnregisterStatus.UNPAID_FINES ;
        }

        for (Borrow b : loans) {
            if (b.getItem() != null && !b.getItem().isAvailable()) {
               // System.out.println("User has active borrowed items!");
                return UserUnregisterStatus.USER_HAS_ACTIVE_BORROWED_ITEM ;
            }
        }

      //  System.out.println("User unregistered successfully.");
        return  UserUnregisterStatus.USER_UNREGISTERED_SUCCESSFULLY;
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