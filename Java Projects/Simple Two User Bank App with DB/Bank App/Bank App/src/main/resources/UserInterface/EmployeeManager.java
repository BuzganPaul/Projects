package UserInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import DomainModelLevel.ClientAccountLogic;
import DomainModelLevel.ClientDataLogic;
import DomainModelLevel.EmployeeLogic;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JDialog;

public class EmployeeManager extends JFrame {

	private JPanel contentPane;
	private JScrollPane table;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public EmployeeManager(String name, String nameEmp) {
		setTitle(name + " Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 777, 462);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEmployees = new JLabel(name +":");
		lblEmployees.setForeground(Color.LIGHT_GRAY);
		lblEmployees.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblEmployees.setBounds(10, 11, 570, 23);
		contentPane.add(lblEmployees);
		
		
		switch(name) {
		case "Employee": table = new JScrollPane(EmployeeLogic.getTable());
						 break;
			
		case "Client Data": table = new JScrollPane(ClientDataLogic.getTable());
		 					break;
			
		case "Client Account": table = new JScrollPane(ClientAccountLogic.getTable());
			 				   break;
		}
		
		table.setBounds(10, 45, 723, 296);
		contentPane.add(table);
		
		JButton btnManage = new JButton("Manage");
		btnManage.setBounds(10, 372, 89, 23);
		contentPane.add(btnManage);
		btnManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1)
		    {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							switch(name) {
							case "Employee": EmployeeUI dialog = new EmployeeUI();
											 dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
											 dialog.setVisible(true);
											 break;
								
							case "Client Data": ClientDataUI dialog2 = new ClientDataUI(nameEmp);
							 					dialog2.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							 					dialog2.setVisible(true);
							 					break;
								
							case "Client Account": ClientAccountUI dialog3 = new ClientAccountUI(nameEmp);
								 				   dialog3.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								 				   dialog3.setVisible(true);
								 				   break;
								
							}
							
								
							
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					
				});
		    }
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(644, 372, 89, 23);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
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
