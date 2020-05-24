package Client.Main;

import Client.UILayer.LoginPage;

public class MainApplication {

	public static void main(String[] args) {
		System.out.println("STARTING THE PLAYLIST GENERATOR - Client");
		
		LoginPage window = new LoginPage();
		window.frmLoginWindow.setVisible(true);
		//Connection.openConnection();

		/*
		 * SongMapper SM = new SongMapper();
		 * 
		 * int testing = SM.addSong("TEST", "TEST", "TEST"); SM.listSong();
		 * SM.updateSong(testing, "aaa", "aaaa", "aaa", 24); SM.listSong();
		 * SM.deleteSong(testing); SM.listSong();
		 */

		/*
		 * RegularMapper RM = new RegularMapper();
		 * 
		 * int test = RM.addRegular("test", "test"); RM.listRegular();
		 * RM.updateRegular(test, "ABA", "BABA"); RM.listRegular();
		 * RM.deleteRegular(test); RM.listRegular();
		 */

		/*
		 * PlaylistMapper RM = new PlaylistMapper();
		 * 
		 * int test = RM.addPlaylist("test", 1); RM.listPlaylist();
		 * RM.updatePlaylist(test, "ABA"); RM.listPlaylist(); RM.deletePlaylist(test);
		 * RM.listPlaylist();
		 */

		/*
		 * SongPlaylistMapper RM = new SongPlaylistMapper();
		 * 
		 * int test = RM.addSongPlaylist(2, 1); RM.listSongPlaylist();
		 * 
		 * RM.deleteSongPlaylist(test); RM.listSongPlaylist();
		 * 
		 * Connection.closeConnection();
		 */
		
//		FriendsMapper m = new FriendsMapper();
//		Friends f1 = new Friends(1,6);
//		int aux = m.addFriends(f1.getRegularID1(), f1.getRegularID2());
//		m.listFriends();
//		m.updateFriends(aux, f1.getRegularID1(), 7);
//		m.listFriends();
//		m.deleteFriends(aux);
//		m.listFriends();
		
//		MessageMapper m = new MessageMapper();
//		Message f1 = new Message(1,1);
//		int aux = m.addMessage(f1.getFriendsID(), f1.getSongID());
//		m.listMessage();
//		m.updateMessage(aux, f1.getFriendsID(), 7);
//		m.listMessage();
//		m.deleteMessage(aux);
//		m.listMessage();
//		

	}

}