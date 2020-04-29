package DataLayer;

public class Song {
	
	private int songID;
	private String songName;
	private String songArtist;
	private String genre;
	private int views;
	
	public Song() {

	}
	
	
	public Song(String songName, String songArtist, String genre, int views) {
		this.songName = songName;
		this.songArtist = songArtist;
		this.genre = genre;
		this.views = views;
	}



	public String getSongName() {
		return songName;
	}



	public void setSongName(String songName) {
		this.songName = songName;
	}



	public String getSongArtist() {
		return songArtist;
	}



	public void setSongArtist(String songArtist) {
		this.songArtist = songArtist;
	}



	public String getGenre() {
		return genre;
	}



	public void setGenre(String genre) {
		this.genre = genre;
	}



	public int getViews() {
		return views;
	}



	public void setViews(int views) {
		this.views = views;
	}



	public int getSongID() {
		return songID;
	}
	
	
	
	
	

}
