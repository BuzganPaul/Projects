package UserInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdministratorMain extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public AdministratorMain(String name) {
		setTitle("Administrator Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 346);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnEmployeeManager = new JButton("Employee Manager");
		btnEmployeeManager.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnEmployeeManager.setBackground(Color.LIGHT_GRAY);
		btnEmployeeManager.setBounds(114, 45, 147, 64);
		contentPane.add(btnEmployeeManager);
		btnEmployeeManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1)
		    {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							EmployeeManager dialog = new EmployeeManager("Employee", "Gigi");
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					
				});
		    }
		});
		
		
		JButton btnEmployeeResume = new JButton("Employee Resume");
		btnEmployeeResume.setBackground(Color.LIGHT_GRAY);
		btnEmployeeResume.setBounds(114, 137, 147, 64);
		contentPane.add(btnEmployeeResume);
		btnEmployeeResume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1)
		    {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							EmployeeResume dialog = new EmployeeResume();
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
		btnBack.setBackground(Color.LIGHT_GRAY);
		btnBack.setBounds(305, 273, 89, 23);
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
		
		JLabel lblAdminName = new JLabel("Admin Name: "+ name);
		lblAdminName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAdminName.setForeground(Color.LIGHT_GRAY);
		lblAdminName.setBounds(10, 11, 267, 23);
		contentPane.add(lblAdminName);
	}
}
