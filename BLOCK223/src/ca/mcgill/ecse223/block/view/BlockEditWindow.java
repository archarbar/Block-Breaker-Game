package ca.mcgill.ecse223.block.view;

import ca.mcgill.ecse.btms.controller.BtmsController;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.TOBlock;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.controller.TOGridCell;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BlockEditWindow extends JFrame {

	private JPanel contentPane;
	private JButton btnCreateBlock;
	private JButton btnDeleteBlock;
	private JComboBox cbBlocks;

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
		setBounds(100, 100, 686, 598);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btnCreateBlock = new JButton("Create Block");
		btnCreateBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "helo");
				addBlockperformed();
			}
		});

		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		btnDeleteBlock = new JButton("Delete Block");
		
		JLabel lblListOfBlocks = new JLabel("List of Blocks:");
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 390, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnCreateBlock, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnDeleteBlock, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblListOfBlocks)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)))
					.addGap(9))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnCreateBlock)
								.addComponent(btnDeleteBlock))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblListOfBlocks)))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 390, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(148, Short.MAX_VALUE))
		);
		
		cbBlocks = new JComboBox();
		cbBlocks.setModel(new DefaultComboBoxModel(new String[] {"1 - Block1", "2 - Blockred"}));
		cbBlocks.setToolTipText("Select a block");
		scrollPane.setViewportView(cbBlocks);
		contentPane.setLayout(gl_contentPane);
	}

	private void addBlockButtonActionPerformed() {
		// TODO Auto-generated method stub
		
		
		Block223Controller.addBlock();
	}
	private void positionBlockButtonActionPerformed( ) {
		
		String error = null;
		
		try {
			Block223Controller.positionBlock();
			}
		catch (InvalidInputException e) {
			error = e.getMessage();
		}
		refreshData();
		
	}
	private void moveBlockButtonActionPerformed( ) {
		try {
			Block223Controller.positionBlock();
		}
		catch (InvalidInputException e) {
			error = e.getMessage();}
		
		refreshData();
		
	}
	
private void addDriverButtonActionPerformed(java.awt.event.ActionEvent evt) {
	// clear error message
	error = null;
	
	// call the controller
	try {
		BtmsController.createDriver(driverNameTextField.getText());
	} catch (InvalidInputException e) {
		error = e.getMessage();
	}
	
	// update visuals
	refreshData();
}



	
}
