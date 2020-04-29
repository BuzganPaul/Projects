package UserInterface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DomainModelLevel.ClientAccountLogic;
import DomainModelLevel.EmployeeOperation;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JComboBox;

public class MoneyTransfer extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public MoneyTransfer(String empName) {
		setTitle("Money Transfer");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblSendAccount = new JLabel("Sender Account:");
			lblSendAccount.setForeground(Color.LIGHT_GRAY);
			lblSendAccount.setFont(new Font("Tahoma", Font.PLAIN, 19));
			lblSendAccount.setBounds(27, 11, 140, 23);
			contentPanel.add(lblSendAccount);
		}
		{
			JLabel lblNewLabel = new JLabel("Recipient Account:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
			lblNewLabel.setForeground(Color.LIGHT_GRAY);
			lblNewLabel.setBounds(10, 45, 157, 23);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblAmmount = new JLabel("Ammount:");
			lblAmmount.setForeground(Color.LIGHT_GRAY);
			lblAmmount.setFont(new Font("Tahoma", Font.PLAIN, 19));
			lblAmmount.setBounds(79, 79, 88, 23);
			contentPanel.add(lblAmmount);
		}
		
			textField = new JTextField();
			textField.setText("000");
			textField.setBounds(177, 84, 86, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		
		
			JLabel label = new JLabel("$");
			label.setForeground(Color.LIGHT_GRAY);
			label.setFont(new Font("Tahoma", Font.PLAIN, 19));
			label.setBounds(276, 79, 46, 23);
			contentPanel.add(label);
		
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(ClientAccountLogic.getCombo()));
			comboBox.setBounds(177, 50, 112, 20);
			contentPanel.add(comboBox);
		
			JComboBox comboBox_1 = new JComboBox();
			comboBox_1.setModel(new DefaultComboBoxModel(ClientAccountLogic.getCombo()));
			comboBox_1.setBounds(177, 16, 112, 20);
			contentPanel.add(comboBox_1);
		
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
									String acc1 = comboBox_1.getSelectedItem().toString();
									String acc2 = comboBox.getSelectedItem().toString();
									String parts1[] = acc1.split(" ");
									String parts2[] = acc2.split(" ");
									int auxIn = Integer.parseInt(textField.getText().toString());

									
									EmployeeOperation.moneyTransfer(Integer.parseInt(parts1[0]), Integer.parseInt(parts2[0]), auxIn, empName);
									
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
