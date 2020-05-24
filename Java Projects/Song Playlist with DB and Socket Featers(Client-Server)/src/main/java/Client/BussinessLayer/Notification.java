package Client.BussinessLayer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JButton;

public class Notification {

	JFrame frmNotification;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the application.
	 */
	public Notification(String user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String user) {
		frmNotification = new JFrame();
		frmNotification.getContentPane().setBackground(Color.DARK_GRAY);
		frmNotification.setTitle("Notification");
		frmNotification.setBounds(100, 100, 334, 235);
		frmNotification.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNotification.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New notification!");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(68, 11, 188, 31);
		frmNotification.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("From: " + user);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.GREEN);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(10, 53, 298, 30);
		frmNotification.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(97, 109, 112, 47);
		frmNotification.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1)
		    {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							frmNotification.dispose();
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					
				});
		    }
		});
	}
}
