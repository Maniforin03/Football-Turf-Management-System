package footballturfmanagement;

public class Coach extends User {
	public Coach(String email, String password, String name) {
        super(email, password, name);
    }
    @Override
	public void viewTurf() {
		
		SesssionManager session = SesssionManager.getInstance();
        System.out.println("\n=== All Turfs (Coach View) ===");
        
        for (Turf turf : session.getTurfList()) {
            System.out.println(turf);
            // Show all slots for this turf
            for (Slot slot : session.getSlotList()) {
                if (slot.getTurfName().equals(turf.getNaame())) {
                    System.out.println("  - " + slot);
                }
            }
        }

	}
    public void schedulePractice() {
        SesssionManager session = SesssionManager.getInstance();
        System.out.println("\n=== Schedule Training Session ===");
        
        viewTurf();
        
        System.out.print("Enter Turf ID for practice session: ");
        String turfId = session.scanner.nextLine();
        
        // Find the turf
        Turf targetTurf = null;
        for (Turf turf : session.getTurfList()) {
            if (turf.getTurfId().equals(turfId)) {
                targetTurf = turf;
                break;
            }
        }
        
        if (targetTurf == null) {
            System.out.println("Error: Invalid turf ID!");
            return;
        }
        if (targetTurf.getStatus().equals("Maintenance")) {
            System.out.println("Error: Turf is under maintenance!");
            return;
        }
        
        System.out.print("Enter start time (e.g., 10:00 AM): ");
        String startTime = session.scanner.nextLine();
        
        System.out.print("Enter end time (e.g., 12:00 PM): ");
        String endTime = session.scanner.nextLine();
        
        System.out.println("Training session scheduled successfully!");
        System.out.println("Coach: " + this.name);
        System.out.println("Turf: " + targetTurf.getNaame() + " (" + targetTurf.getLocation() + ")");
        System.out.println("Time: " + startTime + " - " + endTime);
        System.out.println("Note: This is a non-exclusive session.");
    }
    @Override
	public void performActions() {
    SesssionManager session = SesssionManager.getInstance();
        
        while (true) {
            System.out.println("\n=== Coach Menu ===");
            System.out.println("1. View All Turfs");
            System.out.println("2. Schedule Practice");
            System.out.println("3. Back");
            System.out.print("Choose option: ");
            
            String choice = session.scanner.nextLine();
            
            switch (choice) {
                case "1":
                    viewTurf();
                    break;
                case "2":
                    schedulePractice();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
	}

}
