package UserInterface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DomainModelLevel.ClientDataLogic;
import DomainModelLevel.EmployeeLogic;
import DomainModelLevel.FieldChecker;
import DomainModelLevel.OperationLogic;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ClientDataUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtIdNumber;
	private JTextField txtAdress;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public ClientDataUI(String employee) {
		setTitle("Client Data CRUD");
		setBounds(100, 100, 463, 344);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblClient = new JLabel("Client:");
		lblClient.setForeground(Color.LIGHT_GRAY);
		lblClient.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblClient.setBounds(58, 11, 53, 23);
		contentPanel.add(lblClient);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setForeground(Color.LIGHT_GRAY);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblId.setBounds(84, 45, 27, 23);
		contentPanel.add(lblId);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setForeground(Color.LIGHT_GRAY);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblName.setBounds(55, 79, 56, 23);
		contentPanel.add(lblName);
		
		JLabel lblNewLabel = new JLabel("ID Number:");
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel.setBounds(10, 113, 101, 23);
		contentPanel.add(lblNewLabel);
		
		JLabel lblAdress = new JLabel("Adress:");
		lblAdress.setForeground(Color.LIGHT_GRAY);
		lblAdress.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblAdress.setBounds(49, 147, 62, 23);
		contentPanel.add(lblAdress);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(ClientDataLogic.getCombo()));
		comboBox.setBounds(121, 16, 195, 20);
		contentPanel.add(comboBox);
		
		txtId = new JTextField();
		txtId.setText("id");
		txtId.setBounds(121, 50, 195, 20);
		contentPanel.add(txtId);
		txtId.setColumns(10);
		
		txtName = new JTextField();
		txtName.setText("name");
		txtName.setBounds(121, 84, 195, 20);
		contentPanel.add(txtName);
		txtName.setColumns(10);
		
		txtIdNumber = new JTextField();
		txtIdNumber.setText("id number");
		txtIdNumber.setBounds(121, 118, 195, 20);
		contentPanel.add(txtIdNumber);
		txtIdNumber.setColumns(10);
		
		txtAdress = new JTextField();
		txtAdress.setText("adress");
		txtAdress.setBounds(121, 152, 195, 20);
		contentPanel.add(txtAdress);
		txtAdress.setColumns(10);
		
		JLabel lbltheAddButton = new JLabel("*the Add Button doesn't take data from the combo box");
		lbltheAddButton.setForeground(Color.LIGHT_GRAY);
		lbltheAddButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbltheAddButton.setBounds(10, 181, 427, 14);
		contentPanel.add(lbltheAddButton);
		
		JLabel lbltheUpdateButton = new JLabel("**the Update Button take the data from the combo box and all the fields");
		lbltheUpdateButton.setForeground(Color.LIGHT_GRAY);
		lbltheUpdateButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbltheUpdateButton.setBounds(10, 206, 427, 14);
		contentPanel.add(lbltheUpdateButton);
		
		JLabel lbltheDeleteButton = new JLabel("***the Delete Button doesn't take data from any fields");
		lbltheDeleteButton.setForeground(Color.LIGHT_GRAY);
		lbltheDeleteButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbltheDeleteButton.setBounds(10, 231, 417, 14);
		contentPanel.add(lbltheDeleteButton);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.DARK_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnAdd = new JButton("Add");
			buttonPane.add(btnAdd);
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ad1)
			    {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								int auxID = Integer.parseInt(txtId.getText().toString());
								String auxName = txtName.getText().toString();
								long auxIDNumber = Long.parseLong(txtIdNumber.getText().toString());
								String auxaddress = txtAdress.getText().toString();
								String auxSelect = comboBox.getSelectedItem().toString();
								String parts[] = auxSelect.split(" ");
								String parts2[] = employee.split(" ");
								//System.out.println(parts[parts.length-1]);
								
								if(ClientDataLogic.correctData(String.valueOf(auxID), auxName, String.valueOf(auxIDNumber), auxaddress, "000"))
										{
											ClientDataLogic.clientDataOperation(auxID, auxName, auxIDNumber, auxaddress, Integer.parseInt(parts[parts.length-1]), "Add");
											//OperationLogic.newOperation(FieldChecker.getID(), FieldChecker.getDate(), Integer.parseInt(parts2[parts2.length-1]), "Add Client Data", 1, 0, "Add");
										}
								else {
									System.out.println("Client Add Error - Wrong Inputs");
								}
								
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						
					});
			    }
			});
			
			
			JButton btnUpdate = new JButton("Update");
			buttonPane.add(btnUpdate);
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ad1)
			    {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								int auxID = Integer.parseInt(txtId.getText().toString());
								String auxName = txtName.getText().toString();
								Long auxIDNumber = Long.parseLong(txtIdNumber.getText().toString());
								String auxaddress = txtAdress.getText().toString();
								String auxSelect = comboBox.getSelectedItem().toString();
								String parts[] = auxSelect.split(" ");
								String parts2[] = employee.split(" ");
								//System.out.println(parts[parts.length-1]);
								
								if(ClientDataLogic.correctData(String.valueOf(auxID), auxName, String.valueOf(auxIDNumber), auxaddress, parts[parts.length-1]))
										{
									ClientDataLogic.clientDataOperation(auxID, auxName, auxIDNumber, auxaddress, Integer.parseInt(parts[parts.length-1]), "Update");
									//OperationLogic.newOperation(FieldChecker.getID(), FieldChecker.getDate(), Integer.parseInt(parts2[parts2.length-1]), "Update Client Data", Integer.parseInt(parts[parts.length-1]), 0, "Add");
										}
								else {
									System.out.println("Client Data Update Error - Wrong Inputs");
								}
								
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						
					});
			    }
			});
			
			JButton btnDelete = new JButton("Delete");
			buttonPane.add(btnDelete);
			
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ad1)
			    {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								String auxSelect = comboBox.getSelectedItem().toString();
								String parts[] = auxSelect.split(" ");
								String parts2[] = employee.split(" ");
								//System.out.println(parts[parts.length-1]);
								
								if(ClientDataLogic.correctData("0", "Delete", "0", "Delete", parts[parts.length-1]))
										{
											ClientDataLogic.clientDataOperation(Integer.parseInt(parts[parts.length-1]), "Delete", Integer.parseInt(parts[parts.length-1]), "Delete", Integer.parseInt(parts[parts.length-1]), "Delete");
											//OperationLogic.newOperation(FieldChecker.getID(), FieldChecker.getDate(), Integer.parseInt(parts2[parts2.length-1]), "Delete Client Data", Integer.parseInt(parts[parts.length-1]), 0, "Add");
											
										}
								else {
									System.out.println("Client Data Delete Error - Wrong Inputs");
								}
								
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						
					});
			    }
			});
			
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e1)
				    {
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									dispose();
									
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}
							
						});
				    }
				});
			}
		}
	}
}
