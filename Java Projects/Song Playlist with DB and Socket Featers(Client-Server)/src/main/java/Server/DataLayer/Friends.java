package Server.DataLayer;

public class Friends {

	private int friendsID;
	private int regularID1;
	private int regularID2;
	
	public Friends()
	{
		
	}
	
	public Friends(int f1, int f2)
	{
		super();
		this.regularID1 = f1;
		this.regularID2 = f2;
	}
	
	public int getRegularID1() {
		return regularID1;
	}
	public void setRegularID1(int regularID1) {
		this.regularID1 = regularID1;
	}
	public int getRegularID2() {
		return regularID2;
	}
	public void setRegularID2(int regularID2) {
		this.regularID2 = regularID2;
	}
	public int getFriendsID() {
		return friendsID;
	}
	
	
}
