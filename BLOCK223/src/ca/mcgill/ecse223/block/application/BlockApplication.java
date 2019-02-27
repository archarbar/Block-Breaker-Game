package ca.mcgill.ecse223.block.application;

import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.UserRole;
import ca.mcgill.ecse223.block.view.Block223Page;

public class BlockApplication {
	
	private static Block223 block223;
	private static Game game;
	
	public static void main(String[] args) {
		// start UI
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Block223Page().setVisible(true);
            }
        });
	}

	public static Block223 getBlock223() {
 		return block223;
	}
	
	public static Block223 resetBlock223() {
	}
	
	public static void setCurrentUserRole(UserRole aUserRole) {
	}
	
	public static UserRole getCurrentUserRole() {
	}
	
	public static void setCurrentGame(Game aGame) {
	}
	
	public static Game getCurrentGame() {
	}
	
}
