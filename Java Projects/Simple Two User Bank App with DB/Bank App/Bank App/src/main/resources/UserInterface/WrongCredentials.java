package UserInterface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WrongCredentials extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public WrongCredentials() {
		setTitle("WrongCredentials");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblWrongUsernamepassword = new JLabel("Wrong username/password!");
			lblWrongUsernamepassword.setForeground(Color.LIGHT_GRAY);
			lblWrongUsernamepassword.setFont(new Font("Tahoma", Font.PLAIN, 19));
			lblWrongUsernamepassword.setBounds(89, 38, 237, 49);
			contentPanel.add(lblWrongUsernamepassword);
		}
		{
			JLabel lblPleaseTryAgain = new JLabel("Please try again!");
			lblPleaseTryAgain.setFont(new Font("Tahoma", Font.PLAIN, 19));
			lblPleaseTryAgain.setForeground(Color.LIGHT_GRAY);
			lblPleaseTryAgain.setBounds(135, 98, 138, 23);
			contentPanel.add(lblPleaseTryAgain);
		}


				JButton okButton = new JButton("OK");
				okButton.setBounds(171, 181, 62, 23);
				okButton.setActionCommand("OK");
				okButton.setVisible(true);
				contentPanel.add(okButton);
				okButton.addActionListener(new ActionListener() {
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
