package librarytest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import libraryy.User;



public class FineTest {

	@Test
public void testAddFine() {
    User u = new User("Masa", "M@gmail.com");
    u.addFine(20);

    assertEquals(20, u.getUnpaidFines());
}

@Test
public void testPayFine() {
    User u = new User("Masa", "M@gmail.com");
    u.addFine(20);
    u.pay(5);

    assertEquals(15, u.getUnpaidFines());
}
}