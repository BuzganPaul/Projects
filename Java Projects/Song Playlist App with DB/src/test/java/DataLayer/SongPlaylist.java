package DataLayer;

public class SongPlaylist {
	
	private int entryID;
	private int playlistID;
	private int songID;
	
	public SongPlaylist() {
	}
	
	
	public SongPlaylist(int playlistID, int songID) {
		this.playlistID = playlistID;
		this.songID = songID;
	}


	public int getPlaylistID() {
		return playlistID;
	}


	public void setPlaylistID(int playlistID) {
		this.playlistID = playlistID;
	}


	public int getSongID() {
		return songID;
	}


	public void setSongID(int songID) {
		this.songID = songID;
	}


	public int getEntryID() {
		return entryID;
	}
	
	public void setEntryID(int entryID) {
		this.entryID = entryID;
	}

}
