package librarytest;

import libraryy.User;
import libraryy.Book;
import libraryy.Borrow;
import library.service.BorrowRulesService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BorrowRulesServiceTest {

    @Test
    public void testCannotBorrowIfUnpaidFines() {
        User u = new User("Yumna", "y@mail.com");
        u.addFine(50);

        BorrowRulesService s = new BorrowRulesService();
        assertFalse(s.canBorrow(u, new ArrayList<>()));
    }

    @Test
    public void testCannotBorrowIfOverdueLoan() {
        User u = new User("Yumna", "y@mail.com");
        List<Borrow> loans = new ArrayList<>();

        Book b = new Book("Java", "A", "111");

        Borrow loan = new Borrow(b, LocalDate.of(2024, 1, 1), b.getBorrowPeriod());
        loans.add(loan);

        BorrowRulesService s = new BorrowRulesService();
        assertFalse(s.canBorrow(u, loans));
    }
}