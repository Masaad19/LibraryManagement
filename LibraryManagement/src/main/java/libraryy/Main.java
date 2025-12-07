package libraryy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import library.service.*;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Admin admin = new Admin("admin","1234");
        Library library = new Library();

        User user = new User("Yumna", "yumna@mail.com");

        BorrowSerivce borrowService = new BorrowSerivce();
        BorrowRulesService ruleService = new BorrowRulesService();
        ReminderService reminderService = new ReminderService(new EmailNotifier());
        OverdueReportService reportService = new OverdueReportService();
        UserManagementService ums = new UserManagementService();

        List<Borrow> loans = new ArrayList<>();

        boolean running = true;

        while (running) {

            System.out.println("\n----------------------------");
            System.out.println(" LIBRARY MANAGEMENT MENU");
            System.out.println("------------------------------");
            System.out.println("1. Admin Login");
            System.out.println("2. Add Book");
            System.out.println("3. Search Book");
            System.out.println("4. Borrow Item");
            System.out.println("5. Pay Fine");
            System.out.println("6. Show Overdue Report");
            System.out.println("7. Send Reminder");
            System.out.println("8. Unregister User");
            System.out.println("9. Logout");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {

                case 1:
                    loginAdmin(admin);
                    break;

                case 2:
                    if (!admin.isLoggedIn()) {
                        System.out.println(" You must login as admin!");
                        break;
                    }
                    addBook(library);
                    break;

                case 3:
                    searchBook(library);
                    break;

                case 4:
                    borrowItem(user, library, borrowService, ruleService, loans);
                    break;

                case 5:
                    payFine(user);
                    break;
                case 6:
                	showReport(reportService, loans);
                    break;

                case 7:
                    reminderService.sendReminders(user, loans);
                    break;

                case 8:
                    unregisterUser(user, admin, loans, ums);
                    break;

                case 9:
                    admin.logout();
                    break;

                case 0:
                    running = false;
                    System.out.println("Goodbye ");
                    break;
                default:
                    System.out.println(" Invalid choice!");}
        }
    }private static void loginAdmin(Admin admin) {
        System.out.print("Enter username: ");
        String u = sc.nextLine();

        System.out.print("Enter password: ");
        String p = sc.nextLine();

        admin.login(u, p);
    }

    private static void addBook(Library library) {
        System.out.print("Book title: ");
        String t = sc.nextLine();

        System.out.print("Author: ");
        String a = sc.nextLine();

        System.out.print("ISBN: ");
        String isbn = sc.nextLine();

        library.addBook(new Book(t, a, isbn));
    }

    private static void searchBook(Library library) {
        System.out.print("Enter keyword: ");
        String k = sc.nextLine();
        library.searchBook(k);
    }

    private static void borrowItem(User user,
                                   Library library,
                                   BorrowSerivce borrowService,
                                   BorrowRulesService ruleService,
                                   List<Borrow> loans) {

        System.out.print("Enter ISBN to borrow: ");
        String isbn = sc.nextLine();

        Book book = library.findBook(isbn);

        if (book == null) {
            System.out.println(" Book not found!");
            return;
        }

        if (!ruleService.canBorrow(user, loans)) {
            System.out.println("You cannot borrow due to unpaid fines or overdue items!");
            return;
        }

        try {
            borrowService.borrow(book);
            loans.add(new Borrow(book, LocalDate.now(), book.getBorrowPeriod()));
            System.out.println(" Borrowed successfully!");
        } catch (Exception e) {
            System.out.println(" " + e.getMessage());
        }
    }

    private static void payFine(User user) {
        System.out.println("Your unpaid fines: " + user.getUnpaidFines());
        System.out.print("Enter amount to pay: ");

        double amt = sc.nextDouble();
        sc.nextLine();

        user.pay(amt);

        System.out.println(" New balance: " + user.getUnpaidFines());
    }
    private static void showReport(OverdueReportService reportService, List<Borrow> loans) {

        if (loans.isEmpty()) {
            System.out.println("No borrowed items. No overdue report.");
            return ;
        }

   
        OverdueReport r = reportService.generateReport(loans, LocalDate.now());

        System.out.println("\n=== OVERDUE REPORT ===");
        System.out.println("Total fine: " + r.getTotalFine());

        for (MediaItem m : r.getItems()) {
            System.out.println(" - " + m.getTitle());
        }
    }

    private static void unregisterUser(User user,Admin admin, List<Borrow> loans, UserManagementService ums) {

        try {
            boolean ok = ums.unregister(user, loans, admin.isLoggedIn());

            if (ok)
                System.out.println(" User removed successfully!");

        } catch (Exception e) {
            System.out.println(" Cannot unregister: " + e.getMessage());
        }
    }

    }
