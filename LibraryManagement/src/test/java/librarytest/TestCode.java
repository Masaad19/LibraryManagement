package librarytest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;

import libraryy.*;
import libraryy.CD;
import libraryy.MediaItem;
import library.service.*;

import org.mockito.Mockito;
public class TestCode {

    
    @Test
    public void testBorrowBookPeriod() {
        Book b = new Book("Java", "Masa", "111");
        LocalDate today = LocalDate.of(2025, 1, 1);

        b.borrow(today);

        assertEquals(today.plusDays(28), b.dueDate);
    }

    
    @Test
    public void testBorrowCDPeriod() {
        CD cd = new CD("Music", "CD1");
        LocalDate today = LocalDate.of(2025, 1, 1);

        cd.borrow(today);

        assertEquals(today.plusDays(7), cd.dueDate);
    }

    
    @Test
    public void testBookNotOverdue() {
        Book b = new Book("Java", "Masa", "111");
        b.borrow(LocalDate.of(2025, 1, 1));

        boolean result = b.isOverdue(LocalDate.of(2025, 1, 20));

        assertFalse(result);
    }

    
    @Test
    public void testBookOverdue() {
        Book b = new Book("Java", "Masa", "111");
        b.borrow(LocalDate.of(2025, 1, 1));

        boolean result = b.isOverdue(LocalDate.of(2025, 2, 5));

        assertTrue(result);
    }

    
    @Test
    public void testCDOverdue() {
        CD cd = new CD("Album", "CD1");
        cd.borrow(LocalDate.of(2025, 1, 1));

        boolean result = cd.isOverdue(LocalDate.of(2025, 1, 10));

        assertTrue(result);
    }


@Test
public void testBookFine() {
    Book b = new Book("Java", "Yumna", "111");
    b.borrow(LocalDate.of(2025, 1, 1));

    int overdueDays = b.overdueDays(LocalDate.of(2025, 2, 5)); 

    int fine = b.calculateFine(overdueDays);

    assertEquals(70, fine); 
}


@Test
public void testCDFine() {
    CD cd = new CD("Album", "CD1");
    cd.borrow(LocalDate.of(2025, 1, 1));

    int overdueDays = cd.overdueDays(LocalDate.of(2025, 1, 10)); 

    int fine = cd.calculateFine(overdueDays);

    assertEquals(40, fine); 
}


@Test
public void testBorrowUnavailableItem() {
    BorrowSerivce service = new BorrowSerivce();
    Book b = new Book("Java", "Yumna", "111");

    b.borrow(LocalDate.now()); 

    assertThrows(IllegalStateException.class, () -> {
        service.borrow(b);
    });
}


@Test
public void testMixedMediaFineCalculation() {

    BorrowSerivce s = new BorrowSerivce();

    Book b = new Book("Java", "Yumna", "111");
    CD cd = new CD("Album", "CD1");

    b.borrow(LocalDate.of(2025, 1, 1));   
    cd.borrow(LocalDate.of(2025, 1, 25)); 

    LocalDate today = LocalDate.of(2025, 2, 10);

    int fineBook = s.computeFine(b, today); 
    int fineCD = s.computeFine(cd, today);   

    assertEquals(120, fineBook);
    assertEquals(0, fineCD);
}
@Test
public void testReminderUsingMock() {

    
    Observer mockObserver = Mockito.mock(Observer.class);

    
    ReminderService service = new ReminderService(mockObserver);

  
    User u = new User("Yumna", "yumna@mail.com");

    
    Book b = new Book("Java", "Masa", "111");

    
    LocalDate borrowDate = LocalDate.of(2025, 1, 1);

    
    Borrow br = new Borrow(b, borrowDate, 28);

    
    List<Borrow> list = List.of(br);

    
    Mockito.doNothing().when(mockObserver)
           .notifyUser(Mockito.any(), Mockito.anyString());

    
    service.sendReminders(u, list);

    
    Mockito.verify(mockObserver).notifyUser(
            Mockito.eq(u),
            Mockito.contains("1 overdue")
    );
}
}
