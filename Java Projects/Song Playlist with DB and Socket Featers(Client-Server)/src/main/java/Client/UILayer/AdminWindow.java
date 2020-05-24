package Client.UILayer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;


import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class AdminWindow {

	JFrame frmAdminWindow;
	private JTextField txtName;
	private JTextField txtPassword;
	private JTextField txtTitle;
	private JTextField txtArtist;
	private JTextField txtGenre;
	private JTextField txtNumberofviews;

	JFileChooser chooser;
	String choosertitle;

	/**
	 * Create the application.
	 */
	public AdminWindow(Controller c) {
		initialize(c);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize(Controller c) {
		frmAdminWindow = new JFrame();
		frmAdminWindow.getContentPane().setBackground(Color.DARK_GRAY);
		frmAdminWindow.setTitle("Admin Window");
		frmAdminWindow.setBounds(100, 100, 494, 481);
		frmAdminWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdminWindow.getContentPane().setLayout(null);
		
		Controller control;
		
		if(c == null) {
			control = new Controller();
		}
		else
		{
			control = c;
		}
		
		Model model = new Model(control);
		model.updateViews();
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.LIGHT_GRAY);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tabbedPane.setBounds(10, 44, 458, 387);
		frmAdminWindow.getContentPane().add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.BLACK);
		panel_1.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("SongOp", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_10 = new JLabel("Song:");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_10.setBounds(124, 11, 65, 31);
		panel_1.add(lblNewLabel_10);
		
		JComboBox comboBox_3 = new JComboBox(model.getSongs());
		DefaultComboBoxModel songs = new DefaultComboBoxModel();
		
		for(String aux : model.getSongs())
		{
			songs.addElement(aux);
		}
		
		comboBox_3.setModel(songs);
		
		
		comboBox_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_3.setBounds(199, 11, 170, 31);
		panel_1.add(comboBox_3);
		
		JLabel lblNewLabel_11 = new JLabel("Title:");
		lblNewLabel_11.setForeground(Color.RED);
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_11.setBounds(141, 53, 48, 28);
		panel_1.add(lblNewLabel_11);
		
		txtTitle = new JTextField();
		txtTitle.setText("title");
		txtTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTitle.setBounds(199, 53, 170, 28);
		panel_1.add(txtTitle);
		txtTitle.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("Artist:");
		lblNewLabel_12.setForeground(Color.RED);
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_12.setBounds(135, 92, 54, 28);
		panel_1.add(lblNewLabel_12);
		
		txtArtist = new JTextField();
		txtArtist.setText("artist");
		txtArtist.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtArtist.setBounds(199, 92, 170, 28);
		panel_1.add(txtArtist);
		txtArtist.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("Genre:");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_13.setForeground(Color.RED);
		lblNewLabel_13.setBounds(129, 131, 60, 28);
		panel_1.add(lblNewLabel_13);
		
		txtGenre = new JTextField();
		txtGenre.setText("genre");
		txtGenre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtGenre.setBounds(199, 131, 170, 28);
		panel_1.add(txtGenre);
		txtGenre.setColumns(10);
		
		JLabel lblNewLabel_14 = new JLabel("Views:");
		lblNewLabel_14.setForeground(Color.RED);
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_14.setBounds(129, 170, 60, 28);
		panel_1.add(lblNewLabel_14);
		
		txtNumberofviews = new JTextField();
		txtNumberofviews.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNumberofviews.setText("numberOfViews");
		txtNumberofviews.setBounds(199, 170, 170, 28);
		panel_1.add(txtNumberofviews);
		txtNumberofviews.setColumns(10);
		
		JButton btnNewButton_6 = new JButton("View");
		btnNewButton_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_6.setBounds(10, 249, 117, 31);
		panel_1.add(btnNewButton_6);
		
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1)
		    {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ViewWindow window = new ViewWindow("Song", control);
							window.frmViewWindow.setVisible(true);
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					
				});
		    }
		});
		
		JButton btnNewButton_7 = new JButton("Add");
		btnNewButton_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_7.setBounds(10, 306, 117, 31);
		panel_1.add(btnNewButton_7);
		
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1)
		    {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							control.addSong(txtTitle.getText(), txtArtist.getText(), txtGenre.getText());
							model.updateViews();
							songs.addElement(txtArtist.getText() +"-" +txtTitle.getText());
							comboBox_3.setModel(songs);
							
							
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					
				});
		    }
		});
		
		String[] ratings = {"1", "2", "3", "4", "5"};
		
		JComboBox comboBox_4 = new JComboBox(ratings);
		comboBox_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_4.setBounds(199, 209, 65, 31);
		panel_1.add(comboBox_4);
		
		
		
		JButton btnNewButton_8 = new JButton("Update");
		btnNewButton_8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_8.setBounds(326, 249, 117, 31);
		panel_1.add(btnNewButton_8);
		
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1)
		    {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							control.updateSong(comboBox_3.getSelectedItem().toString(), txtTitle.getText(), txtArtist.getText(), txtGenre.getText(), Integer.parseInt(txtNumberofviews.getText()), comboBox_4.getSelectedItem().toString());
							model.updateViews();
							songs.removeElement(comboBox_3.getSelectedItem().toString());
							songs.addElement(txtArtist.getText() +"-" +txtTitle.getText());
							comboBox_3.setModel(songs);
							
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					
				});
		    }
		});
		
		
		
		JButton btnNewButton_9 = new JButton("Delete");
		btnNewButton_9.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_9.setBounds(326, 306, 117, 31);
		panel_1.add(btnNewButton_9);
		
		
		
		
		
		JLabel lblNewLabel_15 = new JLabel("Rate:");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_15.setBounds(141, 209, 48, 18);
		panel_1.add(lblNewLabel_15);
		

		
		
		
		
		
		
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1)
		    {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							control.deleteSong(comboBox_3.getSelectedItem().toString());
							model.updateViews();
							songs.removeElement(comboBox_3.getSelectedItem().toString());
							comboBox_3.setModel(songs);
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					
				});
		    }
		});
		
		
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.DARK_GRAY);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setToolTipText("User Op");
		tabbedPane.addTab("UserOp", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(79, 57, 97, 25);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(85, 93, 91, 25);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("User:");
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_3.setBounds(118, 11, 58, 35);
		panel.add(lblNewLabel_3);
		
		
		
		
		JComboBox comboBox = new JComboBox();
		DefaultComboBoxModel regs = new DefaultComboBoxModel();
		
		JComboBox comboBox_1 = new JComboBox();
		
		
		
		for(String aux : model.getRegs())
		{
			regs.addElement(aux);
		}
		
		comboBox.setModel(regs);
		comboBox_1.setModel(regs);
		
		
		
		
		
		
		comboBox.setForeground(Color.BLACK);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setBounds(186, 11, 232, 33);
		panel.add(comboBox);
		
		txtName = new JTextField();
		txtName.setText("name");
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtName.setBounds(186, 57, 232, 26);
		panel.add(txtName);
		txtName.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPassword.setText("password");
		txtPassword.setBounds(186, 93, 232, 26);
		panel.add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnNewButton = new JButton("View");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(32, 129, 102, 25);
		panel.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1)
		    {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ViewWindow window = new ViewWindow("Regular", control);
							window.frmViewWindow.setVisible(true);
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					
				});
		    }
		});
		
		JButton btnNewButton_1 = new JButton("Add");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(32, 165, 102, 23);
		panel.add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1)
		    {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							control.addRegular(txtName.getText(), txtPassword.getText());
							model.updateViews();
							regs.addElement(txtName.getText());
							comboBox_1.setModel(regs);
							comboBox.setModel(regs);
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					
				});
		    }
		});
		
		
		
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_2.setBounds(316, 130, 102, 27);
		panel.add(btnNewButton_2);
		
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1)
		    {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							control.updateRegular(comboBox.getSelectedItem().toString(), txtName.getText(), txtPassword.getText());
							model.updateViews();
							regs.removeElement(comboBox.getSelectedItem().toString());
							regs.addElement(txtName.getText());
							comboBox_1.setModel(regs);
							comboBox.setModel(regs);
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					
				});
		    }
		});
		
		
		
		JButton btnNewButton_3 = new JButton("Delete");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_3.setBounds(316, 169, 102, 23);
		panel.add(btnNewButton_3);
		
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1)
		    {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							control.deleteRegular(comboBox.getSelectedItem().toString());
							model.updateViews();
							regs.removeElement(comboBox.getSelectedItem().toString());
							
							
							comboBox_1.setModel(regs);
							comboBox.setModel(regs);
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					
				});
		    }
		});
		
		JLabel lblNewLabel_4 = new JLabel("*view opens a new window ");
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(32, 235, 386, 25);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("*add uses only the fields");
		lblNewLabel_5.setForeground(Color.RED);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(32, 260, 161, 19);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("*update uses both the fields and combo box");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6.setForeground(Color.RED);
		lblNewLabel_6.setBounds(32, 282, 292, 19);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("*delete uses only the combo box");
		lblNewLabel_7.setForeground(Color.RED);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_7.setBounds(32, 301, 217, 25);
		panel.add(lblNewLabel_7);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("Generate Report", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("User:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_8.setBounds(121, 31, 58, 31);
		panel_2.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Playlist:");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_9.setBounds(94, 73, 85, 31);
		panel_2.add(lblNewLabel_9);
		
		
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_1.setBounds(189, 31, 164, 31);
		panel_2.add(comboBox_1);
		
		DefaultComboBoxModel plays = new DefaultComboBoxModel();
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_2.setBounds(189, 73, 164, 31);
		panel_2.add(comboBox_2);
		
		comboBox_1.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	plays.removeAllElements();
		    	
		    	
		        for(String aux : control.getPlaylistByReg(comboBox_1.getSelectedItem().toString()))
		        {
		        	plays.addElement(aux);
		        	
		        }
		        comboBox_2.setModel(plays);
		    }
		        
		});
		

		
		JButton btnNewButton_4 = new JButton("Generate pdf");
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_4.setBounds(147, 131, 164, 42);
		panel_2.add(btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener () {
			
			  public void actionPerformed(ActionEvent e) {            
				    chooser = new JFileChooser(); 
				    chooser.setCurrentDirectory(new java.io.File("."));
				    chooser.setDialogTitle(choosertitle);
				    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				    //
				    // disable the "All files" option.
				    //
				    chooser.setAcceptAllFileFilterUsed(false);
				    
				    String name = comboBox_1.getSelectedItem().toString();
				    String playlist = comboBox_2.getSelectedItem().toString();
				    
				    
				    
				        
				    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
				      System.out.println("getCurrentDirectory(): " 
				         +  chooser.getCurrentDirectory());
				      System.out.println("getSelectedFile() : " 
				         +  chooser.getSelectedFile());
				      
				      control.generateReport("pdf", chooser.getSelectedFile().toString(), name, playlist);
				      
				      }
				    else {
				      System.out.println("No Selection ");
				      }
				     }

		        
		});
		

		
		
		
		JButton btnNewButton_5 = new JButton("Generate txt");
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_5.setBounds(147, 184, 164, 42);
		panel_2.add(btnNewButton_5);
		btnNewButton_5.addActionListener(new ActionListener () {
			
			  public void actionPerformed(ActionEvent e) {            
				    chooser = new JFileChooser(); 
				    chooser.setCurrentDirectory(new java.io.File("."));
				    chooser.setDialogTitle(choosertitle);
				    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				    //
				    // disable the "All files" option.
				    //
				    chooser.setAcceptAllFileFilterUsed(false);
				    
				    String name = comboBox_1.getSelectedItem().toString();
				    String playlist = comboBox_2.getSelectedItem().toString();
				    
				    //    
				    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
				      System.out.println("getCurrentDirectory(): "  +  chooser.getCurrentDirectory());
				      System.out.println("getSelectedFile():" +  chooser.getSelectedFile());
				      
				      control.generateReport("txt", chooser.getSelectedFile().toString(), name, playlist);
				      
				      }
				    else {
				      System.out.println("No Selection ");
				      }
				     }

		        
		});
		
		JLabel lblNewLabel_1 = new JLabel("Admin");
		lblNewLabel_1.setForeground(Color.GREEN);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(10, 11, 458, 34);
		frmAdminWindow.getContentPane().add(lblNewLabel_1);
	}
}

