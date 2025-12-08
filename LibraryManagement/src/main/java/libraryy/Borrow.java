package libraryy;
import java.time.LocalDate;
/**
 * Represents a borrowing operation.
 * Stores the borrowed item, the borrow date, and automatically computes the due date.
 *
 * Provides methods to check if the item is overdue and calculate overdue days.
 */

public class Borrow {
	private MediaItem item;
    private LocalDate borrowDate;
    private LocalDate dueDate;

    public Borrow(MediaItem item, LocalDate borrowDate, int period) {
        this.item = item;
        this.borrowDate = borrowDate;
        this.dueDate = borrowDate.plusDays(period);
    }

    public MediaItem getItem() {
        return item;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isOverdue(LocalDate today) {
        return today.isAfter(dueDate);
    }

    public int getOverdueDays(LocalDate today) {
        if (!isOverdue(today)) {
            return 0;
        }
        return (int) java.time.temporal.ChronoUnit.DAYS.between(dueDate, today);
    }
}
