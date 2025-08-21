package footballturfmanagement;

import java.util.Date;

public class Slot {
	//Private fields
	private String slotId;
	private String turfName;
	private String location;
	private String startTime;
	private String endTime;
	private String status;
	private Date bookingDate;
	
	public Slot(String slotId, String turfName, String location, String startTime, String endTime, String status) {
		this.slotId = slotId;
		this.turfName = turfName ;
		this.location = location;
		this.startTime = startTime ;
		this.endTime = endTime;
		this.status = status ;
		this.bookingDate= new Date(); 
	}
	//Getter methods
	public String getSlotId() {return slotId;}
	public String getTurfName() {return turfName;}
	public String getLocation() {return location;}
	public String getStartTime() {return startTime;}
	public String getEndTime() {return endTime;}
	public String getStatus() { return status;}
	public Date getBookingDate() { return bookingDate;}
	
	//Setter methods
	public void setStatus(String status) { this.status = status;}
    public void setBookingDate(Date date) { this.bookingDate = date;}
    
    @Override
    public String toString() {
        return "Slot ID: " + slotId + ", Turf: " + turfName + 
               ", Location: " + location + ", Time: " + startTime + 
               " - " + endTime + ", Status: " + status;
    } 
	

	

}
