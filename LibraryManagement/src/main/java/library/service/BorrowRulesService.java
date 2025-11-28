
package library.service;

import libraryy.Borrow;
import libraryy.User;

import java.time.LocalDate;
import java.util.List;

public class BorrowRulesService {

    public boolean canBorrow(User user, List<Borrow> loans) {
        if (user.getUnpaidFines() > 0) {
            return false;
        }

        for (Borrow l : loans) {
            if (l.isOverdue(LocalDate.now())) {
                return false;
            }
        }

        return true;
    }
}