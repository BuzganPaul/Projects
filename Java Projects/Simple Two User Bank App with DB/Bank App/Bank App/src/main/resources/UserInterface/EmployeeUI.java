package UserInterface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DataSourceLevel.EmployeeMapper;
import DomainModelLevel.EmployeeLogic;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class EmployeeUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtId;
	private JTextField txtPosition;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EmployeeUI dialog = new EmployeeUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EmployeeUI() {
		setTitle("CRUD on Employee");
		setBounds(100, 100, 483, 345);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblEmployee = new JLabel("Employee:");
		lblEmployee.setForeground(Color.LIGHT_GRAY);
		lblEmployee.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblEmployee.setBounds(10, 11, 88, 23);
		contentPanel.add(lblEmployee);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel.setBounds(42, 45, 56, 23);
		contentPanel.add(lblNewLabel);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setForeground(Color.LIGHT_GRAY);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblId.setBounds(71, 79, 27, 23);
		contentPanel.add(lblId);
		
		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setForeground(Color.LIGHT_GRAY);
		lblPosition.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblPosition.setBounds(28, 113, 70, 23);
		contentPanel.add(lblPosition);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(EmployeeLogic.getCombo()));
		comboBox.setBounds(108, 16, 197, 20);
		contentPanel.add(comboBox);
		
		txtName = new JTextField();
		txtName.setText("name");
		txtName.setBounds(108, 50, 197, 20);
		contentPanel.add(txtName);
		txtName.setColumns(10);
		
		txtId = new JTextField();
		txtId.setText("id");
		txtId.setBounds(108, 84, 197, 20);
		contentPanel.add(txtId);
		txtId.setColumns(10);
		
		txtPosition = new JTextField();
		txtPosition.setText("position");
		txtPosition.setBounds(108, 118, 197, 20);
		contentPanel.add(txtPosition);
		txtPosition.setColumns(10);
		
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
								String auxPosition = txtPosition.getText().toString();
								String auxSelect = comboBox.getSelectedItem().toString();
								String parts[] = auxSelect.split(" ");
								//System.out.println(parts[parts.length-1]);
								
								if(EmployeeLogic.correctData(String.valueOf(auxID), auxName, auxPosition, "000"))
										{
											EmployeeLogic.empOperation(auxID, auxName, auxPosition, 0, "Add");
										}
								else {
									System.out.println("Employee Add Error - Wrong Inputs");
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
								String auxPosition = txtPosition.getText().toString();
								String auxSelect = comboBox.getSelectedItem().toString();
								String parts[] = auxSelect.split(" ");
								//System.out.println(parts[parts.length-1]);
								
								if(EmployeeLogic.correctData(String.valueOf(auxID), auxName, auxPosition, parts[parts.length-1]))
										{
											EmployeeLogic.empOperation(auxID, auxName, auxPosition, Integer.parseInt(parts[parts.length-1]), "Update");
										}
								else {
									System.out.println("Employee Update Error - Wrong Inputs");
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
								//int auxID = Integer.parseInt(txtId.getText().toString());
								String auxName = txtName.getText().toString();
								String auxPosition = txtPosition.getText().toString();
								String auxSelect = comboBox.getSelectedItem().toString();
								String parts[] = auxSelect.split(" ");
								//System.out.println(parts[parts.length-1]);
								
								if(EmployeeLogic.correctData(String.valueOf(0), auxName, auxPosition, parts[parts.length-1]))
										{
											EmployeeLogic.empOperation(Integer.parseInt(parts[parts.length-1]), auxName, auxPosition, Integer.parseInt(parts[parts.length-1]), "Delete");
										}
								else {
									System.out.println("Employee Delete Error - Wrong Inputs");
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
