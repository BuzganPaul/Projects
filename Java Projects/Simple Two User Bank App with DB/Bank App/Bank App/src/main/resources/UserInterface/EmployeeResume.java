package UserInterface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DomainModelLevel.EmployeeLogic;
import DomainModelLevel.OperationLogic;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class EmployeeResume extends JDialog {

	private final JPanel contentPanel = new JPanel();
	String selectedEmployee;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EmployeeResume dialog = new EmployeeResume();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("unchecked")
	public EmployeeResume() {
		setTitle("Employee Resume");
		setBounds(100, 100, 336, 235);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblEmployee = new JLabel("Employee:");
			lblEmployee.setFont(new Font("Tahoma", Font.PLAIN, 19));
			lblEmployee.setForeground(Color.LIGHT_GRAY);
			lblEmployee.setBounds(28, 11, 88, 23);
			contentPanel.add(lblEmployee);
		}
		{
			JLabel lblTimePeriod = new JLabel("Time Period:");
			lblTimePeriod.setForeground(Color.LIGHT_GRAY);
			lblTimePeriod.setFont(new Font("Tahoma", Font.PLAIN, 19));
			lblTimePeriod.setBounds(10, 45, 106, 23);
			contentPanel.add(lblTimePeriod);
		}
		{
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(EmployeeLogic.getCombo()));
			comboBox.setBounds(126, 16, 144, 20);
			contentPanel.add(comboBox);

			
			JComboBox comboBoxResume = new JComboBox();
			comboBoxResume.setModel(new DefaultComboBoxModel(new String[] { "This day", "This month",  "This year"}));
			comboBoxResume.setBounds(126, 50, 144, 20);
			contentPanel.add(comboBoxResume);
			comboBoxResume.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ad1)
			    {

					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								selectedEmployee = comboBox.getSelectedItem().toString();
								String auxSelect = comboBoxResume.getSelectedItem().toString();
								String parts[] = selectedEmployee.split(" ");

								//System.out.println(parts[parts.length-1]);
								//System.out.println(auxSelect);
								OperationLogic.getReport(auxSelect, Integer.parseInt(parts[parts.length -1]));

							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						
					});
			    }
			});
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.DARK_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

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
