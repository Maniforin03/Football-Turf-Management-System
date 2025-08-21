package footballturfmanagement;

public class FootballTurfManagement {

	public static void main(String[] args) {
		SesssionManager sesssion = SesssionManager.getInstance();
		System.out.println("=== Football Turf Management System ===\n ");
		
		while(true) {
			try {
				System.out.print("Email(or 'exit' to quit): ");
				String email = sesssion.scanner.nextLine().trim();
				
				if(email.equalsIgnoreCase("exit")) {
					System.out.println("Thank you for using Football Turf Management System!");
                    break;
				}
				System.out.print("Password: ");
                String password = sesssion.scanner.nextLine().trim();
                User authenticatedUser = sesssion.authenticateUser(email, password);
                
                if(authenticatedUser == null) {
                	System.out.println(" Authentication failed! Invalid email or password.\n");
                    System.out.println(" Sample credentials:");
                    System.out.println(" Player: player1@turfsys.com / pass");
                    System.out.println(" Coach: coach1@turfsys.com / pass");
                    System.out.println(" Admin: manager1@turfsys.com / pass\n");
                    continue; 
                }
                
				System.out.println(" Login successful! Welcome, " + authenticatedUser.getName() + "!");
                boolean userSesssionActive = true;
                
				while(userSesssionActive) {
                	sesssion.showMainMenu(authenticatedUser);
                	String menuChoice = sesssion.scanner.nextLine().trim();
                	userSesssionActive = sesssion.handleMainMenu(authenticatedUser, menuChoice);
                	
                }
                
                System.out.println(" Session ended. Returning to login screen...\n");
                
			} catch (Exception e) {
				System.out.println(" An error occured : " + e.getMessage());
				System.out.println("Please try again.\n");
			}
				
		}
		 // Close scanner before exit
        sesssion.scanner.close();
        System.out.println(" System shutdown complete.");
	}

}
