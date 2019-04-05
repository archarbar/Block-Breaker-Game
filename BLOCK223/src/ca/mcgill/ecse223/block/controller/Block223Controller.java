package ca.mcgill.ecse223.block.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.block.controller.TOUserMode;
import ca.mcgill.ecse223.block.controller.TOUserMode.Mode;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Ball;
import ca.mcgill.ecse223.block.model.Block;
import ca.mcgill.ecse223.block.model.BlockAssignment;
import ca.mcgill.ecse223.block.model.BouncePoint;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.HallOfFameEntry;
import ca.mcgill.ecse223.block.model.Level;
import ca.mcgill.ecse223.block.model.Paddle;
import ca.mcgill.ecse223.block.model.PlayedBlockAssignment;
import ca.mcgill.ecse223.block.model.Player;
import ca.mcgill.ecse223.block.model.User;
import ca.mcgill.ecse223.block.model.UserRole;
import ca.mcgill.ecse223.block.model.PlayedGame.PlayStatus;
import ca.mcgill.ecse223.block.model.PlayedGame;
import ca.mcgill.ecse223.block.model.PlayedGame.PlayStatus;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGridCell;
import ca.mcgill.ecse223.block.controller.TOBlock;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.controller.TOHallOfFameEntry;
import ca.mcgill.ecse223.block.controller.TOHallOfFame;
import ca.mcgill.ecse223.block.controller.TOPlayableGame;
import ca.mcgill.ecse223.block.controller.TOPlayableGame;
import ca.mcgill.ecse223.block.view.Block223PlayModeInterface;


public class Block223Controller {

