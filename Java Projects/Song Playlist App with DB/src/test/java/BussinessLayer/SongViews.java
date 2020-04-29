package BussinessLayer;

public class SongViews implements Comparable{
	
	private String songName;
	private int views;
	
	
	
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	
	public SongViews(String songName, int views) {
		super();
		this.songName = songName;
		this.views = views;
	}
	


	@Override
	public int compareTo(Object o) {
		int compareviews=((SongViews)o).getViews();
		return compareviews - this.views;
	}
	
	

}
