package ca.mcgill.ecse223.block.controller;

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
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGridCell;
import ca.mcgill.ecse223.block.controller.TOBlock;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.controller.TOUserMode;


public class Block223Controller {

	// ****************************
	// Modifier methods
	// ****************************
	public static void createGame(String name) throws InvalidInputException {
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
			e.getMessage();
		}
	}

	public static void setGameDetails(int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
		UserRole currentUser = Block223Application.getCurrentUserRole();
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
			e.getMessage();
		}
		Ball ball = currentGame.getBall();
		try {
			ball.setMinBallSpeedX(minBallSpeedX);
		}
		catch (RuntimeException e) {
			e.getMessage();
		}
		try {
			ball.setMinBallSpeedY(minBallSpeedY);
		}
		catch (RuntimeException e) {
			e.getMessage();
		}
		try {
			ball.setBallSpeedIncreaseFactor(ballSpeedIncreaseFactor);
		}
		catch (RuntimeException e) {
			e.getMessage();
		}
		Paddle paddle = currentGame.getPaddle();
		try {
			paddle.setMaxPaddleLength(maxPaddleLength);
		}
		catch (RuntimeException e) {
			e.getMessage();
		}
		try {
			paddle.setMinPaddleLength(minPaddleLength);
		}
		catch (RuntimeException e) {
			e.getMessage();
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
	}

	public static void updateBlock(int id, int red, int green, int blue, int points) throws InvalidInputException {
	}

	public static void positionBlock(int id, int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
		
		//Check if the user is an admin
		UserRole currentUser = Block223Application.getCurrentUserRole();
		if (!(currentUser instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to position a block.");
		}
		//check if the game exists 
		Game game = Block223Application.getCurrentGame();
		if (game == null) {
			throw new InvalidInputException("A game must be selected to position a block.");
		}
		//check if the admin created the game ***************question
		Admin admin = game.getAdmin();
		if (admin != (Admin) currentUser) {
			throw new InvalidInputException("Only the admin who created the game can position a block.");
		}
		
		String error = "";
		
		try {
			Level currentLevel = game.getLevel(level - 1);
		}
		catch (IndexOutOfBoundsException e) {//***************QUESTION good? how do we add the condition that it has to be between 1 and 99?/What is the error message suppose to be?					
			error = e.getMessage();
			if (error.equals("the index is out of range(index < 0 || index >= size())")) {
				error = "Level" + (level - 1) + "does not exist for the game.";
			}
			throw new InvalidInputException(error);
		}
		
		Level currentLevel = game.getLevel(level - 1); //*****************QUESTION do we have to put it even if its in the try/catch
		
		Block block = game.findBlock(id);
		
		//Check if number of blocks in the level of the current game, if its already at the maximum, print the following error ****************QUESTION is the numberofblockassignments equivalent to the number of blocks per level?
		if (currentLevel.numberOfBlockAssignments() >= game.getNrBlocksPerLevel()) {
			throw new InvalidInputException("The number of blocks has reached the maximum number (" + game.getNrBlocksPerLevel() + ") allowed for this game.");
		}
				
		//If the position is not empty ((Horizontal/Vertical)gridLocation already occupied), print out error. 
		if(currentLevel.findBlockAssignment(gridHorizontalPosition, gridVerticalPosition) != null) {
			throw new InvalidInputException("A block already exists at location" + gridHorizontalPosition + "/" + gridVerticalPosition + ".");
		}
		//If block does not exist return null
		if(game.findBlock(id) == null) {
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
			throw new InvalidInputException("Admin privileges are required to move a block.");
		}
		//check if the game exists 
		Game game = Block223Application.getCurrentGame();
		if (game == null) {
			throw new InvalidInputException("A game must be selected to move a block.");
		}
		//check if the admin created the game ***************question
		Admin admin = game.getAdmin();
		if (admin != (Admin) currentUser) {
			throw new InvalidInputException("Only the admin who created the game can move a block.");
		}
		String error = "";
		
		try {
			Level currentLevel = game.getLevel(level - 1);
		}
		catch (IndexOutOfBoundsException e) {//***************QUESTION good? how do we add the condition that it has to be between 1 and 99?/What is the error message suppose to be?					
			error = e.getMessage();
			if (error.equals("the index is out of range(index < 0 || index >= size())")) {
				error = "Level" + (level - 1)+ "does not exist for the game.";
			}
			throw new InvalidInputException(error);
		}
		Level currentLevel = game.getLevel(level - 1);
		
		
		
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
	}

	public static TOGame getCurrentDesignableGame() throws InvalidInputException {
	}

	public static List<TOBlock> getBlocksOfCurrentDesignableGame() throws InvalidInputException {
	}

	public static TOBlock getBlockOfCurrentDesignableGame(int id) throws InvalidInputException {
	}

	public static List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {
		
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
		
		List<TOGridCell> result = new ArrayList<TOGridCell>(); //***************QUESTION &&
		
		String error = "";
		
		try {
			Level currentLevel = game.getLevel(level - 1);
		}
		catch (IndexOutOfBoundsException e) {//***************QUESTION good? how do we know what's the string of the error?
			
			error = e.getMessage();
			if (error.equals("if the index is out of range(index < 0 || index >= size())")) {
				error = "Level" + (level - 1)+ "does not exist for the game.";
			}
			throw new InvalidInputException(error);
		}
		
		Level currentLevel = game.getLevel(level - 1); //***************QUESTION do we have to put it another time if it's there?
		
		
		
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