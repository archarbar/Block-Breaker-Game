package ca.mcgill.ecse223.block.controller;

import java.util.List;

import ca.mcgill.ecse223.block223.model.Block223;
import ca.mcgill.ecse223.block223.application.Block223Application;
import ca.mcgill.ecse223.block223.model.Admin;
import ca.mcgill.ecse223.block223.model.Ball;
import ca.mcgill.ecse223.block223.model.Block;
import ca.mcgill.ecse223.block223.model.Block223;
import ca.mcgill.ecse223.block223.model.BlockAssignment;
import ca.mcgill.ecse223.block223.model.Game;
import ca.mcgill.ecse223.block223.model.Level;
import ca.mcgill.ecse223.block223.model.Paddle;
import ca.mcgill.ecse223.block223.model.Player;
import ca.mcgill.ecse223.block223.model.User;
import ca.mcgill.ecse223.block223.model.UserRole;

public class Block223Controller {

	// ****************************
	// Modifier methods
	// ****************************
	public static void createGame(String name) throws InvalidInputException {
		String error = "";
		Block223 block223 = BlockApplication.getBlock223();
		Admin admin = BlockApplication.getCurrentUserRole();
		if (name == null || name == "") {
			error = "The name of a game must be specified";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		try {
			Game game = new Game(name, 1, admin, 1, 1, 1, 10, 10, block223);
		}
		catch (RuntimeException e) {
			error = e.getMessage();
			if (error.equals("Cannot create due to duplicate name")) {
				error = "The name of a game must be unique.";
			}
			throw new InvalidInputException(error);
		}
	}

	public static void setGameDetails(int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
		String error = "";
		if (nrLevels < 1 || nrLevels > 99) {
			error = "The number of levels must be between 1 and 99.";
		}
		Game game = BlockApplication.getCurrentGame();
		try {
			game.setNrBlocksPerLevel(nrBlocksPerLevel);
		}
		catch (RuntimeException e) {
			error = error + "The number of blocks per level must be greater than zero.";
		}
		Ball ball = game.getBall();
		try {
			ball.setMinBallSpeedX(minBallSpeedX);
		}
		catch (RuntimeException e) {
			error = error + "The minimum speed of the ball must be greater than zero.";
		}
		try {
			ball.setMinBallSpeedY(minBallSpeedY);
		}
		catch (RuntimeException e) {
			error = error + "The minimum speed of the ball must be greater than zero.";
		}
		try {
			ball.setBallSpeedIncreaseFactor(ballSpeedIncreaseFactor);
		}
		catch (RuntimeException e) {
			error = error + "The speed increase factor of the ball must be greater than zero.";
		}
		Paddle paddle = game.getPaddle();
		try {
			paddle.setMaxPaddleLength(maxPaddleLength);
		}
		catch (RuntimeException e) {
			error = error + "The maximum length of the paddle must be greater than zero and less than or equal to 400.";
		}
		try {
			paddle.setMinPaddleLength(minPaddleLength);
		}
		catch (RuntimeException e) {
			error = error + "The minimum length of the paddle must be greater than zero.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		List<Level> levels = game.getLevels();
		int size = levels.size();
		while (nrLevels > size) {
			game.addLevel();
			size = levels.size();
		}
		while (nrLevels < size) {
			Level level = game.getLevel(size-1);
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
	}

	public static void updateBlock(int id, int red, int green, int blue, int points) throws InvalidInputException {
	}

	public static void positionBlock(int id, int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {}
		
		UserRole currentUser = Block223Application.getCurrentUserRole();
		//Check if the user is an admin 
		
		if (!(currentUser instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		//check if the game exists 
		Game currentGame = Block223Application.getCurrentGame();
		if (currentGame == null) {
			throw new InvalidInputException("A game must be selected to access its information.");
		}
		//check if the admin created the game ***************question
		Admin admin = currentGame.getAdmin();
		if (admin != (Admin) currentUser) {
			throw new InvalidInputException("Only the admin who created the game can access its information.")
		}
		
		if (level < 1 || level >99) {
			getLevel(�)
			(catch
			IndexOutOfBoundsException and rethrow)
			"Level " + level + " does not exist for the game."
		}
		
//		try {
//			btms.addRoute(number);
//		}
//		catch (RuntimeException e) {
//			error = e.getMessage();
//			if (error.equals("Cannot create due to duplicate number")) {
//				error = "A route with this number already exists. Please use a different number.";
//			}
//			throw new InvalidInputException(error);
//		
//		try {
//			btms.addSchedule(shift, driver, assignment);
//		}
//		catch (RuntimeException e) {
//			throw new InvalidInputException(e.getMessage());
//		}
//	}
//	
//		
		Game game = Block223Application.getCurrentGame();
		Level level = game.getLevel(level);
		
		//Check if number of blocks in the level of the current game, if its already at the maximum, print the following error
		if (blocks.size() > nrBlocksPerLevel) {
			"The number of blocks has reached the maximum number (" + nrBlocksPerLevel + ") allowed for this game.";
		}
		//If the position is not empty ((Horizontal/Vertical)gridLocation already occupied), print out error.
		if() {
			"A block already exists at location" + gridHorizontalPosition + "/" + gridVerticalPosition + "."
		}
		//If block does not exist return null
		if(findBlock(id) == null) {
			throw new InvalidInputException("The block does not exist.");
		}
		Block block = game.findBlock(id);
		
		
		
		
		
		
		//or maybe use constructor of block assignment 
		BlockAssignment position = create(gridHorizontalPosition, gridVerticalPosition, level, block, game); 
	}

	public static void moveBlock(int level, int oldGridHorizontalPosition, int oldGridVerticalPosition,
			int newGridHorizontalPosition, int newGridVerticalPosition) throws InvalidInputException {
		UserRole currentUser = Block223Application.getCurrentUserRole();
		//Check if the user is an admin 
		
		if (!(currentUser instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		//check if the game exists 
		Game currentGame = Block223Application.getCurrentGame();
		if (currentGame == null) {
			throw new InvalidInputException("A game must be selected to access its information.");
		}
		//check if the admin created the game ***************question
		Admin admin = currentGame.getAdmin();
		if (admin != (Admin) currentUser) {
			throw new InvalidInputException("Only the admin who created the game can access its information.")
		}
		if (level < 1 || level >99) {
			getLevel(�)
			(catch
			IndexOutOfBoundsException and rethrow)
			"Level " + level + " does not exist for the game."
		}
		if (findBlockAssignment() == null) {
			throw new InvalidInputException("A block does not exist at location" + oldGridHorizontalPosition + "/"+ oldGridVerticalPosition + ".");
		}
		
		
		Game game = getCurrentGame();
		Level level = game.getLevel(level);
		
		if(BlockAssignment assignment != null) {
			throw new ("A block already exists at location" + newGridHorizontalPosition + "/"+ gridVerticalPosition + ".");
		}
		
		
		BlockAssignment assignment = level.findBlockAssignment(oldGridHorizontalPosition, oldGridVerticalPosition);
		
		assignment.setGridHorizontalPosition(newGridHorizontalPosition);
		assignment.setGridVerticalPosition(newGridVerticalPosition);
		
		
		
	}

	public static void removeBlock(int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
	}

	public static void saveGame() throws InvalidInputException {
	}

	public static void register(String username, String playerPassword, String adminPassword)
			throws InvalidInputException {
	}

	public static void login(String username, String password) throws InvalidInputException {
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
	}

	public static TOBlock getBlockOfCurrentDesignableGame(int id) throws InvalidInputException {
	}

	public List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {
		
		UserRole currentUser = Block223Application.getCurrentUserRole();
		//Check if the user is an admin 
		
		if (!(currentUser instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		//check if the game exists 
		Game currentGame = Block223Application.getCurrentGame();
		if (currentGame == null) {
			throw new InvalidInputException("A game must be selected to access its information.");
		}
		//check if the admin created the game ***************question
		Admin admin = currentGame.getAdmin();
		if (admin != (Admin) currentUser) {
			throw new InvalidInputException("Only the admin who created the game can access its information.")
		}
		
		if (level < 1 || level >99) {
			getLevel(�)
			(catch
			IndexOutOfBoundsException and rethrow)
			"Level " + level + " does not exist for the game."
		}
		
		
		Game game = Block223Application.getCurrentGame();
		List<TOGridCell> result = create();
		
		Level level = game.getLevel(level);
		
		BlockAssignment assignments = level.getBlockAssignments();
		for (BlockAssignment assignment: assignments) {
			TOGridCell to = create(assignment.getGridHorizontalPosition(), assignment.getGridVerticalPosition(), assignment.getBlock().getId(), assignment.getBlock().getRed(), assignment.getBlock().getGreen(), assignment.getBlock().getBlue(), assignment.getBlock().getPoints())
		
			result.add(to);
		}
		return result;
		
	}

	public static TOUserMode getUserMode() {
	}

}

