package footballturfmanagement;

public class Turf {
	//Private fields
	private String turfId;
	private String name;
	private String location;
	private String status;
	
	public Turf(String turfId, String name, String location, String status) {
		this.turfId = turfId;
		this.name = name;
		this.location = location;
		this.status = status;
		
	}
	//Getter methods
	public String getTurfId() { return turfId;}
	public String getNaame() { return name; }
	public String getLocation() { return location;}
	public String getStatus() { return status;}
	
	//Setter methods
	public void setStatus(String status) { this.status = status;}
	
	@Override
	public String toString() {
		return "Turf Id: "+ turfId +",Name: " + name + ",Location: "+ location +", Status:"+ status;
	}
	
	
	

}
