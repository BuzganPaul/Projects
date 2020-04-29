package UILayer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextField;

import BussinessLayer.LoginChecker;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

public class LoginPage {

	public JFrame frmLoginWindow;
	private JTextField txtUser;
	private JTextField txtPassword;


	/**
	 * Create the application.
	 */
	public LoginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoginWindow = new JFrame();
		frmLoginWindow.setTitle("Login Window");
		frmLoginWindow.getContentPane().setBackground(Color.DARK_GRAY);
		frmLoginWindow.getContentPane().setLayout(null);
		
		txtUser = new JTextField();
		txtUser.setText("user");
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtUser.setBounds(119, 20, 190, 31);
		frmLoginWindow.getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(10, 20, 99, 25);
		frmLoginWindow.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1.setBounds(10, 72, 99, 25);
		frmLoginWindow.getContentPane().add(lblNewLabel_1);
		
		txtPassword = new JTextField();
		txtPassword.setText("password");
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPassword.setBounds(119, 72, 190, 31);
		frmLoginWindow.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnNewButton = new JButton("Enter");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(10, 184, 89, 23);
		frmLoginWindow.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1)
		    {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							String useraux = txtUser.getText();
							String passwordaux = txtPassword.getText();
							
							LoginChecker aux = new LoginChecker();
							
							if(aux.checking(useraux, passwordaux).equals("Error"))
							{
								ErrorUserPass dialog = new ErrorUserPass();
								dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								dialog.setVisible(true);
							}
							else {
								if(aux.checking(useraux, passwordaux).equals("Regular"))
								{
									UserWindow window = new UserWindow(useraux);
									window.frmUserWindow.setVisible(true);
								}
								else {
									if(aux.checking(useraux, passwordaux).equals("Admin"))
									{
										AdminWindow window = new AdminWindow();
										window.frmAdminWindow.setVisible(true);
										frmLoginWindow.dispose();
									}
								}
							}
							
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					
				});
		    }
		});
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.setBounds(220, 183, 89, 25);
		frmLoginWindow.getContentPane().add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1)
		    {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							frmLoginWindow.dispose();
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					
				});
		    }
		});
		frmLoginWindow.setBounds(100, 100, 338, 300);
		frmLoginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
