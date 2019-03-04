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
			block223.addGame(game);
			Block223Application.setCurrentGame(game);
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
		Game game = Block223Application.getCurrentGame();
		if(game == null) {
			throw new InvalidInputException("A game must be selected to define game settings");
		}
		Admin admin = game.getAdmin();
		if(admin != (Admin) currentUser) {
			throw new InvalidInputException("Only the admin who created the game can define its game settings");
		}
		if (nrLevels < 1 || nrLevels > 99) {
			throw new InvalidInputException("The number of levels must be between 1 and 99");
		}
		try {
			game.setNrBlocksPerLevel(nrBlocksPerLevel);
		}
		catch (RuntimeException e) {
			error = e.getMessage();
			if (error.equals("The number of blocks per level must be greater than zero")) {
				error = "The number of blocks per level must be greater than zero";
			}
			throw new InvalidInputException(error);
		}
		Ball ball = game.getBall();
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
		Paddle paddle = game.getPaddle();
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
		if (!(currentUser instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to delete a game.");
		}
		//Game currentGame = Block223Application.getCurrentGame();
		Game currentGame = Block223Application.getCurrentGame();
		Admin admin = currentGame.getAdmin();
		if (admin != (Admin) currentUser) {
			throw new InvalidInputException("Only the admin who created the game can delete the game.");
		}
		Game game = Game.getWithName(name);
		if (game != null) {
			Block223 block223 = game.getBlock223();
			game.delete();
			Block223Persistence.save(block223);
		}
	}

	public static void selectGame(String name) throws InvalidInputException {
		UserRole currentUser = Block223Application.getCurrentUserRole();
		if (!(currentUser instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to select a game.");
		}
		Game currentGame = Block223Application.getCurrentGame();
		Admin admin = currentGame.getAdmin();
		if (admin != (Admin) currentUser) {
			throw new InvalidInputException("Only the admin who created the game can select the game.");
		}
		Game game = Game.getWithName(name);
		if (game == null) {
			throw new InvalidInputException("A game with name " + name + " does not exist.");
		}
		Block223Application.setCurrentGame(game);
	}


	public static void updateGame(String name, int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
		UserRole currentUser = Block223Application.getCurrentUserRole();
		Game currentGame = Block223Application.getCurrentGame();
		String error = "";
		if (!(currentUser instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to define game settings.");
		}
		if (currentGame == null) {
			throw new InvalidInputException("A game must be selected to define game settings.");
		}
		Admin admin = currentGame.getAdmin();
		if (admin != (Admin) currentUser) {
			throw new InvalidInputException("Only the admin who created the game can define its game settings.");
		}
		Block223 block223 = Block223Application.getBlock223();
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
		try {
			Game game = new Game(name, 1, (Admin) currentUser, 1, 1, 1, 10, 10, block223);
		}
		catch (RuntimeException e) {
			if (name == null) {
				throw new InvalidInputException("The  name  of  a  game  must  be  specified.");
			}
		}
		String currentName = currentGame.getName();
		if (currentName != name) {
			currentGame.setName(name);
		}
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
			throw new InvalidInputException("A game must be selected to access its information.");
		}

		Admin admin = currentGame.getAdmin();

		if (admin.equals((Admin) currentUser)) {
			throw new InvalidInputException("Only the admin who created the game can access its information.");
		}

		List<Block> sourceList = currentGame.getBlocks();

		for(Block specificBlock : sourceList) {
			int colorRed = specificBlock.getRed();
			int colorGreen = specificBlock.getGreen();
			int colorBlue = specificBlock.getBlue();

			if (colorRed == aRed && colorGreen == aGreen && colorBlue == aBlue) {
				throw new InvalidInputException("A block with the same color already exists for the game.");
			}

			try {
				Block block = new Block(aRed, aGreen, aBlue, aPoints, currentGame);
				currentGame.addBlock(block);

			}
			catch (RuntimeException e) {
				throw new InvalidInputException(e.getMessage());
			}
		}
	}




	public static void deleteBlock(int id) throws InvalidInputException {
		//William 01/03
		UserRole currentUser = Block223Application.getCurrentUserRole();
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

	public static void updateBlock(int id, int aRed, int aGreen, int aBlue, int aPoints) throws InvalidInputException {

		String error = "";

		UserRole currentUser = Block223Application.getCurrentUserRole();

		if (!(currentUser instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}

		Game currentGame = Block223Application.getCurrentGame();

		if (currentGame == null) {
			throw new InvalidInputException("A game must be selected to access its information.");
		}

		Admin admin = currentGame.getAdmin();

		if (admin.equals((Admin) currentUser)) {
			throw new InvalidInputException("Only the admin who created the game can access its information.");
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
		String error;
		Level currentLevel;
		try {
			currentLevel = game.getLevel(level - 1);
		}
		catch (IndexOutOfBoundsException e) {//***************QUESTION good? how do we add the condition that it has to be between 1 and 99?/What is the error message suppose to be?
			error = e.getMessage();
			if (error.equals("Level must be between 1 and 99 inclusively.")) {
				error = "Level" + level + "does not exist for the game.";
			}
			throw new InvalidInputException(error);
		}

		Block block = game.findBlock(id);

		//Check if number of blocks in the level of the current game, if its already at the maximum, print the following error ****************QUESTION is the numberofblockassignments equivalent to the number of blocks per level?
		if (currentLevel.numberOfBlockAssignments() >= game.getNrBlocksPerLevel()) {
			throw new InvalidInputException("The number of blocks has reached the maximum number (" + game.getNrBlocksPerLevel() + ") allowed for this game.");
		}

		//BlockAssignment newPosition = currentLevel.findBlockAssignment(gridHorizontalPosition, gridVerticalPosition)

		//If the position is not empty ((Horizontal/Vertical)gridLocation already occupied), print out error. 
		if(currentLevel.findBlockAssignment(gridHorizontalPosition, gridVerticalPosition) != null) {
			throw new InvalidInputException("A block already exists at location" + gridHorizontalPosition + "/" + gridVerticalPosition + ".");
		}
		//If block does not exist return null
		if(block == null) {
			throw new InvalidInputException("The block does not exist.");
		}
		//Why can't I reference to newBlockAssignment?
		BlockAssignment newBlockAssignment = null;
		try {
			newBlockAssignment = new BlockAssignment(gridHorizontalPosition, gridVerticalPosition, currentLevel, block, game);
		}
		catch (RuntimeException e) {
			error = e.getMessage();
			if (error.equals("GridHorizontalPosition can't be negative or greater than " + newBlockAssignment.getMaxHorizontalGridPosition())) {
				error = "The horizontal position must be between 1 and " + newBlockAssignment.getMaxHorizontalGridPosition() + ".";}
			if (error.equals("GridVerticalPosition can't be negative or greater than " + newBlockAssignment.getMaxVerticalGridPosition())) {
					error = "The vertical position must be between 1 and " + newBlockAssignment.getMaxVerticalGridPosition() + ".";
			}

			throw new InvalidInputException(error);
		}
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
		Level currentLevel;
		try {
			currentLevel = game.getLevel(level - 1);
		}
		catch (IndexOutOfBoundsException e) {//***************QUESTION good? how do we add the condition that it has to be between 1 and 99?/What is the error message suppose to be?
			error = e.getMessage();
			if (error.equals("Level must be between 1 and 99 inclusively.")) {
				error = "Level" + level + "does not exist for the game.";
			}
			throw new InvalidInputException(error);
		}

		BlockAssignment assignment = currentLevel.findBlockAssignment(oldGridHorizontalPosition, oldGridVerticalPosition);

		if (assignment == null) {
			throw new InvalidInputException("A block does not exist at location" + oldGridHorizontalPosition + "/"+ oldGridVerticalPosition + ".");
		}

		if(currentLevel.findBlockAssignment(newGridHorizontalPosition, newGridVerticalPosition) != null) {
			throw new InvalidInputException("A block already exists at location" + newGridHorizontalPosition + "/"+ newGridVerticalPosition + ".");
		}

		try {
			assignment.setGridHorizontalPosition(newGridHorizontalPosition);
		}
		catch (RuntimeException e) {
			error = e.getMessage();
			if (error.equals("gridHorizontalPosition can't be negative or greater than " + assignment.getMaxHorizontalGridPosition())) {
				error = "The horizontal position must be between 1 and " + assignment.getMaxHorizontalGridPosition() + ".";
			}
			throw new InvalidInputException(error);
		}

		try {
			assignment.setGridVerticalPosition(newGridVerticalPosition);
		}
		catch (RuntimeException e) {
			error = e.getMessage();
			if (error.equals("GridVerticalPosition can't be negative or greater than " + assignment.getMaxVerticalGridPosition())) {
				error = "The vertical position must be between 1 and " + assignment.getMaxVerticalGridPosition() + ".";
			}
			throw new InvalidInputException(error);
		}
	}
	public static void removeBlock(int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
		//William 01/03
		String error = "";
		Level currentLevel;
		UserRole currentUser = Block223Application.getCurrentUserRole();
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
		Block223 block223 = Block223Application.getBlock223();
		UserRole admin = Block223Application.getCurrentUserRole();
		if (!(admin instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		List<TOGame> result = new ArrayList<TOGame>();
		List<Game> games = block223.getGames();
		for (Game game : games) {
			Admin gameAdmin = game.getAdmin();
			if (gameAdmin.equals(admin)) {
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
		Game currentGame = Block223Application.getCurrentGame();
		Admin admin = currentGame.getAdmin();
		if (!(currentUser instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		if (currentGame == null) {
			throw new InvalidInputException("A game must be selected to access its information.");
		}
		if (admin != (Admin) currentUser) {
			throw new InvalidInputException("Only the admin who created the game can access its information.");
		}
		Game game = Block223Application.getCurrentGame();
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
		if (!currentUser.equals("Admin")) {
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

		List<TOGridCell> result = new ArrayList<TOGridCell>(); 

		String error = "";

		Level currentLevel;
		try {
			currentLevel = game.getLevel(level - 1);
		}
		catch (IndexOutOfBoundsException e) {//***************QUESTION good? how do we know what's the string of the error?
			error = e.getMessage();
			if (error.equals("Level must be between 1 and 99 inclusively.")) {
				error = "Level" + level + "does not exist for the game.";
			}
			throw new InvalidInputException(error);
		}

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

}