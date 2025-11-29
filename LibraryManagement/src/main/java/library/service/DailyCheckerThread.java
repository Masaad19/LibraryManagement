

	package library.service;

	import java.time.LocalDate;
	import java.util.List;

	import libraryy.Borrow;
	import libraryy.User;

	public class DailyCheckerThread extends Thread {

	    private User user;
	    private List<Borrow> loans;
	    private ReminderService reminderService;
	    private boolean running = true;

	    public DailyCheckerThread(User user, List<Borrow> loans, ReminderService reminderService) {
	        this.user = user;
	        this.loans = loans;
	        this.reminderService = reminderService;
	    }

	    @Override
	    public void run() {
	        while (running) {

	            System.out.println(" Checking overdue items for user: " + user.getName());

	            reminderService.sendReminders(user, loans);

	            try {
	               

	           
	                Thread.sleep(5000);

	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    public void stopChecker() {
	        running = false;
	    }
	}

