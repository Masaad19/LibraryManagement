
package libraryy;
import java.util.*;
/**
 * Represents a book as a media item in the library.
 * Each book has a title, author, and ISBN, and supports borrowing rules
 * such as borrow period, fine calculation, and overdue detection.
 */

import library.service.BookFine;
public class Book extends MediaItem {
	private String title;
    private String author;
    private String isbn;
    private boolean available = true;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return String.format("Book[Title=%s, Author=%s, ISBN=%s, Available=%s]",
                title, author, isbn, available ? "Yes" : "No");
    }
    @Override
    public int getBorrowPeriod() {
        return 28;
    }

    @Override
    public int calculateFine(int overdueDays) {
        return new BookFine().calculateFine(overdueDays);
    }
}
