package library.service;
import java.time.LocalDate;
import libraryy.MediaItem;
import libraryy.Book;
import libraryy.Borrow;
import libraryy.User;
import java.util.*;
public class BorrowSerivce {
	
	public Borrow borrow(User user, MediaItem item, List<Borrow> loans) {

	    if (!item.isAvailable()) {
	        throw new IllegalStateException("Item unavailable");
	    } 

	    LocalDate today = LocalDate.now();
	    item.borrow(today);

	    Borrow borrow = new Borrow((Book) item, today, item.getBorrowPeriod());
	    loans.add(borrow);

	    return borrow;
	}
    public void borrow(MediaItem item) {
        if (!item.isAvailable()) {
            throw new IllegalStateException("Item unavailable");
        }
        item.borrow(LocalDate.now());
    }

    public int computeFine(MediaItem item, LocalDate today) {
        int days = item.overdueDays(today);
        return item.calculateFine(days);
    }
}