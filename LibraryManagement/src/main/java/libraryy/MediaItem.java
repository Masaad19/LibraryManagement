package libraryy;
import java.time.LocalDate;
/**
 * Abstract parent class for all media items in the library.
 * Provides basic properties such as title and availability, and defines
 * shared behaviors including borrowing, fine calculation, and overdue checks.
 */

public abstract class MediaItem {
	protected String title;
    protected boolean available = true;
    protected LocalDate borrowDate;
    public LocalDate dueDate;

    public abstract int getBorrowPeriod();
    public abstract int calculateFine(int overdueDays);

    public void borrow(LocalDate today) {
        available = false;
        borrowDate = today;
        dueDate = today.plusDays(getBorrowPeriod());
    }

    public boolean isOverdue(LocalDate today) {
        return today.isAfter(dueDate);
    }

    public int overdueDays(LocalDate today) {
        if (!isOverdue(today)) return 0;
        return (int) java.time.temporal.ChronoUnit.DAYS.between(dueDate, today);
    }

    public boolean isAvailable() {
        return available;
    }
	public String getTitle() {
		
		return title;
	}
}
