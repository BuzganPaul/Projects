package UserInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;

public class OriginalFrame {

	private JFrame frmMainMenu;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OriginalFrame window = new OriginalFrame();
					window.frmMainMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OriginalFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMainMenu = new JFrame();
		frmMainMenu.setTitle("Main Menu");
		frmMainMenu.getContentPane().setBackground(Color.DARK_GRAY);
		frmMainMenu.getContentPane().setLayout(null);
		
		JButton btnAdministratorMenu = new JButton("Administrator Menu");
		btnAdministratorMenu.setBounds(156, 48, 176, 38);
		frmMainMenu.getContentPane().add(btnAdministratorMenu);
		btnAdministratorMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
		    {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							AdministratorAccount dialog = new AdministratorAccount();
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
							frmMainMenu.dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
				});
		    }
		});
		
		JButton btnEmployeeMenu = new JButton("Employee Menu");
		btnEmployeeMenu.setBackground(Color.WHITE);
		btnEmployeeMenu.setBounds(156, 131, 176, 38);
		frmMainMenu.getContentPane().add(btnEmployeeMenu);
		frmMainMenu.setBounds(100, 100, 509, 300);
		frmMainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		btnEmployeeMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
		    {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							EmployeeMenu frame = new EmployeeMenu();
							frame.setVisible(true);
							frmMainMenu.dispose();
							
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
				});
		    }
		});
	}

}
