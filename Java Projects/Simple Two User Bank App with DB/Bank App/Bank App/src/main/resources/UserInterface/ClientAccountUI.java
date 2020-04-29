package UserInterface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DomainModelLevel.ClientAccountLogic;
import DomainModelLevel.ClientDataLogic;
import DomainModelLevel.FieldChecker;
import DomainModelLevel.OperationLogic;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ClientAccountUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtType;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public ClientAccountUI(String employee) {
		setTitle("Client Account CRUD");
		setBounds(100, 100, 561, 372);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblClientAccount = new JLabel("Client Account:");
		lblClientAccount.setForeground(Color.LIGHT_GRAY);
		lblClientAccount.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblClientAccount.setBounds(55, 11, 126, 23);
		contentPanel.add(lblClientAccount);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setForeground(Color.LIGHT_GRAY);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblId.setBounds(154, 38, 27, 23);
		contentPanel.add(lblId);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setForeground(Color.LIGHT_GRAY);
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblType.setBounds(133, 72, 48, 23);
		contentPanel.add(lblType);
		
		JLabel lblAmmountOfMoney = new JLabel("Ammount of Money:");
		lblAmmountOfMoney.setForeground(Color.LIGHT_GRAY);
		lblAmmountOfMoney.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblAmmountOfMoney.setBounds(10, 106, 171, 23);
		contentPanel.add(lblAmmountOfMoney);
		
		JLabel lblDataOfCreation = new JLabel("Data of Creation:");
		lblDataOfCreation.setForeground(Color.LIGHT_GRAY);
		lblDataOfCreation.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblDataOfCreation.setBounds(38, 140, 143, 23);
		contentPanel.add(lblDataOfCreation);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(ClientAccountLogic.getCombo()));
		comboBox.setBounds(191, 16, 171, 20);
		contentPanel.add(comboBox);
		
		txtId = new JTextField();
		txtId.setText("id");
		txtId.setBounds(191, 43, 171, 20);
		contentPanel.add(txtId);
		txtId.setColumns(10);
		
		txtType = new JTextField();
		txtType.setText("type");
		txtType.setBounds(191, 77, 171, 20);
		contentPanel.add(txtType);
		txtType.setColumns(10);
		
		textField = new JTextField();
		textField.setText("000");
		textField.setBounds(191, 111, 171, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("yyyy/MM/dd");
		textField_1.setBounds(191, 145, 63, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label = new JLabel("$");
		label.setForeground(Color.LIGHT_GRAY);
		label.setFont(new Font("Tahoma", Font.PLAIN, 19));
		label.setBounds(372, 106, 10, 23);
		contentPanel.add(label);
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
		
		JLabel lbltheIdThat = new JLabel("****the id that is introduced is the client one and not the account one");
		lbltheIdThat.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbltheIdThat.setForeground(Color.LIGHT_GRAY);
		lbltheIdThat.setBounds(10, 250, 396, 14);
		contentPanel.add(lbltheIdThat);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.DARK_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAdd = new JButton("Add");
				buttonPane.add(btnAdd);
				btnAdd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ad1)
				    {
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									int auxID = Integer.parseInt(txtId.getText().toString());
									String auxType = txtType.getText().toString();
									int auxMoney= Integer.parseInt(textField.getText().toString());
									String date = textField_1.getText().toString();
									String auxSelect = comboBox.getSelectedItem().toString();
									String parts[] = auxSelect.split(" ");
									String parts2[] = employee.split(" ");
									//System.out.println(parts[parts.length-1]);
									
									if(ClientAccountLogic.correctData(String.valueOf(auxID), auxType, String.valueOf(auxMoney), date, String.valueOf(parts2[parts2.length-1]), "000"))
											{
												int ID = FieldChecker.getID();
												ClientAccountLogic.clientDataOperation(auxID, auxType, auxMoney, date, ID, Integer.parseInt(parts[0]), "Add");
												OperationLogic.newOperation(FieldChecker.getID(), FieldChecker.getDate(), Integer.parseInt(parts2[parts2.length-1]), "Add Client Account", ID, 0, "Add");
											}
									else {
										System.out.println("Account Add Error - Wrong Inputs");
									}
									
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}
							
						});
				    }
				});
			}
			{
				JButton btnUpdate = new JButton("Update");
				buttonPane.add(btnUpdate);
				btnUpdate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ad1)
				    {
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									int auxID = Integer.parseInt(txtId.getText().toString());
									String auxType = txtType.getText().toString();
									int auxMoney= Integer.parseInt(textField.getText().toString());
									String date = textField_1.getText().toString();
									String auxSelect = comboBox.getSelectedItem().toString();
									String parts[] = auxSelect.split(" ");
									String parts2[] = employee.split(" ");
									//System.out.println(parts[parts.length-1]);
									
									if(ClientAccountLogic.correctData(String.valueOf(auxID), auxType, String.valueOf(auxMoney), date, String.valueOf(parts2[parts2.length-1]), "000"))
											{
												//int ID = FieldChecker.getID();
												ClientAccountLogic.clientDataOperation(auxID, auxType, auxMoney, date, Integer.parseInt(parts[0]), Integer.parseInt(parts[0]), "Update");
												OperationLogic.newOperation(FieldChecker.getID(), FieldChecker.getDate(), Integer.parseInt(parts2[parts2.length-1]), "Update Client Account", Integer.parseInt(parts[0]), 0, "Add");
											}
									else {
										System.out.println("Account Update Error - Wrong Inputs");
									}
									
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}
							
						});
				    }
				});
			}
			{
				JButton btnDelete = new JButton("Delete");
				buttonPane.add(btnDelete);
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ad1)
				    {
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {

									String auxType = txtType.getText().toString();
									int auxMoney= Integer.parseInt(textField.getText().toString());
									String date = textField_1.getText().toString();
									String auxSelect = comboBox.getSelectedItem().toString();
									String parts[] = auxSelect.split(" ");
									String parts2[] = employee.split(" ");
									//System.out.println(parts[parts.length-1]);
									
									if(ClientAccountLogic.correctData("0", "Delete", "0", "2020/01/01", String.valueOf(parts2[parts2.length-1]), "000"))
											{
												//int ID = FieldChecker.getID();
												int auxID = Integer.parseInt(txtId.getText().toString());
												ClientAccountLogic.clientDataOperation(auxID, auxType, auxMoney, date, Integer.parseInt(parts[0]), Integer.parseInt(parts[0]), "Delete");
												OperationLogic.newOperation(FieldChecker.getID(), FieldChecker.getDate(), Integer.parseInt(parts2[parts2.length-1]), "Delete Client Account", Integer.parseInt(parts[0]), 0, "Add");
											}
									else {
										System.out.println("Account Update Error - Wrong Inputs");
									}
									
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}
							
						});
				    }
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
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
