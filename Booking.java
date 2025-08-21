package footballturfmanagement;

import java.util.Date;

public class Booking {
	 private String bookingId;
	 private String slotId;
	 private String playerEmail;
	 private Date bookingDate;
	    
	    public Booking(String bookingId, String slotId, String playerEmail) {
	        this.bookingId = bookingId;
	        this.slotId = slotId;
	        this.playerEmail = playerEmail;
	        this.bookingDate = new Date();
	        }
	    //Getter methods
	    public String getBookingId() {return bookingId;}
	    public String getSlotId() {return slotId;}
	    public String getPlayerEmail() {return playerEmail;}
	    public Date getBookingDate() {return bookingDate;}
	    
	    public boolean canBeCancelled() {
	        long currentTime = System.currentTimeMillis();
	        long bookingTime = bookingDate.getTime();
	        long timeDiff = currentTime - bookingTime;
	        long twentyFourHours = 24 * 60 * 60 * 1000; //24 hours in milliseconds
	        return timeDiff <= twentyFourHours;
	    }
	    
	    @Override
	    public String toString() {
	    	return "Booking ID : "+ bookingId +" Slot ID: "+ slotId +", Player:"+ playerEmail +", Date: "+ bookingDate;
	    }
	    
	    
	    
	

}
