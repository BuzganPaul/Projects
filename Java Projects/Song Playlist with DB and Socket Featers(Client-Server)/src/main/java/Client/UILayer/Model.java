package Client.UILayer;

import javax.swing.JTable;

public class Model {

	private String[] songs;
	private String[] playlists;
	private String[] regs;

	private JTable songFull;
	private JTable regFull;
	private JTable playFull;

	private final String columnSong[] = { "ID", "TITLE", "ARTIST", "GENRE", "VIEWS", "RATING", "NR. RATINGS" };
	private final String columnReg[] = { "ID", "USERNAME", "PASSWORD" };
	private final String columnPlaylist[] = { "ID", "NAME", "REGULAR ID" };
	
	Controller update;

	public Model(Controller c) {
		update = c;
	}

	public void updateViews() {
		//Controller update = new Controller();
		songs = update.getSongs();
		playlists = update.getPlays();
		regs = update.getRegs();

		songFull = new JTable(update.getSongsTable(), columnSong);
		regFull = new JTable(update.getRegsTable(), columnReg);
		playFull = new JTable(update.getPlaysTable(), columnPlaylist);

	}

	public String[] getSongs() {
		return songs;
	}

	public String[] getPlaylists() {
		return playlists;
	}

	public String[] getRegs() {
		return regs;
	}

	public JTable getSongFull() {
		return songFull;
	}

	public JTable getRegFull() {
		return regFull;
	}

	public JTable getPlayFull() {
		return playFull;
	}

}
