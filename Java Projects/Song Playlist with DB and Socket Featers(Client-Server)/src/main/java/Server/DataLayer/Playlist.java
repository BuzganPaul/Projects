package Server.DataLayer;

public class Playlist {
	
	private int playlistID;
	private String playlistName;
	private int regularID;
	
	
	public Playlist() {

	}
	
	
	public Playlist(String playlistName, int regularID) {
		super();
		this.playlistName = playlistName;
		this.regularID = regularID;
	}



	public String getPlaylistName() {
		return playlistName;
	}



	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}



	public int getRegularID() {
		return regularID;
	}



	public void setRegularID(int regularID) {
		this.regularID = regularID;
	}



	public int getPlaylistID() {
		return playlistID;
	}
	
	
	

}
