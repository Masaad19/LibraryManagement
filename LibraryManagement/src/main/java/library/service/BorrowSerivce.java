package library.service;
import java.time.LocalDate;
import libraryy.MediaItem;
import libraryy.Book;
import libraryy.Borrow;
import libraryy.User;
import java.util.*;
/**
 * Handles borrowing operations in the library.
 * Ensures the item is available, records the borrow date,
 * and creates a Borrow record that is stored in the user's loan list.
 *
 * Also provides fine calculation based on overdue days.
 */

public class BorrowSerivce {
	//Refactoring solution
	private void validateAvailability(MediaItem item) {
	    if (!item.isAvailable()) {
	        throw new IllegalStateException("Item unavailable");
	    }
	}

	public Borrow borrow(User user, MediaItem item, List<Borrow> loans) {

	  /*  if (!item.isAvailable()) {
	        throw new IllegalStateException("Item unavailable");
	    } */
// refactoring It goes against the principle(Don’t Repeat Yourself) 
		 validateAvailability( item);
	    LocalDate today = LocalDate.now();
	    item.borrow(today);

	    Borrow borrow = new Borrow((Book) item, today, item.getBorrowPeriod());
	    loans.add(borrow);

	    return borrow;
	}
    public void borrow(MediaItem item) {
      /*  if (!item.isAvailable()) {
            throw new IllegalStateException("Item unavailable");
        }*/
    	// refactoring
    			 validateAvailability( item); 	
        item.borrow(LocalDate.now());
    }

    public int computeFine(MediaItem item, LocalDate today) {
        int days = item.overdueDays(today);
        return item.calculateFine(days);
    }
    
}