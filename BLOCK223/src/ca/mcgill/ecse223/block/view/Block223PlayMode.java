package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.TOBlock;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.controller.TOGridCell;
import ca.mcgill.ecse223.block.controller.TOHallOfFame;
import ca.mcgill.ecse223.block.controller.TOHallOfFameEntry;
import ca.mcgill.ecse223.block.controller.TOUserMode;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.view.PlayerPage;
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
import java.util.ArrayList;
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
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javafx.scene.shape.Circle;
import javafx.geometry.Rectangle2D;

public class Block223PlayMode extends JFrame implements Block223PlayModeInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JLabel displayHOF;
	private JLabel currentGameName;
	// data elements
	private String error = "";
	//blocks
	private JPanel panel_1_1;
	private JPanel panel_1_2;
	private JPanel panel_1_3;
	private JPanel panel_1_4;
	private JPanel panel_1_5;
	private JPanel panel_1_6;
	private JPanel panel_1_7;
	private JPanel panel_1_8;
	private JPanel panel_1_9;
	private JPanel panel_1_10;
	private JPanel panel_1_11;
	private JPanel panel_1_12;
	private JPanel panel_1_13;
	private JPanel panel_1_14;
	private JPanel panel_1_15;
	private JPanel panel_2_1;
	private JPanel panel_3_1;
	private JPanel panel_4_1;
	private JPanel panel_5_1;
	private JPanel panel_6_1;
	private JPanel panel_7_1;
	private JPanel panel_8_1;
	private JPanel panel_2_2;
	private JPanel panel_2_3;
	private JPanel panel_2_4;
	private JPanel panel_2_5;
	private JPanel panel_2_6;
	private JPanel panel_2_7;
	private JPanel panel_2_8;
	private JPanel panel_2_9;
	private JPanel panel_2_10;
	private JPanel panel_2_11;
	private JPanel panel_2_12;
	private JPanel panel_2_13;
	private JPanel panel_2_14;
	private JPanel panel_2_15;
	private JPanel panel_3_2;
	private JPanel panel_3_3;
	private JPanel panel_3_4;
	private JPanel panel_3_5;
	private JPanel panel_3_6;
	private JPanel panel_3_7;
	private JPanel panel_3_8;
	private JPanel panel_3_9;
	private JPanel panel_3_10;
	private JPanel panel_3_11;
	private JPanel panel_3_12;
	private JPanel panel_3_13;
	private JPanel panel_3_14;
	private JPanel panel_3_15;
	private JPanel panel_4_2;
	private JPanel panel_4_3;
	private JPanel panel_4_4;
	private JPanel panel_4_5;
	private JPanel panel_4_6;
	private JPanel panel_4_7;
	private JPanel panel_4_8;
	private JPanel panel_4_9;
	private JPanel panel_4_10;
	private JPanel panel_4_11;
	private JPanel panel_4_12;
	private JPanel panel_4_13;
	private JPanel panel_4_14;
	private JPanel panel_4_15;
	private JPanel panel_5_2;
	private JPanel panel_5_3;
	private JPanel panel_5_4;
	private JPanel panel_5_5;
	private JPanel panel_5_6;
	private JPanel panel_5_7;
	private JPanel panel_5_8;
	private JPanel panel_5_9;
	private JPanel panel_5_10;
	private JPanel panel_5_11;
	private JPanel panel_5_13;
	private JPanel panel_5_14;
	private JPanel panel_5_15;
	private JPanel panel_6_2;
	private JPanel panel_6_3;
	private JPanel panel_6_4;
	private JPanel panel_6_5;
	private JPanel panel_6_6;
	private JPanel panel_6_7;
	private JPanel panel_6_8;
	private JPanel panel_6_9;
	private JPanel panel_6_10;
	private JPanel panel_6_11;
	private JPanel panel_6_12;
	private JPanel panel_6_13;
	private JPanel panel_6_14;
	private JPanel panel_6_15;
	private JPanel panel_7_2;
	private JPanel panel_7_3;
	private JPanel panel_7_4;
	private JPanel panel_7_5;
	private JPanel panel_7_6;
	private JPanel panel_7_7;
	private JPanel panel_7_8;
	private JPanel panel_7_9;
	private JPanel panel_7_10;
	private JPanel panel_7_11;
	private JPanel panel_7_12;
	private JPanel panel_7_13;
	private JPanel panel_7_14;
	private JPanel panel_7_15;
	private JPanel panel_8_2;
	private JPanel panel_8_3;
	private JPanel panel_8_4;
	private JPanel panel_8_5;
	private JPanel panel_8_6;
	private JPanel panel_8_7;
	private JPanel panel_8_8;
	private JPanel panel_8_9;
	private JPanel panel_8_10;
	private JPanel panel_8_11;
	private JPanel panel_8_12;
	private JPanel panel_8_13;
	private JPanel panel_8_14;
	private JPanel panel_8_15;
	ArrayList<JPanel> panelList;
	private JLabel lblPrevious;
	private JLabel lblNext;

	private TOHallOfFame HOF;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Block223PlayMode frame = new Block223PlayMode();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Block223PlayMode() {
		setTitle("BLOCK CREATOR 9000");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 574);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(192, 192, 192));
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
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		panel.setForeground(Color.WHITE);
		panel.setBounds(30, 30, 390, 390);
		panel.setAlignmentX(0.0f);
		panel.setAlignmentY(0.0f);
		panel.setBackground(new Color(176, 196, 222));
		
		panel.setLayout(null);
		
		panel_1_1 = new JPanel();
		panel_1_1.setBounds(10, 10, 20, 20);
		panel_1_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_1.setPreferredSize(new Dimension(20, 20));
		panel.add(panel_1_1);
		GridBagLayout gbl_panel_1_1 = new GridBagLayout();
		gbl_panel_1_1.columnWidths = new int[]{0, 0};
		gbl_panel_1_1.rowHeights = new int[]{0, 0};
		gbl_panel_1_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1_1.setLayout(gbl_panel_1_1);

		panel_1_2 = new JPanel();
		panel_1_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_2.setPreferredSize(new Dimension(20, 20));
		panel_1_2.setBounds(35, 10, 20, 20);
		panel.add(panel_1_2);
		GridBagLayout gbl_panel_1_2 = new GridBagLayout();
		gbl_panel_1_2.columnWidths = new int[]{0, 0};
		gbl_panel_1_2.rowHeights = new int[]{0, 0};
		gbl_panel_1_2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1_2.setLayout(gbl_panel_1_2);

		panel_1_3 = new JPanel();
		panel_1_3.setPreferredSize(new Dimension(20, 20));
		panel_1_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_3.setBounds(60, 10, 20, 20);
		panel.add(panel_1_3);
		GridBagLayout gbl_panel_1_3 = new GridBagLayout();
		gbl_panel_1_3.columnWidths = new int[]{0, 0};
		gbl_panel_1_3.rowHeights = new int[]{0, 0};
		gbl_panel_1_3.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1_3.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1_3.setLayout(gbl_panel_1_3);

		panel_1_4 = new JPanel();
		panel_1_4.setPreferredSize(new Dimension(20, 20));
		panel_1_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_4.setBounds(85, 10, 20, 20);
		panel.add(panel_1_4);
		GridBagLayout gbl_panel_1_4 = new GridBagLayout();
		gbl_panel_1_4.columnWidths = new int[]{0, 0};
		gbl_panel_1_4.rowHeights = new int[]{0, 0};
		gbl_panel_1_4.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1_4.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1_4.setLayout(gbl_panel_1_4);

		panel_1_5 = new JPanel();
		panel_1_5.setPreferredSize(new Dimension(20, 20));
		panel_1_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_5.setBounds(110, 10, 20, 20);
		panel.add(panel_1_5);
		GridBagLayout gbl_panel_1_5 = new GridBagLayout();
		gbl_panel_1_5.columnWidths = new int[]{0, 0};
		gbl_panel_1_5.rowHeights = new int[]{0, 0};
		gbl_panel_1_5.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1_5.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1_5.setLayout(gbl_panel_1_5);

		panel_1_6 = new JPanel();
		panel_1_6.setPreferredSize(new Dimension(20, 20));
		panel_1_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_6.setBounds(135, 10, 20, 20);
		panel.add(panel_1_6);
		GridBagLayout gbl_panel_1_6 = new GridBagLayout();
		gbl_panel_1_6.columnWidths = new int[]{0, 0};
		gbl_panel_1_6.rowHeights = new int[]{0, 0};
		gbl_panel_1_6.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1_6.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1_6.setLayout(gbl_panel_1_6);

		panel_1_7 = new JPanel();
		panel_1_7.setPreferredSize(new Dimension(20, 20));
		panel_1_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_7.setBounds(160, 10, 20, 20);
		panel.add(panel_1_7);
		GridBagLayout gbl_panel_1_7 = new GridBagLayout();
		gbl_panel_1_7.columnWidths = new int[]{0, 0};
		gbl_panel_1_7.rowHeights = new int[]{0, 0};
		gbl_panel_1_7.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1_7.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1_7.setLayout(gbl_panel_1_7);

		panel_1_8 = new JPanel();
		panel_1_8.setPreferredSize(new Dimension(20, 20));
		panel_1_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_8.setAlignmentX(0.0f);
		panel_1_8.setBounds(185, 10, 20, 20);
		panel.add(panel_1_8);
		GridBagLayout gbl_panel_1_8 = new GridBagLayout();
		gbl_panel_1_8.columnWidths = new int[]{0, 0};
		gbl_panel_1_8.rowHeights = new int[]{0, 0};
		gbl_panel_1_8.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1_8.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1_8.setLayout(gbl_panel_1_8);

		panel_1_9 = new JPanel();
		panel_1_9.setPreferredSize(new Dimension(20, 20));
		panel_1_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_9.setBounds(210, 10, 20, 20);
		panel.add(panel_1_9);
		GridBagLayout gbl_panel_1_9 = new GridBagLayout();
		gbl_panel_1_9.columnWidths = new int[]{0, 0};
		gbl_panel_1_9.rowHeights = new int[]{0, 0};
		gbl_panel_1_9.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1_9.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1_9.setLayout(gbl_panel_1_9);

		panel_1_10 = new JPanel();
		panel_1_10.setPreferredSize(new Dimension(20, 20));
		panel_1_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_10.setBounds(235, 10, 20, 20);
		panel.add(panel_1_10);
		GridBagLayout gbl_panel_1_10 = new GridBagLayout();
		gbl_panel_1_10.columnWidths = new int[]{0, 0};
		gbl_panel_1_10.rowHeights = new int[]{0, 0};
		gbl_panel_1_10.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1_10.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1_10.setLayout(gbl_panel_1_10);

		panel_1_11 = new JPanel();
		panel_1_11.setPreferredSize(new Dimension(20, 20));
		panel_1_11.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_11.setBounds(260, 10, 20, 20);
		panel.add(panel_1_11);
		GridBagLayout gbl_panel_1_11 = new GridBagLayout();
		gbl_panel_1_11.columnWidths = new int[]{0, 0};
		gbl_panel_1_11.rowHeights = new int[]{0, 0};
		gbl_panel_1_11.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1_11.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1_11.setLayout(gbl_panel_1_11);

		panel_1_12 = new JPanel();
		panel_1_12.setPreferredSize(new Dimension(20, 20));
		panel_1_12.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_12.setBounds(285, 10, 20, 20);
		panel.add(panel_1_12);
		GridBagLayout gbl_panel_1_12 = new GridBagLayout();
		gbl_panel_1_12.columnWidths = new int[]{0, 0};
		gbl_panel_1_12.rowHeights = new int[]{0, 0};
		gbl_panel_1_12.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1_12.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1_12.setLayout(gbl_panel_1_12);

		panel_1_13 = new JPanel();
		panel_1_13.setPreferredSize(new Dimension(20, 20));
		panel_1_13.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_13.setBounds(310, 10, 20, 20);
		panel.add(panel_1_13);
		GridBagLayout gbl_panel_1_13 = new GridBagLayout();
		gbl_panel_1_13.columnWidths = new int[]{0, 0};
		gbl_panel_1_13.rowHeights = new int[]{0, 0};
		gbl_panel_1_13.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1_13.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1_13.setLayout(gbl_panel_1_13);

		panel_1_14 = new JPanel();
		panel_1_14.setPreferredSize(new Dimension(20, 20));
		panel_1_14.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_14.setBounds(335, 10, 20, 20);
		panel.add(panel_1_14);
		GridBagLayout gbl_panel_1_14 = new GridBagLayout();
		gbl_panel_1_14.columnWidths = new int[]{0, 0};
		gbl_panel_1_14.rowHeights = new int[]{0, 0};
		gbl_panel_1_14.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1_14.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1_14.setLayout(gbl_panel_1_14);

		panel_1_15 = new JPanel();
		panel_1_15.setPreferredSize(new Dimension(20, 20));
		panel_1_15.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_15.setBounds(360, 10, 20, 20);
		panel.add(panel_1_15);
		GridBagLayout gbl_panel_1_15 = new GridBagLayout();
		gbl_panel_1_15.columnWidths = new int[]{0, 0};
		gbl_panel_1_15.rowHeights = new int[]{0, 0};
		gbl_panel_1_15.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1_15.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1_15.setLayout(gbl_panel_1_15);

		panel_2_1 = new JPanel();
		panel_2_1.setPreferredSize(new Dimension(20, 20));
		panel_2_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2_1.setAlignmentX(0.0f);
		panel_2_1.setBounds(10, 32, 20, 20);
		panel.add(panel_2_1);
		GridBagLayout gbl_panel_2_1 = new GridBagLayout();
		gbl_panel_2_1.columnWidths = new int[]{0, 0};
		gbl_panel_2_1.rowHeights = new int[]{0, 0};
		gbl_panel_2_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_2_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2_1.setLayout(gbl_panel_2_1);

		panel_3_1 = new JPanel();
		panel_3_1.setPreferredSize(new Dimension(20, 20));
		panel_3_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3_1.setAlignmentX(0.0f);
		panel_3_1.setBounds(10, 54, 20, 20);
		panel.add(panel_3_1);
		GridBagLayout gbl_panel_3_1 = new GridBagLayout();
		gbl_panel_3_1.columnWidths = new int[]{0, 0};
		gbl_panel_3_1.rowHeights = new int[]{0, 0};
		gbl_panel_3_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_3_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_3_1.setLayout(gbl_panel_3_1);

		panel_4_1 = new JPanel();
		panel_4_1.setPreferredSize(new Dimension(20, 20));
		panel_4_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4_1.setAlignmentX(0.0f);
		panel_4_1.setBounds(10, 76, 20, 20);
		panel.add(panel_4_1);
		GridBagLayout gbl_panel_4_1 = new GridBagLayout();
		gbl_panel_4_1.columnWidths = new int[]{0, 0};
		gbl_panel_4_1.rowHeights = new int[]{0, 0};
		gbl_panel_4_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_4_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_4_1.setLayout(gbl_panel_4_1);

		panel_5_1 = new JPanel();
		panel_5_1.setPreferredSize(new Dimension(20, 20));
		panel_5_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5_1.setAlignmentX(0.0f);
		panel_5_1.setBounds(10, 98, 20, 20);
		panel.add(panel_5_1);
		GridBagLayout gbl_panel_5_1 = new GridBagLayout();
		gbl_panel_5_1.columnWidths = new int[]{0, 0};
		gbl_panel_5_1.rowHeights = new int[]{0, 0};
		gbl_panel_5_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_5_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_5_1.setLayout(gbl_panel_5_1);

		panel_6_1 = new JPanel();
		panel_6_1.setPreferredSize(new Dimension(20, 20));
		panel_6_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6_1.setAlignmentX(0.0f);
		panel_6_1.setBounds(10, 120, 20, 20);
		panel.add(panel_6_1);
		GridBagLayout gbl_panel_6_1 = new GridBagLayout();
		gbl_panel_6_1.columnWidths = new int[]{0, 0};
		gbl_panel_6_1.rowHeights = new int[]{0, 0};
		gbl_panel_6_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_6_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_6_1.setLayout(gbl_panel_6_1);

		panel_7_1 = new JPanel();
		panel_7_1.setPreferredSize(new Dimension(20, 20));
		panel_7_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7_1.setAlignmentX(0.0f);
		panel_7_1.setBounds(10, 142, 20, 20);
		panel.add(panel_7_1);
		GridBagLayout gbl_panel_7_1 = new GridBagLayout();
		gbl_panel_7_1.columnWidths = new int[]{0, 0};
		gbl_panel_7_1.rowHeights = new int[]{0, 0};
		gbl_panel_7_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_7_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_7_1.setLayout(gbl_panel_7_1);

		panel_8_1 = new JPanel();
		panel_8_1.setPreferredSize(new Dimension(20, 20));
		panel_8_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8_1.setAlignmentX(0.0f);
		panel_8_1.setBounds(10, 164, 20, 20);
		panel.add(panel_8_1);
		GridBagLayout gbl_panel_8_1 = new GridBagLayout();
		gbl_panel_8_1.columnWidths = new int[]{0, 0};
		gbl_panel_8_1.rowHeights = new int[]{0, 0};
		gbl_panel_8_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_8_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_8_1.setLayout(gbl_panel_8_1);

		panel_2_2 = new JPanel();
		panel_2_2.setPreferredSize(new Dimension(20, 20));
		panel_2_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2_2.setBounds(35, 32, 20, 20);
		panel.add(panel_2_2);
		GridBagLayout gbl_panel_2_2 = new GridBagLayout();
		gbl_panel_2_2.columnWidths = new int[]{0, 0};
		gbl_panel_2_2.rowHeights = new int[]{0, 0};
		gbl_panel_2_2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_2_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2_2.setLayout(gbl_panel_2_2);

		panel_2_3 = new JPanel();
		panel_2_3.setPreferredSize(new Dimension(20, 20));
		panel_2_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2_3.setBounds(60, 32, 20, 20);
		panel.add(panel_2_3);
		GridBagLayout gbl_panel_2_3 = new GridBagLayout();
		gbl_panel_2_3.columnWidths = new int[]{0, 0};
		gbl_panel_2_3.rowHeights = new int[]{0, 0};
		gbl_panel_2_3.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_2_3.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2_3.setLayout(gbl_panel_2_3);

		panel_2_4 = new JPanel();
		panel_2_4.setPreferredSize(new Dimension(20, 20));
		panel_2_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2_4.setBounds(85, 32, 20, 20);
		panel.add(panel_2_4);
		GridBagLayout gbl_panel_2_4 = new GridBagLayout();
		gbl_panel_2_4.columnWidths = new int[]{0, 0};
		gbl_panel_2_4.rowHeights = new int[]{0, 0};
		gbl_panel_2_4.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_2_4.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2_4.setLayout(gbl_panel_2_4);

		panel_2_5 = new JPanel();
		panel_2_5.setPreferredSize(new Dimension(20, 20));
		panel_2_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2_5.setBounds(110, 32, 20, 20);
		panel.add(panel_2_5);
		GridBagLayout gbl_panel_2_5 = new GridBagLayout();
		gbl_panel_2_5.columnWidths = new int[]{0, 0};
		gbl_panel_2_5.rowHeights = new int[]{0, 0};
		gbl_panel_2_5.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_2_5.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2_5.setLayout(gbl_panel_2_5);

		panel_2_6 = new JPanel();
		panel_2_6.setPreferredSize(new Dimension(20, 20));
		panel_2_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2_6.setBounds(135, 32, 20, 20);
		panel.add(panel_2_6);
		GridBagLayout gbl_panel_2_6 = new GridBagLayout();
		gbl_panel_2_6.columnWidths = new int[]{0, 0};
		gbl_panel_2_6.rowHeights = new int[]{0, 0};
		gbl_panel_2_6.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_2_6.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2_6.setLayout(gbl_panel_2_6);

		panel_2_7 = new JPanel();
		panel_2_7.setPreferredSize(new Dimension(20, 20));
		panel_2_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2_7.setBounds(160, 32, 20, 20);
		panel.add(panel_2_7);
		GridBagLayout gbl_panel_2_7 = new GridBagLayout();
		gbl_panel_2_7.columnWidths = new int[]{0, 0};
		gbl_panel_2_7.rowHeights = new int[]{0, 0};
		gbl_panel_2_7.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_2_7.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2_7.setLayout(gbl_panel_2_7);

		panel_2_8 = new JPanel();
		panel_2_8.setPreferredSize(new Dimension(20, 20));
		panel_2_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2_8.setAlignmentX(0.0f);
		panel_2_8.setBounds(185, 32, 20, 20);
		panel.add(panel_2_8);
		GridBagLayout gbl_panel_2_8 = new GridBagLayout();
		gbl_panel_2_8.columnWidths = new int[]{0, 0};
		gbl_panel_2_8.rowHeights = new int[]{0, 0};
		gbl_panel_2_8.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_2_8.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2_8.setLayout(gbl_panel_2_8);

		panel_2_9 = new JPanel();
		panel_2_9.setPreferredSize(new Dimension(20, 20));
		panel_2_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2_9.setBounds(210, 32, 20, 20);
		panel.add(panel_2_9);
		GridBagLayout gbl_panel_2_9 = new GridBagLayout();
		gbl_panel_2_9.columnWidths = new int[]{0, 0};
		gbl_panel_2_9.rowHeights = new int[]{0, 0};
		gbl_panel_2_9.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_2_9.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2_9.setLayout(gbl_panel_2_9);

		panel_2_10 = new JPanel();
		panel_2_10.setPreferredSize(new Dimension(20, 20));
		panel_2_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2_10.setBounds(235, 32, 20, 20);
		panel.add(panel_2_10);
		GridBagLayout gbl_panel_2_10 = new GridBagLayout();
		gbl_panel_2_10.columnWidths = new int[]{0, 0};
		gbl_panel_2_10.rowHeights = new int[]{0, 0};
		gbl_panel_2_10.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_2_10.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2_10.setLayout(gbl_panel_2_10);

		panel_2_11 = new JPanel();
		panel_2_11.setPreferredSize(new Dimension(20, 20));
		panel_2_11.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2_11.setBounds(260, 32, 20, 20);
		panel.add(panel_2_11);
		GridBagLayout gbl_panel_2_11 = new GridBagLayout();
		gbl_panel_2_11.columnWidths = new int[]{0, 0};
		gbl_panel_2_11.rowHeights = new int[]{0, 0};
		gbl_panel_2_11.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_2_11.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2_11.setLayout(gbl_panel_2_11);

		panel_2_12 = new JPanel();
		panel_2_12.setPreferredSize(new Dimension(20, 20));
		panel_2_12.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2_12.setBounds(285, 32, 20, 20);
		panel.add(panel_2_12);
		GridBagLayout gbl_panel_2_12 = new GridBagLayout();
		gbl_panel_2_12.columnWidths = new int[]{0, 0};
		gbl_panel_2_12.rowHeights = new int[]{0, 0};
		gbl_panel_2_12.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_2_12.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2_12.setLayout(gbl_panel_2_12);

		panel_2_13 = new JPanel();
		panel_2_13.setPreferredSize(new Dimension(20, 20));
		panel_2_13.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2_13.setBounds(310, 32, 20, 20);
		panel.add(panel_2_13);
		GridBagLayout gbl_panel_2_13 = new GridBagLayout();
		gbl_panel_2_13.columnWidths = new int[]{0, 0};
		gbl_panel_2_13.rowHeights = new int[]{0, 0};
		gbl_panel_2_13.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_2_13.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2_13.setLayout(gbl_panel_2_13);

		panel_2_14 = new JPanel();
		panel_2_14.setPreferredSize(new Dimension(20, 20));
		panel_2_14.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2_14.setBounds(335, 32, 20, 20);
		panel.add(panel_2_14);
		GridBagLayout gbl_panel_2_14 = new GridBagLayout();
		gbl_panel_2_14.columnWidths = new int[]{0, 0};
		gbl_panel_2_14.rowHeights = new int[]{0, 0};
		gbl_panel_2_14.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_2_14.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2_14.setLayout(gbl_panel_2_14);

		panel_2_15 = new JPanel();
		panel_2_15.setPreferredSize(new Dimension(20, 20));
		panel_2_15.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2_15.setBounds(360, 32, 20, 20);
		panel.add(panel_2_15);
		GridBagLayout gbl_panel_2_15 = new GridBagLayout();
		gbl_panel_2_15.columnWidths = new int[]{0, 0};
		gbl_panel_2_15.rowHeights = new int[]{0, 0};
		gbl_panel_2_15.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_2_15.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2_15.setLayout(gbl_panel_2_15);

		panel_3_2 = new JPanel();
		panel_3_2.setPreferredSize(new Dimension(20, 20));
		panel_3_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3_2.setBounds(35, 54, 20, 20);
		panel.add(panel_3_2);
		GridBagLayout gbl_panel_3_2 = new GridBagLayout();
		gbl_panel_3_2.columnWidths = new int[]{0, 0};
		gbl_panel_3_2.rowHeights = new int[]{0, 0};
		gbl_panel_3_2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_3_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_3_2.setLayout(gbl_panel_3_2);

		panel_3_3 = new JPanel();
		panel_3_3.setPreferredSize(new Dimension(20, 20));
		panel_3_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3_3.setBounds(60, 54, 20, 20);
		panel.add(panel_3_3);
		GridBagLayout gbl_panel_3_3 = new GridBagLayout();
		gbl_panel_3_3.columnWidths = new int[]{0, 0};
		gbl_panel_3_3.rowHeights = new int[]{0, 0};
		gbl_panel_3_3.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_3_3.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_3_3.setLayout(gbl_panel_3_3);

		panel_3_4 = new JPanel();
		panel_3_4.setPreferredSize(new Dimension(20, 20));
		panel_3_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3_4.setBounds(85, 54, 20, 20);
		panel.add(panel_3_4);
		GridBagLayout gbl_panel_3_4 = new GridBagLayout();
		gbl_panel_3_4.columnWidths = new int[]{0, 0};
		gbl_panel_3_4.rowHeights = new int[]{0, 0};
		gbl_panel_3_4.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_3_4.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_3_4.setLayout(gbl_panel_3_4);

		panel_3_5 = new JPanel();
		panel_3_5.setPreferredSize(new Dimension(20, 20));
		panel_3_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3_5.setBounds(110, 54, 20, 20);
		panel.add(panel_3_5);
		GridBagLayout gbl_panel_3_5 = new GridBagLayout();
		gbl_panel_3_5.columnWidths = new int[]{0, 0};
		gbl_panel_3_5.rowHeights = new int[]{0, 0};
		gbl_panel_3_5.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_3_5.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_3_5.setLayout(gbl_panel_3_5);

		panel_3_6 = new JPanel();
		panel_3_6.setPreferredSize(new Dimension(20, 20));
		panel_3_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3_6.setBounds(135, 54, 20, 20);
		panel.add(panel_3_6);
		GridBagLayout gbl_panel_3_6 = new GridBagLayout();
		gbl_panel_3_6.columnWidths = new int[]{0, 0};
		gbl_panel_3_6.rowHeights = new int[]{0, 0};
		gbl_panel_3_6.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_3_6.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_3_6.setLayout(gbl_panel_3_6);

		panel_3_7 = new JPanel();
		panel_3_7.setPreferredSize(new Dimension(20, 20));
		panel_3_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3_7.setBounds(160, 54, 20, 20);
		panel.add(panel_3_7);
		GridBagLayout gbl_panel_3_7 = new GridBagLayout();
		gbl_panel_3_7.columnWidths = new int[]{0, 0};
		gbl_panel_3_7.rowHeights = new int[]{0, 0};
		gbl_panel_3_7.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_3_7.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_3_7.setLayout(gbl_panel_3_7);

		panel_3_8 = new JPanel();
		panel_3_8.setPreferredSize(new Dimension(20, 20));
		panel_3_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3_8.setAlignmentX(0.0f);
		panel_3_8.setBounds(185, 54, 20, 20);
		panel.add(panel_3_8);
		GridBagLayout gbl_panel_3_8 = new GridBagLayout();
		gbl_panel_3_8.columnWidths = new int[]{0, 0};
		gbl_panel_3_8.rowHeights = new int[]{0, 0};
		gbl_panel_3_8.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_3_8.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_3_8.setLayout(gbl_panel_3_8);

		panel_3_9 = new JPanel();
		panel_3_9.setPreferredSize(new Dimension(20, 20));
		panel_3_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3_9.setBounds(210, 54, 20, 20);
		panel.add(panel_3_9);
		GridBagLayout gbl_panel_3_9 = new GridBagLayout();
		gbl_panel_3_9.columnWidths = new int[]{0, 0};
		gbl_panel_3_9.rowHeights = new int[]{0, 0};
		gbl_panel_3_9.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_3_9.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_3_9.setLayout(gbl_panel_3_9);

		panel_3_10 = new JPanel();
		panel_3_10.setPreferredSize(new Dimension(20, 20));
		panel_3_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3_10.setBounds(235, 54, 20, 20);
		panel.add(panel_3_10);
		GridBagLayout gbl_panel_3_10 = new GridBagLayout();
		gbl_panel_3_10.columnWidths = new int[]{0, 0};
		gbl_panel_3_10.rowHeights = new int[]{0, 0};
		gbl_panel_3_10.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_3_10.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_3_10.setLayout(gbl_panel_3_10);

		panel_3_11 = new JPanel();
		panel_3_11.setPreferredSize(new Dimension(20, 20));
		panel_3_11.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3_11.setBounds(260, 54, 20, 20);
		panel.add(panel_3_11);
		GridBagLayout gbl_panel_3_11 = new GridBagLayout();
		gbl_panel_3_11.columnWidths = new int[]{0, 0};
		gbl_panel_3_11.rowHeights = new int[]{0, 0};
		gbl_panel_3_11.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_3_11.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_3_11.setLayout(gbl_panel_3_11);

		panel_3_12 = new JPanel();
		panel_3_12.setPreferredSize(new Dimension(20, 20));
		panel_3_12.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3_12.setBounds(285, 54, 20, 20);
		panel.add(panel_3_12);
		GridBagLayout gbl_panel_3_12 = new GridBagLayout();
		gbl_panel_3_12.columnWidths = new int[]{0, 0};
		gbl_panel_3_12.rowHeights = new int[]{0, 0};
		gbl_panel_3_12.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_3_12.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_3_12.setLayout(gbl_panel_3_12);

		panel_3_13 = new JPanel();
		panel_3_13.setPreferredSize(new Dimension(20, 20));
		panel_3_13.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3_13.setBounds(310, 54, 20, 20);
		panel.add(panel_3_13);
		GridBagLayout gbl_panel_3_13 = new GridBagLayout();
		gbl_panel_3_13.columnWidths = new int[]{0, 0};
		gbl_panel_3_13.rowHeights = new int[]{0, 0};
		gbl_panel_3_13.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_3_13.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_3_13.setLayout(gbl_panel_3_13);

		panel_3_14 = new JPanel();
		panel_3_14.setPreferredSize(new Dimension(20, 20));
		panel_3_14.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3_14.setBounds(335, 54, 20, 20);
		panel.add(panel_3_14);
		GridBagLayout gbl_panel_3_14 = new GridBagLayout();
		gbl_panel_3_14.columnWidths = new int[]{0, 0};
		gbl_panel_3_14.rowHeights = new int[]{0, 0};
		gbl_panel_3_14.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_3_14.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_3_14.setLayout(gbl_panel_3_14);

		panel_3_15 = new JPanel();
		panel_3_15.setPreferredSize(new Dimension(20, 20));
		panel_3_15.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3_15.setBounds(360, 54, 20, 20);
		panel.add(panel_3_15);
		GridBagLayout gbl_panel_3_15 = new GridBagLayout();
		gbl_panel_3_15.columnWidths = new int[]{0, 0};
		gbl_panel_3_15.rowHeights = new int[]{0, 0};
		gbl_panel_3_15.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_3_15.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_3_15.setLayout(gbl_panel_3_15);

		panel_4_2 = new JPanel();
		panel_4_2.setPreferredSize(new Dimension(20, 20));
		panel_4_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4_2.setBounds(35, 76, 20, 20);
		panel.add(panel_4_2);
		GridBagLayout gbl_panel_4_2 = new GridBagLayout();
		gbl_panel_4_2.columnWidths = new int[]{0, 0};
		gbl_panel_4_2.rowHeights = new int[]{0, 0};
		gbl_panel_4_2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_4_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_4_2.setLayout(gbl_panel_4_2);

		panel_4_3 = new JPanel();
		panel_4_3.setPreferredSize(new Dimension(20, 20));
		panel_4_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4_3.setBounds(60, 76, 20, 20);
		panel.add(panel_4_3);
		GridBagLayout gbl_panel_4_3 = new GridBagLayout();
		gbl_panel_4_3.columnWidths = new int[]{0, 0};
		gbl_panel_4_3.rowHeights = new int[]{0, 0};
		gbl_panel_4_3.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_4_3.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_4_3.setLayout(gbl_panel_4_3);

		panel_4_4 = new JPanel();
		panel_4_4.setPreferredSize(new Dimension(20, 20));
		panel_4_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4_4.setBounds(85, 76, 20, 20);
		panel.add(panel_4_4);
		GridBagLayout gbl_panel_4_4 = new GridBagLayout();
		gbl_panel_4_4.columnWidths = new int[]{0, 0};
		gbl_panel_4_4.rowHeights = new int[]{0, 0};
		gbl_panel_4_4.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_4_4.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_4_4.setLayout(gbl_panel_4_4);

		panel_4_5 = new JPanel();
		panel_4_5.setPreferredSize(new Dimension(20, 20));
		panel_4_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4_5.setBounds(110, 76, 20, 20);
		panel.add(panel_4_5);
		GridBagLayout gbl_panel_4_5 = new GridBagLayout();
		gbl_panel_4_5.columnWidths = new int[]{0, 0};
		gbl_panel_4_5.rowHeights = new int[]{0, 0};
		gbl_panel_4_5.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_4_5.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_4_5.setLayout(gbl_panel_4_5);

		panel_4_6 = new JPanel();
		panel_4_6.setPreferredSize(new Dimension(20, 20));
		panel_4_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4_6.setBounds(135, 76, 20, 20);
		panel.add(panel_4_6);
		GridBagLayout gbl_panel_4_6 = new GridBagLayout();
		gbl_panel_4_6.columnWidths = new int[]{0, 0};
		gbl_panel_4_6.rowHeights = new int[]{0, 0};
		gbl_panel_4_6.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_4_6.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_4_6.setLayout(gbl_panel_4_6);

		panel_4_7 = new JPanel();
		panel_4_7.setPreferredSize(new Dimension(20, 20));
		panel_4_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4_7.setBounds(160, 76, 20, 20);
		panel.add(panel_4_7);
		GridBagLayout gbl_panel_4_7 = new GridBagLayout();
		gbl_panel_4_7.columnWidths = new int[]{0, 0};
		gbl_panel_4_7.rowHeights = new int[]{0, 0};
		gbl_panel_4_7.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_4_7.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_4_7.setLayout(gbl_panel_4_7);

		panel_4_8 = new JPanel();
		panel_4_8.setPreferredSize(new Dimension(20, 20));
		panel_4_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4_8.setAlignmentX(0.0f);
		panel_4_8.setBounds(185, 76, 20, 20);
		panel.add(panel_4_8);
		GridBagLayout gbl_panel_4_8 = new GridBagLayout();
		gbl_panel_4_8.columnWidths = new int[]{0, 0};
		gbl_panel_4_8.rowHeights = new int[]{0, 0};
		gbl_panel_4_8.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_4_8.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_4_8.setLayout(gbl_panel_4_8);

		panel_4_9 = new JPanel();
		panel_4_9.setPreferredSize(new Dimension(20, 20));
		panel_4_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4_9.setBounds(210, 76, 20, 20);
		panel.add(panel_4_9);
		GridBagLayout gbl_panel_4_9 = new GridBagLayout();
		gbl_panel_4_9.columnWidths = new int[]{0, 0};
		gbl_panel_4_9.rowHeights = new int[]{0, 0};
		gbl_panel_4_9.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_4_9.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_4_9.setLayout(gbl_panel_4_9);

		panel_4_10 = new JPanel();
		panel_4_10.setPreferredSize(new Dimension(20, 20));
		panel_4_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4_10.setBounds(235, 76, 20, 20);
		panel.add(panel_4_10);
		GridBagLayout gbl_panel_4_10 = new GridBagLayout();
		gbl_panel_4_10.columnWidths = new int[]{0, 0};
		gbl_panel_4_10.rowHeights = new int[]{0, 0};
		gbl_panel_4_10.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_4_10.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_4_10.setLayout(gbl_panel_4_10);

		panel_4_11 = new JPanel();
		panel_4_11.setPreferredSize(new Dimension(20, 20));
		panel_4_11.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4_11.setBounds(260, 76, 20, 20);
		panel.add(panel_4_11);
		GridBagLayout gbl_panel_4_11 = new GridBagLayout();
		gbl_panel_4_11.columnWidths = new int[]{0, 0};
		gbl_panel_4_11.rowHeights = new int[]{0, 0};
		gbl_panel_4_11.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_4_11.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_4_11.setLayout(gbl_panel_4_11);

		panel_4_12 = new JPanel();
		panel_4_12.setPreferredSize(new Dimension(20, 20));
		panel_4_12.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4_12.setBounds(285, 76, 20, 20);
		panel.add(panel_4_12);
		GridBagLayout gbl_panel_4_12 = new GridBagLayout();
		gbl_panel_4_12.columnWidths = new int[]{0, 0};
		gbl_panel_4_12.rowHeights = new int[]{0, 0};
		gbl_panel_4_12.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_4_12.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_4_12.setLayout(gbl_panel_4_12);

		panel_4_13 = new JPanel();
		panel_4_13.setPreferredSize(new Dimension(20, 20));
		panel_4_13.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4_13.setBounds(310, 76, 20, 20);
		panel.add(panel_4_13);
		GridBagLayout gbl_panel_4_13 = new GridBagLayout();
		gbl_panel_4_13.columnWidths = new int[]{0, 0};
		gbl_panel_4_13.rowHeights = new int[]{0, 0};
		gbl_panel_4_13.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_4_13.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_4_13.setLayout(gbl_panel_4_13);

		panel_4_14 = new JPanel();
		panel_4_14.setPreferredSize(new Dimension(20, 20));
		panel_4_14.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4_14.setBounds(335, 76, 20, 20);
		panel.add(panel_4_14);
		GridBagLayout gbl_panel_4_14 = new GridBagLayout();
		gbl_panel_4_14.columnWidths = new int[]{0, 0};
		gbl_panel_4_14.rowHeights = new int[]{0, 0};
		gbl_panel_4_14.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_4_14.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_4_14.setLayout(gbl_panel_4_14);

		panel_4_15 = new JPanel();
		panel_4_15.setPreferredSize(new Dimension(20, 20));
		panel_4_15.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4_15.setBounds(360, 76, 20, 20);
		panel.add(panel_4_15);
		GridBagLayout gbl_panel_4_15 = new GridBagLayout();
		gbl_panel_4_15.columnWidths = new int[]{0, 0};
		gbl_panel_4_15.rowHeights = new int[]{0, 0};
		gbl_panel_4_15.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_4_15.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_4_15.setLayout(gbl_panel_4_15);

		panel_5_2 = new JPanel();
		panel_5_2.setPreferredSize(new Dimension(20, 20));
		panel_5_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5_2.setBounds(35, 98, 20, 20);
		panel.add(panel_5_2);
		GridBagLayout gbl_panel_5_2 = new GridBagLayout();
		gbl_panel_5_2.columnWidths = new int[]{0, 0};
		gbl_panel_5_2.rowHeights = new int[]{0, 0};
		gbl_panel_5_2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_5_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_5_2.setLayout(gbl_panel_5_2);

		panel_5_3 = new JPanel();
		panel_5_3.setPreferredSize(new Dimension(20, 20));
		panel_5_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5_3.setBounds(60, 98, 20, 20);
		panel.add(panel_5_3);
		GridBagLayout gbl_panel_5_3 = new GridBagLayout();
		gbl_panel_5_3.columnWidths = new int[]{0, 0};
		gbl_panel_5_3.rowHeights = new int[]{0, 0};
		gbl_panel_5_3.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_5_3.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_5_3.setLayout(gbl_panel_5_3);

		panel_5_4 = new JPanel();
		panel_5_4.setPreferredSize(new Dimension(20, 20));
		panel_5_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5_4.setBounds(85, 98, 20, 20);
		panel.add(panel_5_4);
		GridBagLayout gbl_panel_5_4 = new GridBagLayout();
		gbl_panel_5_4.columnWidths = new int[]{0, 0};
		gbl_panel_5_4.rowHeights = new int[]{0, 0};
		gbl_panel_5_4.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_5_4.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_5_4.setLayout(gbl_panel_5_4);

		panel_5_5 = new JPanel();
		panel_5_5.setBackground(SystemColor.menu);
		panel_5_5.setPreferredSize(new Dimension(20, 20));
		panel_5_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5_5.setBounds(110, 98, 20, 20);
		panel.add(panel_5_5);
		GridBagLayout gbl_panel_5_5 = new GridBagLayout();
		gbl_panel_5_5.columnWidths = new int[]{0, 0};
		gbl_panel_5_5.rowHeights = new int[]{0, 0};
		gbl_panel_5_5.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_5_5.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_5_5.setLayout(gbl_panel_5_5);

		panel_5_6 = new JPanel();
		panel_5_6.setPreferredSize(new Dimension(20, 20));
		panel_5_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5_6.setBounds(135, 98, 20, 20);
		panel.add(panel_5_6);
		GridBagLayout gbl_panel_5_6 = new GridBagLayout();
		gbl_panel_5_6.columnWidths = new int[]{0, 0};
		gbl_panel_5_6.rowHeights = new int[]{0, 0};
		gbl_panel_5_6.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_5_6.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_5_6.setLayout(gbl_panel_5_6);

		panel_5_7 = new JPanel();
		panel_5_7.setPreferredSize(new Dimension(20, 20));
		panel_5_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5_7.setBounds(160, 98, 20, 20);
		panel.add(panel_5_7);
		GridBagLayout gbl_panel_5_7 = new GridBagLayout();
		gbl_panel_5_7.columnWidths = new int[]{0, 0};
		gbl_panel_5_7.rowHeights = new int[]{0, 0};
		gbl_panel_5_7.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_5_7.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_5_7.setLayout(gbl_panel_5_7);

		panel_5_8 = new JPanel();
		panel_5_8.setPreferredSize(new Dimension(20, 20));
		panel_5_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5_8.setAlignmentX(0.0f);
		panel_5_8.setBounds(185, 98, 20, 20);
		panel.add(panel_5_8);
		GridBagLayout gbl_panel_5_8 = new GridBagLayout();
		gbl_panel_5_8.columnWidths = new int[]{0, 0};
		gbl_panel_5_8.rowHeights = new int[]{0, 0};
		gbl_panel_5_8.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_5_8.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_5_8.setLayout(gbl_panel_5_8);

		panel_5_9 = new JPanel();
		panel_5_9.setPreferredSize(new Dimension(20, 20));
		panel_5_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5_9.setBounds(210, 98, 20, 20);
		panel.add(panel_5_9);
		GridBagLayout gbl_panel_5_9 = new GridBagLayout();
		gbl_panel_5_9.columnWidths = new int[]{0, 0};
		gbl_panel_5_9.rowHeights = new int[]{0, 0};
		gbl_panel_5_9.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_5_9.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_5_9.setLayout(gbl_panel_5_9);

		panel_5_10 = new JPanel();
		panel_5_10.setPreferredSize(new Dimension(20, 20));
		panel_5_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5_10.setBounds(235, 98, 20, 20);
		panel.add(panel_5_10);
		GridBagLayout gbl_panel_5_10 = new GridBagLayout();
		gbl_panel_5_10.columnWidths = new int[]{0, 0};
		gbl_panel_5_10.rowHeights = new int[]{0, 0};
		gbl_panel_5_10.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_5_10.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_5_10.setLayout(gbl_panel_5_10);

		panel_5_11 = new JPanel();
		panel_5_11.setPreferredSize(new Dimension(20, 20));
		panel_5_11.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5_11.setBounds(260, 98, 20, 20);
		panel.add(panel_5_11);
		GridBagLayout gbl_panel_5_11 = new GridBagLayout();
		gbl_panel_5_11.columnWidths = new int[]{0, 0};
		gbl_panel_5_11.rowHeights = new int[]{0, 0};
		gbl_panel_5_11.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_5_11.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_5_11.setLayout(gbl_panel_5_11);

		JPanel panel_5_12 = new JPanel();
		panel_5_12.setPreferredSize(new Dimension(20, 20));
		panel_5_12.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5_12.setBounds(285, 98, 20, 20);
		panel.add(panel_5_12);
		GridBagLayout gbl_panel_5_12 = new GridBagLayout();
		gbl_panel_5_12.columnWidths = new int[]{0, 0};
		gbl_panel_5_12.rowHeights = new int[]{0, 0};
		gbl_panel_5_12.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_5_12.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_5_12.setLayout(gbl_panel_5_12);

		panel_5_13 = new JPanel();
		panel_5_13.setPreferredSize(new Dimension(20, 20));
		panel_5_13.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5_13.setBounds(310, 98, 20, 20);
		panel.add(panel_5_13);
		GridBagLayout gbl_panel_5_13 = new GridBagLayout();
		gbl_panel_5_13.columnWidths = new int[]{0, 0};
		gbl_panel_5_13.rowHeights = new int[]{0, 0};
		gbl_panel_5_13.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_5_13.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_5_13.setLayout(gbl_panel_5_13);

		panel_5_14 = new JPanel();
		panel_5_14.setPreferredSize(new Dimension(20, 20));
		panel_5_14.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5_14.setBounds(335, 98, 20, 20);
		panel.add(panel_5_14);
		GridBagLayout gbl_panel_5_14 = new GridBagLayout();
		gbl_panel_5_14.columnWidths = new int[]{0, 0};
		gbl_panel_5_14.rowHeights = new int[]{0, 0};
		gbl_panel_5_14.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_5_14.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_5_14.setLayout(gbl_panel_5_14);

		panel_5_15 = new JPanel();
		panel_5_15.setPreferredSize(new Dimension(20, 20));
		panel_5_15.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5_15.setBounds(360, 98, 20, 20);
		panel.add(panel_5_15);
		GridBagLayout gbl_panel_5_15 = new GridBagLayout();
		gbl_panel_5_15.columnWidths = new int[]{0, 0};
		gbl_panel_5_15.rowHeights = new int[]{0, 0};
		gbl_panel_5_15.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_5_15.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_5_15.setLayout(gbl_panel_5_15);

		panel_6_2 = new JPanel();
		panel_6_2.setPreferredSize(new Dimension(20, 20));
		panel_6_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6_2.setBounds(35, 120, 20, 20);
		panel.add(panel_6_2);
		GridBagLayout gbl_panel_6_2 = new GridBagLayout();
		gbl_panel_6_2.columnWidths = new int[]{0, 0};
		gbl_panel_6_2.rowHeights = new int[]{0, 0};
		gbl_panel_6_2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_6_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_6_2.setLayout(gbl_panel_6_2);

		panel_6_3 = new JPanel();
		panel_6_3.setPreferredSize(new Dimension(20, 20));
		panel_6_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6_3.setBounds(60, 120, 20, 20);
		panel.add(panel_6_3);
		GridBagLayout gbl_panel_6_3 = new GridBagLayout();
		gbl_panel_6_3.columnWidths = new int[]{0, 0};
		gbl_panel_6_3.rowHeights = new int[]{0, 0};
		gbl_panel_6_3.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_6_3.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_6_3.setLayout(gbl_panel_6_3);

		panel_6_4 = new JPanel();
		panel_6_4.setPreferredSize(new Dimension(20, 20));
		panel_6_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6_4.setBounds(85, 120, 20, 20);
		panel.add(panel_6_4);
		GridBagLayout gbl_panel_6_4 = new GridBagLayout();
		gbl_panel_6_4.columnWidths = new int[]{0, 0};
		gbl_panel_6_4.rowHeights = new int[]{0, 0};
		gbl_panel_6_4.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_6_4.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_6_4.setLayout(gbl_panel_6_4);

		panel_6_5 = new JPanel();
		panel_6_5.setPreferredSize(new Dimension(20, 20));
		panel_6_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6_5.setBounds(110, 120, 20, 20);
		panel.add(panel_6_5);
		GridBagLayout gbl_panel_6_5 = new GridBagLayout();
		gbl_panel_6_5.columnWidths = new int[]{0, 0};
		gbl_panel_6_5.rowHeights = new int[]{0, 0};
		gbl_panel_6_5.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_6_5.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_6_5.setLayout(gbl_panel_6_5);

		panel_6_6 = new JPanel();
		panel_6_6.setPreferredSize(new Dimension(20, 20));
		panel_6_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6_6.setBounds(135, 120, 20, 20);
		panel.add(panel_6_6);
		GridBagLayout gbl_panel_6_6 = new GridBagLayout();
		gbl_panel_6_6.columnWidths = new int[]{0, 0};
		gbl_panel_6_6.rowHeights = new int[]{0, 0};
		gbl_panel_6_6.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_6_6.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_6_6.setLayout(gbl_panel_6_6);

		panel_6_7 = new JPanel();
		panel_6_7.setPreferredSize(new Dimension(20, 20));
		panel_6_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6_7.setBounds(160, 120, 20, 20);
		panel.add(panel_6_7);
		GridBagLayout gbl_panel_6_7 = new GridBagLayout();
		gbl_panel_6_7.columnWidths = new int[]{0, 0};
		gbl_panel_6_7.rowHeights = new int[]{0, 0};
		gbl_panel_6_7.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_6_7.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_6_7.setLayout(gbl_panel_6_7);

		panel_6_8 = new JPanel();
		panel_6_8.setPreferredSize(new Dimension(20, 20));
		panel_6_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6_8.setAlignmentX(0.0f);
		panel_6_8.setBounds(185, 120, 20, 20);
		panel.add(panel_6_8);
		GridBagLayout gbl_panel_6_8 = new GridBagLayout();
		gbl_panel_6_8.columnWidths = new int[]{0, 0};
		gbl_panel_6_8.rowHeights = new int[]{0, 0};
		gbl_panel_6_8.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_6_8.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_6_8.setLayout(gbl_panel_6_8);

		panel_6_9 = new JPanel();
		panel_6_9.setPreferredSize(new Dimension(20, 20));
		panel_6_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6_9.setBounds(210, 120, 20, 20);
		panel.add(panel_6_9);
		GridBagLayout gbl_panel_6_9 = new GridBagLayout();
		gbl_panel_6_9.columnWidths = new int[]{0, 0};
		gbl_panel_6_9.rowHeights = new int[]{0, 0};
		gbl_panel_6_9.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_6_9.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_6_9.setLayout(gbl_panel_6_9);

		panel_6_10 = new JPanel();
		panel_6_10.setPreferredSize(new Dimension(20, 20));
		panel_6_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6_10.setBounds(235, 120, 20, 20);
		panel.add(panel_6_10);
		GridBagLayout gbl_panel_6_10 = new GridBagLayout();
		gbl_panel_6_10.columnWidths = new int[]{0, 0};
		gbl_panel_6_10.rowHeights = new int[]{0, 0};
		gbl_panel_6_10.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_6_10.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_6_10.setLayout(gbl_panel_6_10);

		panel_6_11 = new JPanel();
		panel_6_11.setPreferredSize(new Dimension(20, 20));
		panel_6_11.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6_11.setBounds(260, 120, 20, 20);
		panel.add(panel_6_11);
		GridBagLayout gbl_panel_6_11 = new GridBagLayout();
		gbl_panel_6_11.columnWidths = new int[]{0, 0};
		gbl_panel_6_11.rowHeights = new int[]{0, 0};
		gbl_panel_6_11.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_6_11.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_6_11.setLayout(gbl_panel_6_11);

		panel_6_12 = new JPanel();
		panel_6_12.setPreferredSize(new Dimension(20, 20));
		panel_6_12.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6_12.setBounds(285, 120, 20, 20);
		panel.add(panel_6_12);
		GridBagLayout gbl_panel_6_12 = new GridBagLayout();
		gbl_panel_6_12.columnWidths = new int[]{0, 0};
		gbl_panel_6_12.rowHeights = new int[]{0, 0};
		gbl_panel_6_12.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_6_12.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_6_12.setLayout(gbl_panel_6_12);

		panel_6_13 = new JPanel();
		panel_6_13.setPreferredSize(new Dimension(20, 20));
		panel_6_13.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6_13.setBounds(310, 120, 20, 20);
		panel.add(panel_6_13);
		GridBagLayout gbl_panel_6_13 = new GridBagLayout();
		gbl_panel_6_13.columnWidths = new int[]{0, 0};
		gbl_panel_6_13.rowHeights = new int[]{0, 0};
		gbl_panel_6_13.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_6_13.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_6_13.setLayout(gbl_panel_6_13);

		panel_6_14 = new JPanel();
		panel_6_14.setPreferredSize(new Dimension(20, 20));
		panel_6_14.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6_14.setBounds(335, 120, 20, 20);
		panel.add(panel_6_14);
		GridBagLayout gbl_panel_6_14 = new GridBagLayout();
		gbl_panel_6_14.columnWidths = new int[]{0, 0};
		gbl_panel_6_14.rowHeights = new int[]{0, 0};
		gbl_panel_6_14.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_6_14.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_6_14.setLayout(gbl_panel_6_14);

		panel_6_15 = new JPanel();
		panel_6_15.setPreferredSize(new Dimension(20, 20));
		panel_6_15.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6_15.setBounds(360, 120, 20, 20);
		panel.add(panel_6_15);
		GridBagLayout gbl_panel_6_15 = new GridBagLayout();
		gbl_panel_6_15.columnWidths = new int[]{0, 0};
		gbl_panel_6_15.rowHeights = new int[]{0, 0};
		gbl_panel_6_15.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_6_15.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_6_15.setLayout(gbl_panel_6_15);

		panel_7_2 = new JPanel();
		panel_7_2.setPreferredSize(new Dimension(20, 20));
		panel_7_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7_2.setBounds(35, 142, 20, 20);
		panel.add(panel_7_2);
		GridBagLayout gbl_panel_7_2 = new GridBagLayout();
		gbl_panel_7_2.columnWidths = new int[]{0, 0};
		gbl_panel_7_2.rowHeights = new int[]{0, 0};
		gbl_panel_7_2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_7_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_7_2.setLayout(gbl_panel_7_2);

		panel_7_3 = new JPanel();
		panel_7_3.setPreferredSize(new Dimension(20, 20));
		panel_7_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7_3.setBounds(60, 142, 20, 20);
		panel.add(panel_7_3);
		GridBagLayout gbl_panel_7_3 = new GridBagLayout();
		gbl_panel_7_3.columnWidths = new int[]{0, 0};
		gbl_panel_7_3.rowHeights = new int[]{0, 0};
		gbl_panel_7_3.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_7_3.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_7_3.setLayout(gbl_panel_7_3);

		panel_7_4 = new JPanel();
		panel_7_4.setPreferredSize(new Dimension(20, 20));
		panel_7_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7_4.setBounds(85, 142, 20, 20);
		panel.add(panel_7_4);
		GridBagLayout gbl_panel_7_4 = new GridBagLayout();
		gbl_panel_7_4.columnWidths = new int[]{0, 0};
		gbl_panel_7_4.rowHeights = new int[]{0, 0};
		gbl_panel_7_4.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_7_4.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_7_4.setLayout(gbl_panel_7_4);

		panel_7_5 = new JPanel();
		panel_7_5.setPreferredSize(new Dimension(20, 20));
		panel_7_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7_5.setBounds(110, 142, 20, 20);
		panel.add(panel_7_5);
		GridBagLayout gbl_panel_7_5 = new GridBagLayout();
		gbl_panel_7_5.columnWidths = new int[]{0, 0};
		gbl_panel_7_5.rowHeights = new int[]{0, 0};
		gbl_panel_7_5.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_7_5.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_7_5.setLayout(gbl_panel_7_5);

		panel_7_6 = new JPanel();
		panel_7_6.setPreferredSize(new Dimension(20, 20));
		panel_7_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7_6.setBounds(135, 142, 20, 20);
		panel.add(panel_7_6);
		GridBagLayout gbl_panel_7_6 = new GridBagLayout();
		gbl_panel_7_6.columnWidths = new int[]{0, 0};
		gbl_panel_7_6.rowHeights = new int[]{0, 0};
		gbl_panel_7_6.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_7_6.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_7_6.setLayout(gbl_panel_7_6);

		panel_7_7 = new JPanel();
		panel_7_7.setPreferredSize(new Dimension(20, 20));
		panel_7_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7_7.setBounds(160, 142, 20, 20);
		panel.add(panel_7_7);
		GridBagLayout gbl_panel_7_7 = new GridBagLayout();
		gbl_panel_7_7.columnWidths = new int[]{0, 0};
		gbl_panel_7_7.rowHeights = new int[]{0, 0};
		gbl_panel_7_7.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_7_7.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_7_7.setLayout(gbl_panel_7_7);

		panel_7_8 = new JPanel();
		panel_7_8.setPreferredSize(new Dimension(20, 20));
		panel_7_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7_8.setAlignmentX(0.0f);
		panel_7_8.setBounds(185, 142, 20, 20);
		panel.add(panel_7_8);
		GridBagLayout gbl_panel_7_8 = new GridBagLayout();
		gbl_panel_7_8.columnWidths = new int[]{0, 0};
		gbl_panel_7_8.rowHeights = new int[]{0, 0};
		gbl_panel_7_8.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_7_8.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_7_8.setLayout(gbl_panel_7_8);

		panel_7_9 = new JPanel();
		panel_7_9.setPreferredSize(new Dimension(20, 20));
		panel_7_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7_9.setBounds(210, 142, 20, 20);
		panel.add(panel_7_9);
		GridBagLayout gbl_panel_7_9 = new GridBagLayout();
		gbl_panel_7_9.columnWidths = new int[]{0, 0};
		gbl_panel_7_9.rowHeights = new int[]{0, 0};
		gbl_panel_7_9.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_7_9.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_7_9.setLayout(gbl_panel_7_9);

		panel_7_10 = new JPanel();
		panel_7_10.setPreferredSize(new Dimension(20, 20));
		panel_7_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7_10.setBounds(235, 142, 20, 20);
		panel.add(panel_7_10);
		GridBagLayout gbl_panel_7_10 = new GridBagLayout();
		gbl_panel_7_10.columnWidths = new int[]{0, 0};
		gbl_panel_7_10.rowHeights = new int[]{0, 0};
		gbl_panel_7_10.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_7_10.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_7_10.setLayout(gbl_panel_7_10);

		panel_7_11 = new JPanel();
		panel_7_11.setPreferredSize(new Dimension(20, 20));
		panel_7_11.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7_11.setBounds(260, 142, 20, 20);
		panel.add(panel_7_11);
		GridBagLayout gbl_panel_7_11 = new GridBagLayout();
		gbl_panel_7_11.columnWidths = new int[]{0, 0};
		gbl_panel_7_11.rowHeights = new int[]{0, 0};
		gbl_panel_7_11.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_7_11.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_7_11.setLayout(gbl_panel_7_11);

		panel_7_12 = new JPanel();
		panel_7_12.setPreferredSize(new Dimension(20, 20));
		panel_7_12.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7_12.setBounds(285, 142, 20, 20);
		panel.add(panel_7_12);
		GridBagLayout gbl_panel_7_12 = new GridBagLayout();
		gbl_panel_7_12.columnWidths = new int[]{0, 0};
		gbl_panel_7_12.rowHeights = new int[]{0, 0};
		gbl_panel_7_12.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_7_12.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_7_12.setLayout(gbl_panel_7_12);

		panel_7_13 = new JPanel();
		panel_7_13.setPreferredSize(new Dimension(20, 20));
		panel_7_13.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7_13.setBounds(310, 142, 20, 20);
		panel.add(panel_7_13);
		GridBagLayout gbl_panel_7_13 = new GridBagLayout();
		gbl_panel_7_13.columnWidths = new int[]{0, 0};
		gbl_panel_7_13.rowHeights = new int[]{0, 0};
		gbl_panel_7_13.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_7_13.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_7_13.setLayout(gbl_panel_7_13);

		panel_7_14 = new JPanel();
		panel_7_14.setPreferredSize(new Dimension(20, 20));
		panel_7_14.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7_14.setBounds(335, 142, 20, 20);
		panel.add(panel_7_14);
		GridBagLayout gbl_panel_7_14 = new GridBagLayout();
		gbl_panel_7_14.columnWidths = new int[]{0, 0};
		gbl_panel_7_14.rowHeights = new int[]{0, 0};
		gbl_panel_7_14.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_7_14.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_7_14.setLayout(gbl_panel_7_14);

		panel_7_15 = new JPanel();
		panel_7_15.setPreferredSize(new Dimension(20, 20));
		panel_7_15.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7_15.setBounds(360, 142, 20, 20);
		panel.add(panel_7_15);
		GridBagLayout gbl_panel_7_15 = new GridBagLayout();
		gbl_panel_7_15.columnWidths = new int[]{0, 0};
		gbl_panel_7_15.rowHeights = new int[]{0, 0};
		gbl_panel_7_15.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_7_15.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_7_15.setLayout(gbl_panel_7_15);

		panel_8_2 = new JPanel();
		panel_8_2.setPreferredSize(new Dimension(20, 20));
		panel_8_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8_2.setBounds(35, 164, 20, 20);
		panel.add(panel_8_2);
		GridBagLayout gbl_panel_8_2 = new GridBagLayout();
		gbl_panel_8_2.columnWidths = new int[]{0, 0};
		gbl_panel_8_2.rowHeights = new int[]{0, 0};
		gbl_panel_8_2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_8_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_8_2.setLayout(gbl_panel_8_2);

		panel_8_3 = new JPanel();
		panel_8_3.setPreferredSize(new Dimension(20, 20));
		panel_8_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8_3.setBounds(60, 164, 20, 20);
		panel.add(panel_8_3);
		GridBagLayout gbl_panel_8_3 = new GridBagLayout();
		gbl_panel_8_3.columnWidths = new int[]{0, 0};
		gbl_panel_8_3.rowHeights = new int[]{0, 0};
		gbl_panel_8_3.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_8_3.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_8_3.setLayout(gbl_panel_8_3);

		panel_8_4 = new JPanel();
		panel_8_4.setPreferredSize(new Dimension(20, 20));
		panel_8_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8_4.setBounds(85, 164, 20, 20);
		panel.add(panel_8_4);
		GridBagLayout gbl_panel_8_4 = new GridBagLayout();
		gbl_panel_8_4.columnWidths = new int[]{0, 0};
		gbl_panel_8_4.rowHeights = new int[]{0, 0};
		gbl_panel_8_4.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_8_4.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_8_4.setLayout(gbl_panel_8_4);

		panel_8_5 = new JPanel();
		panel_8_5.setPreferredSize(new Dimension(20, 20));
		panel_8_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8_5.setBounds(110, 164, 20, 20);
		panel.add(panel_8_5);
		GridBagLayout gbl_panel_8_5 = new GridBagLayout();
		gbl_panel_8_5.columnWidths = new int[]{0, 0};
		gbl_panel_8_5.rowHeights = new int[]{0, 0};
		gbl_panel_8_5.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_8_5.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_8_5.setLayout(gbl_panel_8_5);

		panel_8_6 = new JPanel();
		panel_8_6.setPreferredSize(new Dimension(20, 20));
		panel_8_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8_6.setBounds(135, 164, 20, 20);
		panel.add(panel_8_6);
		GridBagLayout gbl_panel_8_6 = new GridBagLayout();
		gbl_panel_8_6.columnWidths = new int[]{0, 0};
		gbl_panel_8_6.rowHeights = new int[]{0, 0};
		gbl_panel_8_6.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_8_6.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_8_6.setLayout(gbl_panel_8_6);

		panel_8_7 = new JPanel();
		panel_8_7.setPreferredSize(new Dimension(20, 20));
		panel_8_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8_7.setBounds(160, 164, 20, 20);
		panel.add(panel_8_7);
		GridBagLayout gbl_panel_8_7 = new GridBagLayout();
		gbl_panel_8_7.columnWidths = new int[]{0, 0};
		gbl_panel_8_7.rowHeights = new int[]{0, 0};
		gbl_panel_8_7.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_8_7.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_8_7.setLayout(gbl_panel_8_7);

		panel_8_8 = new JPanel();
		panel_8_8.setPreferredSize(new Dimension(20, 20));
		panel_8_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8_8.setAlignmentX(0.0f);
		panel_8_8.setBounds(185, 164, 20, 20);
		panel.add(panel_8_8);
		GridBagLayout gbl_panel_8_8 = new GridBagLayout();
		gbl_panel_8_8.columnWidths = new int[]{0, 0};
		gbl_panel_8_8.rowHeights = new int[]{0, 0};
		gbl_panel_8_8.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_8_8.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_8_8.setLayout(gbl_panel_8_8);

		panel_8_9 = new JPanel();
		panel_8_9.setPreferredSize(new Dimension(20, 20));
		panel_8_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8_9.setBounds(210, 164, 20, 20);
		panel.add(panel_8_9);
		GridBagLayout gbl_panel_8_9 = new GridBagLayout();
		gbl_panel_8_9.columnWidths = new int[]{0, 0};
		gbl_panel_8_9.rowHeights = new int[]{0, 0};
		gbl_panel_8_9.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_8_9.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_8_9.setLayout(gbl_panel_8_9);

		panel_8_10 = new JPanel();
		panel_8_10.setPreferredSize(new Dimension(20, 20));
		panel_8_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8_10.setBounds(235, 164, 20, 20);
		panel.add(panel_8_10);
		GridBagLayout gbl_panel_8_10 = new GridBagLayout();
		gbl_panel_8_10.columnWidths = new int[]{0, 0};
		gbl_panel_8_10.rowHeights = new int[]{0, 0};
		gbl_panel_8_10.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_8_10.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_8_10.setLayout(gbl_panel_8_10);

		panel_8_11 = new JPanel();
		panel_8_11.setPreferredSize(new Dimension(20, 20));
		panel_8_11.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8_11.setBounds(260, 164, 20, 20);
		panel.add(panel_8_11);
		GridBagLayout gbl_panel_8_11 = new GridBagLayout();
		gbl_panel_8_11.columnWidths = new int[]{0, 0};
		gbl_panel_8_11.rowHeights = new int[]{0, 0};
		gbl_panel_8_11.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_8_11.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_8_11.setLayout(gbl_panel_8_11);

		panel_8_12 = new JPanel();
		panel_8_12.setPreferredSize(new Dimension(20, 20));
		panel_8_12.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8_12.setBounds(285, 164, 20, 20);
		panel.add(panel_8_12);
		GridBagLayout gbl_panel_8_12 = new GridBagLayout();
		gbl_panel_8_12.columnWidths = new int[]{0, 0};
		gbl_panel_8_12.rowHeights = new int[]{0, 0};
		gbl_panel_8_12.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_8_12.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_8_12.setLayout(gbl_panel_8_12);

		panel_8_13 = new JPanel();
		panel_8_13.setPreferredSize(new Dimension(20, 20));
		panel_8_13.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8_13.setBounds(310, 164, 20, 20);
		panel.add(panel_8_13);
		GridBagLayout gbl_panel_8_13 = new GridBagLayout();
		gbl_panel_8_13.columnWidths = new int[]{0, 0};
		gbl_panel_8_13.rowHeights = new int[]{0, 0};
		gbl_panel_8_13.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_8_13.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_8_13.setLayout(gbl_panel_8_13);

		panel_8_14 = new JPanel();
		panel_8_14.setPreferredSize(new Dimension(20, 20));
		panel_8_14.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8_14.setBounds(335, 164, 20, 20);
		panel.add(panel_8_14);
		GridBagLayout gbl_panel_8_14 = new GridBagLayout();
		gbl_panel_8_14.columnWidths = new int[]{0, 0};
		gbl_panel_8_14.rowHeights = new int[]{0, 0};
		gbl_panel_8_14.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_8_14.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_8_14.setLayout(gbl_panel_8_14);

		panel_8_15 = new JPanel();
		panel_8_15.setPreferredSize(new Dimension(20, 20));
		panel_8_15.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8_15.setBounds(360, 164, 20, 20);
		panel.add(panel_8_15);
		GridBagLayout gbl_panel_8_15 = new GridBagLayout();
		gbl_panel_8_15.columnWidths = new int[]{0, 0};
		gbl_panel_8_15.rowHeights = new int[]{0, 0};
		gbl_panel_8_15.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_8_15.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_8_15.setLayout(gbl_panel_8_15);
		contentPane.setLayout(null);
		contentPane.add(panel);

		JPanel panel_121 = new JPanel();
		panel_121.setBorder(null);
		panel_121.setBackground(new Color(230, 230, 250));
		panel_121.setBounds(10, 30, 20, 390);
		contentPane.add(panel_121);
		panel_121.setLayout(null);

		JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setBackground(new Color(230, 230, 250));
		formattedTextField_1.setBorder(null);
		formattedTextField_1.setBounds(0, 10, 20, 20);
		panel_121.add(formattedTextField_1);
		formattedTextField_1.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField_1.setText("1");

		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBackground(new Color(230, 230, 250));
		formattedTextField.setText("8");
		formattedTextField.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField.setBorder(null);
		formattedTextField.setBounds(0, 164, 20, 20);
		panel_121.add(formattedTextField);

		JFormattedTextField formattedTextField_2 = new JFormattedTextField();
		formattedTextField_2.setBackground(new Color(230, 230, 250));
		formattedTextField_2.setText("7");
		formattedTextField_2.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField_2.setBorder(null);
		formattedTextField_2.setBounds(0, 142, 20, 20);
		panel_121.add(formattedTextField_2);

		JFormattedTextField formattedTextField_3 = new JFormattedTextField();
		formattedTextField_3.setBackground(new Color(230, 230, 250));
		formattedTextField_3.setText("6");
		formattedTextField_3.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField_3.setBorder(null);
		formattedTextField_3.setBounds(0, 120, 20, 20);
		panel_121.add(formattedTextField_3);

		JFormattedTextField formattedTextField_4 = new JFormattedTextField();
		formattedTextField_4.setBackground(new Color(230, 230, 250));
		formattedTextField_4.setText("5");
		formattedTextField_4.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField_4.setBorder(null);
		formattedTextField_4.setBounds(0, 98, 20, 20);
		panel_121.add(formattedTextField_4);

		JFormattedTextField formattedTextField_5 = new JFormattedTextField();
		formattedTextField_5.setBackground(new Color(230, 230, 250));
		formattedTextField_5.setText("4");
		formattedTextField_5.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField_5.setBorder(null);
		formattedTextField_5.setBounds(0, 76, 20, 20);
		panel_121.add(formattedTextField_5);

		JFormattedTextField formattedTextField_6 = new JFormattedTextField();
		formattedTextField_6.setBackground(new Color(230, 230, 250));
		formattedTextField_6.setText("3");
		formattedTextField_6.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField_6.setBorder(null);
		formattedTextField_6.setBounds(0, 54, 20, 20);
		panel_121.add(formattedTextField_6);

		JFormattedTextField formattedTextField_7 = new JFormattedTextField();
		formattedTextField_7.setBackground(new Color(230, 230, 250));
		formattedTextField_7.setText("2");
		formattedTextField_7.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField_7.setBorder(null);
		formattedTextField_7.setBounds(0, 32, 20, 20);
		panel_121.add(formattedTextField_7);
		
		JPanel panel_122 = new JPanel();
		panel_122.setBackground(new Color(230, 230, 250));
		panel_122.setBorder(null);
		panel_122.setBounds(30, 10, 390, 20);
		contentPane.add(panel_122);
		panel_122.setLayout(null);
		
		JLabel label = new JLabel("1");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 0, 20, 20);
		panel_122.add(label);
		
		JLabel label_1 = new JLabel("2");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(35, 0, 20, 20);
		panel_122.add(label_1);
		
		JLabel label_2 = new JLabel("3");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(60, 0, 20, 20);
		panel_122.add(label_2);
		
		JLabel label_3 = new JLabel("4");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(85, 0, 20, 20);
		panel_122.add(label_3);
		
		JLabel label_4 = new JLabel("5");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(110, 0, 20, 20);
		panel_122.add(label_4);
		
		JLabel label_5 = new JLabel("6");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(135, 0, 20, 20);
		panel_122.add(label_5);
		
		JLabel label_6 = new JLabel("7");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(160, 0, 20, 20);
		panel_122.add(label_6);
		
		JLabel label_7 = new JLabel("8");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(185, 0, 20, 20);
		panel_122.add(label_7);
		
		JLabel label_8 = new JLabel("9");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setBounds(210, 0, 20, 20);
		panel_122.add(label_8);
		
		JLabel label_9 = new JLabel("10");
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setBounds(235, 0, 20, 20);
		panel_122.add(label_9);
		
		JLabel label_10 = new JLabel("11");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setBounds(260, 0, 20, 20);
		panel_122.add(label_10);
		
		JLabel label_11 = new JLabel("12");
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setBounds(285, 0, 20, 20);
		panel_122.add(label_11);
		
		JLabel label_12 = new JLabel("13");
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setBounds(310, 0, 20, 20);
		panel_122.add(label_12);
		
		JLabel label_13 = new JLabel("14");
		label_13.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setBounds(335, 0, 20, 20);
		panel_122.add(label_13);
		
		JLabel label_14 = new JLabel("15");
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setBounds(360, 0, 20, 20);
		panel_122.add(label_14);



		panelList = new ArrayList<>();
		panelList.add(panel_1_1);
		panelList.add(panel_1_2);
		panelList.add(panel_1_3);
		panelList.add(panel_1_4);
		panelList.add(panel_1_5);
		panelList.add(panel_1_6);
		panelList.add(panel_1_7);
		panelList.add(panel_1_8);
		panelList.add(panel_1_9);
		panelList.add(panel_1_10);
		panelList.add(panel_1_11);
		panelList.add(panel_1_12);
		panelList.add(panel_1_13);
		panelList.add(panel_1_14);
		panelList.add(panel_1_15);
		panelList.add(panel_2_1);
		panelList.add(panel_2_2);
		panelList.add(panel_2_3);
		panelList.add(panel_2_4);
		panelList.add(panel_2_5);
		panelList.add(panel_2_6);
		panelList.add(panel_2_7);
		panelList.add(panel_2_8);
		panelList.add(panel_2_9);
		panelList.add(panel_2_10);
		panelList.add(panel_2_11);
		panelList.add(panel_2_12);
		panelList.add(panel_2_13);
		panelList.add(panel_2_14);
		panelList.add(panel_2_15);
		panelList.add(panel_3_1);
		panelList.add(panel_3_2);
		panelList.add(panel_3_3);
		panelList.add(panel_3_4);
		panelList.add(panel_3_5);
		panelList.add(panel_3_6);
		panelList.add(panel_3_7);
		panelList.add(panel_3_8);
		panelList.add(panel_3_9);
		panelList.add(panel_3_10);
		panelList.add(panel_3_11);
		panelList.add(panel_3_12);
		panelList.add(panel_3_13);
		panelList.add(panel_3_14);
		panelList.add(panel_3_15);
		panelList.add(panel_4_1);
		
		BasicArrowButton nextHOF = new BasicArrowButton(0);
		nextHOF.setBackground(new Color(192, 192, 192));
		nextHOF.setBounds(615, 385, 40, 35);
		contentPane.add(nextHOF);
		
		BasicArrowButton previousHOF = new BasicArrowButton(0);
		previousHOF.setBackground(new Color(192, 192, 192));
		previousHOF.setBounds(477, 385, 40, 35);
		contentPane.add(previousHOF);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(459, 73, 207, 2);
		contentPane.add(separator);
		
		JLabel currentLevel = new JLabel("Level: ");
		currentLevel.setBounds(468, 14, 83, 16);
		contentPane.add(currentLevel);
		
		JLabel numberOfLives = new JLabel("Lives:");
		numberOfLives.setBounds(563, 14, 83, 16);
		contentPane.add(numberOfLives);
		
		JLabel playerScore = new JLabel("Score:");
		playerScore.setBounds(468, 45, 83, 16);
		contentPane.add(playerScore);
		
		JLabel lblHallOfFame = new JLabel("Hall Of Fame:");
		lblHallOfFame.setBounds(469, 88, 186, 16);
		contentPane.add(lblHallOfFame);
		
		currentGameName = new JLabel("Current game:");
		currentGameName.setBounds(469, 118, 186, 16);
		contentPane.add(currentGameName);
		
		displayHOF = new JLabel();
		displayHOF.setBounds(464, 140, 202, 232);
		contentPane.add(displayHOF);
		
		lblPrevious = new JLabel("Previous");
		lblPrevious.setBounds(472, 433, 61, 16);
		contentPane.add(lblPrevious);
		
		lblNext = new JLabel("Next");
		lblNext.setBounds(622, 433, 31, 16);
		contentPane.add(lblNext);
		
		
		displayHOF(); //TO BE INCLUDED IN REFRESH DATA LATER
		
	}
	
	private void mntmLogOutActionPerformed(ActionEvent evt) {
		Block223Controller.logout();
		RegisterLoginPage loginpage = new RegisterLoginPage();
		loginpage.setVisible(true);
		this.setVisible(false);
	}
	
	private void displayHOF() {
		error = "ello";
		int start = 0;
		int end;
		
		TOHallOfFame randomHOF = new TOHallOfFame("mlej8");
		TOHallOfFameEntry player1 = new TOHallOfFameEntry(0, "Mike", 10000000, randomHOF);
		TOHallOfFameEntry player2 = new TOHallOfFameEntry(1, "Tony", 50, randomHOF);
		TOHallOfFameEntry player3 = new TOHallOfFameEntry(2, "Victor", 40, randomHOF);
		TOHallOfFameEntry player4 = new TOHallOfFameEntry(3, "ShiTong", 30, randomHOF);
		TOHallOfFameEntry player5 = new TOHallOfFameEntry(4, "JWS", 20, randomHOF);
		TOHallOfFameEntry player6 = new TOHallOfFameEntry(5, "William Zhang", 0, randomHOF);
		TOHallOfFameEntry player7 = new TOHallOfFameEntry(6, "trash", 0, randomHOF);
		TOHallOfFameEntry player8 = new TOHallOfFameEntry(7, "trash", 0, randomHOF);
		TOHallOfFameEntry player9 = new TOHallOfFameEntry(8, "trash", 0, randomHOF);
		TOHallOfFameEntry player10 = new TOHallOfFameEntry(9, "trash", 0, randomHOF);
		TOHallOfFameEntry player11 = new TOHallOfFameEntry(10, "trash", 0, randomHOF);

		randomHOF.addEntry(player1);
		randomHOF.addEntry(player2);
		randomHOF.addEntry(player3);
		randomHOF.addEntry(player4);
		randomHOF.addEntry(player5);
		randomHOF.addEntry(player6);
		randomHOF.addEntry(player7);
		randomHOF.addEntry(player8);
		randomHOF.addEntry(player9);
		randomHOF.addEntry(player10);
		randomHOF.addEntry(player11);
		
//		
//test it by creating a random hall of fame 
		
	
//		System.out.println(randomHOF);
		
		//set
		//Hof.getGamename();
		//The player views the first ten entries of the hall of fame and can browse to the next/previous ten entries in the hall of fame.
//		try {
//			HOF = Block223Controller.getHallOfFame(1, 100); //returns a TOHallOfFame
//		} catch (InvalidInputException e ) {
//			error = e.getMessage();
//			JOptionPane.showMessageDialog(null, error);
//		}
//		
		if(randomHOF.numberOfEntries() > 10) {
			end = 10;
		} else {
			end = randomHOF.numberOfEntries();
		}
		
		String hallOfFame = "";
		
		if(randomHOF.numberOfEntries() > 0) {
		for (int index = 0; index < end; index++) {
			hallOfFame += randomHOF.getEntry(index).getPosition() + randomHOF.getEntry(index).getPlayername() + randomHOF.getEntry(index).getScore() + "/n" ;	
			}
		}
				
		currentGameName.setText("Current game:" + randomHOF.getGamename() );
		displayHOF.setText(error);
		
		
	}


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