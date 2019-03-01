package ca.mcgill.ecse223.block223.application;

import ca.mcgill.ecse223.block223.model.Block223;
import ca.mcgill.ecse223.block223.model.Game;
import ca.mcgill.ecse223.block223.model.UserRole;
import ca.mcgill.ecse223.block223.view.Block223Page;

public class Block223Application {
	
	private static Block223 block223;
	private static Game currentGame;
	private static UserRole currentUserRole;
	
	public static void main(String[] args) {
		
		// start UI
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Block223Page().setVisible(true);
            }
        });
	}

	public static Block223 getBlock223() {
	}
	
	public static Block223 resetBlock223() {
	}
	
	public static UserRole getCurrentUserRole() {
		return currentUserRole;
	}
	
	public static void setCurrentUserRole(UserRole aUserRole) {
		currentUserRole = aUserRole;
	}
	
	public static Game getCurrentGame() {
		return currentGame;
	}
	
	public static void setCurrentGame(Game aGame) {
		currentGame = aGame;
	}
	
}
