package Server.DataLayer;

public class Message {
	
	private int messageID;
	private int friendsID;
	private int songID;
	
	
	public Message()
	{
		
	}
	
	
	public Message(int f, int s)
	{
		super();
		this.friendsID = f;
		this.songID = s;
	}


	public int getMessageID() {
		return messageID;
	}


	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}


	public int getFriendsID() {
		return friendsID;
	}


	public void setFriendsID(int friendsID) {
		this.friendsID = friendsID;
	}


	public int getSongID() {
		return songID;
	}


	public void setSongID(int songID) {
		this.songID = songID;
	}
	
	
	
}
