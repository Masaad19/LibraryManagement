package librarytest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import libraryy.Book;
import libraryy.CD;
import libraryy.MediaItem;
import library.service.BookFine;
import library.service.CDFine;
import library.service.BorrowSerivce;

public class TestCode {

    // 1) Test: Borrow Book period = 28 days
    @Test
    public void testBorrowBookPeriod() {
        Book b = new Book("Java", "Masa", "111");
        LocalDate today = LocalDate.of(2025, 1, 1);

        b.borrow(today);

        assertEquals(today.plusDays(28), b.dueDate);
    }

    // 2) Test: Borrow CD period = 7 days
    @Test
    public void testBorrowCDPeriod() {
        CD cd = new CD("Music", "CD1");
        LocalDate today = LocalDate.of(2025, 1, 1);

        cd.borrow(today);

        assertEquals(today.plusDays(7), cd.dueDate);
    }

    // 3) Test: Book not overdue
    @Test
    public void testBookNotOverdue() {
        Book b = new Book("Java", "Masa", "111");
        b.borrow(LocalDate.of(2025, 1, 1));

        boolean result = b.isOverdue(LocalDate.of(2025, 1, 20));

        assertFalse(result);
    }

    // 4) Test: Book overdue
    @Test
    public void testBookOverdue() {
        Book b = new Book("Java", "Masa", "111");
        b.borrow(LocalDate.of(2025, 1, 1));

        boolean result = b.isOverdue(LocalDate.of(2025, 2, 5));

        assertTrue(result);
    }

    // 5) Test: CD overdue
    @Test
    public void testCDOverdue() {
        CD cd = new CD("Album", "CD1");
        cd.borrow(LocalDate.of(2025, 1, 1));

        boolean result = cd.isOverdue(LocalDate.of(2025, 1, 10));

        assertTrue(result);
    }

}
