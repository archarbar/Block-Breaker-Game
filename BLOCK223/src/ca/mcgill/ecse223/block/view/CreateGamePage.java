package ca.mcgill.ecse223.block.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JSeparator;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.TOBlock;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.controller.TOGridCell;
import ca.mcgill.ecse223.block.controller.TOUserMode;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class CreateGamePage extends JFrame {

	//Initialize Main Panel
	private JPanel contentPanel;
	//Initialize Create Game Variables
	private JTextField GameNameTextField;
	//Initialize Play Area Variables
	private JTextField WidthTextField;
	private JTextField HeightTextField;
	//Initialize Ball Variables
	private JTextField MinYSpeedTextField;
	private JTextField MinXSpeedTextField;
	private JTextField SpeedIncreaseFactorTextField;
	//Initialize Paddle Variables
	private JTextField MinPaddleLengthTextField;
	private JTextField MaxPaddleLengthTextField;
	private JTextField textField;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 457);
		
		//Menu Bar Items
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnUser = new JMenu("User");
		menuBar.add(mnUser);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		//Create game parameters
		
		JLabel lblMainTitle = new JLabel("BLOCK223 Game Builder");
		lblMainTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMainTitle.setBounds(10, 11, 156, 14);
		contentPanel.add(lblMainTitle);
		
		GameNameTextField = new JTextField();
		GameNameTextField.setColumns(10);
		GameNameTextField.setBounds(91, 43, 87, 20);
		contentPanel.add(GameNameTextField);
		
		JLabel lblGameName = new JLabel("Game name:");
		lblGameName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGameName.setBounds(10, 45, 92, 14);
		contentPanel.add(lblGameName);
		
		JButton btnCreateGame = new JButton("Create Game");
		btnCreateGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCreateGame.setBounds(321, 42, 112, 23);
		contentPanel.add(btnCreateGame);
		
		//Separator 1
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 79, 464, 14);
		contentPanel.add(separator_1);
		
		//Play area parameters
		
		JLabel lblPlayArea = new JLabel("Play Area");
		lblPlayArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPlayArea.setBounds(10, 94, 68, 14);
		contentPanel.add(lblPlayArea);
		
		JLabel lblWidth = new JLabel("Width:");
		lblWidth.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblWidth.setBounds(10, 127, 46, 14);
		contentPanel.add(lblWidth);
		
		WidthTextField = new JTextField();
		WidthTextField.setBounds(66, 125, 112, 20);
		contentPanel.add(WidthTextField);
		WidthTextField.setColumns(10);
		
		JLabel lblHeight = new JLabel("Height:");
		lblHeight.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHeight.setBounds(10, 152, 46, 14);
		contentPanel.add(lblHeight);
		
		HeightTextField = new JTextField();
		HeightTextField.setColumns(10);
		HeightTextField.setBounds(66, 150, 112, 20);
		contentPanel.add(HeightTextField);
		
		//Level parameters
		
		JLabel lblLevel = new JLabel("Level\r\n");
		lblLevel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLevel.setBounds(219, 94, 68, 14);
		contentPanel.add(lblLevel);
		
		JLabel lblBlocksPerLevel = new JLabel("Blocks per level:\r\n");
		lblBlocksPerLevel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBlocksPerLevel.setBounds(219, 127, 92, 14);
		contentPanel.add(lblBlocksPerLevel);
		
		JLabel lblRandomBlocks = new JLabel("Random Blocks:\r\n");
		lblRandomBlocks.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRandomBlocks.setBounds(219, 152, 92, 14);
		contentPanel.add(lblRandomBlocks);
		
		JComboBox RandomBlocksComboBox = new JComboBox();
		RandomBlocksComboBox.setBounds(321, 150, 112, 20);
		RandomBlocksComboBox.addItem("Select");
		RandomBlocksComboBox.addItem("True");
		RandomBlocksComboBox.addItem("False");
		contentPanel.add(RandomBlocksComboBox);
		
		//Separator 2
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 190, 464, 14);
		contentPanel.add(separator_2);
		
		//Ball parameters
		
		JLabel lblBallParameters = new JLabel("Ball Parameters\r\n");
		lblBallParameters.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBallParameters.setBounds(10, 215, 92, 14);
		contentPanel.add(lblBallParameters);
		
		JLabel lblMinyspeed = new JLabel("MinYSpeed:\r\n");
		lblMinyspeed.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMinyspeed.setBounds(10, 247, 78, 14);
		contentPanel.add(lblMinyspeed);
		
		MinYSpeedTextField = new JTextField();
		MinYSpeedTextField.setColumns(10);
		MinYSpeedTextField.setBounds(110, 245, 68, 20);
		contentPanel.add(MinYSpeedTextField);
		
		JLabel lblMinxspeed = new JLabel("MinXSpeed:\r\n");
		lblMinxspeed.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMinxspeed.setBounds(10, 272, 78, 14);
		contentPanel.add(lblMinxspeed);
		
		MinXSpeedTextField = new JTextField();
		MinXSpeedTextField.setColumns(10);
		MinXSpeedTextField.setBounds(110, 270, 68, 20);
		contentPanel.add(MinXSpeedTextField);
		
		JLabel lblSpeedIncreaseFactor = new JLabel("Increase Factor:\r\n");
		lblSpeedIncreaseFactor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSpeedIncreaseFactor.setBounds(10, 297, 97, 14);
		contentPanel.add(lblSpeedIncreaseFactor);
		
		
		SpeedIncreaseFactorTextField = new JTextField();
		SpeedIncreaseFactorTextField.setColumns(10);
		SpeedIncreaseFactorTextField.setBounds(110, 295, 68, 20);
		contentPanel.add(SpeedIncreaseFactorTextField);
		
		//Paddle parameters
		
		JLabel lblPaddleParameters = new JLabel("Paddle Parameters\r\n");
		lblPaddleParameters.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPaddleParameters.setBounds(219, 215, 134, 14);
		contentPanel.add(lblPaddleParameters);
		
		JLabel lblMinlength = new JLabel("MinLength: \r\n");
		lblMinlength.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMinlength.setBounds(219, 247, 78, 14);
		contentPanel.add(lblMinlength);
		
		MinPaddleLengthTextField = new JTextField();
		MinPaddleLengthTextField.setColumns(10);
		MinPaddleLengthTextField.setBounds(321, 245, 112, 20);
		contentPanel.add(MinPaddleLengthTextField);
		
		JLabel lblMaxlength = new JLabel("MaxLength:");
		lblMaxlength.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMaxlength.setBounds(219, 272, 78, 14);
		contentPanel.add(lblMaxlength);
		
		MaxPaddleLengthTextField = new JTextField();
		MaxPaddleLengthTextField.setColumns(10);
		MaxPaddleLengthTextField.setBounds(321, 270, 112, 20);
		contentPanel.add(MaxPaddleLengthTextField);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(321, 125, 112, 20);
		contentPanel.add(textField);
		
		//Separator 3
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 331, 464, 14);
		contentPanel.add(separator);
		
		//Apply Game Settings
		
		JButton btnApplyGameSettings = new JButton("Apply Game Settings");
		btnApplyGameSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnApplyGameSettings.setBounds(175, 356, 136, 23);
		contentPanel.add(btnApplyGameSettings);
	}
}
