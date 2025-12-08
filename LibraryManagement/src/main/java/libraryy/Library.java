package libraryy;
import java.util.*;
/**
 * Represents the library which stores all media items (books).
 * This class allows adding books, searching for books, and finding books by ISBN.
 *
 * It acts as the main storage for items available to be borrowed.
 */

public class Library {
    private List<MediaItem> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println(" Book added: " + book.getTitle());
    }

    public void searchBook(String keyword) {
        System.out.println(" Search results:");
        for (MediaItem m : books) {
            if (m instanceof Book) {
                Book b = (Book) m;
                if (b.getTitle().toLowerCase().contains(keyword.toLowerCase())
                    || b.getAuthor().toLowerCase().contains(keyword.toLowerCase())
                    || b.getIsbn().equalsIgnoreCase(keyword)) {
                    System.out.println(b);
                }
            }
        }
    }

    public Book findBook(String isbn) {
        for (MediaItem m : books) {
            if (m instanceof Book) {
                Book b = (Book) m;
                if (b.getIsbn().equalsIgnoreCase(isbn)) {
                    return b;
                }
            }
        }
        return null;
    }
	public List<MediaItem> getBooks() {
		
		return books;
	}
}
   