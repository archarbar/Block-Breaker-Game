package ca.mcgill.ecse223.block.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Ball;
import ca.mcgill.ecse223.block.model.Block;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.BlockAssignment;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.Level;
import ca.mcgill.ecse223.block.model.Paddle;
import ca.mcgill.ecse223.block.model.Player;
import ca.mcgill.ecse223.block.model.User;
import ca.mcgill.ecse223.block.model.UserRole;

public class Block223ControllerTest {
	
	@Before
	public void setUp() {
		//clear all data
		Block223 block223 = Block223Application.getBlock223();
		block223.delete();
	}
	
	//Create game tests
	@Test
	public void testCreateGameSuccess() {
		Block223 block223 = Block223Application.getBlock223();
		String name = "testGame";
		try {
			Block223Controller.createGame(name);
		}
		catch (InvalidInputException e) {
			//check that no error occurred
			fail();
		}
	}
	
	@Test
	public void testCreateGameNull() {
		Block223 block223 = Block223Application.getBlock223();
		String name = null;
		String error = null;
		try {
			Block223Controller.createGame(name);
		}
		catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error
		assertEquals("The name of a game must be specified", error);
	}
	
	@Test
	public void testCreateGameEmpty() {
		Block223 block223 = Block223Application.getBlock223();
		String name = "";
		String error = null;
		try {
			Block223Controller.createGame(name);
		}
		catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error
		assertEquals("The name of a game must be specified", error);
	}
	
	@Test
	public void testCreateGameDuplicate() {
		Block223 block223 = Block223Application.getBlock223();
		
		//Creates a test game
		String nameTest = "DuplicateGame";
		Admin admin = (Admin) Block223Application.getCurrentUserRole();
		Game gameTest = new Game(nameTest, 1, admin, 1, 1, 1, 10, 10, block223);
		block223.addGame(gameTest);
		
		String error = "";
		try {
			Block223Controller.createGame(nameTest);
		}
		catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error
		assertEquals("The name of a game must be unique", error);
	}
	
	@Test
	public void testCreateGameNotAdmin() {
		Block223 block223 = Block223Application.getBlock223();
		
		String error = "";
	}
}
