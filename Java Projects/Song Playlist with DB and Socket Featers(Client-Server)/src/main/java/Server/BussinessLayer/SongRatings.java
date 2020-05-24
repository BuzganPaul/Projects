package Server.BussinessLayer;

@SuppressWarnings("rawtypes")
public class SongRatings implements Comparable{
	
	private String songName;
	private float ratings;
	
	
	
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public float getRatings() {
		return ratings;
	}
	public void setViews(int ratings) {
		this.ratings = ratings;
	}
	
	public SongRatings(String songName, float ratings) {
		super();
		this.songName = songName;
		this.ratings = ratings;
	}
	


	@Override
	public int compareTo(Object o) {
		int compareviews=(int) ((SongRatings)o).getRatings();
		return (int) (compareviews - this.ratings);
	}
	
	

}