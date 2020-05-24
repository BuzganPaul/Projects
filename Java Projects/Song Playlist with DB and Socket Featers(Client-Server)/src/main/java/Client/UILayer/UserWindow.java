package Client.UILayer;


import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class UserWindow {

	JFrame frmUserWindow;
	private JTextField txtPlaylistName;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_searchedSongs;


	/**
	 * Create the application.
	 */
	public UserWindow(String user, Controller c) {
		initialize(user, c);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String user, Controller c) {
		
		DefaultComboBoxModel<String> playlist = new DefaultComboBoxModel<String>();
		DefaultComboBoxModel<String> songsOnPlaylist = new DefaultComboBoxModel<String>();
		DefaultComboBoxModel<String> SearchedSongs = new DefaultComboBoxModel<String>();
		
		
		DefaultComboBoxModel<String> allSongs = new DefaultComboBoxModel<String>();
		DefaultComboBoxModel<String> allUsers = new DefaultComboBoxModel<String>();
		
		DefaultComboBoxModel<String> friends = new DefaultComboBoxModel<String>();
		
		DefaultComboBoxModel<String> messages = new DefaultComboBoxModel<String>();
		
		DefaultComboBoxModel<String> buildedPlaylist = new DefaultComboBoxModel<String>();
		
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
		
		String[] users = control.getAllUsers(user);
		String[] songs = control.getAllSongs();
		
		String[] userFriends = control.getFriends(user);
		
		String[] messages1 = control.getMessage(user);
		
		
		for(String aux : control.getPlaylistByReg(user))
		{
			playlist.addElement(aux);
		}
		
		
		for(String aux : users)
		{
			allUsers.addElement(aux);
		}
		
		for(String aux : messages1)
		{
			messages.addElement(aux);
		}
		
		
		for(String aux : songs)
		{
			allSongs.addElement(aux);
		}
		
		
		
		for(String aux : userFriends)
		{
			friends.addElement(aux);
		}
		
		
		JComboBox<String> comboBox_Playlists = new JComboBox<String>();
		JComboBox<String> comboBox_userPlaylists = new JComboBox<String>();
		JComboBox<String> comboBox_Songs = new JComboBox<String>();
		JComboBox<String> comboBox_searchedSongs = new JComboBox<String>();
		
		JComboBox<String> comboBox_3 = new JComboBox<String>();
		comboBox_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_3.setBounds(134, 11, 519, 31);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		JComboBox<String> comboBox_2 = new JComboBox<String>();
		
		JComboBox<String> comboBox_4 = new JComboBox<String>();
		
		comboBox_4.setModel(playlist);
		
		
		comboBox.setModel(friends);
		
		
		
		comboBox_Playlists.setModel(playlist);
		comboBox_userPlaylists.setModel(playlist);
		
		comboBox_3.setModel(messages);
		comboBox_2.setModel(allSongs);
		
		
		comboBox_3.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
				model.updateViews();
				
		    	messages.removeAllElements();
		        
		        String[] auxMessages = control.getMessage(user);
		        
				for(String aux : auxMessages)
				{
					messages.addElement(aux);
				}
		        
		        comboBox_3.setModel(messages);
		    	
		    }
		        
		});
		
		
		
		
		frmUserWindow = new JFrame();
		frmUserWindow.setTitle("User Window");
		frmUserWindow.getContentPane().setBackground(Color.DARK_GRAY);
		frmUserWindow.getContentPane().setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("User: " + user);
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(10, 11, 562, 31);
		frmUserWindow.getContentPane().add(lblNewLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tabbedPane.setBounds(20, 53, 711, 382);
		frmUserWindow.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("Add Playlist");
		panel.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("Add Playlist", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Playlist Name:");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(60, 28, 157, 31);
		panel.add(lblNewLabel_1);
		
		txtPlaylistName = new JTextField();
		txtPlaylistName.setText("playlist name");
		txtPlaylistName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPlaylistName.setBounds(227, 30, 387, 31);
		panel.add(txtPlaylistName);
		txtPlaylistName.setColumns(10);
		
		JButton btnNewButton = new JButton("Create");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(10, 301, 207, 31);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_20 = new JLabel("Generated Playlist:");
		lblNewLabel_20.setForeground(new Color(0, 128, 0));
		lblNewLabel_20.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_20.setBounds(10, 86, 207, 25);
		panel.add(lblNewLabel_20);
		
		
		//GENRATE PLAYLIST
		
		
		
		
		JComboBox<String> comboBox_6 = new JComboBox<String>();
		comboBox_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_6.setBounds(227, 86, 387, 28);
		panel.add(comboBox_6);
		
		
		
		//GENERATE PLAYLIST
		
		
		
		JLabel lblNewLabel_21 = new JLabel("*if we press create before generate we will have a blank playlist");
		lblNewLabel_21.setForeground(Color.RED);
		lblNewLabel_21.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_21.setBounds(10, 180, 686, 31);
		panel.add(lblNewLabel_21);
		
		JButton btnNewButton_8 = new JButton("Generate Playlist");
		btnNewButton_8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_8.setBounds(489, 301, 207, 31);
		panel.add(btnNewButton_8);
		
		
		
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Artist");
		chckbxNewCheckBox_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxNewCheckBox_2.setBounds(10, 135, 59, 23);
		panel.add(chckbxNewCheckBox_2);
		
		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("Genre");
		chckbxNewCheckBox_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxNewCheckBox_3.setBounds(89, 135, 65, 23);
		panel.add(chckbxNewCheckBox_3);
		
		
		btnNewButton_8.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
				String[] auxBuild =  control.buildPlaylist(user, chckbxNewCheckBox_2.isSelected(), chckbxNewCheckBox_3.isSelected());
				
				buildedPlaylist.removeAllElements();
				
				for(String auxi : auxBuild) 
				{
				    buildedPlaylist.addElement(auxi);
				}

				comboBox_6.setModel(buildedPlaylist);
		    	
		    }
		        
		});
		
		//BUTTON TO GENERATE
		
		
		btnNewButton.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
				control.createPlaylist(user, txtPlaylistName.getText());
				model.updateViews();
				playlist.addElement(txtPlaylistName.getText());
				comboBox_Playlists.setModel(playlist);
				comboBox_userPlaylists.setModel(playlist);
		    	
		    }
		        
		});
		
		JPanel panel_searchedSongs = new JPanel();
		panel_searchedSongs.setForeground(Color.BLACK);
		panel_searchedSongs.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("Playlist", null, panel_searchedSongs, null);
		panel_searchedSongs.setLayout(null);
		
		JLabel lblNewLabel_searchedSongs = new JLabel("Playlist:");
		lblNewLabel_searchedSongs.setForeground(Color.BLACK);
		lblNewLabel_searchedSongs.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_searchedSongs.setBounds(10, 30, 85, 31);
		panel_searchedSongs.add(lblNewLabel_searchedSongs);
		
		JLabel lblNewLabel_userPlaylists = new JLabel("Songs:");
		lblNewLabel_userPlaylists.setForeground(Color.BLACK);
		lblNewLabel_userPlaylists.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_userPlaylists.setBounds(20, 72, 76, 31);
		panel_searchedSongs.add(lblNewLabel_userPlaylists);
		
		
		comboBox_Playlists.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_Playlists.setBounds(105, 32, 336, 31);
		panel_searchedSongs.add(comboBox_Playlists);
		comboBox_Playlists.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	model.updateViews();
		    	songsOnPlaylist.removeAllElements();

		    	String selectedPlaylist = comboBox_Playlists.getSelectedItem().toString();
		    	String[] auxsongs = control.getSongsInPlaylist(selectedPlaylist);
		        for(String aux : auxsongs)
		        {
		        	songsOnPlaylist.addElement(aux);
		        }
		        comboBox_Songs.setModel(songsOnPlaylist);
		    }
		        
		});
		
		
		
		
		comboBox_Songs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_Songs.setBounds(106, 74, 335, 31);
		panel_searchedSongs.add(comboBox_Songs);

		
		
		
		JButton btnNewButton_searchedSongs = new JButton("Delete Song");
		btnNewButton_searchedSongs.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_searchedSongs.setBounds(551, 292, 145, 36);
		panel_searchedSongs.add(btnNewButton_searchedSongs);
		btnNewButton_searchedSongs.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	control.deleteFromPlaylist(comboBox_Songs.getSelectedItem().toString(), comboBox_Playlists.getSelectedItem().toString());
		    	
		    	model.updateViews();
		    	
		    	songsOnPlaylist.removeAllElements();

		    	String selectedPlaylist = comboBox_Playlists.getSelectedItem().toString();
		    	String[] auxsongs = control.getSongsInPlaylist(selectedPlaylist);
		        for(String aux : auxsongs)
		        {
		        	songsOnPlaylist.addElement(aux);
		        }
		        comboBox_Songs.setModel(songsOnPlaylist);
		    }
		    
		        
		});
		
		
		
		
		JButton btnNewButton_userPlaylists = new JButton("Play Song");
		btnNewButton_userPlaylists.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_userPlaylists.setBounds(10, 290, 145, 40);
		panel_searchedSongs.add(btnNewButton_userPlaylists);
		
		JLabel lblNewLabel_19 = new JLabel("Rate:");
		lblNewLabel_19.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_19.setBounds(36, 114, 59, 31);
		panel_searchedSongs.add(lblNewLabel_19);
		
		
		
		
		
		String[] rating = {"1", "2", "3", "4", "5"};
		
		JComboBox<?> comboBox_5 = new JComboBox<Object>(rating);
		comboBox_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_5.setBounds(105, 116, 59, 29);
		panel_searchedSongs.add(comboBox_5);
		
		JButton btnNewButton_2 = new JButton("Rate Song");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_2.setBounds(270, 291, 145, 38);
		panel_searchedSongs.add(btnNewButton_2);
		
		btnNewButton_userPlaylists.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	model.updateViews();
		    	
		    	control.playSong(comboBox_Songs.getSelectedItem().toString());
		    	
		    	
		    }
		        
		});
		
		
		btnNewButton_2.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	model.updateViews();
		    	
		    	control.rateSong(comboBox_Songs.getSelectedItem().toString(), comboBox_5.getSelectedItem().toString());
		    	
		    	//RATE SONG
		    	
		    }
		        
		});
		
		
		
		
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("Search Song", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Song Title:");
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_4.setBounds(19, 33, 121, 31);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Song Artist:");
		lblNewLabel_5.setForeground(Color.RED);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_5.setBounds(10, 75, 130, 31);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Genre:");
		lblNewLabel_6.setForeground(Color.RED);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_6.setBounds(65, 117, 75, 31);
		panel_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Top views:");
		lblNewLabel_7.setForeground(new Color(0, 128, 0));
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_7.setBounds(19, 159, 121, 31);
		panel_1.add(lblNewLabel_7);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Enable");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		chckbxNewCheckBox.setBounds(146, 159, 77, 33);
		panel_1.add(chckbxNewCheckBox);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setBounds(150, 33, 224, 31);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_1.setBounds(150, 75, 224, 31);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		textField_searchedSongs = new JTextField();
		textField_searchedSongs.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_searchedSongs.setBounds(150, 117, 224, 31);
		panel_1.add(textField_searchedSongs);
		textField_searchedSongs.setColumns(10);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Enable");
		chckbxNewCheckBox_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		chckbxNewCheckBox_1.setBounds(309, 159, 77, 31);
		panel_1.add(chckbxNewCheckBox_1);
		
		JButton btnNewButton_4 = new JButton("Search");
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_4.setBounds(10, 215, 159, 41);
		panel_1.add(btnNewButton_4);
		btnNewButton_4.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
				String[] aux = control.searchSong(textField.getText(), textField_1.getText(), textField_searchedSongs.getText(), chckbxNewCheckBox.isSelected(), chckbxNewCheckBox_1.isSelected());
				model.updateViews();
				
				SearchedSongs.removeAllElements();
				
				for(String a : aux)
				{
					SearchedSongs.addElement(a);
				}
				
				comboBox_searchedSongs.setModel(SearchedSongs);
		    	
		    }
		        
		});
		
		
		
		JButton btnNewButton_5 = new JButton("Add to Playlist");
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_5.setBounds(10, 267, 159, 41);
		panel_1.add(btnNewButton_5);
		btnNewButton_5.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
				control.addSongToPlaylist(comboBox_searchedSongs.getSelectedItem().toString(), comboBox_userPlaylists.getSelectedItem().toString());
				model.updateViews();
				
		    	songsOnPlaylist.removeAllElements();

		    	String selectedPlaylist = comboBox_Playlists.getSelectedItem().toString();
		    	String[] auxsongs = control.getSongsInPlaylist(selectedPlaylist);
		        for(String aux : auxsongs)
		        {
		        	songsOnPlaylist.addElement(aux);
		        }
		        comboBox_Songs.setModel(songsOnPlaylist);

		    	
		    }
		        
		});
		
		comboBox_searchedSongs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_searchedSongs.setBounds(179, 215, 419, 41);
		panel_1.add(comboBox_searchedSongs);
		
		comboBox_userPlaylists.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_userPlaylists.setBounds(179, 267, 419, 41);
		panel_1.add(comboBox_userPlaylists);
		
		JLabel lblNewLabel_8 = new JLabel("*leave field blank if you ");
		lblNewLabel_8.setForeground(Color.RED);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_8.setBounds(429, 33, 267, 31);
		panel_1.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("don't search with that criteria");
		lblNewLabel_9.setForeground(Color.RED);
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_9.setBounds(429, 59, 267, 31);
		panel_1.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("*1st combobox shows search res");
		lblNewLabel_10.setForeground(Color.RED);
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_10.setBounds(427, 101, 269, 31);
		panel_1.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("*2nd selects playlist");
		lblNewLabel_11.setForeground(Color.RED);
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_11.setBounds(429, 143, 200, 22);
		panel_1.add(lblNewLabel_11);
		
		JLabel lblNewLabel_22 = new JLabel("Rate:");
		lblNewLabel_22.setForeground(new Color(0, 128, 0));
		lblNewLabel_22.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_22.setBounds(244, 159, 59, 28);
		panel_1.add(lblNewLabel_22);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("Friends", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Friends:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(10, 37, 89, 21);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Users:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_3.setBounds(30, 76, 69, 21);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_12 = new JLabel("Songs:");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_12.setBounds(23, 108, 76, 31);
		panel_2.add(lblNewLabel_12);
		
		JButton btnNewButton_ADD_FRIENDS = new JButton("Add Friend");
		btnNewButton_ADD_FRIENDS.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_ADD_FRIENDS.setBounds(10, 301, 160, 31);
		panel_2.add(btnNewButton_ADD_FRIENDS);
		
		
		
		

		
		
		JButton btnNewButton_SendSong = new JButton("Send Song");
		btnNewButton_SendSong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_SendSong.setBounds(536, 301, 160, 31);
		panel_2.add(btnNewButton_SendSong);
		
		
		btnNewButton_SendSong.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	

		    	//control.addFriends(user, comboBox_1.getSelectedItem().toString());
		    	
		    	control.createMessage(user, comboBox_2.getSelectedItem().toString(), comboBox.getSelectedItem().toString());

				//model.updateViews();
				
		    	//messages.removeAllElements();
		    	String auxMe = user+ ":"+comboBox_2.getSelectedItem().toString()+":"+comboBox.getSelectedItem().toString();
		    	
		    	System.out.println(auxMe);
		    	
		    	messages.addElement(auxMe);
		        
//		        String[] auxMessages = control.getMessage(user);
//		        
//				for(String aux : auxMessages)
//				{
//					messages.addElement(aux);
//				}
//		        
		        comboBox_3.setModel(messages);
		    	}

		    	
		    
		        
		});
		
		
		
		
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setBounds(109, 27, 480, 31);
		panel_2.add(comboBox);
		
		
		
		JButton btnNewButton_9 = new JButton("R");
		btnNewButton_9.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_9.setBounds(607, 27, 58, 29);
		panel_2.add(btnNewButton_9);
		
		btnNewButton_9.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	

				model.updateViews();
				
		    	friends.removeAllElements();
		        
		        String[] auxFriends = control.getFriends(user);
		        
				for(String aux : auxFriends)
				{
					friends.addElement(aux);
				}
		        
		        comboBox.setModel(friends);
		    	}

		    	
		    
		        
		});
		
		
		//FIRENDS
		
		
		
		
		
		
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_1.setBounds(109, 69, 480, 31);
		panel_2.add(comboBox_1);
		
		comboBox_1.setModel(allUsers);
		
		//USERS
		
		//ADD FRIEND
		
		btnNewButton_ADD_FRIENDS.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	
		    	String[] previousAux = control.getFriends(user);
		    	boolean check = false;
		    	
		    	for(String aux : previousAux)
		    	{
		    		if(comboBox_1.getSelectedItem().toString().equals(aux))
		    		{
		    			check = true;
		    		}
		    	}
		    	
		    	if(check == false)
		    	{
		    	control.addFriends(user, comboBox_1.getSelectedItem().toString());
		    	

				model.updateViews();
				
		    	friends.removeAllElements();
		        
		        String[] auxFriends = control.getFriends(user);
		        
				for(String aux : auxFriends)
				{
					friends.addElement(aux);
				}
		        
		        comboBox.setModel(friends);
		    	}

		    	
		    }
		        
		});
		
		
		
		comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_2.setBounds(109, 114, 480, 31);
		panel_2.add(comboBox_2);

		
		//SONGS
		
		
		JLabel lblNewLabel_13 = new JLabel("*to add a friend just select one of the user from combo box and use the button");
		lblNewLabel_13.setForeground(Color.RED);
		lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_13.setBounds(10, 195, 638, 21);
		panel_2.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("*to send a song to a friend just select the friend fro combo box and press the button");
		lblNewLabel_14.setForeground(Color.RED);
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_14.setBounds(10, 227, 686, 14);
		panel_2.add(lblNewLabel_14);
		
		JButton btnNewButton_7 = new JButton("Delete Friend");
		btnNewButton_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_7.setBounds(273, 301, 160, 31);
		panel_2.add(btnNewButton_7);
		
		
		btnNewButton_7.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	control.deleteFriends(user, comboBox.getSelectedItem().toString());
		    	

				model.updateViews();
				
		    	friends.removeAllElements();
		        
		        String[] auxFriends = control.getFriends(user);
		        
				for(String aux : auxFriends)
				{
					friends.addElement(aux);
				}
		        
		        comboBox.setModel(friends);
		    	}

		    	
		    
		        
		});
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("Messages", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_15 = new JLabel("Messages:");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_15.setBounds(10, 11, 114, 31);
		panel_3.add(lblNewLabel_15);
		
		JLabel lblNewLabel_16 = new JLabel("Playlist:");
		lblNewLabel_16.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_16.setBounds(39, 53, 85, 31);
		panel_3.add(lblNewLabel_16);
		
		
		
		
		
		JButton btnNewButton_3 = new JButton("Add Song");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_3.setBounds(10, 293, 265, 39);
		panel_3.add(btnNewButton_3);
		
		btnNewButton_3.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	

		    	String infoPrev = comboBox_3.getSelectedItem().toString();
		    	
		    	String[] parts = infoPrev.split(":");
		    	
		    	control.addSongToPlaylist(parts[1], comboBox_4.getSelectedItem().toString());
		    	
		    	control.deleteMessage(parts[0], parts[1], parts[2]);
		    	
		    	
		    	

				model.updateViews();
				
		    	messages.removeAllElements();
		        
		        String[] auxMessages = control.getMessage(user);
		        
				for(String aux : auxMessages)
				{
					messages.addElement(aux);
				}
		        
		        comboBox_3.setModel(messages);
		        
		        
		    	songsOnPlaylist.removeAllElements();

		    	String selectedPlaylist = comboBox_Playlists.getSelectedItem().toString();
		    	String[] auxsongs = control.getSongsInPlaylist(selectedPlaylist);
		        for(String aux : auxsongs)
		        {
		        	songsOnPlaylist.addElement(aux);
		        }
		        comboBox_Songs.setModel(songsOnPlaylist);
		        
		        
		        
		    	}

		    	
		    
		        
		});
		
		
		
		JButton btnNewButton_6 = new JButton("Delete Message");
		btnNewButton_6.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_6.setBounds(431, 293, 265, 37);
		panel_3.add(btnNewButton_6);
		
		btnNewButton_6.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	

		    	String infoPrev = comboBox_3.getSelectedItem().toString();
		    	
		    	String[] parts = infoPrev.split(":");
		    	
		    	control.deleteMessage(parts[0], parts[1], parts[2]);

				model.updateViews();
				
		    	messages.removeAllElements();
		        
		        String[] auxMessages = control.getMessage(user);
		        
				for(String aux : auxMessages)
				{
					messages.addElement(aux);
				}
		        
		        comboBox_3.setModel(messages);
		               
		        
		    	}

		    	
		    
		        
		});
		
		
		
		
		
		
		
		panel_3.add(comboBox_3);
		

		
		//AICI MESAJELE
		

		comboBox_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_4.setBounds(134, 53, 519, 31);
		panel_3.add(comboBox_4);
		
		//comboBox_4.add(playlist);
		
		JLabel lblNewLabel_17 = new JLabel("*to Add Song you have to select both message and playlist");
		lblNewLabel_17.setForeground(Color.RED);
		lblNewLabel_17.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_17.setBounds(10, 160, 649, 22);
		panel_3.add(lblNewLabel_17);
		
		JLabel lblNewLabel_18 = new JLabel("*to delete a message just select the message");
		lblNewLabel_18.setForeground(Color.RED);
		lblNewLabel_18.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_18.setBounds(10, 197, 649, 22);
		panel_3.add(lblNewLabel_18);
		
		JButton btnNewButton_1 = new JButton("Sign Out");
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(597, 16, 134, 33);
		
		btnNewButton_1.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {         
		        control.signOut(user);
		    	}  
		});
		
		frmUserWindow.getContentPane().add(btnNewButton_1);
		frmUserWindow.setBounds(100, 100, 757, 485);
		frmUserWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
