package UILayer;

import java.awt.EventQueue;

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
	public UserWindow(String user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String user) {
		
		DefaultComboBoxModel playlist = new DefaultComboBoxModel();
		DefaultComboBoxModel songsOnPlaylist = new DefaultComboBoxModel();
		DefaultComboBoxModel SearchedSongs = new DefaultComboBoxModel();
		
		Controller control = new Controller();
		
		Model model = new Model();
		model.updateViews();
		
		for(String aux : control.getPlaylistByReg(user))
		{
			playlist.addElement(aux);
		}
		
		JComboBox comboBox_Playlists = new JComboBox();
		JComboBox comboBox_userPlaylists = new JComboBox();
		JComboBox comboBox_Songs = new JComboBox();
		JComboBox comboBox_searchedSongs = new JComboBox();
		
		comboBox_Playlists.setModel(playlist);
		comboBox_userPlaylists.setModel(playlist);
		
		
		frmUserWindow = new JFrame();
		frmUserWindow.setTitle("User Window");
		frmUserWindow.getContentPane().setBackground(Color.DARK_GRAY);
		frmUserWindow.getContentPane().setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("User: " + user);
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(10, 11, 721, 31);
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
		lblNewLabel_1.setBounds(10, 28, 157, 31);
		panel.add(lblNewLabel_1);
		
		txtPlaylistName = new JTextField();
		txtPlaylistName.setText("playlist name");
		txtPlaylistName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPlaylistName.setBounds(177, 30, 321, 31);
		panel.add(txtPlaylistName);
		txtPlaylistName.setColumns(10);
		
		JButton btnNewButton = new JButton("Create");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(10, 301, 119, 31);
		panel.add(btnNewButton);
		
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
		
		btnNewButton_userPlaylists.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	model.updateViews();
		    	
		    	control.playSong(comboBox_Songs.getSelectedItem().toString());
		    	
		    	
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
		
		JButton btnNewButton_4 = new JButton("Search");
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_4.setBounds(10, 215, 159, 41);
		panel_1.add(btnNewButton_4);
		btnNewButton_4.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
				String[] aux = control.searchSong(textField.getText(), textField_1.getText(), textField_searchedSongs.getText(), chckbxNewCheckBox.isSelected());
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
		frmUserWindow.setBounds(100, 100, 757, 485);
		frmUserWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
