package ca.mcgill.ecse223.block.view;

import java.util.Scanner;

public class Block223PlayMode implements Block223PlayModeInterface {
	
	public String takeInputs() {
		Scanner scan = new Scanner(System.in);
		String userInput = scan.nextLine();
		String inputs = "";
		for (int i=0; i < userInput.length(); i++) {
			if (userInput.charAt(i) == 'l' || userInput.charAt(i) == 'r' || userInput.charAt(i) == ' ') {
				inputs += Character.toString(userInput.charAt(i));
			}
		}
		scan.close();
		return inputs;
	}
	
	public void refresh() {
		
	}
}