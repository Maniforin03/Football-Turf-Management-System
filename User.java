package footballturfmanagement;

public abstract class User implements ActionHandler {
	protected String email;
	protected String password;
	protected String name;
	
	public User(String email, String password, String name ) {
		this.email = email;
		this.password = password;
		this.name = name;
	}
	public String getEmail() {return email;}
	public String getPassword() {return password;}
	public String getName() {return name;}
	
	public boolean validateLogin(String email, String password) {
		return this.email.equals(email) && this.password.equals(password);
	}
	public abstract void viewTurf();
	
	public abstract void performActions();
	

}
