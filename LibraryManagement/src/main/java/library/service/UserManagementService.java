
	package library.service;

	import libraryy.User;
	import libraryy.Borrow;

import java.time.LocalDate;
import java.util.List;

	public class UserManagementService {

	    public boolean unregister(User user, List<Borrow> loans, boolean isAdmin) {

	        if (!isAdmin) {
	            throw new SecurityException("Only admin can unregister users.");
	        }

	        if (user.getUnpaidFines() > 0) {
	            throw new IllegalStateException("Cannot unregister user with unpaid fines.");
	        }

	        for (Borrow l : loans) {
	            if (!l.isOverdue(LocalDate.now())) {
	                throw new IllegalStateException("Cannot unregister user with active loans.");
	            }
	        }

	        return true;
	    }
	}

