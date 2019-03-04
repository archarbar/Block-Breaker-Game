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

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JSeparator;
import javax.swing.JComboBox;

public class CreateGamePage extends JFrame {

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
		setTitle("Create Game Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 250);
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//Header 1

		JLabel lblCreateNewGame = new JLabel("Create New Game");
		lblCreateNewGame.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCreateNewGame.setBounds(10, 11, 124, 14);
		contentPane.add(lblCreateNewGame);
		
		//Create game section
		
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
		
		//Separator 1
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 87, 414, 14);
		contentPane.add(separator);
		
		//Select existing game section
		
		JLabel lblNewLabel = new JLabel("Select Existing Game");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 106, 157, 20);
		contentPane.add(lblNewLabel);
		
		JComboBox gamesList = new JComboBox();
		gamesList.setBounds(20, 148, 114, 20);
		contentPane.add(gamesList);
		
		JButton btnSelectGame = new JButton("Select Game");
		btnSelectGame.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSelectGame.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
			}
		});
		btnSelectGame.setBounds(173, 147, 124, 23);
		contentPane.add(btnSelectGame);
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
	
	private void mntmLogOutActionPerformed(ActionEvent evt) {
		Block223Controller.logout();
		RegisterLoginPage loginpage = new RegisterLoginPage();
		loginpage.setVisible(true);
		this.setVisible(false);
	}
}
