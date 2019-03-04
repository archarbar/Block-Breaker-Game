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
import java.util.HashMap;
import java.util.List;
import java.awt.event.ActionEvent;

public class BlockEditWindow extends JFrame {

	private JPanel contentPane;
	private JButton btnCreateBlock;
	private JButton btnDeleteBlock;
	private JComboBox cbBlocks;
	// data elements
	private String error = null;
	//blocks
	private HashMap<Integer, TOBlock> blocks;
	//grid cells
	private HashMap<Integer, TOGridCell> gridCells;
	//games
	private HashMap<Integer, TOGame> games;
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
				addBlockButtonActionPerformed();
				
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
		
		int selectedBlock = cbBlocks.getSelectedIndex();
		
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
	private void positionBlockButtonActionPerformed(java.awt.event.ActionEvent evt) {
		
		String error = "";
		int selectedBlock = toBlockComboBox.getSelectedIndex();
		int level = comboBox.getSelectIndex();
		int newPosition = JComboBox.getSelectedIndex();
		
		List<TOGridCell> newPosition = Block223Controller.getBlocksAtLevelOfCurrentDesignableGame(23);//set jpanel 
		if (selectedBlock == null) {
		error = "Block needs to be selected in order to placed in the game!";}
	
		int selectedAssignment = selectedBlock.getId(); //replace assignmentlist par JPanel list?? CA VA ETRE LE PANEL QUI VA ETRE CHOISI
		if (selectedAssignment < 0)
		error = error + "A grid cell needs to be selected for block! ";
		error = error.trim();
		
		if (error == "") {
		try {
			Block223Controller.positionBlock(blocks.get(selectedBlock).getId(), level, newPosition.getGridHorizontalPosition(), newPosition.getGridVerticalPosition());
			}
		catch (InvalidInputException e) {
			error = e.getMessage();
		}
	}
		//update visuals
		refreshData();
	}

	private void moveBlockButtonActionPerformed( ) {
		String error = "";
		
		TOGridCell oldPosition = //chosed JPanel(Old checkbox)
		TOGridCell newPosition = //chosed new JPanel (new checkbox)
		int level = JComboBox.getSelectedIndex();
		
		//chose checkbox 
		
		//for this checkbox for the second checkbox == newlocation  
		
		if (error == "") {
		try {
			Block223Controller.moveBlock(level, oldPosition.getGridHorizontalPosition(), oldPosition.getGridVerticalPosition(),
					newPosition.getGridHorizontalPosition(), newPosition.getGridVerticalPosition());
		}
		catch (InvalidInputException e) {
			error = e.getMessage();}
		}		
		refreshData();
	}	
	
	private void refreshData() {		

		if (error == null || error.length() == 0) {
		
			
		blocks = new HashMap<Integer, TOBlock>();
		cb.removeAllItems();
		Integer index = 0;
		for (TODriver driver : BtmsController.getDrivers()) {
				drivers.put(index, driver.getId());
				driverToggleList.addItem("#" + driver.getId() + " " + driver.getName());
				index++;
		};
		driverToggleList.setSelectedIndex(-1);	
		
		int level = 1;	
		gridCells = new HashMap<Integer, TOGridCell>();
		badsfsfasds(name for jcombobox that has all to grid cells).removeAllItems();
		Integer index = 0;
		for (TOGridCell gridCell : Block223Controller.getBlocksAtLevelOfCurrentDesignableGame(level)) {
			gridCells.put(index, block.getId());
			badsfsfasds.addItem("Hor Position: "gridCell.getGridHorizontalPosition() + "Ver Position: " gridCell.getGridVerticalPosition() + "ID: " gridCell.getId());
			index++;
		};
		badsfsfasds.setSelectedIndex(-1);
		
		
		
		
		
		}
	}


	
}
