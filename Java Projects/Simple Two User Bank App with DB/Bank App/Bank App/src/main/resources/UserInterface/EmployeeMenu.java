package UserInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import DomainModelLevel.EmployeeLogic;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;

public class EmployeeMenu extends JFrame{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EmployeeMenu() {
		setTitle("Employee Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 410, 462);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEmployee = new JLabel("Employee: ");
		lblEmployee.setForeground(Color.LIGHT_GRAY);
		lblEmployee.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmployee.setBounds(10, 11, 247, 23);
		contentPane.add(lblEmployee);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(EmployeeLogic.getCombo()));
		comboBox.setBounds(90, 14, 147, 20);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Client Data Manager");
		btnNewButton.setBounds(90, 84, 183, 62);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1)
		    {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							EmployeeManager dialog = new EmployeeManager("Client Data", comboBox.getSelectedItem().toString());
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					
				});
		    }
		});
		
		
		JButton btnClientAccount = new JButton("Client Account Manager");
		btnClientAccount.setBounds(90, 157, 183, 62);
		contentPane.add(btnClientAccount);
		btnClientAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1)
		    {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							EmployeeManager dialog = new EmployeeManager("Client Account", comboBox.getSelectedItem().toString());
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					
				});
		    }
		});
		
		JButton btnMoneyTransfer1 = new JButton("Money Transfer");
		btnMoneyTransfer1.setBounds(90, 230, 183, 62);
		contentPane.add(btnMoneyTransfer1);
		btnMoneyTransfer1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1)
		    {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							MoneyTransfer dialog = new MoneyTransfer(comboBox.getSelectedItem().toString());
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					
				});
		    }
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(280, 389, 89, 23);
		contentPane.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
		    {
				EventQueue.invokeLater(new Runnable() {

					public void run() {
						try {
							OriginalFrame.main();
							JComponent comp = (JComponent) e.getSource();
							  Window win = SwingUtilities.getWindowAncestor(comp);
							  win.dispose();

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
				});
		    }
		});
		
		JButton btnPayBills = new JButton("Pay Bills");
		btnPayBills.setBounds(90, 303, 183, 62);
		contentPane.add(btnPayBills);
		btnPayBills.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1)
		    {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {

							PayBill dialog = new PayBill(comboBox.getSelectedItem().toString());
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);

							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					
				});
		    }
		});
		

	}


}
