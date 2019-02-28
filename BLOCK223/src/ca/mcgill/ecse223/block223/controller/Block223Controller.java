package ca.mcgill.ecse223.block223.controller;

import java.util.List;

import ca.mcgill.ecse223.block223.model.Block223;
import ca.mcgill.ecse.btms.application.BtmsApplication;
import ca.mcgill.ecse.btms.model.Driver;
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
import ca.mcgill.ecse223.block223.controller.InvalidInputException;
import ca.mcgill.ecse223.block223.controller.TOGridCell;

public class Block223Controller {

	// ****************************
	// Modifier methods
	// ****************************
	public static void createGame(String name) throws InvalidInputException {
		String error = "";
		UserRole currentUser = Block223Application.getCurrentUserRole();
		if (!(currentUser instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to create a game.");
		}
		Block223 block223 = BlockApplication.getBlock223();
		try {
			Game game = new Game(name, 1, (Admin) currentUser, 1, 1, 1, 10, 10, block223);
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
		UserRole currentUser = Block223Application.getCurrentUserRole();
		if (!(currentUser instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to define game settings.");
		}
		Game currentGame = Block223Application.getCurrentGame();
		if (currentGame == null) {
			throw new InvalidInputException("A game must be selected to define game settings.");
		}
		Admin admin = currentGame.getAdmin();
		if (admin != (Admin) currentUser) {
			throw new InvalidInputException("Only the admin who created the game can define its game settings.");
		}
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
		UserRole currentUser = Block223Application.getCurrentUserRole();
		Admin admin = currentGame.getAdmin();
		if (!(currentUser instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		if (admin != (Admin) Block223Application.getCurrentUserRole()) {
			throw new InavlidInputException("Only the admin who created the game can access its information.");
		}
		String error = "";
		Game game = game.getWithName(name);
		if (game != null) {
			block223 = game.getBlock223();
			game.delete();
			Block223Persistence.save(block223);
		}
	}

	public static void selectGame(String name) throws InvalidInputException {
		String error = "";
		UserRole currentUser = Block223Application.getCurrentUserRole();
		Admin admin = currentGame.getAdmin();
		if (!(currentUser instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		if (admin != (Admin) Block223Application.getCurrentUserRole()) {
			throw new InavlidInputException("Only the admin who created the game can access its information.");
		}
		Game game = Game.getWithGame(name);
		if (game == null) {
			error = "A game with name" + name + "does not exist.";
		}
		BlockApplication.setCurrentGame(game);
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
		Block223 block223 = Block223Application.getBlock223();
		Admin admin = Block223Application.getCurrentUserRole();
		if (!(admin instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		List<TOGame> result = create();
		Game games = getGames();
		for (i=0; i<games.getSize(); i++) {
			Admin gameAdmin = getAdmin();
			if (gameAdmin.equals(admin)) {
				TOGame to = create(game.getName(), game.getLevels().size(), 
						game.getNrBlocksPerLevel(), 
						game.getBall().getMinBallSpeedX(), 
						game.getBall().getMinBallSpeedY(), 
						game.getBall().getBallSpeedIncreaseFactor(), 
						game.getPaddle().getMaxPaddleLength(), 
						game.getPaddle().getMinPaddleLength());
				result.add(to);
			}
		}
		return result;
	}

	public static TOGame getCurrentDesignableGame() throws InvalidInputException {
		UserRole currentUser = Block223Application.getCurrentUserRole();
		Game currentgame = Block223Application.getCurrentGame();
		Admin admin = currentGame.getAdmin();
		if (!(currentUser instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		if (currentGame == null) {
			throw new InvalidInputException("A game must be selected to access its information.");
		}
		if (admin != (Admin) Block223Application.getCurrentUserRole()) {
			throw new InavlidInputException("Only the admin who created the game can access its information.");
		}
		Block223Application game = Block223Application.getCurrentGame();
		TOGame to = create(game.getName(), game.getLevels().size(), 
				game.getNrBlocksPerLevel(), 
				game.getBall().getMinBallSpeedX(), 
				game.getBall().getMinBallSpeedY(), 
				game.getBall().getBallSpeedIncreaseFactor(), 
				game.getPaddle().getMaxPaddleLength(), 
				game.getPaddle().getMinPaddleLength());
		return to;
	}

	public static List<TOBlock> getBlocksOfCurrentDesignableGame() throws InvalidInputException {
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
	}

}


//***DO NOT REMOVE***
//private BlockAssignment findBlockAssignment (int gridHorizontalPosition, int gridVerticalPosition) {}
//		List<BlockAssignment> assignments = Block223Application.getBlock223().getBlockAssignments();					
//				for (BlockAssignment assignment : assignments) {
//					int h= assignment.getGridHorizontalPosition();
//					int v= assignment.getGridVerticalPosition();
//					if(h == gridHorizontalPosition && v==gridVerticalPosition) {
//						return assignment;}
//				}
//		
//		return null;
//		}

//private static Driver findDriver(int id) {
//	Driver foundDriver = null;
//	for (Driver driver : BtmsApplication.getBtms().getDrivers()) {
//		if (driver.getId() == id) {
//			foundDriver = driver;
//			break;
//		}
//	}
//	return foundDriver;
//}