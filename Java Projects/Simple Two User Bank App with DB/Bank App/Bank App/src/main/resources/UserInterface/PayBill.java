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
import DomainModelLevel.EmployeeOperation;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class PayBill extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("rawtypes")
	public PayBill(String emp) {

		setTitle("Bills Payment");
		setBounds(100, 100, 375, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblProvider = new JLabel("Provider:");
		lblProvider.setForeground(Color.LIGHT_GRAY);
		lblProvider.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblProvider.setBounds(56, 29, 75, 23);
		contentPanel.add(lblProvider);
		
		JLabel lblBankAccount = new JLabel("Bank Account:");
		lblBankAccount.setForeground(Color.LIGHT_GRAY);
		lblBankAccount.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblBankAccount.setBounds(10, 63, 121, 23);
		contentPanel.add(lblBankAccount);
		
		JLabel lblAmmount = new JLabel("Ammount:");
		lblAmmount.setForeground(Color.LIGHT_GRAY);
		lblAmmount.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblAmmount.setBounds(43, 97, 88, 23);
		contentPanel.add(lblAmmount);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Electricity", "Utility",  "Gas",  "Internet"}));
		comboBox.setBounds(141, 32, 121, 20);
		contentPanel.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(ClientAccountLogic.getCombo()));
		comboBox_1.setBounds(141, 63, 121, 20);
		contentPanel.add(comboBox_1);
		
		textField = new JTextField();
		textField.setText("000");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(141, 100, 121, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("$");
		label.setFont(new Font("Tahoma", Font.PLAIN, 19));
		label.setForeground(Color.LIGHT_GRAY);
		label.setBounds(272, 97, 46, 23);
		contentPanel.add(label);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.DARK_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ad1)
				    {
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									int auxID = Integer.parseInt(textField.getText().toString());
									String auxSelect = comboBox.getSelectedItem().toString();
									String parts = comboBox_1.getSelectedItem().toString();
									String parts2[] = parts.split(" ");

									
									EmployeeOperation.payBill(Integer.parseInt(parts2[0]), auxID, emp, auxSelect);
									
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
