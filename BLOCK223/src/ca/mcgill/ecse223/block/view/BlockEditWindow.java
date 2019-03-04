package ca.mcgill.ecse223.block.view;

import ca.mcgill.ecse.btms.controller.BtmsController;
import ca.mcgill.ecse.btms.controller.TODriver;
import ca.mcgill.ecse.btms.controller.TORoute;
import ca.mcgill.ecse.btms.controller.TORouteAssignment;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.TOBlock;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.controller.TOGridCell;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.Box;
import javax.swing.JSeparator;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.SystemColor;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JInternalFrame;
import javax.swing.JSlider;
import java.awt.ComponentOrientation;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class BlockEditWindow extends JFrame {

	private JPanel contentPane;
	private JButton createBlockButton;
	private JButton deleteBlockButton;
	private JComboBox toBlockComboBox;
	private JButton gameSettingsButton;
	private JComboBox toGridCellComboBox;
	private JComboBox levelComboBox;
	private JButton saveButton;
	private JComboBox yPositionComboBox;
	private JComboBox xPositionComboBox;
	private JButton removeBlockButton;
	private JButton moveBlockButton;
	private JButton positionBlockButton;
	private JButton updateBlockButton;
	private JSlider blockBlueSlider;
	private JSlider blockGreenSlider;
	private JPanel testBlock;
	private JSlider blockRedSlider;

	// data elements
	private String error = "";
	//blocks
	private HashMap<Integer, TOBlock> blocks;
	//grid cells
	private HashMap<Integer, TOGridCell> gridCells;
	//games
	private HashMap<Integer, TOGame> games;
	private JSlider pointsSlider;
	private HashMap<Integer, Integer> levels;
	private HashMap<Integer, Integer> gridHorizontalPosition;
	private HashMap<Integer, Integer> gridVerticalPosition;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BlockEditWindow frame = new BlockEditWindow();
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
	public BlockEditWindow() {
		setTitle("BLOCK CREATOR 9000");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 574);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnUser = new JMenu("User");
		menuBar.add(mnUser);

		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				mntmLogOutActionPerformed(evt);
			}
		});
		mnUser.add(mntmLogout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		createBlockButton = new JButton("Create Block");
		createBlockButton.setBounds(432, 191, 123, 23);
		createBlockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "helo");
				//addBlockperformed();
			}
		});

		
		JPanel panel = new JPanel();
		panel.setBounds(30, 30, 390, 390);
		panel.setAlignmentX(0.0f);
		panel.setAlignmentY(0.0f);
		panel.setBackground(Color.WHITE);
		
		deleteBlockButton = new JButton("Delete Block");
		deleteBlockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		deleteBlockButton.setBounds(563, 257, 123, 23);

		JLabel lblListOfBlocks = new JLabel("List of Game Blocks:\r\n");
		lblListOfBlocks.setBounds(432, 227, 139, 14);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 10, 20, 20);
		panel_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setPreferredSize(new Dimension(20, 20));
		panel.add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setPreferredSize(new Dimension(20, 20));
		panel_2.setBounds(35, 10, 20, 20);
		panel.add(panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);

		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(20, 20));
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(60, 10, 20, 20);
		panel.add(panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);

		JPanel panel_4 = new JPanel();
		panel_4.setPreferredSize(new Dimension(20, 20));
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(85, 10, 20, 20);
		panel.add(panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{0, 0};
		gbl_panel_4.rowHeights = new int[]{0, 0};
		gbl_panel_4.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);

		JPanel panel_5 = new JPanel();
		panel_5.setPreferredSize(new Dimension(20, 20));
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBounds(110, 10, 20, 20);
		panel.add(panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{0, 0};
		gbl_panel_5.rowHeights = new int[]{0, 0};
		gbl_panel_5.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_5.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);

		JPanel panel_6 = new JPanel();
		panel_6.setPreferredSize(new Dimension(20, 20));
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setBounds(135, 10, 20, 20);
		panel.add(panel_6);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[]{0, 0};
		gbl_panel_6.rowHeights = new int[]{0, 0};
		gbl_panel_6.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_6.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_6.setLayout(gbl_panel_6);

		JPanel panel_7 = new JPanel();
		panel_7.setPreferredSize(new Dimension(20, 20));
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7.setBounds(160, 10, 20, 20);
		panel.add(panel_7);
		GridBagLayout gbl_panel_7 = new GridBagLayout();
		gbl_panel_7.columnWidths = new int[]{0, 0};
		gbl_panel_7.rowHeights = new int[]{0, 0};
		gbl_panel_7.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_7.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_7.setLayout(gbl_panel_7);

		JPanel panel_8 = new JPanel();
		panel_8.setPreferredSize(new Dimension(20, 20));
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8.setAlignmentX(0.0f);
		panel_8.setBounds(185, 10, 20, 20);
		panel.add(panel_8);
		GridBagLayout gbl_panel_8 = new GridBagLayout();
		gbl_panel_8.columnWidths = new int[]{0, 0};
		gbl_panel_8.rowHeights = new int[]{0, 0};
		gbl_panel_8.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_8.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_8.setLayout(gbl_panel_8);

		JPanel panel_9 = new JPanel();
		panel_9.setPreferredSize(new Dimension(20, 20));
		panel_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_9.setBounds(210, 10, 20, 20);
		panel.add(panel_9);
		GridBagLayout gbl_panel_9 = new GridBagLayout();
		gbl_panel_9.columnWidths = new int[]{0, 0};
		gbl_panel_9.rowHeights = new int[]{0, 0};
		gbl_panel_9.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_9.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_9.setLayout(gbl_panel_9);

		JPanel panel_10 = new JPanel();
		panel_10.setPreferredSize(new Dimension(20, 20));
		panel_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_10.setBounds(235, 10, 20, 20);
		panel.add(panel_10);
		GridBagLayout gbl_panel_10 = new GridBagLayout();
		gbl_panel_10.columnWidths = new int[]{0, 0};
		gbl_panel_10.rowHeights = new int[]{0, 0};
		gbl_panel_10.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_10.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_10.setLayout(gbl_panel_10);

		JPanel panel_11 = new JPanel();
		panel_11.setPreferredSize(new Dimension(20, 20));
		panel_11.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_11.setBounds(260, 10, 20, 20);
		panel.add(panel_11);
		GridBagLayout gbl_panel_11 = new GridBagLayout();
		gbl_panel_11.columnWidths = new int[]{0, 0};
		gbl_panel_11.rowHeights = new int[]{0, 0};
		gbl_panel_11.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_11.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_11.setLayout(gbl_panel_11);

		JPanel panel_12 = new JPanel();
		panel_12.setPreferredSize(new Dimension(20, 20));
		panel_12.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_12.setBounds(285, 10, 20, 20);
		panel.add(panel_12);
		GridBagLayout gbl_panel_12 = new GridBagLayout();
		gbl_panel_12.columnWidths = new int[]{0, 0};
		gbl_panel_12.rowHeights = new int[]{0, 0};
		gbl_panel_12.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_12.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_12.setLayout(gbl_panel_12);

		JPanel panel_13 = new JPanel();
		panel_13.setPreferredSize(new Dimension(20, 20));
		panel_13.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_13.setBounds(310, 10, 20, 20);
		panel.add(panel_13);
		GridBagLayout gbl_panel_13 = new GridBagLayout();
		gbl_panel_13.columnWidths = new int[]{0, 0};
		gbl_panel_13.rowHeights = new int[]{0, 0};
		gbl_panel_13.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_13.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_13.setLayout(gbl_panel_13);

		JPanel panel_14 = new JPanel();
		panel_14.setPreferredSize(new Dimension(20, 20));
		panel_14.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_14.setBounds(335, 10, 20, 20);
		panel.add(panel_14);
		GridBagLayout gbl_panel_14 = new GridBagLayout();
		gbl_panel_14.columnWidths = new int[]{0, 0};
		gbl_panel_14.rowHeights = new int[]{0, 0};
		gbl_panel_14.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_14.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_14.setLayout(gbl_panel_14);

		JPanel panel_15 = new JPanel();
		panel_15.setPreferredSize(new Dimension(20, 20));
		panel_15.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_15.setBounds(360, 10, 20, 20);
		panel.add(panel_15);
		GridBagLayout gbl_panel_15 = new GridBagLayout();
		gbl_panel_15.columnWidths = new int[]{0, 0};
		gbl_panel_15.rowHeights = new int[]{0, 0};
		gbl_panel_15.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_15.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_15.setLayout(gbl_panel_15);

		JPanel panel_16 = new JPanel();
		panel_16.setPreferredSize(new Dimension(20, 20));
		panel_16.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_16.setAlignmentX(0.0f);
		panel_16.setBounds(10, 32, 20, 20);
		panel.add(panel_16);
		GridBagLayout gbl_panel_16 = new GridBagLayout();
		gbl_panel_16.columnWidths = new int[]{0, 0};
		gbl_panel_16.rowHeights = new int[]{0, 0};
		gbl_panel_16.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_16.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_16.setLayout(gbl_panel_16);

		JPanel panel_17 = new JPanel();
		panel_17.setPreferredSize(new Dimension(20, 20));
		panel_17.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_17.setAlignmentX(0.0f);
		panel_17.setBounds(10, 54, 20, 20);
		panel.add(panel_17);
		GridBagLayout gbl_panel_17 = new GridBagLayout();
		gbl_panel_17.columnWidths = new int[]{0, 0};
		gbl_panel_17.rowHeights = new int[]{0, 0};
		gbl_panel_17.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_17.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_17.setLayout(gbl_panel_17);

		JPanel panel_18 = new JPanel();
		panel_18.setPreferredSize(new Dimension(20, 20));
		panel_18.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_18.setAlignmentX(0.0f);
		panel_18.setBounds(10, 76, 20, 20);
		panel.add(panel_18);
		GridBagLayout gbl_panel_18 = new GridBagLayout();
		gbl_panel_18.columnWidths = new int[]{0, 0};
		gbl_panel_18.rowHeights = new int[]{0, 0};
		gbl_panel_18.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_18.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_18.setLayout(gbl_panel_18);

		JPanel panel_19 = new JPanel();
		panel_19.setPreferredSize(new Dimension(20, 20));
		panel_19.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_19.setAlignmentX(0.0f);
		panel_19.setBounds(10, 98, 20, 20);
		panel.add(panel_19);
		GridBagLayout gbl_panel_19 = new GridBagLayout();
		gbl_panel_19.columnWidths = new int[]{0, 0};
		gbl_panel_19.rowHeights = new int[]{0, 0};
		gbl_panel_19.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_19.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_19.setLayout(gbl_panel_19);

		JPanel panel_20 = new JPanel();
		panel_20.setPreferredSize(new Dimension(20, 20));
		panel_20.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_20.setAlignmentX(0.0f);
		panel_20.setBounds(10, 120, 20, 20);
		panel.add(panel_20);
		GridBagLayout gbl_panel_20 = new GridBagLayout();
		gbl_panel_20.columnWidths = new int[]{0, 0};
		gbl_panel_20.rowHeights = new int[]{0, 0};
		gbl_panel_20.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_20.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_20.setLayout(gbl_panel_20);

		JPanel panel_21 = new JPanel();
		panel_21.setPreferredSize(new Dimension(20, 20));
		panel_21.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_21.setAlignmentX(0.0f);
		panel_21.setBounds(10, 142, 20, 20);
		panel.add(panel_21);
		GridBagLayout gbl_panel_21 = new GridBagLayout();
		gbl_panel_21.columnWidths = new int[]{0, 0};
		gbl_panel_21.rowHeights = new int[]{0, 0};
		gbl_panel_21.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_21.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_21.setLayout(gbl_panel_21);

		JPanel panel_22 = new JPanel();
		panel_22.setPreferredSize(new Dimension(20, 20));
		panel_22.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_22.setAlignmentX(0.0f);
		panel_22.setBounds(10, 164, 20, 20);
		panel.add(panel_22);
		GridBagLayout gbl_panel_22 = new GridBagLayout();
		gbl_panel_22.columnWidths = new int[]{0, 0};
		gbl_panel_22.rowHeights = new int[]{0, 0};
		gbl_panel_22.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_22.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_22.setLayout(gbl_panel_22);

		JPanel panel_23 = new JPanel();
		panel_23.setPreferredSize(new Dimension(20, 20));
		panel_23.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_23.setBounds(35, 32, 20, 20);
		panel.add(panel_23);
		GridBagLayout gbl_panel_23 = new GridBagLayout();
		gbl_panel_23.columnWidths = new int[]{0, 0};
		gbl_panel_23.rowHeights = new int[]{0, 0};
		gbl_panel_23.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_23.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_23.setLayout(gbl_panel_23);

		JPanel panel_24 = new JPanel();
		panel_24.setPreferredSize(new Dimension(20, 20));
		panel_24.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_24.setBounds(60, 32, 20, 20);
		panel.add(panel_24);
		GridBagLayout gbl_panel_24 = new GridBagLayout();
		gbl_panel_24.columnWidths = new int[]{0, 0};
		gbl_panel_24.rowHeights = new int[]{0, 0};
		gbl_panel_24.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_24.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_24.setLayout(gbl_panel_24);

		JPanel panel_25 = new JPanel();
		panel_25.setPreferredSize(new Dimension(20, 20));
		panel_25.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_25.setBounds(85, 32, 20, 20);
		panel.add(panel_25);
		GridBagLayout gbl_panel_25 = new GridBagLayout();
		gbl_panel_25.columnWidths = new int[]{0, 0};
		gbl_panel_25.rowHeights = new int[]{0, 0};
		gbl_panel_25.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_25.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_25.setLayout(gbl_panel_25);

		JPanel panel_26 = new JPanel();
		panel_26.setPreferredSize(new Dimension(20, 20));
		panel_26.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_26.setBounds(110, 32, 20, 20);
		panel.add(panel_26);
		GridBagLayout gbl_panel_26 = new GridBagLayout();
		gbl_panel_26.columnWidths = new int[]{0, 0};
		gbl_panel_26.rowHeights = new int[]{0, 0};
		gbl_panel_26.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_26.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_26.setLayout(gbl_panel_26);

		JPanel panel_27 = new JPanel();
		panel_27.setPreferredSize(new Dimension(20, 20));
		panel_27.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_27.setBounds(135, 32, 20, 20);
		panel.add(panel_27);
		GridBagLayout gbl_panel_27 = new GridBagLayout();
		gbl_panel_27.columnWidths = new int[]{0, 0};
		gbl_panel_27.rowHeights = new int[]{0, 0};
		gbl_panel_27.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_27.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_27.setLayout(gbl_panel_27);

		JPanel panel_28 = new JPanel();
		panel_28.setPreferredSize(new Dimension(20, 20));
		panel_28.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_28.setBounds(160, 32, 20, 20);
		panel.add(panel_28);
		GridBagLayout gbl_panel_28 = new GridBagLayout();
		gbl_panel_28.columnWidths = new int[]{0, 0};
		gbl_panel_28.rowHeights = new int[]{0, 0};
		gbl_panel_28.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_28.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_28.setLayout(gbl_panel_28);

		JPanel panel_29 = new JPanel();
		panel_29.setPreferredSize(new Dimension(20, 20));
		panel_29.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_29.setAlignmentX(0.0f);
		panel_29.setBounds(185, 32, 20, 20);
		panel.add(panel_29);
		GridBagLayout gbl_panel_29 = new GridBagLayout();
		gbl_panel_29.columnWidths = new int[]{0, 0};
		gbl_panel_29.rowHeights = new int[]{0, 0};
		gbl_panel_29.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_29.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_29.setLayout(gbl_panel_29);

		JPanel panel_30 = new JPanel();
		panel_30.setPreferredSize(new Dimension(20, 20));
		panel_30.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_30.setBounds(210, 32, 20, 20);
		panel.add(panel_30);
		GridBagLayout gbl_panel_30 = new GridBagLayout();
		gbl_panel_30.columnWidths = new int[]{0, 0};
		gbl_panel_30.rowHeights = new int[]{0, 0};
		gbl_panel_30.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_30.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_30.setLayout(gbl_panel_30);

		JPanel panel_31 = new JPanel();
		panel_31.setPreferredSize(new Dimension(20, 20));
		panel_31.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_31.setBounds(235, 32, 20, 20);
		panel.add(panel_31);
		GridBagLayout gbl_panel_31 = new GridBagLayout();
		gbl_panel_31.columnWidths = new int[]{0, 0};
		gbl_panel_31.rowHeights = new int[]{0, 0};
		gbl_panel_31.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_31.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_31.setLayout(gbl_panel_31);

		JPanel panel_32 = new JPanel();
		panel_32.setPreferredSize(new Dimension(20, 20));
		panel_32.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_32.setBounds(260, 32, 20, 20);
		panel.add(panel_32);
		GridBagLayout gbl_panel_32 = new GridBagLayout();
		gbl_panel_32.columnWidths = new int[]{0, 0};
		gbl_panel_32.rowHeights = new int[]{0, 0};
		gbl_panel_32.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_32.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_32.setLayout(gbl_panel_32);

		JPanel panel_33 = new JPanel();
		panel_33.setPreferredSize(new Dimension(20, 20));
		panel_33.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_33.setBounds(285, 32, 20, 20);
		panel.add(panel_33);
		GridBagLayout gbl_panel_33 = new GridBagLayout();
		gbl_panel_33.columnWidths = new int[]{0, 0};
		gbl_panel_33.rowHeights = new int[]{0, 0};
		gbl_panel_33.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_33.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_33.setLayout(gbl_panel_33);

		JPanel panel_34 = new JPanel();
		panel_34.setPreferredSize(new Dimension(20, 20));
		panel_34.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_34.setBounds(310, 32, 20, 20);
		panel.add(panel_34);
		GridBagLayout gbl_panel_34 = new GridBagLayout();
		gbl_panel_34.columnWidths = new int[]{0, 0};
		gbl_panel_34.rowHeights = new int[]{0, 0};
		gbl_panel_34.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_34.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_34.setLayout(gbl_panel_34);

		JPanel panel_35 = new JPanel();
		panel_35.setPreferredSize(new Dimension(20, 20));
		panel_35.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_35.setBounds(335, 32, 20, 20);
		panel.add(panel_35);
		GridBagLayout gbl_panel_35 = new GridBagLayout();
		gbl_panel_35.columnWidths = new int[]{0, 0};
		gbl_panel_35.rowHeights = new int[]{0, 0};
		gbl_panel_35.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_35.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_35.setLayout(gbl_panel_35);

		JPanel panel_36 = new JPanel();
		panel_36.setPreferredSize(new Dimension(20, 20));
		panel_36.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_36.setBounds(360, 32, 20, 20);
		panel.add(panel_36);
		GridBagLayout gbl_panel_36 = new GridBagLayout();
		gbl_panel_36.columnWidths = new int[]{0, 0};
		gbl_panel_36.rowHeights = new int[]{0, 0};
		gbl_panel_36.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_36.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_36.setLayout(gbl_panel_36);

		JPanel panel_37 = new JPanel();
		panel_37.setPreferredSize(new Dimension(20, 20));
		panel_37.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_37.setBounds(35, 54, 20, 20);
		panel.add(panel_37);
		GridBagLayout gbl_panel_37 = new GridBagLayout();
		gbl_panel_37.columnWidths = new int[]{0, 0};
		gbl_panel_37.rowHeights = new int[]{0, 0};
		gbl_panel_37.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_37.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_37.setLayout(gbl_panel_37);

		JPanel panel_38 = new JPanel();
		panel_38.setPreferredSize(new Dimension(20, 20));
		panel_38.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_38.setBounds(60, 54, 20, 20);
		panel.add(panel_38);
		GridBagLayout gbl_panel_38 = new GridBagLayout();
		gbl_panel_38.columnWidths = new int[]{0, 0};
		gbl_panel_38.rowHeights = new int[]{0, 0};
		gbl_panel_38.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_38.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_38.setLayout(gbl_panel_38);

		JPanel panel_39 = new JPanel();
		panel_39.setPreferredSize(new Dimension(20, 20));
		panel_39.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_39.setBounds(85, 54, 20, 20);
		panel.add(panel_39);
		GridBagLayout gbl_panel_39 = new GridBagLayout();
		gbl_panel_39.columnWidths = new int[]{0, 0};
		gbl_panel_39.rowHeights = new int[]{0, 0};
		gbl_panel_39.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_39.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_39.setLayout(gbl_panel_39);

		JPanel panel_40 = new JPanel();
		panel_40.setPreferredSize(new Dimension(20, 20));
		panel_40.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_40.setBounds(110, 54, 20, 20);
		panel.add(panel_40);
		GridBagLayout gbl_panel_40 = new GridBagLayout();
		gbl_panel_40.columnWidths = new int[]{0, 0};
		gbl_panel_40.rowHeights = new int[]{0, 0};
		gbl_panel_40.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_40.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_40.setLayout(gbl_panel_40);

		JPanel panel_41 = new JPanel();
		panel_41.setPreferredSize(new Dimension(20, 20));
		panel_41.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_41.setBounds(135, 54, 20, 20);
		panel.add(panel_41);
		GridBagLayout gbl_panel_41 = new GridBagLayout();
		gbl_panel_41.columnWidths = new int[]{0, 0};
		gbl_panel_41.rowHeights = new int[]{0, 0};
		gbl_panel_41.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_41.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_41.setLayout(gbl_panel_41);

		JPanel panel_42 = new JPanel();
		panel_42.setPreferredSize(new Dimension(20, 20));
		panel_42.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_42.setBounds(160, 54, 20, 20);
		panel.add(panel_42);
		GridBagLayout gbl_panel_42 = new GridBagLayout();
		gbl_panel_42.columnWidths = new int[]{0, 0};
		gbl_panel_42.rowHeights = new int[]{0, 0};
		gbl_panel_42.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_42.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_42.setLayout(gbl_panel_42);

		JPanel panel_43 = new JPanel();
		panel_43.setPreferredSize(new Dimension(20, 20));
		panel_43.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_43.setAlignmentX(0.0f);
		panel_43.setBounds(185, 54, 20, 20);
		panel.add(panel_43);
		GridBagLayout gbl_panel_43 = new GridBagLayout();
		gbl_panel_43.columnWidths = new int[]{0, 0};
		gbl_panel_43.rowHeights = new int[]{0, 0};
		gbl_panel_43.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_43.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_43.setLayout(gbl_panel_43);

		JPanel panel_44 = new JPanel();
		panel_44.setPreferredSize(new Dimension(20, 20));
		panel_44.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_44.setBounds(210, 54, 20, 20);
		panel.add(panel_44);
		GridBagLayout gbl_panel_44 = new GridBagLayout();
		gbl_panel_44.columnWidths = new int[]{0, 0};
		gbl_panel_44.rowHeights = new int[]{0, 0};
		gbl_panel_44.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_44.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_44.setLayout(gbl_panel_44);

		JPanel panel_45 = new JPanel();
		panel_45.setPreferredSize(new Dimension(20, 20));
		panel_45.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_45.setBounds(235, 54, 20, 20);
		panel.add(panel_45);
		GridBagLayout gbl_panel_45 = new GridBagLayout();
		gbl_panel_45.columnWidths = new int[]{0, 0};
		gbl_panel_45.rowHeights = new int[]{0, 0};
		gbl_panel_45.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_45.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_45.setLayout(gbl_panel_45);

		JPanel panel_46 = new JPanel();
		panel_46.setPreferredSize(new Dimension(20, 20));
		panel_46.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_46.setBounds(260, 54, 20, 20);
		panel.add(panel_46);
		GridBagLayout gbl_panel_46 = new GridBagLayout();
		gbl_panel_46.columnWidths = new int[]{0, 0};
		gbl_panel_46.rowHeights = new int[]{0, 0};
		gbl_panel_46.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_46.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_46.setLayout(gbl_panel_46);

		JPanel panel_47 = new JPanel();
		panel_47.setPreferredSize(new Dimension(20, 20));
		panel_47.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_47.setBounds(285, 54, 20, 20);
		panel.add(panel_47);
		GridBagLayout gbl_panel_47 = new GridBagLayout();
		gbl_panel_47.columnWidths = new int[]{0, 0};
		gbl_panel_47.rowHeights = new int[]{0, 0};
		gbl_panel_47.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_47.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_47.setLayout(gbl_panel_47);

		JPanel panel_48 = new JPanel();
		panel_48.setPreferredSize(new Dimension(20, 20));
		panel_48.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_48.setBounds(310, 54, 20, 20);
		panel.add(panel_48);
		GridBagLayout gbl_panel_48 = new GridBagLayout();
		gbl_panel_48.columnWidths = new int[]{0, 0};
		gbl_panel_48.rowHeights = new int[]{0, 0};
		gbl_panel_48.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_48.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_48.setLayout(gbl_panel_48);

		JPanel panel_49 = new JPanel();
		panel_49.setPreferredSize(new Dimension(20, 20));
		panel_49.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_49.setBounds(335, 54, 20, 20);
		panel.add(panel_49);
		GridBagLayout gbl_panel_49 = new GridBagLayout();
		gbl_panel_49.columnWidths = new int[]{0, 0};
		gbl_panel_49.rowHeights = new int[]{0, 0};
		gbl_panel_49.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_49.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_49.setLayout(gbl_panel_49);

		JPanel panel_50 = new JPanel();
		panel_50.setPreferredSize(new Dimension(20, 20));
		panel_50.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_50.setBounds(360, 54, 20, 20);
		panel.add(panel_50);
		GridBagLayout gbl_panel_50 = new GridBagLayout();
		gbl_panel_50.columnWidths = new int[]{0, 0};
		gbl_panel_50.rowHeights = new int[]{0, 0};
		gbl_panel_50.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_50.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_50.setLayout(gbl_panel_50);

		JPanel panel_51 = new JPanel();
		panel_51.setPreferredSize(new Dimension(20, 20));
		panel_51.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_51.setBounds(35, 76, 20, 20);
		panel.add(panel_51);
		GridBagLayout gbl_panel_51 = new GridBagLayout();
		gbl_panel_51.columnWidths = new int[]{0, 0};
		gbl_panel_51.rowHeights = new int[]{0, 0};
		gbl_panel_51.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_51.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_51.setLayout(gbl_panel_51);

		JPanel panel_52 = new JPanel();
		panel_52.setPreferredSize(new Dimension(20, 20));
		panel_52.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_52.setBounds(60, 76, 20, 20);
		panel.add(panel_52);
		GridBagLayout gbl_panel_52 = new GridBagLayout();
		gbl_panel_52.columnWidths = new int[]{0, 0};
		gbl_panel_52.rowHeights = new int[]{0, 0};
		gbl_panel_52.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_52.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_52.setLayout(gbl_panel_52);

		JPanel panel_53 = new JPanel();
		panel_53.setPreferredSize(new Dimension(20, 20));
		panel_53.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_53.setBounds(85, 76, 20, 20);
		panel.add(panel_53);
		GridBagLayout gbl_panel_53 = new GridBagLayout();
		gbl_panel_53.columnWidths = new int[]{0, 0};
		gbl_panel_53.rowHeights = new int[]{0, 0};
		gbl_panel_53.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_53.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_53.setLayout(gbl_panel_53);

		JPanel panel_54 = new JPanel();
		panel_54.setPreferredSize(new Dimension(20, 20));
		panel_54.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_54.setBounds(110, 76, 20, 20);
		panel.add(panel_54);
		GridBagLayout gbl_panel_54 = new GridBagLayout();
		gbl_panel_54.columnWidths = new int[]{0, 0};
		gbl_panel_54.rowHeights = new int[]{0, 0};
		gbl_panel_54.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_54.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_54.setLayout(gbl_panel_54);

		JPanel panel_55 = new JPanel();
		panel_55.setPreferredSize(new Dimension(20, 20));
		panel_55.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_55.setBounds(135, 76, 20, 20);
		panel.add(panel_55);
		GridBagLayout gbl_panel_55 = new GridBagLayout();
		gbl_panel_55.columnWidths = new int[]{0, 0};
		gbl_panel_55.rowHeights = new int[]{0, 0};
		gbl_panel_55.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_55.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_55.setLayout(gbl_panel_55);

		JPanel panel_56 = new JPanel();
		panel_56.setPreferredSize(new Dimension(20, 20));
		panel_56.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_56.setBounds(160, 76, 20, 20);
		panel.add(panel_56);
		GridBagLayout gbl_panel_56 = new GridBagLayout();
		gbl_panel_56.columnWidths = new int[]{0, 0};
		gbl_panel_56.rowHeights = new int[]{0, 0};
		gbl_panel_56.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_56.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_56.setLayout(gbl_panel_56);

		JPanel panel_57 = new JPanel();
		panel_57.setPreferredSize(new Dimension(20, 20));
		panel_57.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_57.setAlignmentX(0.0f);
		panel_57.setBounds(185, 76, 20, 20);
		panel.add(panel_57);
		GridBagLayout gbl_panel_57 = new GridBagLayout();
		gbl_panel_57.columnWidths = new int[]{0, 0};
		gbl_panel_57.rowHeights = new int[]{0, 0};
		gbl_panel_57.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_57.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_57.setLayout(gbl_panel_57);

		JPanel panel_58 = new JPanel();
		panel_58.setPreferredSize(new Dimension(20, 20));
		panel_58.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_58.setBounds(210, 76, 20, 20);
		panel.add(panel_58);
		GridBagLayout gbl_panel_58 = new GridBagLayout();
		gbl_panel_58.columnWidths = new int[]{0, 0};
		gbl_panel_58.rowHeights = new int[]{0, 0};
		gbl_panel_58.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_58.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_58.setLayout(gbl_panel_58);

		JPanel panel_59 = new JPanel();
		panel_59.setPreferredSize(new Dimension(20, 20));
		panel_59.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_59.setBounds(235, 76, 20, 20);
		panel.add(panel_59);
		GridBagLayout gbl_panel_59 = new GridBagLayout();
		gbl_panel_59.columnWidths = new int[]{0, 0};
		gbl_panel_59.rowHeights = new int[]{0, 0};
		gbl_panel_59.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_59.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_59.setLayout(gbl_panel_59);

		JPanel panel_60 = new JPanel();
		panel_60.setPreferredSize(new Dimension(20, 20));
		panel_60.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_60.setBounds(260, 76, 20, 20);
		panel.add(panel_60);
		GridBagLayout gbl_panel_60 = new GridBagLayout();
		gbl_panel_60.columnWidths = new int[]{0, 0};
		gbl_panel_60.rowHeights = new int[]{0, 0};
		gbl_panel_60.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_60.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_60.setLayout(gbl_panel_60);

		JPanel panel_61 = new JPanel();
		panel_61.setPreferredSize(new Dimension(20, 20));
		panel_61.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_61.setBounds(285, 76, 20, 20);
		panel.add(panel_61);
		GridBagLayout gbl_panel_61 = new GridBagLayout();
		gbl_panel_61.columnWidths = new int[]{0, 0};
		gbl_panel_61.rowHeights = new int[]{0, 0};
		gbl_panel_61.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_61.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_61.setLayout(gbl_panel_61);

		JPanel panel_62 = new JPanel();
		panel_62.setPreferredSize(new Dimension(20, 20));
		panel_62.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_62.setBounds(310, 76, 20, 20);
		panel.add(panel_62);
		GridBagLayout gbl_panel_62 = new GridBagLayout();
		gbl_panel_62.columnWidths = new int[]{0, 0};
		gbl_panel_62.rowHeights = new int[]{0, 0};
		gbl_panel_62.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_62.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_62.setLayout(gbl_panel_62);

		JPanel panel_63 = new JPanel();
		panel_63.setPreferredSize(new Dimension(20, 20));
		panel_63.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_63.setBounds(335, 76, 20, 20);
		panel.add(panel_63);
		GridBagLayout gbl_panel_63 = new GridBagLayout();
		gbl_panel_63.columnWidths = new int[]{0, 0};
		gbl_panel_63.rowHeights = new int[]{0, 0};
		gbl_panel_63.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_63.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_63.setLayout(gbl_panel_63);

		JPanel panel_64 = new JPanel();
		panel_64.setPreferredSize(new Dimension(20, 20));
		panel_64.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_64.setBounds(360, 76, 20, 20);
		panel.add(panel_64);
		GridBagLayout gbl_panel_64 = new GridBagLayout();
		gbl_panel_64.columnWidths = new int[]{0, 0};
		gbl_panel_64.rowHeights = new int[]{0, 0};
		gbl_panel_64.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_64.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_64.setLayout(gbl_panel_64);

		JPanel panel_65 = new JPanel();
		panel_65.setPreferredSize(new Dimension(20, 20));
		panel_65.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_65.setBounds(35, 98, 20, 20);
		panel.add(panel_65);
		GridBagLayout gbl_panel_65 = new GridBagLayout();
		gbl_panel_65.columnWidths = new int[]{0, 0};
		gbl_panel_65.rowHeights = new int[]{0, 0};
		gbl_panel_65.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_65.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_65.setLayout(gbl_panel_65);

		JPanel panel_66 = new JPanel();
		panel_66.setPreferredSize(new Dimension(20, 20));
		panel_66.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_66.setBounds(60, 98, 20, 20);
		panel.add(panel_66);
		GridBagLayout gbl_panel_66 = new GridBagLayout();
		gbl_panel_66.columnWidths = new int[]{0, 0};
		gbl_panel_66.rowHeights = new int[]{0, 0};
		gbl_panel_66.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_66.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_66.setLayout(gbl_panel_66);

		JPanel panel_67 = new JPanel();
		panel_67.setPreferredSize(new Dimension(20, 20));
		panel_67.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_67.setBounds(85, 98, 20, 20);
		panel.add(panel_67);
		GridBagLayout gbl_panel_67 = new GridBagLayout();
		gbl_panel_67.columnWidths = new int[]{0, 0};
		gbl_panel_67.rowHeights = new int[]{0, 0};
		gbl_panel_67.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_67.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_67.setLayout(gbl_panel_67);

		JPanel panel_68 = new JPanel();
		panel_68.setBackground(SystemColor.menu);
		panel_68.setPreferredSize(new Dimension(20, 20));
		panel_68.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_68.setBounds(110, 98, 20, 20);
		panel.add(panel_68);
		GridBagLayout gbl_panel_68 = new GridBagLayout();
		gbl_panel_68.columnWidths = new int[]{0, 0};
		gbl_panel_68.rowHeights = new int[]{0, 0};
		gbl_panel_68.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_68.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_68.setLayout(gbl_panel_68);

		JPanel panel_69 = new JPanel();
		panel_69.setPreferredSize(new Dimension(20, 20));
		panel_69.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_69.setBounds(135, 98, 20, 20);
		panel.add(panel_69);
		GridBagLayout gbl_panel_69 = new GridBagLayout();
		gbl_panel_69.columnWidths = new int[]{0, 0};
		gbl_panel_69.rowHeights = new int[]{0, 0};
		gbl_panel_69.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_69.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_69.setLayout(gbl_panel_69);

		JPanel panel_70 = new JPanel();
		panel_70.setPreferredSize(new Dimension(20, 20));
		panel_70.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_70.setBounds(160, 98, 20, 20);
		panel.add(panel_70);
		GridBagLayout gbl_panel_70 = new GridBagLayout();
		gbl_panel_70.columnWidths = new int[]{0, 0};
		gbl_panel_70.rowHeights = new int[]{0, 0};
		gbl_panel_70.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_70.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_70.setLayout(gbl_panel_70);

		JPanel panel_71 = new JPanel();
		panel_71.setPreferredSize(new Dimension(20, 20));
		panel_71.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_71.setAlignmentX(0.0f);
		panel_71.setBounds(185, 98, 20, 20);
		panel.add(panel_71);
		GridBagLayout gbl_panel_71 = new GridBagLayout();
		gbl_panel_71.columnWidths = new int[]{0, 0};
		gbl_panel_71.rowHeights = new int[]{0, 0};
		gbl_panel_71.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_71.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_71.setLayout(gbl_panel_71);

		JPanel panel_72 = new JPanel();
		panel_72.setPreferredSize(new Dimension(20, 20));
		panel_72.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_72.setBounds(210, 98, 20, 20);
		panel.add(panel_72);
		GridBagLayout gbl_panel_72 = new GridBagLayout();
		gbl_panel_72.columnWidths = new int[]{0, 0};
		gbl_panel_72.rowHeights = new int[]{0, 0};
		gbl_panel_72.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_72.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_72.setLayout(gbl_panel_72);

		JPanel panel_73 = new JPanel();
		panel_73.setPreferredSize(new Dimension(20, 20));
		panel_73.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_73.setBounds(235, 98, 20, 20);
		panel.add(panel_73);
		GridBagLayout gbl_panel_73 = new GridBagLayout();
		gbl_panel_73.columnWidths = new int[]{0, 0};
		gbl_panel_73.rowHeights = new int[]{0, 0};
		gbl_panel_73.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_73.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_73.setLayout(gbl_panel_73);

		JPanel panel_74 = new JPanel();
		panel_74.setPreferredSize(new Dimension(20, 20));
		panel_74.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_74.setBounds(260, 98, 20, 20);
		panel.add(panel_74);
		GridBagLayout gbl_panel_74 = new GridBagLayout();
		gbl_panel_74.columnWidths = new int[]{0, 0};
		gbl_panel_74.rowHeights = new int[]{0, 0};
		gbl_panel_74.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_74.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_74.setLayout(gbl_panel_74);

		JPanel panel_75 = new JPanel();
		panel_75.setPreferredSize(new Dimension(20, 20));
		panel_75.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_75.setBounds(285, 98, 20, 20);
		panel.add(panel_75);
		GridBagLayout gbl_panel_75 = new GridBagLayout();
		gbl_panel_75.columnWidths = new int[]{0, 0};
		gbl_panel_75.rowHeights = new int[]{0, 0};
		gbl_panel_75.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_75.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_75.setLayout(gbl_panel_75);

		JPanel panel_76 = new JPanel();
		panel_76.setPreferredSize(new Dimension(20, 20));
		panel_76.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_76.setBounds(310, 98, 20, 20);
		panel.add(panel_76);
		GridBagLayout gbl_panel_76 = new GridBagLayout();
		gbl_panel_76.columnWidths = new int[]{0, 0};
		gbl_panel_76.rowHeights = new int[]{0, 0};
		gbl_panel_76.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_76.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_76.setLayout(gbl_panel_76);

		JPanel panel_77 = new JPanel();
		panel_77.setPreferredSize(new Dimension(20, 20));
		panel_77.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_77.setBounds(335, 98, 20, 20);
		panel.add(panel_77);
		GridBagLayout gbl_panel_77 = new GridBagLayout();
		gbl_panel_77.columnWidths = new int[]{0, 0};
		gbl_panel_77.rowHeights = new int[]{0, 0};
		gbl_panel_77.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_77.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_77.setLayout(gbl_panel_77);

		JPanel panel_78 = new JPanel();
		panel_78.setPreferredSize(new Dimension(20, 20));
		panel_78.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_78.setBounds(360, 98, 20, 20);
		panel.add(panel_78);
		GridBagLayout gbl_panel_78 = new GridBagLayout();
		gbl_panel_78.columnWidths = new int[]{0, 0};
		gbl_panel_78.rowHeights = new int[]{0, 0};
		gbl_panel_78.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_78.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_78.setLayout(gbl_panel_78);

		JPanel panel_79 = new JPanel();
		panel_79.setPreferredSize(new Dimension(20, 20));
		panel_79.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_79.setBounds(35, 120, 20, 20);
		panel.add(panel_79);
		GridBagLayout gbl_panel_79 = new GridBagLayout();
		gbl_panel_79.columnWidths = new int[]{0, 0};
		gbl_panel_79.rowHeights = new int[]{0, 0};
		gbl_panel_79.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_79.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_79.setLayout(gbl_panel_79);

		JPanel panel_80 = new JPanel();
		panel_80.setPreferredSize(new Dimension(20, 20));
		panel_80.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_80.setBounds(60, 120, 20, 20);
		panel.add(panel_80);
		GridBagLayout gbl_panel_80 = new GridBagLayout();
		gbl_panel_80.columnWidths = new int[]{0, 0};
		gbl_panel_80.rowHeights = new int[]{0, 0};
		gbl_panel_80.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_80.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_80.setLayout(gbl_panel_80);

		JPanel panel_81 = new JPanel();
		panel_81.setPreferredSize(new Dimension(20, 20));
		panel_81.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_81.setBounds(85, 120, 20, 20);
		panel.add(panel_81);
		GridBagLayout gbl_panel_81 = new GridBagLayout();
		gbl_panel_81.columnWidths = new int[]{0, 0};
		gbl_panel_81.rowHeights = new int[]{0, 0};
		gbl_panel_81.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_81.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_81.setLayout(gbl_panel_81);

		JPanel panel_82 = new JPanel();
		panel_82.setPreferredSize(new Dimension(20, 20));
		panel_82.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_82.setBounds(110, 120, 20, 20);
		panel.add(panel_82);
		GridBagLayout gbl_panel_82 = new GridBagLayout();
		gbl_panel_82.columnWidths = new int[]{0, 0};
		gbl_panel_82.rowHeights = new int[]{0, 0};
		gbl_panel_82.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_82.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_82.setLayout(gbl_panel_82);

		JPanel panel_83 = new JPanel();
		panel_83.setPreferredSize(new Dimension(20, 20));
		panel_83.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_83.setBounds(135, 120, 20, 20);
		panel.add(panel_83);
		GridBagLayout gbl_panel_83 = new GridBagLayout();
		gbl_panel_83.columnWidths = new int[]{0, 0};
		gbl_panel_83.rowHeights = new int[]{0, 0};
		gbl_panel_83.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_83.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_83.setLayout(gbl_panel_83);

		JPanel panel_84 = new JPanel();
		panel_84.setPreferredSize(new Dimension(20, 20));
		panel_84.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_84.setBounds(160, 120, 20, 20);
		panel.add(panel_84);
		GridBagLayout gbl_panel_84 = new GridBagLayout();
		gbl_panel_84.columnWidths = new int[]{0, 0};
		gbl_panel_84.rowHeights = new int[]{0, 0};
		gbl_panel_84.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_84.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_84.setLayout(gbl_panel_84);

		JPanel panel_85 = new JPanel();
		panel_85.setPreferredSize(new Dimension(20, 20));
		panel_85.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_85.setAlignmentX(0.0f);
		panel_85.setBounds(185, 120, 20, 20);
		panel.add(panel_85);
		GridBagLayout gbl_panel_85 = new GridBagLayout();
		gbl_panel_85.columnWidths = new int[]{0, 0};
		gbl_panel_85.rowHeights = new int[]{0, 0};
		gbl_panel_85.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_85.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_85.setLayout(gbl_panel_85);

		JPanel panel_86 = new JPanel();
		panel_86.setPreferredSize(new Dimension(20, 20));
		panel_86.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_86.setBounds(210, 120, 20, 20);
		panel.add(panel_86);
		GridBagLayout gbl_panel_86 = new GridBagLayout();
		gbl_panel_86.columnWidths = new int[]{0, 0};
		gbl_panel_86.rowHeights = new int[]{0, 0};
		gbl_panel_86.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_86.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_86.setLayout(gbl_panel_86);

		JPanel panel_87 = new JPanel();
		panel_87.setPreferredSize(new Dimension(20, 20));
		panel_87.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_87.setBounds(235, 120, 20, 20);
		panel.add(panel_87);
		GridBagLayout gbl_panel_87 = new GridBagLayout();
		gbl_panel_87.columnWidths = new int[]{0, 0};
		gbl_panel_87.rowHeights = new int[]{0, 0};
		gbl_panel_87.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_87.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_87.setLayout(gbl_panel_87);

		JPanel panel_88 = new JPanel();
		panel_88.setPreferredSize(new Dimension(20, 20));
		panel_88.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_88.setBounds(260, 120, 20, 20);
		panel.add(panel_88);
		GridBagLayout gbl_panel_88 = new GridBagLayout();
		gbl_panel_88.columnWidths = new int[]{0, 0};
		gbl_panel_88.rowHeights = new int[]{0, 0};
		gbl_panel_88.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_88.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_88.setLayout(gbl_panel_88);

		JPanel panel_89 = new JPanel();
		panel_89.setPreferredSize(new Dimension(20, 20));
		panel_89.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_89.setBounds(285, 120, 20, 20);
		panel.add(panel_89);
		GridBagLayout gbl_panel_89 = new GridBagLayout();
		gbl_panel_89.columnWidths = new int[]{0, 0};
		gbl_panel_89.rowHeights = new int[]{0, 0};
		gbl_panel_89.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_89.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_89.setLayout(gbl_panel_89);

		JPanel panel_90 = new JPanel();
		panel_90.setPreferredSize(new Dimension(20, 20));
		panel_90.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_90.setBounds(310, 120, 20, 20);
		panel.add(panel_90);
		GridBagLayout gbl_panel_90 = new GridBagLayout();
		gbl_panel_90.columnWidths = new int[]{0, 0};
		gbl_panel_90.rowHeights = new int[]{0, 0};
		gbl_panel_90.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_90.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_90.setLayout(gbl_panel_90);

		JPanel panel_91 = new JPanel();
		panel_91.setPreferredSize(new Dimension(20, 20));
		panel_91.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_91.setBounds(335, 120, 20, 20);
		panel.add(panel_91);
		GridBagLayout gbl_panel_91 = new GridBagLayout();
		gbl_panel_91.columnWidths = new int[]{0, 0};
		gbl_panel_91.rowHeights = new int[]{0, 0};
		gbl_panel_91.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_91.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_91.setLayout(gbl_panel_91);

		JPanel panel_92 = new JPanel();
		panel_92.setPreferredSize(new Dimension(20, 20));
		panel_92.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_92.setBounds(360, 120, 20, 20);
		panel.add(panel_92);
		GridBagLayout gbl_panel_92 = new GridBagLayout();
		gbl_panel_92.columnWidths = new int[]{0, 0};
		gbl_panel_92.rowHeights = new int[]{0, 0};
		gbl_panel_92.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_92.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_92.setLayout(gbl_panel_92);

		JPanel panel_93 = new JPanel();
		panel_93.setPreferredSize(new Dimension(20, 20));
		panel_93.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_93.setBounds(35, 142, 20, 20);
		panel.add(panel_93);
		GridBagLayout gbl_panel_93 = new GridBagLayout();
		gbl_panel_93.columnWidths = new int[]{0, 0};
		gbl_panel_93.rowHeights = new int[]{0, 0};
		gbl_panel_93.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_93.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_93.setLayout(gbl_panel_93);

		JPanel panel_94 = new JPanel();
		panel_94.setPreferredSize(new Dimension(20, 20));
		panel_94.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_94.setBounds(60, 142, 20, 20);
		panel.add(panel_94);
		GridBagLayout gbl_panel_94 = new GridBagLayout();
		gbl_panel_94.columnWidths = new int[]{0, 0};
		gbl_panel_94.rowHeights = new int[]{0, 0};
		gbl_panel_94.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_94.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_94.setLayout(gbl_panel_94);

		JPanel panel_95 = new JPanel();
		panel_95.setPreferredSize(new Dimension(20, 20));
		panel_95.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_95.setBounds(85, 142, 20, 20);
		panel.add(panel_95);
		GridBagLayout gbl_panel_95 = new GridBagLayout();
		gbl_panel_95.columnWidths = new int[]{0, 0};
		gbl_panel_95.rowHeights = new int[]{0, 0};
		gbl_panel_95.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_95.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_95.setLayout(gbl_panel_95);

		JPanel panel_96 = new JPanel();
		panel_96.setPreferredSize(new Dimension(20, 20));
		panel_96.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_96.setBounds(110, 142, 20, 20);
		panel.add(panel_96);
		GridBagLayout gbl_panel_96 = new GridBagLayout();
		gbl_panel_96.columnWidths = new int[]{0, 0};
		gbl_panel_96.rowHeights = new int[]{0, 0};
		gbl_panel_96.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_96.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_96.setLayout(gbl_panel_96);

		JPanel panel_97 = new JPanel();
		panel_97.setPreferredSize(new Dimension(20, 20));
		panel_97.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_97.setBounds(135, 142, 20, 20);
		panel.add(panel_97);
		GridBagLayout gbl_panel_97 = new GridBagLayout();
		gbl_panel_97.columnWidths = new int[]{0, 0};
		gbl_panel_97.rowHeights = new int[]{0, 0};
		gbl_panel_97.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_97.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_97.setLayout(gbl_panel_97);

		JPanel panel_98 = new JPanel();
		panel_98.setPreferredSize(new Dimension(20, 20));
		panel_98.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_98.setBounds(160, 142, 20, 20);
		panel.add(panel_98);
		GridBagLayout gbl_panel_98 = new GridBagLayout();
		gbl_panel_98.columnWidths = new int[]{0, 0};
		gbl_panel_98.rowHeights = new int[]{0, 0};
		gbl_panel_98.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_98.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_98.setLayout(gbl_panel_98);

		JPanel panel_99 = new JPanel();
		panel_99.setPreferredSize(new Dimension(20, 20));
		panel_99.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_99.setAlignmentX(0.0f);
		panel_99.setBounds(185, 142, 20, 20);
		panel.add(panel_99);
		GridBagLayout gbl_panel_99 = new GridBagLayout();
		gbl_panel_99.columnWidths = new int[]{0, 0};
		gbl_panel_99.rowHeights = new int[]{0, 0};
		gbl_panel_99.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_99.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_99.setLayout(gbl_panel_99);

		JPanel panel_100 = new JPanel();
		panel_100.setPreferredSize(new Dimension(20, 20));
		panel_100.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_100.setBounds(210, 142, 20, 20);
		panel.add(panel_100);
		GridBagLayout gbl_panel_100 = new GridBagLayout();
		gbl_panel_100.columnWidths = new int[]{0, 0};
		gbl_panel_100.rowHeights = new int[]{0, 0};
		gbl_panel_100.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_100.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_100.setLayout(gbl_panel_100);

		JPanel panel_101 = new JPanel();
		panel_101.setPreferredSize(new Dimension(20, 20));
		panel_101.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_101.setBounds(235, 142, 20, 20);
		panel.add(panel_101);
		GridBagLayout gbl_panel_101 = new GridBagLayout();
		gbl_panel_101.columnWidths = new int[]{0, 0};
		gbl_panel_101.rowHeights = new int[]{0, 0};
		gbl_panel_101.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_101.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_101.setLayout(gbl_panel_101);

		JPanel panel_102 = new JPanel();
		panel_102.setPreferredSize(new Dimension(20, 20));
		panel_102.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_102.setBounds(260, 142, 20, 20);
		panel.add(panel_102);
		GridBagLayout gbl_panel_102 = new GridBagLayout();
		gbl_panel_102.columnWidths = new int[]{0, 0};
		gbl_panel_102.rowHeights = new int[]{0, 0};
		gbl_panel_102.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_102.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_102.setLayout(gbl_panel_102);

		JPanel panel_103 = new JPanel();
		panel_103.setPreferredSize(new Dimension(20, 20));
		panel_103.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_103.setBounds(285, 142, 20, 20);
		panel.add(panel_103);
		GridBagLayout gbl_panel_103 = new GridBagLayout();
		gbl_panel_103.columnWidths = new int[]{0, 0};
		gbl_panel_103.rowHeights = new int[]{0, 0};
		gbl_panel_103.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_103.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_103.setLayout(gbl_panel_103);

		JPanel panel_104 = new JPanel();
		panel_104.setPreferredSize(new Dimension(20, 20));
		panel_104.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_104.setBounds(310, 142, 20, 20);
		panel.add(panel_104);
		GridBagLayout gbl_panel_104 = new GridBagLayout();
		gbl_panel_104.columnWidths = new int[]{0, 0};
		gbl_panel_104.rowHeights = new int[]{0, 0};
		gbl_panel_104.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_104.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_104.setLayout(gbl_panel_104);

		JPanel panel_105 = new JPanel();
		panel_105.setPreferredSize(new Dimension(20, 20));
		panel_105.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_105.setBounds(335, 142, 20, 20);
		panel.add(panel_105);
		GridBagLayout gbl_panel_105 = new GridBagLayout();
		gbl_panel_105.columnWidths = new int[]{0, 0};
		gbl_panel_105.rowHeights = new int[]{0, 0};
		gbl_panel_105.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_105.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_105.setLayout(gbl_panel_105);

		JPanel panel_106 = new JPanel();
		panel_106.setPreferredSize(new Dimension(20, 20));
		panel_106.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_106.setBounds(360, 142, 20, 20);
		panel.add(panel_106);
		GridBagLayout gbl_panel_106 = new GridBagLayout();
		gbl_panel_106.columnWidths = new int[]{0, 0};
		gbl_panel_106.rowHeights = new int[]{0, 0};
		gbl_panel_106.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_106.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_106.setLayout(gbl_panel_106);

		JPanel panel_107 = new JPanel();
		panel_107.setPreferredSize(new Dimension(20, 20));
		panel_107.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_107.setBounds(35, 164, 20, 20);
		panel.add(panel_107);
		GridBagLayout gbl_panel_107 = new GridBagLayout();
		gbl_panel_107.columnWidths = new int[]{0, 0};
		gbl_panel_107.rowHeights = new int[]{0, 0};
		gbl_panel_107.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_107.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_107.setLayout(gbl_panel_107);

		JPanel panel_108 = new JPanel();
		panel_108.setPreferredSize(new Dimension(20, 20));
		panel_108.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_108.setBounds(60, 164, 20, 20);
		panel.add(panel_108);
		GridBagLayout gbl_panel_108 = new GridBagLayout();
		gbl_panel_108.columnWidths = new int[]{0, 0};
		gbl_panel_108.rowHeights = new int[]{0, 0};
		gbl_panel_108.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_108.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_108.setLayout(gbl_panel_108);

		JPanel panel_109 = new JPanel();
		panel_109.setPreferredSize(new Dimension(20, 20));
		panel_109.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_109.setBounds(85, 164, 20, 20);
		panel.add(panel_109);
		GridBagLayout gbl_panel_109 = new GridBagLayout();
		gbl_panel_109.columnWidths = new int[]{0, 0};
		gbl_panel_109.rowHeights = new int[]{0, 0};
		gbl_panel_109.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_109.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_109.setLayout(gbl_panel_109);

		JPanel panel_110 = new JPanel();
		panel_110.setPreferredSize(new Dimension(20, 20));
		panel_110.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_110.setBounds(110, 164, 20, 20);
		panel.add(panel_110);
		GridBagLayout gbl_panel_110 = new GridBagLayout();
		gbl_panel_110.columnWidths = new int[]{0, 0};
		gbl_panel_110.rowHeights = new int[]{0, 0};
		gbl_panel_110.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_110.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_110.setLayout(gbl_panel_110);

		JPanel panel_111 = new JPanel();
		panel_111.setPreferredSize(new Dimension(20, 20));
		panel_111.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_111.setBounds(135, 164, 20, 20);
		panel.add(panel_111);
		GridBagLayout gbl_panel_111 = new GridBagLayout();
		gbl_panel_111.columnWidths = new int[]{0, 0};
		gbl_panel_111.rowHeights = new int[]{0, 0};
		gbl_panel_111.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_111.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_111.setLayout(gbl_panel_111);

		JPanel panel_112 = new JPanel();
		panel_112.setPreferredSize(new Dimension(20, 20));
		panel_112.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_112.setBounds(160, 164, 20, 20);
		panel.add(panel_112);
		GridBagLayout gbl_panel_112 = new GridBagLayout();
		gbl_panel_112.columnWidths = new int[]{0, 0};
		gbl_panel_112.rowHeights = new int[]{0, 0};
		gbl_panel_112.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_112.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_112.setLayout(gbl_panel_112);

		JPanel panel_113 = new JPanel();
		panel_113.setPreferredSize(new Dimension(20, 20));
		panel_113.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_113.setAlignmentX(0.0f);
		panel_113.setBounds(185, 164, 20, 20);
		panel.add(panel_113);
		GridBagLayout gbl_panel_113 = new GridBagLayout();
		gbl_panel_113.columnWidths = new int[]{0, 0};
		gbl_panel_113.rowHeights = new int[]{0, 0};
		gbl_panel_113.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_113.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_113.setLayout(gbl_panel_113);

		JPanel panel_114 = new JPanel();
		panel_114.setPreferredSize(new Dimension(20, 20));
		panel_114.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_114.setBounds(210, 164, 20, 20);
		panel.add(panel_114);
		GridBagLayout gbl_panel_114 = new GridBagLayout();
		gbl_panel_114.columnWidths = new int[]{0, 0};
		gbl_panel_114.rowHeights = new int[]{0, 0};
		gbl_panel_114.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_114.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_114.setLayout(gbl_panel_114);

		JPanel panel_115 = new JPanel();
		panel_115.setPreferredSize(new Dimension(20, 20));
		panel_115.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_115.setBounds(235, 164, 20, 20);
		panel.add(panel_115);
		GridBagLayout gbl_panel_115 = new GridBagLayout();
		gbl_panel_115.columnWidths = new int[]{0, 0};
		gbl_panel_115.rowHeights = new int[]{0, 0};
		gbl_panel_115.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_115.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_115.setLayout(gbl_panel_115);

		JPanel panel_116 = new JPanel();
		panel_116.setPreferredSize(new Dimension(20, 20));
		panel_116.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_116.setBounds(260, 164, 20, 20);
		panel.add(panel_116);
		GridBagLayout gbl_panel_116 = new GridBagLayout();
		gbl_panel_116.columnWidths = new int[]{0, 0};
		gbl_panel_116.rowHeights = new int[]{0, 0};
		gbl_panel_116.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_116.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_116.setLayout(gbl_panel_116);

		JPanel panel_117 = new JPanel();
		panel_117.setPreferredSize(new Dimension(20, 20));
		panel_117.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_117.setBounds(285, 164, 20, 20);
		panel.add(panel_117);
		GridBagLayout gbl_panel_117 = new GridBagLayout();
		gbl_panel_117.columnWidths = new int[]{0, 0};
		gbl_panel_117.rowHeights = new int[]{0, 0};
		gbl_panel_117.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_117.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_117.setLayout(gbl_panel_117);

		JPanel panel_118 = new JPanel();
		panel_118.setPreferredSize(new Dimension(20, 20));
		panel_118.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_118.setBounds(310, 164, 20, 20);
		panel.add(panel_118);
		GridBagLayout gbl_panel_118 = new GridBagLayout();
		gbl_panel_118.columnWidths = new int[]{0, 0};
		gbl_panel_118.rowHeights = new int[]{0, 0};
		gbl_panel_118.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_118.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_118.setLayout(gbl_panel_118);

		JPanel panel_119 = new JPanel();
		panel_119.setPreferredSize(new Dimension(20, 20));
		panel_119.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_119.setBounds(335, 164, 20, 20);
		panel.add(panel_119);
		GridBagLayout gbl_panel_119 = new GridBagLayout();
		gbl_panel_119.columnWidths = new int[]{0, 0};
		gbl_panel_119.rowHeights = new int[]{0, 0};
		gbl_panel_119.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_119.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_119.setLayout(gbl_panel_119);

		JPanel panel_120 = new JPanel();
		panel_120.setPreferredSize(new Dimension(20, 20));
		panel_120.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_120.setBounds(360, 164, 20, 20);
		panel.add(panel_120);
		GridBagLayout gbl_panel_120 = new GridBagLayout();
		gbl_panel_120.columnWidths = new int[]{0, 0};
		gbl_panel_120.rowHeights = new int[]{0, 0};
		gbl_panel_120.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_120.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_120.setLayout(gbl_panel_120);
		contentPane.setLayout(null);
		contentPane.add(panel);
		contentPane.add(createBlockButton);
		contentPane.add(deleteBlockButton);
		contentPane.add(lblListOfBlocks);
		toBlockComboBox = new JComboBox();
		toBlockComboBox.setBounds(563, 224, 123, 20);
		contentPane.add(toBlockComboBox);
		toBlockComboBox.setModel(new DefaultComboBoxModel(new String[] {"1 - Block1", "2 - Blockred", "3-as;dklja", "4-asdkaod", "5-", "asdasjkd", "5", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1!!1!", "1", "1"}));
		toBlockComboBox.setToolTipText("Select a block");

		JLabel lblListOfBlocks_1 = new JLabel("List of Level Blocks:");
		lblListOfBlocks_1.setBounds(432, 367, 146, 14);
		contentPane.add(lblListOfBlocks_1);

		JLabel lblListOfLevels = new JLabel("List of Levels:");
		lblListOfLevels.setBounds(432, 47, 110, 14);
		contentPane.add(lblListOfLevels);

		levelComboBox = new JComboBox();
		levelComboBox.setModel(new DefaultComboBoxModel(new String[] {"Level 1", "Level 2", "Level 3", "Level 4", "Level 5", "Level 6", "Level 7", "Level 8", "Level 9", "Level 10", "Level 11", "Level 12", "Level 13", "Level 14", "Level 15", "Level 16", "Level 17", "Level 18", "Level 19", "Level 20", "Level 21", "Level 22", "Level 23", "Level 24", "Level 25", "Level 26", "Level 27", "Level 28", "Level 29", "Level 30", "Level 31", "Level 32", "Level 33", "Level 34", "Level 35", "Level 36", "Level 37", "Level 38", "Level 39", "Level 40", "Level 41", "Level 42", "Level 43", "Level 44", "Level 45", "Level 46", "Level 47", "Level 48", "Level 49", "Level 50", "Level 51", "Level 52", "Level 53", "Level 54", "Level 55", "Level 56", "Level 57", "Level 58", "Level 59", "Level 60", "Level 61", "Level 62", "Level 63", "Level 64", "Level 65", "Level 66", "Level 67", "Level 68", "Level 69", "Level 70", "Level 71", "Level 72", "Level 73", "Level 74", "Level 75"}));
		levelComboBox.setBounds(563, 44, 123, 20);
		contentPane.add(levelComboBox);

		toGridCellComboBox = new JComboBox();
		toGridCellComboBox.setModel(new DefaultComboBoxModel(new String[] {"1- Block1asWELL"}));
		toGridCellComboBox.setBounds(563, 364, 123, 20);
		contentPane.add(toGridCellComboBox);

		updateBlockButton = new JButton("Update Block");
		updateBlockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		updateBlockButton.setBounds(563, 191, 123, 23);
		contentPane.add(updateBlockButton);

		positionBlockButton = new JButton("Position Block");
		positionBlockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				positionBlockButtonActionPerformed(evt);
			}
		});
		positionBlockButton.setBounds(430, 224, 123, 23);

		JButton positionBlockButton = new JButton("Position Block");
		positionBlockButton.setBounds(432, 257, 123, 23);
		contentPane.add(positionBlockButton);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(432, 68, 254, 2);
		contentPane.add(separator_1);

		gameSettingsButton = new JButton("Game Settings");
		gameSettingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		gameSettingsButton.setBounds(432, 8, 123, 23);
		contentPane.add(gameSettingsButton);

		moveBlockButton = new JButton("Move Block");
		moveBlockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				moveBlockButtonActionPerformed(evt);
			}
		});
		moveBlockButton.setBounds(432, 397, 123, 23);
		contentPane.add(moveBlockButton);

		removeBlockButton = new JButton("Remove Block");
		removeBlockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		removeBlockButton.setBounds(563, 397, 123, 23);
		contentPane.add(removeBlockButton);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(430, 494, 254, 2);
		contentPane.add(separator_2);

		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				saveButtonActionPerformed(evt);
			}
		});
		saveButton.setBounds(563, 8, 123, 23);
		contentPane.add(saveButton);

		JFormattedTextField txtHorizontalIndex = new JFormattedTextField();
		txtHorizontalIndex.setBorder(null);
		txtHorizontalIndex.setBounds(30, 10, 390, 20);
		contentPane.add(txtHorizontalIndex);
		txtHorizontalIndex.setText("     1      2      3      4       5      6      7       8      9     10     11     12    13    14    15 ");

		JPanel panel_121 = new JPanel();
		panel_121.setBorder(null);
		panel_121.setBackground(Color.WHITE);
		panel_121.setBounds(10, 30, 20, 390);
		contentPane.add(panel_121);
		panel_121.setLayout(null);

		JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setBorder(null);
		formattedTextField_1.setBounds(0, 10, 20, 20);
		panel_121.add(formattedTextField_1);
		formattedTextField_1.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField_1.setText("1");

		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setText("8");
		formattedTextField.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField.setBorder(null);
		formattedTextField.setBounds(0, 164, 20, 20);
		panel_121.add(formattedTextField);

		JFormattedTextField formattedTextField_2 = new JFormattedTextField();
		formattedTextField_2.setText("7");
		formattedTextField_2.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField_2.setBorder(null);
		formattedTextField_2.setBounds(0, 142, 20, 20);
		panel_121.add(formattedTextField_2);

		JFormattedTextField formattedTextField_3 = new JFormattedTextField();
		formattedTextField_3.setText("6");
		formattedTextField_3.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField_3.setBorder(null);
		formattedTextField_3.setBounds(0, 120, 20, 20);
		panel_121.add(formattedTextField_3);

		JFormattedTextField formattedTextField_4 = new JFormattedTextField();
		formattedTextField_4.setText("5");
		formattedTextField_4.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField_4.setBorder(null);
		formattedTextField_4.setBounds(0, 98, 20, 20);
		panel_121.add(formattedTextField_4);

		JFormattedTextField formattedTextField_5 = new JFormattedTextField();
		formattedTextField_5.setText("4");
		formattedTextField_5.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField_5.setBorder(null);
		formattedTextField_5.setBounds(0, 76, 20, 20);
		panel_121.add(formattedTextField_5);

		JFormattedTextField formattedTextField_6 = new JFormattedTextField();
		formattedTextField_6.setText("3");
		formattedTextField_6.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField_6.setBorder(null);
		formattedTextField_6.setBounds(0, 54, 20, 20);
		panel_121.add(formattedTextField_6);

		JFormattedTextField formattedTextField_7 = new JFormattedTextField();
		formattedTextField_7.setText("2");
		formattedTextField_7.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField_7.setBorder(null);
		formattedTextField_7.setBounds(0, 32, 20, 20);
		panel_121.add(formattedTextField_7);

		xPositionComboBox = new JComboBox();
		xPositionComboBox.setModel(new DefaultComboBoxModel(new String[] {"xPosition"}));
		xPositionComboBox.setBounds(563, 293, 123, 22);
		contentPane.add(xPositionComboBox);

		yPositionComboBox = new JComboBox();
		yPositionComboBox.setModel(new DefaultComboBoxModel(new String[] {"yPosition"}));
		yPositionComboBox.setBounds(563, 328, 123, 23);
		contentPane.add(yPositionComboBox);

		JLabel lblLevelXposition = new JLabel("Level xPosition:");
		lblLevelXposition.setBounds(432, 297, 123, 14);
		contentPane.add(lblLevelXposition);

		JLabel lblLevelYposition = new JLabel("Level yPosition:");
		lblLevelYposition.setBounds(432, 332, 110, 14);
		contentPane.add(lblLevelYposition);

		blockBlueSlider = new JSlider();

		blockBlueSlider.setValue(128);
		blockBlueSlider.setMaximum(255);
		blockBlueSlider.setBounds(467, 164, 175, 14);
		contentPane.add(blockBlueSlider);
		blockBlueSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//JSlider source = (JSlider) e.getSource();
				//testBlock.setBackground(new Color(blockRedSlider.getValue(), blockGreenSlider.getValue(), source.getValue()));
				testBlock.setBackground(new Color(blockRedSlider.getValue(), blockGreenSlider.getValue(), blockBlueSlider.getValue()));
			}
		});
		JLabel lblBlue = new JLabel("Blue:");
		lblBlue.setBounds(430, 164, 46, 14);
		contentPane.add(lblBlue);

		JLabel lblGreen = new JLabel("Green:");
		lblGreen.setBounds(432, 137, 46, 14);
		contentPane.add(lblGreen);

		blockGreenSlider = new JSlider();
		blockGreenSlider.setValue(30);
		blockGreenSlider.setMaximum(255);
		blockGreenSlider.setBounds(467, 137, 175, 14);
		contentPane.add(blockGreenSlider);
		blockGreenSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				testBlock.setBackground(new Color(blockRedSlider.getValue(), source.getValue(), blockBlueSlider.getValue()));
			}
		});
		blockRedSlider = new JSlider();

		blockRedSlider.setSnapToTicks(true);
		blockRedSlider.setValue(150);
		blockRedSlider.setMaximum(255);
		blockRedSlider.setBounds(467, 110, 175, 14);
		contentPane.add(blockRedSlider);
		blockRedSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				testBlock.setBackground(new Color(source.getValue(), blockGreenSlider.getValue(), blockBlueSlider.getValue()));
			}
		});

		JLabel lblRed = new JLabel("Red:");
		lblRed.setBounds(432, 110, 46, 14);
		contentPane.add(lblRed);

		testBlock = new JPanel();
		testBlock.setBackground(new Color(blockRedSlider.getValue(), blockGreenSlider.getValue(), blockBlueSlider.getValue()));
		testBlock.setPreferredSize(new Dimension(20, 20));
		testBlock.setBorder(new LineBorder(new Color(0, 0, 0)));
		testBlock.setBounds(654, 74, 20, 20);
		contentPane.add(testBlock);
		GridBagLayout gbl_testBlock = new GridBagLayout();
		gbl_testBlock.columnWidths = new int[]{0, 0};
		gbl_testBlock.rowHeights = new int[]{0, 0};
		gbl_testBlock.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_testBlock.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		testBlock.setLayout(gbl_testBlock);

		JLabel lblPoints = new JLabel("Points:");
		lblPoints.setBounds(430, 83, 46, 14);
		contentPane.add(lblPoints);

		pointsSlider = new JSlider();
		pointsSlider.setMinimum(1);
		pointsSlider.setMaximum(1000);

		contentPane.add(pointsSlider);

		JLabel pointsLabel = new JLabel(String.valueOf(pointsSlider.getValue()));
		pointsLabel.setBounds(656, 83, 30, 14);
		contentPane.add(pointsLabel);
		pointsSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				pointsLabel.setText(String.valueOf(pointsSlider.getValue()));

			}
		});
		pointsSlider.setBounds(467, 83, 175, 14);
	}

	private void saveButtonActionPerformed(ActionEvent evt) {
		try {

			Block223Controller.saveGame();
			JOptionPane.showMessageDialog(null, "Game Saved");
		} catch (InvalidInputException e) {
			String error = e.getMessage();
			JOptionPane.showMessageDialog(null, error);
		}


		pointsSlider = new JSlider();
		pointsSlider.setMaximum(1000);

		contentPane.add(pointsSlider);

		JLabel pointsLabel = new JLabel(String.valueOf(pointsSlider.getValue()));
		pointsLabel.setBounds(654, 30, 30, 14);
		contentPane.add(pointsLabel);
		pointsSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				pointsLabel.setText(String.valueOf(pointsSlider.getValue()));

			}
		});
		pointsSlider.setBounds(467, 30, 175, 14);
	}


	private void mntmLogOutActionPerformed(ActionEvent evt) {
		Block223Controller.logout();
		RegisterLoginPage loginpage = new RegisterLoginPage();
		loginpage.setVisible(true);
		this.setVisible(false);
	}
	private void addBlockButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message. Enter integer, take these integer as input for a
		String error="";
		// call the controller
		try {
			Block223Controller.addBlock(); 	//manque les 4 JTextField de will
			//addBlock(blockNameTextField.getText());

		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// update visuals
		refreshData();
	}

	private void updateBlockButtonActionPerformed(java.awt.event.ActionEvent evt) {

		String error = "";
		TOBlock selectedBlock = (TOBlock) cbBlocks.getSelectedItem();

		if(selectedBlock == null) {
		error = "Block needs to be selected for update!";
		}

		if(error == null) {
		Block223Controller.updateBlock(selectedBlock.getId(),
				selectedBlock.getRed(),
				selectedBlock.getGreen(),
				selectedBlock.getBlue(),
				selectedBlock.getPoints());

		}

	refreshData();
	}

	private void deleteBlockButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		error = "";

		int selectedBlock = toBlockComboBox.getSelectedIndex();

		if (selectedBlock < 0) {
			error = "Block needs to be selected for deletion!";}

		if (error.length() == 0) {
			// call the controller
			try{
				Block223Controller.deleteBlock(blocks.get(selectedBlock).getId()); 	//We need to get the blockId value that is associated with this block index in hashMap
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}


		// update visuals
		refreshData();
	}

	private void removeBlockButtonActionPerformed(java.awt.event.ActionEvent evt) {

		String error = "";
		TOGridCell selectedBlock = (TOGridCell) cbBlocks.getSelectedItem();

		if(selectedBlock == null) {
		error = "Block needs to be selected for update!";
		}

		if(error == null) {
			Block223Controller.removeBlock(selectedBlock.getcurrentLevel(),	//getCurrentLevel existe pas dans TOGridCell (et aucun des transfer objects) mais ca existe dans le model (Game.java), jsp comment le get.

					selectedBlock.getGridHorizontalPosition(),
					selectedBlock.getGridVerticalPosition());
		}

	}
	private void positionBlockButtonActionPerformed(ActionEvent evt) {

		String error = "";
		int selectedBlock = toBlockComboBox.getSelectedIndex();
		int level = levelComboBox.getSelectedIndex();
		int newGridHorizontalPosition = xPositionComboBox.getSelectedIndex();
		int newGridVerticalPosition = yPositionComboBox.getSelectedIndex();

		if (selectedBlock < 0) {
		error = "Block needs to be selected in order to placed in the game!";}

		//int selectedAssignment = selectedBlock.getId(); //replace assignmentlist par JPanel list?? CA VA ETRE LE PANEL QUI VA ETRE CHOISI
		if (level < 0)
		error = error + "A level needs to be selected for block! ";
		if (newGridHorizontalPosition < 0)
		error = error + "A horizontal grid position needs to be selected for block! ";
		if (newGridVerticalPosition < 0)
		error = error + "A vertical grid position needs to be selected for block! ";

		error = error.trim();

		if (error == "") {
		try {
			Block223Controller.positionBlock(blocks.get(selectedBlock).getId(), levels.get(level), newGridHorizontalPosition, newGridVerticalPosition);
			}
		catch (InvalidInputException e) {
			error = e.getMessage();
		}
	}
		//update visuals
		refreshData();
	}

	private void moveBlockButtonActionPerformed(java.awt.event.ActionEvent evt) {

		String error = "";

		int gridCell = toGridCellComboBox.getSelectedIndex();
		int level = levelComboBox.getSelectedIndex();
		int newGridHorizontalPosition = xPositionComboBox.getSelectedIndex();
		int newGridVerticalPosition = yPositionComboBox.getSelectedIndex();


		if (error == "") {
		try {
			Block223Controller.moveBlock(level, gridCells.get(gridCell).getGridHorizontalPosition(), gridCells.get(gridCell).getGridHorizontalPosition(),
					newGridHorizontalPosition,  newGridVerticalPosition);
		}
		catch (InvalidInputException e) {
			error = e.getMessage();}
		}
		refreshData();
	}

	private void refreshData() {


		error = "";
		// TODO Auto-generated method stub
		if (error == null || error.length() == 0) {
			blocks = new HashMap<Integer, TOBlock>();
			toBlockComboBox.removeAllItems();
			Integer index = 0;
			List<TOBlock> toBlocks = null;
			try {
				toBlocks = Block223Controller.getBlocksOfCurrentDesignableGame();
			}catch (InvalidInputException e) {
				error = e.getMessage();
			}
			for (TOBlock block : toBlocks) {
				blocks.put(index, block);
				toBlockComboBox.addItem(block.getId() + block.getRed() + block.getGreen() + block.getBlue() + block.getPoints());
			}
			toBlockComboBox.setSelectedIndex(-1);


			gridCells = new HashMap<Integer, TOGridCell>();
			toGridCellComboBox.removeAllItems();
			index = 0;
			List<TOGridCell> toGridCells = null;
			try {
				toGridCells =  Block223Controller.getBlocksAtLevelOfCurrentDesignableGame(levels.get(levelComboBox.getSelectedIndex()));

			} catch(InvalidInputException e) {
				error = e.getMessage();
			}
			for (TOGridCell gridCell : toGridCells) {
			gridCells.put(index, gridCell);
			toGridCellComboBox.addItem( gridCell.getGridHorizontalPosition() + gridCell.getGridVerticalPosition() + gridCell.getId() + gridCell.getBlue() + gridCell.getPoints());


			}
		}
		}
}