	// ****************************
	// Modifier methods		
	// ****************************
	public static void createGame(String name) throws InvalidInputException {
		UserRole currentUser = Block223Application.getCurrentUserRole();
		if (!(currentUser instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to create a game.");
		}
		Block223 block223 = Block223Application.getBlock223();
		Admin admin = (Admin) currentUser;
		List<Game> games = block223.getGames();
		for (Game g : games) {
			if (g.getName().equals(name)) {
				throw new InvalidInputException("The name of a game must be unique.");
			}
		}
		try {
			Game game = new Game(name, 1, admin, 1, 1, 1, 10, 10, block223);
			block223.addGame(game);
			((Admin) Block223Application.getCurrentUserRole()).addGame(game);
			Block223Application.setCurrentGame(game);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}

	public static void setGameDetails(int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
		UserRole currentUser = Block223Application.getCurrentUserRole();
		if (!(currentUser instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to define game settings.");
		}
		Game game = Block223Application.getCurrentGame();
		if(game == null) {
			throw new InvalidInputException("A game must be selected to define game settings.");
		}
		Admin admin = game.getAdmin();
		if(admin != (Admin) currentUser) {
			throw new InvalidInputException("Only the admin who created the game can define its game settings.");
		}
		if (nrLevels < 1 || nrLevels > 99) {
			throw new InvalidInputException("The number of levels must be between 1 and 99.");
		}
		if (minBallSpeedX == 0 && minBallSpeedY == 0) {
			throw new InvalidInputException("The minimum speed of the ball must be greater than zero.");
		}
		try {
			game.setNrBlocksPerLevel(nrBlocksPerLevel);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
		Ball ball = game.getBall();
		try {
			ball.setMinBallSpeedX(minBallSpeedX);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
		try {
			ball.setMinBallSpeedY(minBallSpeedY);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
		try {
			ball.setBallSpeedIncreaseFactor(ballSpeedIncreaseFactor);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
		Paddle paddle = game.getPaddle();
		try {
			paddle.setMaxPaddleLength(maxPaddleLength);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
		try {
			paddle.setMinPaddleLength(minPaddleLength);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
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
		// check if current user is an admin
		if (!(currentUser instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to delete a game.");
		}
		Game currentGame = Block223Application.getCurrentGame();
		Admin admin = currentGame.getAdmin();
		// compare current user with the admin who created the game
		if (admin != (Admin) currentUser) {
			throw new InvalidInputException("Only the admin who created the game can delete the game.");
		}
		Block223 block223 = Block223Application.getBlock223();
		Game game = block223.findGame(name);
		// delete game if it exists
		if (game != null) {
			if (game.isPublished()) {
				throw new InvalidInputException("A published game cannot be deleted.");
			}
			Block223 block = game.getBlock223();
			game.delete();
			Block223Persistence.save(block);
		}
	}

	public static void selectGame(String name) throws InvalidInputException {
		String error = "";
		UserRole currentUser = Block223Application.getCurrentUserRole();
		// check if current user is an admin
		if (!(currentUser instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to select a game.");
		}
		Block223 block223 = Block223Application.getBlock223();
		Game currentGame = block223.findGame(name);
		// check if specified game exists
		if (currentGame == null) {
			throw new InvalidInputException("A game with name " + name + " does not exist.");
		}
		Admin admin = currentGame.getAdmin();
		// compare current user with the admin who created the game
		if (admin != (Admin) currentUser) {
			throw new InvalidInputException("Only the admin who created the game can select the game.");
		}
		if (currentGame.isPublished()) {
			throw new InvalidInputException("A published game cannot be changed.");
		}
		try {
			Block223Application.setCurrentGame(currentGame);
		}
		catch (RuntimeException e) {
			error = e.getMessage();
			throw new InvalidInputException(error);
		}
	}


	public static void updateGame(String name, int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
		UserRole currentUser = Block223Application.getCurrentUserRole();
		Game currentGame = Block223Application.getCurrentGame();
		// check if current user is an admin
		if (!(currentUser instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to define game settings.");
		}
		// check if current game is set
		if (currentGame == null) {
			throw new InvalidInputException("A game must be selected to define game settings.");
		}
		Admin admin = currentGame.getAdmin();
		// compare current user with the admin who created the game
		if (admin != (Admin) currentUser) {
			throw new InvalidInputException("Only the admin who created the game can define its game settings.");
		}
		// change the name if it is different than the wanted name
		try {
<<<<<<<<< Temporary merge branch 1
			String currentName = currentGame.getName();
			if (currentName != name) {
				currentGame.setName(name);
=========
			Game game = new Game(name, 1, (Admin) currentUser, 1, 1, 1, 10, 10, block223);
		}
		// catch and rethrow error if game is duplicate
		catch (RuntimeException e) {
			error = e.getMessage();
			if (error.equals("Cannot create due to duplicate name.")) {
				error = "The name of a game must be unique.";
>>>>>>>>> Temporary merge branch 2
			}
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
		// change all the other parameters of the game
		Block223Controller.setGameDetails(nrLevels, nrBlocksPerLevel,
				minBallSpeedX, minBallSpeedY,
				ballSpeedIncreaseFactor,
				maxPaddleLength, minPaddleLength);
	}

	public static void addBlock(int aRed, int aGreen, int aBlue, int aPoints) throws InvalidInputException {

		//String error = "";

		UserRole currentUser = Block223Application.getCurrentUserRole();

		if (!(currentUser instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}

		Game currentGame = Block223Application.getCurrentGame();

		if (currentGame == null) {
			throw new InvalidInputException("A game must be selected to add a block.");
		}

		Admin admin = currentGame.getAdmin();

		if (admin != (Admin) currentUser) {
			throw new InvalidInputException("Only the admin who created the game can add a block.");
		}

		List<Block> sourceList = currentGame.getBlocks();

		for(Block specificBlock : sourceList) {
			int colorRed = specificBlock.getRed();
			int colorGreen = specificBlock.getGreen();
			int colorBlue = specificBlock.getBlue();

			if (colorRed == aRed && colorGreen == aGreen && colorBlue == aBlue) {
				throw new InvalidInputException("A block with the same color already exists for the game.");
			}
		}

					try {
						Block block = new Block(aRed, aGreen, aBlue, aPoints, currentGame);
						System.out.println("TRIED IN ADD BLOCK TO ADD");
//						currentGame.addBlock(aRed, aGreen, aBlue, aPoints);

					}
					catch (RuntimeException e) {
						throw new InvalidInputException(e.getMessage());
					}
	}

	public static void deleteBlock(int id) throws InvalidInputException {
		//William 01/03
		UserRole currentUser = Block223Application.getCurrentUserRole();
		if (!(currentUser instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to delete a block.");
		}
		Game game = Block223Application.getCurrentGame();
		if (game == null) {
			throw new InvalidInputException("A game must be selected to delete a block.");
		}
		Admin admin = game.getAdmin();
		if (admin != (Admin) currentUser) {
			throw new InvalidInputException("Only the admin who created the game can delete a block.");
		}
		Block block = game.findBlock(id);
		if (block != null) {
			block.delete();
		}
	}

	public static void updateBlock(int id, int aRed, int aGreen, int aBlue, int aPoints) throws InvalidInputException {

		String error = "";

		UserRole currentUser = Block223Application.getCurrentUserRole();

		if (!(currentUser instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}

		Game currentGame = Block223Application.getCurrentGame();

		if (currentGame == null) {
			throw new InvalidInputException("A game must be selected to update a block.");
		}

		Admin admin = currentGame.getAdmin();

		if (admin != (Admin) currentUser) {
			throw new InvalidInputException("Only the admin who created the game can update a block.");
		}

		if(currentGame.findBlock(id) == null){
			throw new InvalidInputException("The block does not exist.");
		}

		Block block = currentGame.findBlock(id);

		List<Block> sourceList = currentGame.getBlocks();

		for(Block specificBlock : sourceList) {
			int colorRed = specificBlock.getRed();
			int colorGreen = specificBlock.getGreen();
			int colorBlue = specificBlock.getBlue();

			if (colorRed == aRed && colorGreen == aGreen && colorBlue == aBlue) {
				throw new InvalidInputException("A block with the same color already exists for the game.");
			}
		}


		try {
			block.setRed(aRed);
		}
		catch (RuntimeException e){
			error = e.getMessage();
			if(error.equals("Red must be between 0 and 255.")) {
				error = "Red must be between 0 and 255.";
			}
			throw new InvalidInputException(error);
		}
		try {
			block.setGreen(aGreen);
		}
		catch (RuntimeException e){
			error = e.getMessage();
			if(error.equals("Green must be between 0 and 255.")) {
				error = "Green must be between 0 and 255.";
			}
			throw new InvalidInputException(error);
		}
		try {
			block.setBlue(aBlue);
		}
		catch (RuntimeException e){
			error = e.getMessage();
			if(error.equals("Blue must be between 0 and 255.")) {
				error = "Blue must be between 0 and 255.";
			}
			throw new InvalidInputException(error);
		}

		try {
			block.setPoints(aPoints);
		}
		catch (RuntimeException e){
			error = e.getMessage();
			if(error.equals("Points must be between 1 and 1000.")) {
				error = "Points must be between 1 and 1000.";
			}
			throw new InvalidInputException(error);
		}


	}

	public static void positionBlock(int id, int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
<<<<<<<<< Temporary merge branch 1
		String error = "";

=========

		String error = "";
>>>>>>>>> Temporary merge branch 2
		//Check if the user is an admin
		UserRole currentUser = Block223Application.getCurrentUserRole();
		if (!(currentUser instanceof Admin)) {
			error = "Admin privileges are required to position a block.";
		}
		//check if the game exists 
		Game game = Block223Application.getCurrentGame();
		if (game == null) {
			error = "A game must be selected to position a block.";
		}
		//check if the admin created the game ***************question
		if(currentUser instanceof Admin) {
			boolean isAdminCurrentGameCreator = false;
			List<Game> games = ((Admin) currentUser).getGames();

			for(Game instanceOfGame : games) {
				if(instanceOfGame.getName().equals(game.getName())) {
					isAdminCurrentGameCreator = true;
				}
			}
			if(isAdminCurrentGameCreator == false) {
<<<<<<<<< Temporary merge branch 1
				throw new InvalidInputException("Only the admin who created the game can position a block.");
			}
		}

		Level currentLevel;
		try {
			currentLevel = game.getLevel(level - 1);
		}
		catch (IndexOutOfBoundsException e) {
				throw new InvalidInputException("Level " + level + " does not exist for the game.");
=========
				error += "Only the admin who created the game can position a block.";
			}
		}

		if(error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

		Level currentLevel = null;
		try {
			currentLevel = game.getLevel(level - 1);
		}
		catch (IndexOutOfBoundsException e) {//***************QUESTION good? how do we add the condition that it has to be between 1 and 99?/What is the error message suppose to be?
			//			error = e.getMessage();
			//			if (error.equals("Level must be between 1 and the number of levels in the current game.")) {
			throw new InvalidInputException("Level " + level + " does not exist for the game.");
			//			}
>>>>>>>>> Temporary merge branch 2
		}


		Block block = game.findBlock(id);

		//Check if number of blocks in the level of the current game, if its already at the maximum, print the following error ****************QUESTION is the numberofblockassignments equivalent to the number of blocks per level?
		if (currentLevel.numberOfBlockAssignments() == game.getNrBlocksPerLevel()) {
			throw new InvalidInputException("The number of blocks has reached the maximum number (" + game.getNrBlocksPerLevel() + ") allowed for this game.");
		}

		//BlockAssignment newPosition = currentLevel.findBlockAssignment(gridHorizontalPosition, gridVerticalPosition)

		//If the position is not empty ((Horizontal/Vertical)gridLocation already occupied), print out error. 
		if(currentLevel.findBlockAssignment(gridHorizontalPosition, gridVerticalPosition) != null) {
			throw new InvalidInputException("A block already exists at location " + gridHorizontalPosition + "/" + gridVerticalPosition + ".");
		}
		//If block does not exist return null
		if(block == null) {
			throw new InvalidInputException("The block does not exist.");
		}
		//Why can't I reference to newBlockAssignment?
<<<<<<<<< Temporary merge branch 1
		//BlockAssignment newBlockAssignment;
=========
		BlockAssignment newBlockAssignment;
>>>>>>>>> Temporary merge branch 2
		//System.out.println(newBlockAssignment.getMaxHorizontalGridPosition());
		try {
			BlockAssignment newBlockAssignment = new BlockAssignment(gridHorizontalPosition, gridVerticalPosition, currentLevel, block, game);
		}
		catch (RuntimeException e) {
			error = e.getMessage();
			if (error.equals("GridHorizontalPosition can't be negative or greater than " + game.maxNumberOfHorizontalBlocks())) {
				throw new InvalidInputException("The horizontal position must be between 1 and " + game.maxNumberOfHorizontalBlocks() + ".");
<<<<<<<<< Temporary merge branch 1
				}
			if (error.equals("GridVerticalPosition can't be negative or greater than " + game.maxNumberOfVerticalBlocks())) {
				throw new InvalidInputException("The vertical position must be between 1 and " + game.maxNumberOfVerticalBlocks() + ".");
				}
=========
			}
			if (error.equals("GridVerticalPosition can't be negative or greater than " + game.maxNumberOfVerticalBlocks())) {
				throw new InvalidInputException("The vertical position must be between 1 and " + game.maxNumberOfVerticalBlocks() + ".");
			}
>>>>>>>>> Temporary merge branch 2

		}

	}

	public static void moveBlock(int level, int oldGridHorizontalPosition, int oldGridVerticalPosition,
			int newGridHorizontalPosition, int newGridVerticalPosition) throws InvalidInputException {
		String error = "";
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
		if(currentUser instanceof Admin) {
			boolean isAdminCurrentGameCreator = false;
			List<Game> games = ((Admin) currentUser).getGames();

			for(Game instanceOfGame : games) {
				if(instanceOfGame.getName().equals(game.getName())) {
					isAdminCurrentGameCreator = true;
				}
			}
			if(isAdminCurrentGameCreator == false) {
<<<<<<<<< Temporary merge branch 1
				throw new InvalidInputException("Only the admin who created the game can move a block.");
			}
		}

=========
				error += "Only the admin who created the game can move a block.";
			}
		}

		if(error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

>>>>>>>>> Temporary merge branch 2
		Level currentLevel;
		try {
			currentLevel = game.getLevel(level - 1);
		}
		catch (IndexOutOfBoundsException e) {
			error = e.getMessage();
<<<<<<<<< Temporary merge branch 1
			throw new InvalidInputException("Level " + level + " does not exist for the game.");
=========
			throw new InvalidInputException("Level" + level + "does not exist for the game.");
>>>>>>>>> Temporary merge branch 2
		}

		BlockAssignment assignment = currentLevel.findBlockAssignment(oldGridHorizontalPosition, oldGridVerticalPosition);

		if (assignment == null) {
			throw new InvalidInputException("A block does not exist at location " + oldGridHorizontalPosition + "/"+ oldGridVerticalPosition + ".");
		}

		if(currentLevel.findBlockAssignment(newGridHorizontalPosition, newGridVerticalPosition) != null) {
			throw new InvalidInputException("A block already exists at location " + newGridHorizontalPosition + "/"+ newGridVerticalPosition + ".");
		}


		try {
			assignment.setGridHorizontalPosition(newGridHorizontalPosition);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException("The horizontal position must be between 1 and " + game.maxNumberOfHorizontalBlocks() + ".");
<<<<<<<<< Temporary merge branch 1
=========

>>>>>>>>> Temporary merge branch 2
		}

		try {
			assignment.setGridVerticalPosition(newGridVerticalPosition);
		}
		catch (RuntimeException e) {
<<<<<<<<< Temporary merge branch 1
			throw new InvalidInputException("The vertical position must be between 1 and " + game.maxNumberOfVerticalBlocks() + ".");
=========
			throw new InvalidInputException("The horizontal position must be between 1 and " + game.maxNumberOfVerticalBlocks() + ".");
>>>>>>>>> Temporary merge branch 2
		}
	}
	public static void removeBlock(int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
		//		//William 01/03
<<<<<<<<< Temporary merge branch 1
		//FINISHED 03/23
		String error = "";
=========
		//		String error = "";
		//		Level currentLevel;
		//		UserRole currentUser = Block223Application.getCurrentUserRole();
		//		if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
		//			throw new InvalidInputException("Admin privileges are required to access game information.");
		//		}
		//		Game game = Block223Application.getCurrentGame();
		//		if (game == null) {
		//			throw new InvalidInputException("A game must be selected to access its information.");
		//		}
		//		//check if the admin created the game *****************QUESTION is this (admin) notation fine?
		//		Admin admin = game.getAdmin();
		//		if (admin != (Admin) currentUser) {
		//			throw new InvalidInputException("Only the admin who created the game can access its information.");
		//		}
		//		try {
		//			currentLevel = game.getLevel(level-1);
		//		}
		//		catch (IndexOutOfBoundsException e) {
		//			error = e.getMessage();
		//			if (error.equals("the index is out of range(index < 0 || index >= size())")) {
		//				error = "Level" + level + "does not exist for the game.";
		//			}
		//			throw new InvalidInputException(error);
		//		}
		//		BlockAssignment assignment = currentLevel.findBlockAssignment(gridHorizontalPosition, gridVerticalPosition);
		//		if(assignment != null){
		//			assignment.delete();
		//		}
>>>>>>>>> Temporary merge branch 2
		UserRole currentUser = Block223Application.getCurrentUserRole();
		if (!(currentUser instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to remove a block.");
		}
		Game game = Block223Application.getCurrentGame();
		if (game == null) {
			throw new InvalidInputException("A game must be selected to remove a block.");
		}
		Admin admin = game.getAdmin();
		if (admin != (Admin) currentUser) {
			throw new InvalidInputException("Only the admin who created the game can remove a block.");
<<<<<<<<< Temporary merge branch 1
		}
		Level currentLevel;
		try {
			currentLevel = game.getLevel(level-1);
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

=========
		}
		//		Block block = game.findBlock(id);
		//		if (block != null) {
		//			block.delete();
		//		}
>>>>>>>>> Temporary merge branch 2

	}

	public static void saveGame() throws InvalidInputException {
<<<<<<<<< Temporary merge branch 1

        if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
            throw new InvalidInputException("Admin privileges are required to save a game.");
        }
        if (Block223Application.getCurrentGame() == null) {
            throw new InvalidInputException("A game must be selected to save it.");
        }
        Game game = Block223Application.getCurrentGame();

        if (Block223Application.getCurrentUserRole() != game.getAdmin()) {
            throw new InvalidInputException("Only the admin who created the game can save it.");
        }
        Block223 block223 = Block223Application.getBlock223();
        try {
            Block223Persistence.save(block223);
        } catch (RuntimeException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }
=========
		if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to save a game.");
		}
		if (Block223Application.getCurrentGame() == null) {
			throw new InvalidInputException("A game must be selected to save it.");
		}
		if (Block223Application.getCurrentUserRole().equals(Block223Application.getCurrentGame().getAdmin())) {
			throw new InvalidInputException("Only the admin who created the game can save it.");
		}
		Block223 block223 = Block223Application.getBlock223();
		try {
			Block223Persistence.save(block223);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException("Failed to save game.");
		}
	}
>>>>>>>>> Temporary merge branch 2

	public static void register(String username, String playerPassword, String adminPassword)
			throws InvalidInputException {
		if (Block223Application.getCurrentUserRole()!= null) {
			throw new InvalidInputException("Cannot register a new user while a user is logged in.");
<<<<<<<<< Temporary merge branch 1
		}
		if (playerPassword==null || playerPassword.equals("")) {
			throw new InvalidInputException("The player password needs to be specified.");
		}
		if (playerPassword.equals(adminPassword)) {
			throw new InvalidInputException("The passwords have to be different.");
		}
		if (username==null || username.equals("")) {
			throw new InvalidInputException("The username must be specified.");
=========
		}
		if (playerPassword.equals(adminPassword)) {
			throw new InvalidInputException("The passwords have to be different.");
>>>>>>>>> Temporary merge branch 2
		}
		Block223 block223 = Block223Application.getBlock223();
		Player player;
		User user;
		
		try {
			player = new Player(playerPassword, block223);
		}
		catch (RuntimeException e) {
<<<<<<<<< Temporary merge branch 1
			throw new InvalidInputException("The player password needs to be specified.");
=========
			throw new InvalidInputException("The player password need to be specified.");
>>>>>>>>> Temporary merge branch 2
		}
		try {
			user = new User(username, block223, player);
		}
		catch (RuntimeException e) {
			player.delete();
<<<<<<<<< Temporary merge branch 1
			throw new InvalidInputException("The username has already been taken.");
=========
			throw new InvalidInputException("The username has already been taken or the username must be specified.");
>>>>>>>>> Temporary merge branch 2
		}

		if (adminPassword != null && adminPassword != "") {
			Admin admin = new Admin(adminPassword, block223);
			user.addRole(admin);
		}
		Block223Persistence.save(block223);
	}

	public static void login(String username, String password) throws InvalidInputException {
		if (Block223Application.getCurrentUserRole()!= null) {
			throw new InvalidInputException("Cannot login a user while a user is already logged in.");
		}
		Block223Application.resetBlock223();
<<<<<<<<< Temporary merge branch 1
=========
		if (User.getWithUsername(username)==null) {
			throw new InvalidInputException("The username and password do not match.");
		}
>>>>>>>>> Temporary merge branch 2
		User user = User.getWithUsername(username);
		if (user==null) {
			throw new InvalidInputException("The username and password do not match.");
		}
		List<UserRole> roles = user.getRoles();
		String rolePassword;
		boolean loginsuccessful= false;
		for (UserRole role : roles) {
			rolePassword= role.getPassword();
			if (rolePassword.equals(password)) {
				Block223Application.setCurrentUserRole(role);
				loginsuccessful=true;
			}
		}
<<<<<<<<< Temporary merge branch 1
		
		if (loginsuccessful=false) {//if no one is still not logged in
=========
		if (Block223Application.getCurrentUserRole()== null) {//if no one is still not logged in
>>>>>>>>> Temporary merge branch 2
			throw new InvalidInputException("The username and password do not match.");
		}

	}

	public static void logout() {
		Block223Application.setCurrentUserRole(null);
	}

	// ****************************
	// Query methods
	// ****************************
	public static List<TOGame> getDesignableGames() throws InvalidInputException {
		Block223 block223 = Block223Application.getBlock223();
		UserRole admin = Block223Application.getCurrentUserRole();
		// check if the current user is an admin
		if (!(admin instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		List<TOGame> result = new ArrayList<TOGame>();
		List<Game> games = block223.getGames();
		// return the list of all games
		for (Game game : games) {
			Admin gameAdmin = game.getAdmin();
			if (gameAdmin.equals(admin) && !game.isPublished()) {
				TOGame to = new TOGame(game.getName(), game.getLevels().size(), 
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
		Game game = Block223Application.getCurrentGame();
		if (game == null) {
			throw new InvalidInputException("A game must be selected to access its information.");
		}
		Admin admin = game.getAdmin();
		// check if the current user is an admin
		if (!(currentUser instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		// check if current game is set
		// compare current user is the admin of the current game
		if (admin != (Admin) currentUser) {
			throw new InvalidInputException("Only the admin who created the game can access its information.");
		}
		// return current game
		TOGame to = new TOGame(game.getName(), game.getLevels().size(),
				game.getNrBlocksPerLevel(),
				game.getBall().getMinBallSpeedX(),
				game.getBall().getMinBallSpeedY(),
				game.getBall().getBallSpeedIncreaseFactor(),
				game.getPaddle().getMaxPaddleLength(),
				game.getPaddle().getMinPaddleLength());
		return to;
	}

	public static List<TOBlock> getBlocksOfCurrentDesignableGame() throws InvalidInputException {
		//William 28/02
		UserRole currentUser = Block223Application.getCurrentUserRole();
		if (!(currentUser instanceof Admin)) {
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
		List<TOBlock> result = new ArrayList<TOBlock>();

		List<Block> blocks = game.getBlocks();
		for(Block block: blocks){
			TOBlock to = new TOBlock(block.getId(), block.getRed(), block.getGreen(), block.getBlue(), block.getPoints());
			result.add(to);
		}
		return result;
	}

	public static TOBlock getBlockOfCurrentDesignableGame(int id) throws InvalidInputException {

		UserRole currentUser = Block223Application.getCurrentUserRole();

		if (!(currentUser instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}

		Game currentGame = Block223Application.getCurrentGame();

		if (currentGame == null) {
			throw new InvalidInputException("A game must be selected to access its information.");
		}

		Admin admin = currentGame.getAdmin();

		if (admin != (Admin) Block223Application.getCurrentUserRole()) {
			throw new InvalidInputException("Only the admin who created the game can access its information.");
		}

		Block block = currentGame.findBlock(id);

		TOBlock to = new TOBlock(block.getId(),
				block.getRed(),
				block.getGreen(),
				block.getBlue(),
				block.getPoints());
		return to;
	}

	public static List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {
		String error = "";
		//Check if the user is an admin 
		UserRole currentUser = Block223Application.getCurrentUserRole();
		if (!(currentUser instanceof Admin)) {
			error += "Admin privileges are required to access game information.";
		}
		//check if the game exists
		Game game = Block223Application.getCurrentGame();
		if (game == null) {
			error += "A game must be selected to access its information.";
		}

<<<<<<<<< Temporary merge branch 1

=========
>>>>>>>>> Temporary merge branch 2
		//check if admin is creator of the game
		if(currentUser instanceof Admin) {
			boolean isAdminCurrentGameCreator = false;
			List<Game> games = ((Admin) currentUser).getGames();

			for(Game instanceOfGame : games) {
				if(instanceOfGame.getName().equals(game.getName())) {
					isAdminCurrentGameCreator = true;
				}
			}
			if(isAdminCurrentGameCreator == false) {
<<<<<<<<< Temporary merge branch 1
				throw new InvalidInputException("Only the admin who created the game can access its information.");
=========
				error += "Only the admin who created the game can access its information.";
>>>>>>>>> Temporary merge branch 2
			}
		}

		if(error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

<<<<<<<<< Temporary merge branch 1
=========
		List<TOGridCell> result = new ArrayList<TOGridCell>(); 

>>>>>>>>> Temporary merge branch 2
		Level currentLevel;
		try {
			currentLevel = game.getLevel(level - 1);
		}
		catch (IndexOutOfBoundsException e) {
<<<<<<<<< Temporary merge branch 1
			throw new InvalidInputException("Level " + level + " does not exist for the game.");
=========
			throw new InvalidInputException("Level" + level + "does not exist for the game.");
>>>>>>>>> Temporary merge branch 2
		}


		if (currentLevel != null)
		{
			List<BlockAssignment> assignments = currentLevel.getBlockAssignments();
			for (BlockAssignment assignment: assignments) {

				TOGridCell to = new TOGridCell(assignment.getGridHorizontalPosition(), assignment.getGridVerticalPosition(), assignment.getBlock().getId(), assignment.getBlock().getRed(), assignment.getBlock().getGreen(), assignment.getBlock().getBlue(), assignment.getBlock().getPoints());

				result.add(to);
			}
		}

		return result;
<<<<<<<<< Temporary merge branch 1
	}

=========


	}

>>>>>>>>> Temporary merge branch 2
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

	// ****************************
	// P1. Start/pause/resume game
	// ****************************
<<<<<<<<< Temporary merge branch 1

	public static List<TOPlayableGame> getPlayableGames() throws InvalidInputException {
		Block223 block223 = Block223Application.getBlock223();
		UserRole role = Block223Application.getCurrentUserRole();
		if (!(role instanceof Player)) {
			throw new InvalidInputException("Player privileges are required to play a game.");
		}
		List<TOPlayableGame> result = new ArrayList<TOPlayableGame>();
		List<Game> games = block223.getGames();
		for (Game game: games) {
			boolean published = game.isPublished();
			if (published) {
				TOPlayableGame to = new TOPlayableGame(game.getName(), -1, 0);
				result.add(to);
			}
		}
		Player player = (Player) role;
		List<PlayedGame> playedGames = player.getPlayedGames();
		for (PlayedGame game: playedGames) {
			TOPlayableGame to = new TOPlayableGame(game.getGame().getName(), game.getId(), game.getCurrentLevel());
			result.add(to);
		}
		return result;

	}

	public static void selectPlayableGames(String name, int id) throws InvalidInputException {
		PlayedGame pgame = null;
		Block223 block223 = Block223Application.getBlock223();
		Game game = block223.findGame(name);
		UserRole player = Block223Application.getCurrentUserRole();
		if (!(player instanceof Player)) {
			throw new InvalidInputException("Player privileges are required to play a game.");
		}
		if (game != null) {
			String username = User.findUsername(player);
			pgame = new PlayedGame(username, game, block223);
			pgame.setPlayer((Player) player);
		}
		else {
			pgame = block223.findPlayableGame(id);
			if (pgame == null) {
				throw new InvalidInputException("The game does not exist.");
			}
			else if (player != pgame.getPlayer()) {
				throw new InvalidInputException("Only the player that started a game can continue the game.");
			}
		}
		Block223Application.setCurrentPlayableGame(pgame);
	}

	public static void startGame(Block223PlayModeInterface ui) throws InvalidInputException {
		PlayedGame game = Block223Application.getCurrentPlayableGame();
		UserRole player = Block223Application.getCurrentUserRole();
		if (player == null) {
			throw new InvalidInputException("Player privileges are required to play a game.");
		}
		if (game == null) {
			throw new InvalidInputException("A game must be selected to play it.");
		}
		Player currentPlayer = game.getPlayer();
		if (player instanceof Admin && currentPlayer != null) {
			throw new InvalidInputException("Player privileges are required to play a game.");
		}
		Game currentGame = game.getGame();
		if (player instanceof Admin && player != currentGame.getAdmin()) {
			throw new InvalidInputException("Only the admin of a game can test the game.");
		}
		if (player instanceof Player && currentPlayer == null) {
			throw new InvalidInputException("Admin privileges are required to test a game.");
		}
		game.play();
		ui.takeInputs();
		while (game.getPlayStatus() == PlayStatus.Moving) {
			String userInputs = ui.takeInputs();
			Block223Controller.updatePaddlePosition(userInputs);
			game.move();
			if (userInputs.contains(" ")) {
				game.pause();
			}
			game.getWaitTime();
			ui.refresh();
		}
		if (game.getPlayStatus() == PlayStatus.GameOver) {
			Block223Application.setCurrentPlayableGame(null);
		}
		else if (game.getPlayer() != null) {
			Block223 block223 = Block223Application.getBlock223();
			Block223Persistence.save(block223);
		}
	}


	public static TOCurrentlyPlayedGame getCurrentPlayableGame() throws InvalidInputException {
		UserRole player = Block223Application.getCurrentUserRole();
		if (player == null) {
			throw new InvalidInputException("Player privileges are required to play a game.");
		}
		PlayedGame game = Block223Application.getCurrentPlayableGame();
		if (game == null) {
			throw new InvalidInputException("A game must be selected to play it.");
		}
		Player currentPlayer = game.getPlayer();
		if (player instanceof Admin && currentPlayer != null) {
			throw new InvalidInputException("Player privileges are required to play a game.");
		}
		Game currentGame = game.getGame();
		if (player instanceof Admin && player != currentGame.getAdmin()) {
			throw new InvalidInputException("Only the admin of a game can test the game.");
		}
		if (player instanceof Player && currentPlayer == null) {
			throw new InvalidInputException("Admin privileges are required to test a game.");
		}
		PlayedGame pgame = Block223Application.getCurrentPlayableGame();
		boolean paused = (pgame.getPlayStatus() == PlayStatus.Ready || pgame.getPlayStatus() == PlayStatus.Paused);
		TOCurrentlyPlayedGame result = new TOCurrentlyPlayedGame(pgame.getGame().getName(), paused, pgame.getScore(),
				pgame.getLives(),pgame.getCurrentLevel(), pgame.getPlayername(), pgame.getCurrentBallX(), pgame.getCurrentBallY(),
				pgame.getCurrentPaddleLength(), pgame.getCurrentPaddleX());
		List<PlayedBlockAssignment> blocks = pgame.getBlocks();
		for (PlayedBlockAssignment pblock: blocks) {
			TOCurrentBlock to = new TOCurrentBlock(pblock.getBlock().getRed(),pblock.getBlock().getGreen(),
					pblock.getBlock().getBlue(),
					pblock.getBlock().getPoints(),
					pblock.getX(),
					pblock.getY(),
					result);
					}
		return result;
		}


    // ****************************
    // P3. Ball hits paddle or wall
    // ****************************

    //All methods are in PlayedGame.Java and in Block223State.uml


	// ****************************
	// P4. Ball hits block
	// ****************************

	private boolean hitLastBlockAndLastLevel() {
		PlayedGame currentPlayedGame = Block223Application.getCurrentPlayableGame();
		Game currentLevel = Block223Application.getCurrentGame();
		Game nrLevels = Block223Application.getCurrentGame();
		int nrBlocks = currentPlayedGame.numberOfBlocks();
		currentPlayedGame.setBounce(null);
		if(nrLevels == currentLevel) {
			currentPlayedGame.numberOfBlocks();
			if(nrBlocks == 1) {
				PlayedBlockAssignment block = currentPlayedGame.getBlock(0);
			}
			return true;
		}
		return false;
	}

	private void doHitBlock() {
		PlayedGame currentPlayedGame = Block223Application.getCurrentPlayableGame();
		int score = currentPlayedGame.getScore();
		BouncePoint bounce = currentPlayedGame.getBounce();
		PlayedBlockAssignment pBlock = bounce.getHitBlock();
		Block block = pBlock.getBlock();
		int points = block.getPoints();
		currentPlayedGame.setScore(score + points);
		pBlock.delete();
		currentPlayedGame.bounceBall();

	}

	private boolean hitLastBlock() {
		PlayedGame currentPlayedGame = Block223Application.getCurrentPlayableGame();
		int nrBlocks = currentPlayedGame.numberOfBlocks();
		currentPlayedGame.setBounce(null);
		if(nrBlocks == 1) {
			PlayedBlockAssignment block = currentPlayedGame.getBlock(0);
			BouncePoint bp = currentPlayedGame.calculateBouncePointBlock(block);
			currentPlayedGame.setBounce(bp);
			if(bp == null) {
				return (Boolean) null;
			}
			return true;
		}
		return false;





	}

	private void doHitBlockNextLevel() {
	PlayedGame currentPlayedGame = Block223Application.getCurrentPlayableGame();
	int level =


	}


	private boolean hitBlock() {
		PlayedGame currentPlayedGame = Block223Application.getCurrentPlayableGame();
		int nrBlocks = currentPlayedGame.numberOfBlocks();
		currentPlayedGame.setBounce(null);
		for(int index = 0; index <= currentPlayedGame.numberOfBlocks(); index++) {
			PlayedBlockAssignment block = currentPlayedGame.getBlock(index);
			BouncePoint bp = currentPlayedGame.calculateBouncePointBlock(block);
			currentPlayedGame.getBounce();
			if(closer = true) {
				currentPlayedGame(bp);
			}
			if(bp == null) {
				return (Boolean) null;
			}
			return false;
	}
	}

	private void setPlayStatus(PlayStatus aPlayStatus) {
		playStatus = aPlayStatus;
		if(p)
	}

	// ****************************
	// P5. Ball is out of bounds
	// ****************************

	//all methods in Block223States.ump
	//isBalloutofbound in block223playmode.ump

	// ****************************
	// P6. View hall of fame
	// ****************************

	public static TOHallOfFame getHallOfFame(int start, int end) throws InvalidInputException {

		PlayedGame pgame = Block223Application.getCurrentPlayableGame();

		Game game = PlayedGame.getGame();

		TOHallOfFame result = new TOHallOfFame(game.getName());

		for (int i = start; i <= end ; i++ ) {
			TOHallOfFameEntry to = new TOHallOfFameEntry(index +1, game.getHallOfFameEntry(index).getPlayername(), game.getHallOfFameEntry(index).getScore(), result);
		}
		return result;
	}

	public static TOHallOfFame getHallOfFameWithMostRecentEntry(int numberOfEntries) throws InvalidInputException {
		PlayedGame pgame = getCurrentPlayableGame();
		Game game = pgame.getGame();
	}


	// ****************************
	// P7. Test game
	// ****************************

	public static void testGame(Block223PlayModeInterface ui) throws InvalidInputException {
		UserRole admin = Block223Application.getCurrentUserRole();
		if (!(admin instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to test a game.");
		}
		Game game = Block223Application.getCurrentGame();
		if (game == null) {
			throw new InvalidInputException("A game must be selected to test it.");
		}
		Admin testAdmin = game.getAdmin();
		if (testAdmin != (Admin) admin) {
			throw new InvalidInputException("Only the admin who created the game can test it.");
		}
		String username = User.findUsername(admin);
		Block223 block223 = Block223Application.getBlock223();
		PlayedGame pgame = new PlayedGame(username, game, block223);
		pgame.setPlayer(null);
		Block223Application.setCurrentPlayableGame(pgame);
		Block223Controller.startGame(ui);
	}

	// ****************************
	// P8. Publish game
	// ****************************

	public static void publishGame () throws InvalidInputException {
		UserRole userRole = Block223Application.getCurrentUserRole();
		if (!(userRole instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to publish a game.");
		}
		Game game = Block223Application.getCurrentGame();
		if (game == null) {
			throw new InvalidInputException("A game must be selected to publish it.");
		}
		if (game.getAdmin() != (Admin) userRole) {
			throw new InvalidInputException("Only the admin who created the game can publish it.");
		}
		if (game.getBlocks().size() < 1) {
			throw new InvalidInputException("At least one block must be defined for a game to be published.");
		}
		game.setPublished(true);
	}

}