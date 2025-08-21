package footballturfmanagement;

import java.util.ArrayList;
import java.util.Scanner;

public class SesssionManager {
	private static SesssionManager instance = null;
	
	private ArrayList<User> userList;
    private ArrayList<Turf> turfList;
    private ArrayList<Slot> slotList;
    private ArrayList<Booking> bookingList;
    public Scanner scanner = new Scanner(System.in);
    
    private SesssionManager() {
    	initializeData();
    	
    }
    public static synchronized SesssionManager getInstance() {
        if (instance == null) {
            instance = new SesssionManager();
        }
        return instance;
    }
    
    private void initializeData() {
        // Initialize ArrayLists
    	// Sample Users (Add this after initializing ArrayLists)
    	
        userList = new ArrayList<>();
        turfList = new ArrayList<>();
        slotList = new ArrayList<>();
        bookingList = new ArrayList<>();
        
        userList.add(new Player("player1@turfsys.com", "pass", "Kinga"));
    	userList.add(new Coach("coach1@turfsys.com", "pass", "Tabassum"));
    	userList.add(new TurfAdmin("manager1@turfsys.com", "pass", "Messi"));
    	
        
        turfList.add(new Turf("T1", "Main Field", "Stadium A", "Available"));
        turfList.add(new Turf("T2", "Training Field", "Stadium B", "Maintenance"));
        
        slotList.add(new Slot("S1", "Main Field", "Stadium A", "10:00 AM", "12:00 PM", "Available"));
        slotList.add(new Slot("S2", "Training Field", "Stadium B", "2:00 PM", "4:00 PM", "Booked"));
        slotList.add(new Slot("S3", "Main Field", "Stadium A", "2:00 PM", "4:00 PM", "Available"));
        slotList.add(new Slot("S4", "Main Field", "Stadium A", "6:00 PM", "8:00 PM", "Available"));
        
        bookingList.add(new Booking("1", "S2", "player1@football.com"));
        
        System.out.println("=== Football Turf Management System Initialized ===");
        System.out.println("Sample data loaded successfully!");
        System.out.println("=================================================\n");
    }
    // Getter methods
    public ArrayList<User> getUserList() { return userList; }
    public ArrayList<Turf> getTurfList() { return turfList; }
    public ArrayList<Slot> getSlotList() { return slotList; }
    public ArrayList<Booking> getBookingList() { return bookingList; }
    
    // Authentication method
    public User authenticateUser(String email, String password) {
        for (User user : userList) {
            if (user.validateLogin(email, password)) {
                return user;
            }
        }
        return null; // Authentication failed
    }
 // Display main menu (Polymorphism)
    public void showMainMenu(User user) {
        System.out.println("\n=== Main Menu ===");
        System.out.println("Welcome, " + user.getName() + "!");
        System.out.println("1. View Available Turfs");
        
        if (user instanceof Player) {
            System.out.println("2. Book a Slot (Player Only)");
        } else if (user instanceof Coach) {
            System.out.println("3. Schedule Practice (Coach Only)");
        } else if (user instanceof TurfAdmin) {
            System.out.println("4. Manage Turfs (Manager Only)");
        }
        
        System.out.println("5. Logout");
        System.out.print("Choose option: ");
    }
    
 // Handle menu selection
    public boolean handleMainMenu(User user, String choice) {
        switch (choice) {
            case "1":
                user.viewTurf(); //Polymorphic call
                return true;
            case "2":
                if (user instanceof Player) {
                    user.performActions();
                } else {
                    System.out.println("Invalid option for your user type!");
                }
                return true;
            case "3":
                if (user instanceof Coach) {
                    user.performActions();
                } else {
                    System.out.println("Invalid option for your user type!");
                }
                return true;
            case "4":
                if (user instanceof TurfAdmin) {
                    user.performActions();
                } else {
                    System.out.println("Invalid option for your user type!");
                }
                return true;
            case "5":
                System.out.println("Logged out successfully!");
                return false;
            default:
                System.out.println("Invalid option!");
                return true;
        }
        
    }
    
    

}
