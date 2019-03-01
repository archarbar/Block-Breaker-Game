package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.TOBlock;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.controller.TOGridCell;
import ca.mcgill.ecse223.block.controller.TOUserMode;

public class Block223Page extends JFrame {
	
	//UI elements
	private JLabel errorMessage;
	//Create game
	private JTextField gameNameTextField;
	private JLabel gameNameLabel;
	private JButton createGameButton;
	
	//data elements
	private String error = null;
	
	//Create new form Block223Page
	public Block223Page() {
		initComponents();
		refreshData();
	}
	
	private void initComponents() {
		//elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		//elements for new game
		gameNameTextField = new JTextField();
		gameNameLabel = new JLabel();
		gameNameLabel.setText("Name:");
		createGameButton = new JButton();
		createGameButton.setText("Create Game");
		
		//global settings
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Block223 The Best Game Ever");
		
		//Listener for createGame button
		createGameButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createGameButtonActionPerformed(evt);
			}
		});
	}
	
	private void refreshData() {
		//error
		errorMessage.setText(error);
		if (error == null || error.length() == 0) {
			//populate page with data
			// game
			gameNameTextField.setText("");
		}
	}
}