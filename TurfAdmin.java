package footballturfmanagement;

public class TurfAdmin extends User {
	 public TurfAdmin(String email, String password, String name) {
	        super(email, password, name);
	    }
	

	@Override
	public void viewTurf() {
		SesssionManager sesssion = SesssionManager.getInstance();
		System.out.println("\n== All Turfs (Admin View) === ");
		
		for (Turf turf : sesssion.getTurfList()) {
			System.out.println(turf);
			for (Slot slot : sesssion.getSlotList()) {
				if (slot.getTurfName().equals(turf.getNaame())) {
					System.out.println("  - " + slot);
				}
			}
			System.out.println();
		}

	}
	public void addNewTurfSlot() {
        SesssionManager session = SesssionManager.getInstance();
        
        System.out.print("Enter Slot ID: ");
        String slotId = session.scanner.nextLine();
        
        for (Slot slot : session.getSlotList()) {
        	if (slot.getSlotId().equals(slotId)) {
        		System.out.println("Error: Slot ID already exists!");
                return;
        	}
        }
        
        System.out.print("Enter Turf Name: ");
        String turfName = session.scanner.nextLine();
        
        System.out.print("Enter Location: ");
        String location = session.scanner.nextLine();
        
        System.out.print("Enter Start Time (e.g., 10:00 AM): ");
        String startTime = session.scanner.nextLine();
        
        System.out.print("Enter End Time (e.g., 12:00 PM): ");
        String endTime = session.scanner.nextLine();
        
        Slot newSlot = new Slot(slotId, turfName, location, startTime, endTime, "Available");
        session.getSlotList().add(newSlot);
        
        System.out.println("New slot added successfully!");
        System.out.println(newSlot);
	}
	
	public void markSlotMaintenance() {
		SesssionManager sesssion = SesssionManager.getInstance();
		viewTurf();
		System.out.print("Enter Slot ID to mark for maintenance: ");
        String slotId = sesssion.scanner.nextLine();
        
        Slot targetSlot = null;
        for(Slot slot : sesssion.getSlotList()) {
        	if(slot.getSlotId().equals(slotId)) {
        		targetSlot = slot;
        		break;
        	}
        }
        if(targetSlot == null) {
        	System.out.println("Error: Slot not found!");
            return;
        }
        if(targetSlot.getStatus().equals("Booked")) {
        	System.out.println("Warning: This slot is currently booked!");
            System.out.print("Do you want to proceed? (y/n): ");
            String confirm = sesssion.scanner.nextLine();
            
            if(!confirm.toLowerCase().equals("Y")) {
            	System.out.println("Operation cancelled.");
                return;
            }
            //cancel existing booking
            for(Booking booking : sesssion.getBookingList()) {
            	if (booking.getSlotId().equals(slotId)) {
            		sesssion.getBookingList().remove(booking);
                    System.out.println("Existing booking cancelled due to maintenance.");
                    break;
            	}
            }
        }
        targetSlot.setStatus("Maintenance");
        // Mark Turf as under maintenance
        for (Turf turf : sesssion.getTurfList()) {
        
        		if(turf.getNaame().equals(targetSlot.getTurfName())) {
        			turf.setStatus("Maintenance");
        			break;
        		}
        	}System.out.println("Slot marked for maintenance successfully!");
        }
	public void addNewTurf() {
		SesssionManager sesssion = SesssionManager.getInstance();
		System.out.print("Enter Turf ID: ");
		String turfId = sesssion.scanner.nextLine();
		
		for(Turf turf: sesssion.getTurfList()) {
			if(turf.getTurfId().equals(turfId)) {
				System.out.println("Error: Turf ID already exists!");
				return;
			}
		}
		 System.out.print("Enter Turf Name: ");
	        String name = sesssion.scanner.nextLine();
	        
	        System.out.print("Enter Location: ");
	        String location = sesssion.scanner.nextLine();
	        
	        Turf newTurf = new Turf(turfId, name, location, "Available");
	        sesssion.getTurfList().add(newTurf);
	        
	        System.out.println("New turf added successfully!");
	        System.out.println(newTurf);
	}
	

	@Override
	public void performActions() {
		
		SesssionManager sesssion = SesssionManager.getInstance();
		while(true) {
			 System.out.println("\n=== Turf Admin Menu ===");
	            System.out.println("1. Add New Turf Slot");
	            System.out.println("2. Mark Slot as Under Maintenance");
	            System.out.println("3. Add New Turf");
	            System.out.println("4. View All Turfs");
	            System.out.println("5. Back");
	            System.out.print("Choose option: ");
	            String choice = sesssion.scanner.nextLine();
	            
	            switch (choice) {
	            case "1":
                    addNewTurfSlot();
                    break;
                case "2":
                    markSlotMaintenance();
                    break;
                case "3":
                    addNewTurf();
                    break;
                case "4":
                    viewTurf();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid option!");
	               
	            }
	            
	            
		}
	}

}
