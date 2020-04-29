package UILayer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ViewWindow {

	JFrame frmViewWindow;
	private JScrollPane table;


	/**
	 * Create the application.
	 */
	public ViewWindow(String type) {
		initialize( type);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize( String type ) {
		
		Model tables= new Model();
		tables.updateViews();
		
		frmViewWindow = new JFrame();
		frmViewWindow.getContentPane().setBackground(Color.DARK_GRAY);
		frmViewWindow.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(type + ":");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setBounds(10, 11, 668, 31);
		frmViewWindow.getContentPane().add(lblNewLabel);
		
		if(type.equals("Regular"))
		{
			table = new JScrollPane(tables.getRegFull());
		}
		if(type.equals("Playlist"))
		{
			table = new JScrollPane(tables.getPlayFull());
		}
		if(type.equals("Song"))
		{
			table = new JScrollPane(tables.getSongFull());
		}
		

		table.setBounds(10, 53, 668, 398); 
		
		frmViewWindow.getContentPane().add(table);
		
		
		frmViewWindow.setTitle("View Window");
		frmViewWindow.setBounds(100, 100, 704, 501);
		frmViewWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
