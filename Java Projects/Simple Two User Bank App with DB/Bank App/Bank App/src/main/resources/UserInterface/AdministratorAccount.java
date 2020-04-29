package UserInterface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DomainModelLevel.AdminLogin;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class AdministratorAccount extends JDialog {

	private JPanel contentPanel = new JPanel();
	private JTextField txtPassword;
	private JTextField txtName;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public AdministratorAccount() {
		setTitle("LOGIN");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblLogin.setForeground(Color.LIGHT_GRAY);
		lblLogin.setBounds(10, 11, 97, 32);
		contentPanel.add(lblLogin);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblName.setForeground(Color.LIGHT_GRAY);
		lblName.setBounds(38, 66, 49, 32);
		contentPanel.add(lblName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPassword.setForeground(Color.LIGHT_GRAY);
		lblPassword.setBounds(10, 109, 77, 21);
		contentPanel.add(lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setText("password");
		txtPassword.setBounds(97, 112, 156, 20);
		contentPanel.add(txtPassword);
		txtPassword.setColumns(10);
		
		txtName = new JTextField();
		txtName.setText("name");
		txtName.setBounds(97, 75, 156, 20);
		contentPanel.add(txtName);
		txtName.setColumns(10);
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
					public void actionPerformed(ActionEvent e1)
				    {
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									String useraux = txtName.getText();
									String passwordaux = txtPassword.getText();
									
									
									if(AdminLogin.loginChecker(useraux, passwordaux))
									{
										dispose();
										AdministratorMain frame = new AdministratorMain(useraux);
										frame.setVisible(true);
									}
									else
									{	
										WrongCredentials dialog = new WrongCredentials();
										dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
										dialog.setVisible(true);

										//frmMainMenu.dispose();
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
