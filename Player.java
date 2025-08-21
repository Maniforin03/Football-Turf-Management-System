package footballturfmanagement;

import java.util.ArrayList;

public class Player extends User {
	private ArrayList<String> bookedSlots;
	
	 public Player(String email, String password, String name) {
	        super(email, password, name); // Call parent constructor
	        this.bookedSlots = new ArrayList<>();
	    }

	@Override
	public void viewTurf() {
		
		SesssionManager sesssion = SesssionManager.getInstance();
		System.out.println("\n=== Available Turfs (Player View) ===");
		
		for(Turf turf : sesssion.getTurfList()) {
			if(turf.getStatus().equals("Available")) {
				System.out.println(turf);
		for(Slot slot: sesssion.getSlotList()) {
			if(slot.getTurfName().equals(turf.getNaame()) && slot.getStatus().equals("Available")){
				System.out.println(" - "+slot);
			}
			
		}
			}
		}

	}
   public void bookslot() {
	   SesssionManager session = SesssionManager.getInstance();
       System.out.print("Enter Slot ID to book: ");
       String slotId = session.scanner.nextLine();
       
       Slot targetSlot = null;
       for (Slot slot : session.getSlotList()) {
           if (slot.getSlotId().equals(slotId)) {
               targetSlot = slot;
               break;
           }
       
   }
       if (targetSlot == null) {
           System.out.println("Error: Invalid slot ID!");
           return;
       }
       if (!targetSlot.getStatus().equals("Available")) {
           System.out.println("Error: Slot is not available!");
           return;
       }
       boolean turfUnderMaintenance = false;
       for (Turf turf : session.getTurfList()) {
           if (turf.getNaame().equals(targetSlot.getTurfName()) && 
               turf.getStatus().equals("Maintenance")) {
               turfUnderMaintenance = true;
               break;
           }
       }
       if (turfUnderMaintenance) {
           System.out.println("Error: Turf is under maintenance!");
           return;
       }
       String bookingId = "B" + (session.getBookingList().size() + 1);
       Booking newBooking = new Booking(bookingId, slotId, this.email);
       
       session.getBookingList().add(newBooking);
       targetSlot.setStatus("Booked");
       bookedSlots.add(slotId);
       
       System.out.println("Slot booked successfully! Booking ID: " + bookingId);
   }
   public void cancelBooking() {
       System.out.print("Enter Booking ID to cancel: ");
       SesssionManager session = SesssionManager.getInstance();
       String bookingId = session.scanner.nextLine();
       
       Booking targetBooking = null;
       for (Booking booking : session.getBookingList()) {
           if (booking.getBookingId().equals(bookingId) && 
               booking.getPlayerEmail().equals(this.email)) {
               targetBooking = booking;
               break;
           }
       }
       if (targetBooking == null) {
           System.out.println("Error: Booking not found or not yours!");
           return;
       }
       
       if (!targetBooking.canBeCancelled()) {
           System.out.println("Error: Cannot cancel booking after 24 hours!");
           return;
       }
       session.getBookingList().remove(targetBooking);
       bookedSlots.remove(targetBooking.getSlotId());
       
       // Mark slot as available
       for (Slot slot : session.getSlotList()) {
           if (slot.getSlotId().equals(targetBooking.getSlotId())) {
               slot.setStatus("Available");
               break;
           }
       }
       
       System.out.println("Booking cancelled successfully!");
   }
   public void viewMyBookings() {
       SesssionManager session = SesssionManager.getInstance();
       System.out.println("\n=== My Bookings ===");
       
       boolean hasBookings = false;
       for (Booking booking : session.getBookingList()) {
           if (booking.getPlayerEmail().equals(this.email)) {
               System.out.println(booking);
               hasBookings = true;
           }
       }
       
       if (!hasBookings) {
           System.out.println("No bookings found.");
       }
   }

	@Override
	public void performActions() {
SesssionManager session = SesssionManager.getInstance();
        
        while (true) {
            System.out.println("\n=== Player Menu ===");
            System.out.println("1. Book Slot");
            System.out.println("2. Cancel Booking");
            System.out.println("3. View My Bookings");
            System.out.println("4. Back");
            System.out.print("Choose option: ");
            
            String choice = session.scanner.nextLine();
            
            switch (choice) {
                case "1":
                    viewTurf();
                    bookslot();
                    break;
                case "2":
                    cancelBooking();
                    break;
                case "3":
                    viewMyBookings();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }
    
    public ArrayList<String> getBookedSlots() {
        return bookedSlots;

	}

}
