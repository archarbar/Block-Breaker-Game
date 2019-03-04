package ca.mcgill.ecse223.block.view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.Game;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JSeparator;
import javax.swing.JComboBox;

public class CreateGamePage extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 6791018688197203010L;
	private JPanel contentPane;
	private JTextField GameNameTextField;
	
	private String error = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateGamePage frame = new CreateGamePage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CreateGamePage() {
		setTitle("Edit Game Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//Menu Bar Items

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenu mnUser = new JMenu("User");
		menuBar.add(mnUser);

		JMenuItem mntmLogOut = new JMenuItem("Log out");
		mntmLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				mntmLogOutActionPerformed(evt);
			}
		});
		mnUser.add(mntmLogOut);

		//Header 1

		JLabel lblCreateNewGame = new JLabel("Create or Delete Game");
		lblCreateNewGame.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCreateNewGame.setBounds(10, 11, 157, 14);
		contentPane.add(lblCreateNewGame);

		//Create or delete game section
		
		JLabel lblGameName = new JLabel("Game name:");
		lblGameName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGameName.setBounds(20, 46, 81, 14);
		contentPane.add(lblGameName);
		
		GameNameTextField = new JTextField();
		GameNameTextField.setBounds(111, 44, 86, 20);
		contentPane.add(GameNameTextField);
		GameNameTextField.setColumns(10);
		
		JButton btnNewButton = new JButton("Create Game");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createGameActionPerformed(evt);
			}
		});
		btnNewButton.setBounds(234, 42, 114, 23);
		contentPane.add(btnNewButton);
		
		JButton btnDeleteGame = new JButton("Delete Game");
		btnDeleteGame.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDeleteGame.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteGameActionPerformed(evt);
			}
		});
		btnDeleteGame.setBounds(360, 42, 112, 23);
		contentPane.add(btnDeleteGame);

		//Separator 1

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 87, 483, 14);
		contentPane.add(separator);
		
		//Select existing game section
		
		JLabel lblNewLabel = new JLabel("Update Game");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 106, 98, 20);
		contentPane.add(lblNewLabel);

		JComboBox gamesList = new JComboBox();
		gamesList.setBounds(20, 148, 114, 32);
		contentPane.add(gamesList);
		Block223 block223 = Block223Application.getBlock223();
		List<Game> games = block223.getGames();
		gamesList.addItem("select");
		for (Game game : games) {
			gamesList.addItem(game);
		}

		JButton btnUpdateGame = new JButton("Update Game");
		btnUpdateGame.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnUpdateGame.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateGameActionPerformed(evt);
			}
		});
		btnUpdateGame.setBounds(173, 156, 114, 23);
		contentPane.add(btnUpdateGame);
	}
	
	private void createGameActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		String name = GameNameTextField.getText();
		try {
			Block223Controller.createGame(name);
			GamePage gameSettings = new GamePage();
			gameSettings.setVisible(true);
			this.setVisible(false);
		}
		catch (InvalidInputException e) {
			error = e.getMessage();
			JOptionPane.showMessageDialog(null, error);
		}
	}
	
	private void deleteGameActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		String name = GameNameTextField.getText();
		try {
			Block223Controller.deleteGame(name);
		}
		catch (InvalidInputException e) {
			error = e.getMessage();
			JOptionPane.showMessageDialog(null, error);
		}
	}

	private void updateGameActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		String name = GameNameTextField.getText();
		try {
			Block223Controller.selectGame(name);
			GamePage adminpage = new GamePage();
			adminpage.setVisible(true);
			this.setVisible(false);
		}
		catch (InvalidInputException e) {
			error = e.getMessage();
			JOptionPane.showMessageDialog(null, error);
		}
	}

	private void mntmLogOutActionPerformed(ActionEvent evt) {
		Block223Controller.logout();
		RegisterLoginPage loginpage = new RegisterLoginPage();
		loginpage.setVisible(true);
		this.setVisible(false);
	}
}
