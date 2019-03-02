package ca.mcgill.ecse223.block.controller;

import ca.mcgill.ecse223.block.model.*;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Ball;
import ca.mcgill.ecse223.block.model.Block;
import ca.mcgill.ecse223.block.model.BlockAssignment;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.Level;
import ca.mcgill.ecse223.block.model.Paddle;
import ca.mcgill.ecse223.block.model.Player;
import ca.mcgill.ecse223.block.model.User;
import ca.mcgill.ecse223.block.model.UserRole;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGridCell;
import ca.mcgill.ecse223.block.controller.TOBlock;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.controller.TOUserMode;
import ca.mcgill.ecse223.block.controller.TOUserMode.Mode;


public class Block223Controller {

	// ****************************
	// Modifier methods
	// ****************************
	public static void createGame(String name) throws InvalidInputException {
		String error = "";
		UserRole currentUser = Block223Application.getCurrentUserRole();
		if (!(currentUser instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to create a game");
		}
		Block223 block223 = Block223Application.getBlock223();
		Admin admin = (Admin) currentUser;
		try {
			Game game = new Game(name, 1, admin, 1, 1, 1, 10, 10, block223);
		}
		catch (RuntimeException e) {
			error = e.getMessage();
			if (error.equals("The name of a game must be unique")) {
				error = "The name of a game must be unique";
			}
			if (error.equals("The name of a game must be specified")) {
				error = "The name of a game must be specified";
			}
			throw new InvalidInputException(error);
		}
	}

	public static void setGameDetails(int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
		UserRole currentUser = Block223Application.getCurrentUserRole();
		String error = "";
		if (!(currentUser instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to define game settings");
		}
		Game currentGame = Block223Application.getCurrentGame();
		if(currentGame == null) {
			throw new InvalidInputException("A game must be selected to define game settings");
		}
		Admin admin = currentGame.getAdmin();
		if(admin != (Admin) currentUser) {
			throw new InvalidInputException("Only the admin who created the game can define its game settings");
		}
		if (nrLevels < 1 || nrLevels > 99) {
			throw new InvalidInputException("The number of levels must be between 1 and 99");
		}
		try {
			currentGame.setNrBlocksPerLevel(nrBlocksPerLevel);
		}
		catch (RuntimeException e) {
			error = e.getMessage();
			if (error.equals("The number of blocks per level must be greater than zero")) {
				error = "The number of blocks per level must be greater than zero";
			}
			throw new InvalidInputException(error);
		}
		Ball ball = currentGame.getBall();
		try {
			ball.setMinBallSpeedX(minBallSpeedX);
		}
		catch (RuntimeException e) {
			error = e.getMessage();
			if (error.equals("The minimum speed of the ball must be greater than zero")) {
				error = "The minimum speed of the ball must be greater than zero";
			}
			throw new InvalidInputException(error);
		}
		try {
			ball.setMinBallSpeedY(minBallSpeedY);
		}
		catch (RuntimeException e) {
			error = e.getMessage();
			if (error.equals("The minimum speed of the ball must be greater than zero")) {
				error = "The minimum speed of the ball must be greater than zero";
			}
			throw new InvalidInputException(error);
		}
		try {
			ball.setBallSpeedIncreaseFactor(ballSpeedIncreaseFactor);
		}
		catch (RuntimeException e) {
			error = e.getMessage();
			if (error.equals("The speed increase factor of the ball must be greater than zero")) {
				error = "The speed increase factor of the ball must be greater than zero";
			}
			throw new InvalidInputException(error);
		}
		Paddle paddle = currentGame.getPaddle();
		try {
			paddle.setMaxPaddleLength(maxPaddleLength);
		}
		catch (RuntimeException e) {
			error = e.getMessage();
			if (error.equals("The maximum length of the paddle must be greater than zero and less than or equal to 400")) {
				error = "The maximum length of the paddle must be greater than zero and less than or equal to 400";
			}
			throw new InvalidInputException(error);
		}
		try {
			paddle.setMinPaddleLength(minPaddleLength);
		}
		catch (RuntimeException e) {
			error = e.getMessage();
			if (error.equals("The minimum length of the paddle must be greater than zero")) {
				error = "The minimum length of the paddle must be greater than zero";
			}
			throw new InvalidInputException(error);
		}
		List<Level> levels = currentGame.getLevels();
		int size = levels.size();
		while (nrLevels > size) {
			currentGame.addLevel();
			size = levels.size();
		}
		while (nrLevels < size) {
			Level level = currentGame.getLevel(size-1);
			level.delete();
			size = levels.size();
		}
	}

	public static void deleteGame(String name) throws InvalidInputException {
	}

	public static void selectGame(String name) throws InvalidInputException {
	}

	public static void updateGame(String name, int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
	}

	public static void addBlock(int red, int green, int blue, int points) throws InvalidInputException {
	}

	public static void deleteBlock(int id) throws InvalidInputException {
		//William 01/03
		if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		Game game = Block223Application.getCurrentGame();
		if (game == null) {
			throw new InvalidInputException("A game must be selected to access its information.");
		}
		//check if the admin created the game *****************QUESTION is this (admin) notation fine?
		Admin admin = game.getAdmin();
		if (admin != (Admin) currentUser) {
			throw new InvalidInputException("Only the admin who created the game can access its information.");
		}
		Block block = game.findBlock(id);
		if (block != null) {
			block.delete();
		}
	}

	public static void updateBlock(int id, int red, int green, int blue, int points) throws InvalidInputException {
	}

	public static void positionBlock(int id, int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {

		//Check if the user is an admin
		UserRole currentUser = Block223Application.getCurrentUserRole();
		if (!(currentUser instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		//check if the game exists
		Game game = Block223Application.getCurrentGame();
		if (game == null) {
			throw new InvalidInputException("A game must be selected to access its information.");
		}
		//check if the admin created the game ***************question
		Admin admin = game.getAdmin();
		if (admin != (Admin) currentUser) {
			throw new InvalidInputException("Only the admin who created the game can access its information.");
		}

		String error = "";

		try {
			Level currentLevel = game.getLevel(level);
		}
		catch (IndexOutOfBoundsException e) {//***************QUESTION good? how do we add the condition that it has to be between 1 and 99?/What is the error message suppose to be?
			error = e.getMessage();
			if (error.equals("the index is out of range(index < 0 || index >= size())")) {
				error = "Level" + level + "does not exist for the game.";
			}
			throw new InvalidInputException(error);
		}

		Level currentLevel = game.getLevel(level); //*****************QUESTION do we have to put it even if its in the try/catch

		Block block = game.findBlock(id);

		//Check if number of blocks in the level of the current game, if its already at the maximum, print the following error ****************QUESTION is the numberofblockassignments equivalent to the number of blocks per level?
		if (currentLevel.numberOfBlockAssignments() > game.getNrBlocksPerLevel()) {
			throw new InvalidInputException("The number of blocks has reached the maximum number (" + game.getNrBlocksPerLevel() + ") allowed for this game.");
		}

		//If the position is not empty ((Horizontal/Vertical)gridLocation already occupied), print out error.
		if(currentLevel.findBlockAssignment(gridHorizontalPosition, gridVerticalPosition) != null) {
			throw new InvalidInputException("A block already exists at location" + gridHorizontalPosition + "/" + gridVerticalPosition + ".");
		}
		//If block does not exist return null
		if(currentLevel.findBlock(id) == null) {
			throw new InvalidInputException("The block does not exist.");
		}


		//BlockAssignment constructor InvalidInputException ****************QUESTION what is message suppose to be ? how to fin maxNumberOFHorizontalBlocks?

		try {
			BlockAssignment newBlockAssignment = new BlockAssignment(gridHorizontalPosition, gridVerticalPosition,currentLevel, block, game);
		}
		catch (RuntimeException e) {
			error = e.getMessage();
			if (error.equals("Cannot create due to duplicate name")) {
				error = "The horizontal position must be between 1 and " + maxNumberOfHorizontalBlocks + ".";
			}
			throw new InvalidInputException(error);
		}

		try {
			BlockAssignment newBlockAssignment = new BlockAssignment(gridHorizontalPosition, gridVerticalPosition,currentLevel, block, game);
		}
		catch (RuntimeException e) {
			error = e.getMessage();
			if (error.equals("Cannot create due to duplicate name")) {
				error = "The vertical position must be between 1 and " + maxNumberOfVerticalBlocks + ".";
			}
			throw new InvalidInputException(error);
		}
		BlockAssignment newBlockAssignment = new BlockAssignment(gridHorizontalPosition, gridVerticalPosition,currentLevel, block, game);

	}

	public static void moveBlock(int level, int oldGridHorizontalPosition, int oldGridVerticalPosition,
			int newGridHorizontalPosition, int newGridVerticalPosition) throws InvalidInputException {

		//Check if the user is an admin
		UserRole currentUser = Block223Application.getCurrentUserRole();
		if (!(currentUser instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		//check if the game exists
		Game game = Block223Application.getCurrentGame();
		if (game == null) {
			throw new InvalidInputException("A game must be selected to access its information.");
		}
		//check if the admin created the game ***************question
		Admin admin = game.getAdmin();
		if (admin != (Admin) currentUser) {
			throw new InvalidInputException("Only the admin who created the game can access its information.");
		}
		String error = "";

		try {
			Level currentLevel = game.getLevel(level);
		}
		catch (IndexOutOfBoundsException e) {//***************QUESTION good? how do we add the condition that it has to be between 1 and 99?/What is the error message suppose to be?
			error = e.getMessage();
			if (error.equals("the index is out of range(index < 0 || index >= size())")) {
				error = "Level" + level + "does not exist for the game.";
			}
			throw new InvalidInputException(error);
		}
		Level currentLevel = game.getLevel(level);



		BlockAssignment assignment = currentLevel.findBlockAssignment(oldGridHorizontalPosition, oldGridVerticalPosition);

		if (assignment == null) {
			throw new InvalidInputException("A block does not exist at location" + oldGridHorizontalPosition + "/"+ oldGridVerticalPosition + ".");
		}

		if(currentLevel.findBlockAssignment(newGridHorizontalPosition, newGridVerticalPosition) != null) {
			throw new InvalidInputException("A block already exists at location" + newGridHorizontalPosition + "/"+ newGridVerticalPosition + ".");
		}


		//We need to calculate maximum number of horizontal and vertical blocks!!! *******QUESTIONhow to put condition that gridHori and gridVerti has to be > 0 , maxNumberof(Verti/Horizontal)Blocks
		try {
			assignment.setGridHorizontalPosition(newGridHorizontalPosition);
		}
		catch (RuntimeException e) {
			error = e.getMessage();
			if (error.equals("Cannot create due to duplicate name")) {
				error = "The horizontal position must be between 1 and " + maxNumberOfHorizontalBlocks + ".";
			}
			throw new InvalidInputException(error);
		}

		try {
			assignment.setGridVerticalPosition(newGridVerticalPosition);
		}
		catch (RuntimeException e) {
			error = e.getMessage();
			if (error.equals("Cannot create due to duplicate name")) {
				error = "The vertical position must be between 1 and " + maxNumberOfVerticalBlocks + ".";
			}
			throw new InvalidInputException(error);
		}


		assignment.setGridHorizontalPosition(newGridHorizontalPosition);
		assignment.setGridVerticalPosition(newGridVerticalPosition);
	}





	public static void removeBlock(int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
		//William 01/03
		Level currentLevel;

		if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		Game game = Block223Application.getCurrentGame();
		if (game == null) {
			throw new InvalidInputException("A game must be selected to access its information.");
		}
		//check if the admin created the game *****************QUESTION is this (admin) notation fine?
		Admin admin = game.getAdmin();
		if (admin != (Admin) currentUser) {
			throw new InvalidInputException("Only the admin who created the game can access its information.");
		}
		try {
			currentLevel = game.getLevel(level);
		}
		catch (IndexOutOfBoundsException e) {
			error = e.getMessage();
			if (error.equals("the index is out of range(index < 0 || index >= size())")) {
				error = "Level" + level + "does not exist for the game.";
			}
			throw new InvalidInputException(error);
		}
		BlockAssignment assignment = currentLevel.findBlockAssignment(gridHorizontalPosition, gridVerticalPosition);
		if(assignment != null){
			assignment.delete();
		}
	}

	public static void saveGame() throws InvalidInputException {
		if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to save a game.");
		}
		if (Block223Application.getCurrentGame() == null) {
			throw new InvalidInputException("A game must be selected to save it");
		}
		if (Block223Application.getCurrentUserRole().equals(Block223Application.getCurrentGame().getAdmin())) {
			throw new InvalidInputException("Only the admin who created the game can save it");
		}
		Block223 block223 = Block223Application.getBlock223();
		try {
			Block223Persistence.save(block223);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException("Failed to save game");
		}
	}

	public static void register(String username, String playerPassword, String adminPassword)
			throws InvalidInputException {
		if (Block223Application.getCurrentUserRole()!= null) {
			throw new InvalidInputException("Cannot register a new user while a user is logged in");
		}
		if (playerPassword.equals(adminPassword)) {
			throw new InvalidInputException("The passwords have to be different");
		}
		Block223 block223 = Block223Application.getBlock223();
		Player player;
		User user;
		try {
			player = new Player(playerPassword, block223);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException("The player password need to be specified");
		}
		try {
			user = new User(username, block223, player);
		}
		catch (RuntimeException e) {
			player.delete();
			throw new InvalidInputException("The username has already been taken or the username must be specified");
		}

		if (adminPassword != null && adminPassword != "") {
			Admin admin = new Admin(adminPassword, block223);
			user.addRole(admin);
		}
		Block223Persistence.save(block223);
	}

	public static void login(String username, String password) throws InvalidInputException {
		if (Block223Application.getCurrentUserRole()!= null) {
			throw new InvalidInputException("Cannot login a user while a user is already logged in");
		}
		Block223Application.resetBlock223();
		if (User.getWithUsername(username)==null) {
			throw new InvalidInputException("The username and password do not match");
		}
		User user = User.getWithUsername(username);
		List<UserRole> roles = user.getRoles();
		String rolePassword;
		for (UserRole role : roles) {
			rolePassword= role.getPassword();
			if (rolePassword.equals(password)) {
				Block223Application.setCurrentUserRole(role);
			}
		}
		if (Block223Application.getCurrentUserRole()== null) {//if no one is still not logged in
			throw new InvalidInputException("The username and password do not match");
		}

	}

	public static void logout() {
	}

	// ****************************
	// Query methods
	// ****************************
	public static List<TOGame> getDesignableGames() throws InvalidInputException {
	}

	public static TOGame getCurrentDesignableGame() throws InvalidInputException {
	}

	public static List<TOBlock> getBlocksOfCurrentDesignableGame() throws InvalidInputException {
		//William 28/02
		UserRole currentUser = ca.mcgill.ecse223.block223.application.Block223Application.getCurrentUserRole();
		if (!currentUser.equals("Admin")) {
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		Game game = ca.mcgill.ecse223.block223.application.Block223Application.getCurrentGame();
		if (game == null) {
			throw new InvalidInputException("A game must be selected to access its information.");
		}
		//check if the admin created the game *****************QUESTION is this (admin) notation fine?
		Admin admin = game.getAdmin();
		if (admin != (Admin) currentUser) {
			throw new InvalidInputException("Only the admin who created the game can access its information.");
		}
		List<TOBlock> result = new ArrayList<TOBlock>();

		List<Block> blocks = game.getBlocks();
		for(Block block: blocks){
			TOBlock to = new TOBlock(block.getId(), block.getRed(), block.getGreen(), block.getBlue(), block.getPoints());
			result.add(to);
		}
		return result;
	}

	public static TOBlock getBlockOfCurrentDesignableGame(int id) throws InvalidInputException {
	}

	public List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {

		//Check if the user is an admin
		UserRole currentUser = Block223Application.getCurrentUserRole();
		if (!(currentUser instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		//check if the game exists
		Game game = Block223Application.getCurrentGame();
		if (game == null) {
			throw new InvalidInputException("A game must be selected to access its information.");
		}
		//check if the admin created the game *****************QUESTION is this (admin) notation fine?
		Admin admin = game.getAdmin();
		if (admin != (Admin) currentUser) {
			throw new InvalidInputException("Only the admin who created the game can access its information.");
		}

		List<TOGridCell> result = new List<TOGridCell>(); //***************QUESTION

		String error = "";

		try {
			Level currentLevel = game.getLevel(level);
		}
		catch (IndexOutOfBoundsException e) {//***************QUESTION good? how do we know what's the string of the error?

			error = e.getMessage();
			if (error.equals("if the index is out of range(index < 0 || index >= size())")) {
				error = "Level" + level + "does not exist for the game.";
			}
			throw new InvalidInputException(error);
		}

		Level currentLevel = game.getLevel(level); //***************QUESTION do we have to put it another time if it's there?



		List<BlockAssignment> assignments = currentLevel.getBlockAssignments();

		for (BlockAssignment assignment: assignments) {
			TOGridCell to = new TOGridCell(assignment.getGridHorizontalPosition(), assignment.getGridVerticalPosition(), assignment.getBlock().getId(), assignment.getBlock().getRed(), assignment.getBlock().getGreen(), assignment.getBlock().getBlue(), assignment.getBlock().getPoints());

			result.add(to);
		}
		return result;

	}

	public static TOUserMode getUserMode() {
		UserRole userRole = Block223Application.getCurrentUserRole();
		if (userRole==null) {
			TOUserMode to = new TOUserMode(Mode.None);
			return to;
		}
		if (userRole instanceof Player) {
			TOUserMode to = new TOUserMode(Mode.Play);
			return to;
		}
		if (userRole instanceof Admin) {
			TOUserMode to = new TOUserMode(Mode.Design);
			return to;
		}
		return null;

	}

	//***DO NOT REMOVE***
	//private BlockAssignment findBlockAssignment (int gridHorizontalPosition, int gridVerticalPosition) {}
//			List<BlockAssignment> assignments = Block223Application.getBlock223().getBlockAssignments();
//					for (BlockAssignment assignment : assignments) {
//						int h= assignment.getGridHorizontalPosition();
//						int v= assignment.getGridVerticalPosition();
//						if(h == gridHorizontalPosition && v==gridVerticalPosition) {
//							return assignment;}
//					}
//
//			return null;
//			}

	//private static Driver findDriver(int id) {
//		Driver foundDriver = null;
//		for (Driver driver : BtmsApplication.getBtms().getDrivers()) {
//			if (driver.getId() == id) {
//				foundDriver = driver;
//				break;
//			}
//		}
//		return foundDriver;
	//}

}