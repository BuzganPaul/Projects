package Test;

import static org.mockito.Mockito.*;

import java.net.Socket;
import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import Client.BussinessLayer.Notification;
import Client.BussinessLayer.ReportFactory;
import Client.UILayer.AdminWindow;
import Client.UILayer.Controller;
import Client.UILayer.ErrorUserPass;
import Client.UILayer.LoginPage;
import Client.UILayer.UserWindow;
import Client.UILayer.ViewWindow;
import Server.BussinessLayer.ArtistList;
import Server.BussinessLayer.ControllerToDBOperation;
import Server.BussinessLayer.FriendsManager;
import Server.BussinessLayer.GeneralList;
import Server.BussinessLayer.GenreList;
import Server.BussinessLayer.OnlineClient;
import Server.BussinessLayer.PlaylistBuilder;
import Server.BussinessLayer.PlaylistManager;
import Server.DataLayer.Connection;
import Server.DataLayer.Friends;
import Server.DataLayer.FriendsMapper;
import Server.DataLayer.Message;
import Server.DataLayer.MessageMapper;
import Server.DataLayer.Playlist;
import Server.DataLayer.PlaylistMapper;
import Server.DataLayer.Regular;
import Server.DataLayer.RegularMapper;
import Server.DataLayer.Song;
import Server.DataLayer.SongMapper;
import Server.DataLayer.SongPlaylist;
import Server.DataLayer.SongPlaylistMapper;

import static org.junit.Assert.*;

public class TestApplication {

	@Mock
	PlaylistManager testPlaylistManager = new PlaylistManager();

	// @Mock
	// ControllerToDBOperation testOperation = new ControllerToDBOperation();

	@Mock
	ReportFactory testReport = new ReportFactory();

	@Mock
	SongMapper testSongMapper = new SongMapper();

	@Mock
	RegularMapper testRegularMapper = new RegularMapper();

	@Mock
	PlaylistMapper testPlaylistMapper = new PlaylistMapper();

	@Mock
	SongPlaylistMapper testEntriesMapper = new SongPlaylistMapper();

	@Mock
	FriendsMapper testFriendsMapper = new FriendsMapper();

	@Mock
	MessageMapper testMessageMapper = new MessageMapper();

	@Mock
	ArtistList testArtistList2 = new ArtistList();
	@Mock
	GenreList testGenreList2 = new GenreList();
	@Mock
	GeneralList testGeneralList2 = new GeneralList();
	
	@Mock
	Controller testControl = new Controller("test");
	
	@Mock
	Socket testSocket = new Socket();
	
	

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	
	public void setController() {
		String[] strings = { "String1", "String2", "String3", "String4", "String5", "String6", "String7", "String8", "String9", "String10"};
		
		Object[][] table = {
			      {"String1", "String2", "String3", "String4", "String5", "String6", "String7"}, 
			      {"String1", "String2", "String3", "String4", "String5", "String6", "String7"}, 
			      {"String1", "String2", "String3", "String4", "String5", "String6", "String7"}, 
			};
	
		
//		doAnswer(invocation -> {
//			return strings;
//		}).when(testControl).getAllSongs();
		//when((testControl.getAllUsers("user1")).thenReturn();
		
		//when((testControl.getFriends("user1")).thenReturn(strings);
		when(testControl.getMessage("user1")).thenReturn(strings);
		when(testControl.getFriends("user1")).thenReturn(strings);
		when(testControl.getPlaylistByReg("user1")).thenReturn(strings);
		when(testControl.getAllSongs()).thenReturn(strings);
		when(testControl.getAllUsers("user1")).thenReturn(strings);
		when(testControl.getPlaylistByReg("user1")).thenReturn(strings);
		when(testControl.getPlays()).thenReturn(strings);
		when(testControl.getRegs()).thenReturn(strings);
		when(testControl.getSongs()).thenReturn(strings);
		when(testControl.getSongsInPlaylist("user1")).thenReturn(strings);
		when(testControl.getSongsTable()).thenReturn(table);
		when(testControl.getRegsTable()).thenReturn(table);
		when(testControl.getPlaysTable()).thenReturn(table);
		
		

	}

	public void setValues() {
		

		String[] songs1 = { "Song1", "Song2", "Song3" };

		String[] songs2 = { "Song3", "Song4", "Song5" };

		String[] songs3 = { "Song1", "Song2", "Song3", "Song4", "Song5" };

		when((testArtistList2.getSong())).thenReturn(songs1);
		when((testGenreList2.getSong())).thenReturn(songs2);
		when((testGeneralList2.getSong())).thenReturn(songs3);

		ArrayList<Song> toListSong = new ArrayList<Song>();

		Song a1 = new Song("Song1", "SongName1", "SongGenre1", 0, 5, 3);
		Song a2 = new Song("Song2", "SongName2", "SongGenre1", 2, 5, 1);
		Song a3 = new Song("Song3", "SongName3", "SongGenre2", 4, 1, 40);
		Song a4 = new Song("Song4", "SongName4", "SongGenre3", 3, 2, 23);
		Song a5 = new Song("Song5", "SongName5", "SongGenre4", 5, 5, 8);

		toListSong.add(a1);
		toListSong.add(a2);
		toListSong.add(a3);
		toListSong.add(a4);
		toListSong.add(a5);

		when((testSongMapper.listSong())).thenReturn(toListSong);

		ArrayList<Regular> toListRegular = new ArrayList<Regular>();

		Regular b1 = new Regular("user1", "pass1");
		Regular b2 = new Regular("user2", "pass2");
		Regular b3 = new Regular("user3", "pass3");
		Regular b4 = new Regular("user4", "pass4");
		Regular b5 = new Regular("user5", "pass5");

		toListRegular.add(b1);
		toListRegular.add(b2);
		toListRegular.add(b3);
		toListRegular.add(b4);
		toListRegular.add(b5);

		when((testRegularMapper.listRegular())).thenReturn(toListRegular);

		ArrayList<Playlist> toListPlaylist = new ArrayList<Playlist>();

		Playlist c1 = new Playlist("playlist1", 1);
		Playlist c2 = new Playlist("playlist1.2", 1);
		Playlist c3 = new Playlist("playlist2", 2);
		Playlist c4 = new Playlist("playlist3", 3);
		Playlist c5 = new Playlist("playlist4", 4);

		toListPlaylist.add(c1);
		toListPlaylist.add(c2);
		toListPlaylist.add(c3);
		toListPlaylist.add(c4);
		toListPlaylist.add(c5);

		when((testPlaylistMapper.listPlaylist())).thenReturn(toListPlaylist);

		ArrayList<SongPlaylist> toListEntries = new ArrayList<SongPlaylist>();

		SongPlaylist e1 = new SongPlaylist(1, 1);
		SongPlaylist e2 = new SongPlaylist(2, 2);
		SongPlaylist e3 = new SongPlaylist(3, 3);
		SongPlaylist e4 = new SongPlaylist(4, 4);
		SongPlaylist e5 = new SongPlaylist(5, 5);

		toListEntries.add(e1);
		toListEntries.add(e2);
		toListEntries.add(e3);
		toListEntries.add(e4);
		toListEntries.add(e5);

		when((testEntriesMapper.listSongPlaylist())).thenReturn(toListEntries);

		ArrayList<Friends> toListFriends = new ArrayList<Friends>();

		Friends f1 = new Friends(1, 2);
		Friends f2 = new Friends(2, 3);
		Friends f3 = new Friends(1, 3);
		Friends f4 = new Friends(4, 5);
		Friends f5 = new Friends(1, 5);

		toListFriends.add(f1);
		toListFriends.add(f2);
		toListFriends.add(f3);
		toListFriends.add(f4);
		toListFriends.add(f5);

		when((testFriendsMapper.listFriends())).thenReturn(toListFriends);

		ArrayList<Message> toListMessages = new ArrayList<Message>();

		Message m1 = new Message(1, 2);
		Message m2 = new Message(2, 3);
		Message m3 = new Message(1, 3);
		Message m4 = new Message(4, 5);
		Message m5 = new Message(1, 5);

		toListMessages.add(m1);
		toListMessages.add(m2);
		toListMessages.add(m3);
		toListMessages.add(m4);
		toListMessages.add(m5);

		when((testMessageMapper.listMessage())).thenReturn(toListMessages);

		doAnswer(invocation -> {

			String arg0 = invocation.getArgument(0);
			String arg1 = invocation.getArgument(1);
			String arg2 = invocation.getArgument(1);

			assertTrue(arg0 instanceof String);
			assertTrue(arg1 instanceof String);
			assertTrue(arg2 instanceof String);
			return null;
		}).when(testSongMapper).addSong(any(String.class), any(String.class), any(String.class));

		doAnswer(invocation -> {

			Integer arg00 = invocation.getArgument(0);
			String arg0 = invocation.getArgument(1);
			String arg1 = invocation.getArgument(2);
			String arg2 = invocation.getArgument(3);

			Integer arg3 = invocation.getArgument(4);
			Float arg4 = invocation.getArgument(5);
			Integer arg5 = invocation.getArgument(6);

			assertTrue(arg00 instanceof Integer);
			assertTrue(arg0 instanceof String);
			assertTrue(arg1 instanceof String);
			assertTrue(arg2 instanceof String);
			assertTrue(arg3 instanceof Integer);
			assertTrue(arg4 instanceof Float);
			assertTrue(arg5 instanceof Integer);

			return null;
		}).when(testSongMapper).updateSong(any(Integer.class), any(String.class), any(String.class), any(String.class),
				any(Integer.class), any(Float.class), any(Integer.class));

		doAnswer(invocation -> {

			Integer arg00 = invocation.getArgument(0);

			assertTrue(arg00 instanceof Integer);

			return null;
		}).when(testSongMapper).deleteSong(any(Integer.class));

		doAnswer(invocation -> {

			String arg0 = invocation.getArgument(0);
			String arg1 = invocation.getArgument(1);

			assertTrue(arg0 instanceof String);
			assertTrue(arg1 instanceof String);
			return null;
		}).when(testRegularMapper).addRegular(any(String.class), any(String.class));

		doAnswer(invocation -> {

			Integer arg00 = invocation.getArgument(0);
			String arg0 = invocation.getArgument(1);
			String arg1 = invocation.getArgument(2);

			assertTrue(arg00 instanceof Integer);
			assertTrue(arg0 instanceof String);
			assertTrue(arg1 instanceof String);

			return null;
		}).when(testRegularMapper).updateRegular(any(Integer.class), any(String.class), any(String.class));

		doAnswer(invocation -> {

			Integer arg00 = invocation.getArgument(0);

			assertTrue(arg00 instanceof Integer);

			return null;
		}).when(testRegularMapper).deleteRegular(any(Integer.class));

		doAnswer(invocation -> {

			String arg0 = invocation.getArgument(0);
			Integer arg1 = invocation.getArgument(1);

			assertTrue(arg0 instanceof String);
			assertTrue(arg1 instanceof Integer);
			return null;
		}).when(testPlaylistMapper).addPlaylist(any(String.class), any(Integer.class));

		doAnswer(invocation -> {

			Integer arg0 = invocation.getArgument(0);
			String arg1 = invocation.getArgument(1);

			assertTrue(arg0 instanceof Integer);
			assertTrue(arg1 instanceof String);
			return null;
		}).when(testPlaylistMapper).updatePlaylist(any(Integer.class), any(String.class));

		doAnswer(invocation -> {

			Integer arg00 = invocation.getArgument(0);

			assertTrue(arg00 instanceof Integer);

			return null;
		}).when(testPlaylistMapper).deletePlaylist(any(Integer.class));

		doAnswer(invocation -> {

			Integer arg0 = invocation.getArgument(0);
			Integer arg1 = invocation.getArgument(1);

			assertTrue(arg0 instanceof Integer);
			assertTrue(arg1 instanceof Integer);
			return null;
		}).when(testEntriesMapper).addSongPlaylist(any(Integer.class), any(Integer.class));

		doAnswer(invocation -> {

			Integer arg0 = invocation.getArgument(0);
			Integer arg1 = invocation.getArgument(1);
			Integer arg2 = invocation.getArgument(1);

			assertTrue(arg0 instanceof Integer);
			assertTrue(arg1 instanceof Integer);
			assertTrue(arg2 instanceof Integer);
			return null;
		}).when(testEntriesMapper).updateSongPlaylist(any(Integer.class), any(Integer.class), any(Integer.class));

		doAnswer(invocation -> {

			Integer arg00 = invocation.getArgument(0);

			assertTrue(arg00 instanceof Integer);

			return null;
		}).when(testEntriesMapper).deleteSongPlaylist(any(Integer.class));

		doAnswer(invocation -> {

			Integer arg0 = invocation.getArgument(0);
			Integer arg1 = invocation.getArgument(1);

			assertTrue(arg0 instanceof Integer);
			assertTrue(arg1 instanceof Integer);
			return null;
		}).when(testFriendsMapper).addFriends(any(Integer.class), any(Integer.class));

		doAnswer(invocation -> {

			Integer arg0 = invocation.getArgument(0);
			Integer arg1 = invocation.getArgument(1);
			Integer arg2 = invocation.getArgument(2);

			assertTrue(arg0 instanceof Integer);
			assertTrue(arg1 instanceof Integer);
			assertTrue(arg2 instanceof Integer);
			return null;
		}).when(testFriendsMapper).updateFriends(any(Integer.class), any(Integer.class), any(Integer.class));

		doAnswer(invocation -> {

			Integer arg00 = invocation.getArgument(0);

			assertTrue(arg00 instanceof Integer);

			return null;
		}).when(testFriendsMapper).deleteFriends(any(Integer.class));

		doAnswer(invocation -> {

			Integer arg0 = invocation.getArgument(0);
			Integer arg1 = invocation.getArgument(1);

			assertTrue(arg0 instanceof Integer);
			assertTrue(arg1 instanceof Integer);
			return null;
		}).when(testMessageMapper).addMessage(any(Integer.class), any(Integer.class));

		doAnswer(invocation -> {

			Integer arg0 = invocation.getArgument(0);
			Integer arg1 = invocation.getArgument(1);
			Integer arg2 = invocation.getArgument(2);

			assertTrue(arg0 instanceof Integer);
			assertTrue(arg1 instanceof Integer);
			assertTrue(arg2 instanceof Integer);
			return null;
		}).when(testMessageMapper).updateMessage(any(Integer.class), any(Integer.class), any(Integer.class));

		doAnswer(invocation -> {

			Integer arg00 = invocation.getArgument(0);

			assertTrue(arg00 instanceof Integer);

			return null;
		}).when(testMessageMapper).deleteMessage(any(Integer.class));

	}
	

	@Test
	public void TestControllerToDBOperation() {

		setValues();

		ControllerToDBOperation testOperation = new ControllerToDBOperation(testRegularMapper, testSongMapper,
				testPlaylistMapper, testEntriesMapper);

		assertEquals(testOperation.getSongs()[0], "SongName1-Song1");
		assertEquals(testOperation.getSongs()[1], "SongName2-Song2");

		assertEquals(testOperation.getRegs()[0], "user1");
		assertEquals(testOperation.getRegs()[1], "user2");

		assertEquals(testOperation.getPlays()[0], "playlist1");

		testOperation.addRegular("user", "password");

		testOperation.updateRegular("user", "user", "password");

		testOperation.deleteRegular("user");

		testOperation.addSong("title", "artist", "genre");

		testOperation.updateSong("old1", "old2", "title", "artist", "genre", 1, 2);

		testOperation.deleteSong("artist", "song");

		testOperation.refreshList();

		assertEquals(testOperation.getPlaysTable()[0][0], "0");
		assertEquals(testOperation.getPlaysTable()[0][1], "playlist1");

		assertEquals(testOperation.getRegsTable()[0][0], "0");
		assertEquals(testOperation.getRegsTable()[0][1], "user1");

		assertEquals(testOperation.getSongsTable()[0][0], "0");
		assertEquals(testOperation.getSongsTable()[0][1], "SongName1");

		// verify(testSongMapper, times(1)).addSong("name", "artist", "genre");
//	    verify(testSongMapper, times(1)).updateSong(1, "name", "artist", "genre", 1, 2, 3);
//	    verify(testSongMapper, times(1)).deleteSong(1);

	}

	@Test
	public void TestPlaylistManager() {

		setValues();

		PlaylistManager testPlaylistManager = new PlaylistManager(testRegularMapper, testSongMapper, testPlaylistMapper,
				testEntriesMapper);

		assertEquals(testPlaylistManager.getByRegular("user1").size(), 0);

		testPlaylistManager.addPlaylist("name playlist", "user");
		
		//testPlaylistManager.addEntry("name artist", "nameS");

		testPlaylistManager.addEntry("Song1", "SongName1", "playlist1");

		testPlaylistManager.deleteEntry("name", "playlist", "user");

		assertEquals(testPlaylistManager.getByRegular("user1").size(), 0);

		assertEquals(testPlaylistManager.getSongInPlaylist("playlist1").size(), 0);

		testPlaylistManager.playSong("Song1", "SongName1");

		testPlaylistManager.rateSong("Song1", "SongName1", 4);

		testPlaylistManager.searchSong("Song1", "SongName1", "SongGenre1", false, false);
		testPlaylistManager.searchSong("Song1", "SongName1", "SongGenre1", false, true);
		testPlaylistManager.searchSong("Song1", "SongName1", "SongGenre1", true, false);
		testPlaylistManager.searchSong("Song1", "SongName1", "SongGenre1", true, true);
		
		testPlaylistManager.searchSong("SongName1", "Song1", "SongGenre1", false, false);
		testPlaylistManager.searchSong("SongName1", "Song1", "SongGenre1", false, true);
		testPlaylistManager.searchSong("SongName1", "Song1", "SongGenre1", true, false);
		testPlaylistManager.searchSong("SongName1", "Song1", "SongGenre1", true, true);

	}

	@Test
	public void TestFriendsManager() {

		setValues();

		FriendsManager testFriendsManager = new FriendsManager(testRegularMapper, testSongMapper, testPlaylistMapper,
				testEntriesMapper, testFriendsMapper, testMessageMapper);

		// assertEquals(testFriendsManager.getByRegular("user1").size(), 0);

		testFriendsManager.addFriends("user3", "user5");
		testFriendsManager.deleteFriends("user3", "user5");
		testFriendsManager.createMessage("user1", "SongName1", "Song1", "user2");
		testFriendsManager.deleteMessage("user1", "SongName1", "Song1", "user2");

		assertEquals(testFriendsManager.getAllSongs()[0], "SongName1-Song1");

		assertEquals(testFriendsManager.getAllUsers("user2")[0], "user1");

		assertEquals(testFriendsManager.getFriends("user1")[0], null);

		assertEquals(testFriendsManager.getMessages("user1")[0], null);

	}

	@Test
	public void TestPlaylistBuilder() {

		setValues();

		ArtistList testArtistList = new ArtistList(testRegularMapper, testSongMapper, testPlaylistMapper,
				testEntriesMapper);
		GenreList testGenreList = new GenreList(testRegularMapper, testSongMapper, testPlaylistMapper,
				testEntriesMapper);
		GeneralList testGeneralList = new GeneralList(testRegularMapper, testSongMapper, testPlaylistMapper,
				testEntriesMapper);

		testArtistList.refreshList();
		testArtistList.updateCriteria("user1");
		testArtistList.updateList();

		assertEquals(testArtistList.getType(), "Artist");

		assertEquals(testArtistList.getSong().length, 0);

		testGenreList.refreshList();
		testGenreList.updateCriteria("user2");
		testGenreList.updateList();

		assertEquals(testGenreList.getType(), "Genre");

		assertEquals(testGenreList.getSong().length, 0);

		testGeneralList.refreshList();
		testGeneralList.updateCriteria("user1");
		testGeneralList.updateList();

		assertEquals(testGeneralList.getType(), "General");

		assertEquals(testGeneralList.getSong()[0], "SongName1-Song1");

		PlaylistBuilder testPlaylistBuilder = new PlaylistBuilder(testArtistList2, testGenreList2, testGeneralList2);

		ArrayList<String> testStrings = new ArrayList<String>();
		ArrayList<Song> testSongs = new ArrayList<Song>();
		ArrayList<Regular> testRegs = new ArrayList<Regular>();
		ArrayList<Playlist> testPlays = new ArrayList<Playlist>();
		ArrayList<SongPlaylist> testEnts = new ArrayList<SongPlaylist>();

		testStrings.add("Song1");
		testStrings.add("Song1");
		testStrings.add("Song2");

		assertEquals(testPlaylistBuilder.eliminateDuplicates(testStrings).get(1), "Song2");

		assertEquals(testPlaylistBuilder.getBuildedPlaylist("user1", false, false)[0], "Song1");
		assertEquals(testPlaylistBuilder.getBuildedPlaylist("user1", true, false)[0], "Song1");
		assertEquals(testPlaylistBuilder.getBuildedPlaylist("user1", false, true)[0], "Song3");
		assertEquals(testPlaylistBuilder.getBuildedPlaylist("user1", true, true)[0], "Song1");

		testGeneralList.getContains();
		testGeneralList.getCriteria();
		
		testGeneralList.setContains(testStrings);
		testGeneralList.setCriteria(testStrings);
		
		testGeneralList.setEntriesList(testEnts);
		testGeneralList.setEM(testEntriesMapper);
		
		testGeneralList.setPlaylistList(testPlays);
		testGeneralList.setPM(testPlaylistMapper);
		
		testGeneralList.setRegularList(testRegs);
		testGeneralList.setRM(testRegularMapper);
		
		testGeneralList.setSM(testSongMapper);
		testGeneralList.setSongList(testSongs);

	}

	@Test
	public void TestMappers() {

		setValues();
		
		testRegularMapper.addRegular("test", "test");
		testRegularMapper.updateRegular(1, "test", "test");
		testRegularMapper.deleteRegular(1);
		testRegularMapper.listRegular();
		
		testSongMapper.addSong("test", "test", "test");
		testSongMapper.updateSong(1, "test", "test", "test", 0, 0, 0);
		testSongMapper.deleteSong(1);
		testSongMapper.listSong();
		
		
		testPlaylistMapper.addPlaylist("test", 1);
		testPlaylistMapper.updatePlaylist(1, "test");
		testPlaylistMapper.deletePlaylist(1);
		testPlaylistMapper.listPlaylist();
		
		testEntriesMapper.addSongPlaylist(1, 1);
		testEntriesMapper.updateSongPlaylist(1, 1, 1);
		testEntriesMapper.deleteSongPlaylist(1);
		testEntriesMapper.listSongPlaylist();
		
		
		testMessageMapper.addMessage(1, 1);
		testMessageMapper.updateMessage(1, 1, 1);
		testMessageMapper.deleteMessage(1);
		testMessageMapper.listMessage();
		
		testFriendsMapper.addFriends(1, 1);
		testFriendsMapper.updateFriends(1, 1, 1);
		testFriendsMapper.deleteFriends(1);
		testFriendsMapper.listFriends();

	}
	
	
	@Test
	public void TestAdminWindow() {

		setController();
		
		@SuppressWarnings("unused")
		AdminWindow adminTest = new AdminWindow(testControl);


	}
	
	@Test
	public void TestUserWindow() {

		setController();
		
		@SuppressWarnings("unused")
		UserWindow userTest = new UserWindow("user1", testControl);


	}
	
	@Test
	public void TestLoginWindow() {

		setController();
		
		@SuppressWarnings("unused")
		LoginPage loginTest = new LoginPage();


	}
	
	@Test
	public void TestError() {

		setController();
		
		@SuppressWarnings("unused")
		ErrorUserPass loginTest = new ErrorUserPass();


	}
	
	
	@Test
	public void TestTabelView() {

		setController();
		
		@SuppressWarnings("unused")
		ViewWindow loginTest = new ViewWindow("Regular", testControl);
		
		@SuppressWarnings("unused")
		ViewWindow loginTest2 = new ViewWindow("Song", testControl);


	}
	
	@Test
	public void TestNotification() {

		setController();
		
		@SuppressWarnings("unused")
		Notification notTest = new Notification("user1");
		


	}
	
	@Test
	public void TestOnlineClient() {

		
		@SuppressWarnings("unused")
		OnlineClient toTest = new OnlineClient(testSocket, "user1");
		toTest.getClient();
		toTest.getClientSocket();
		toTest.setClient("User1");
		toTest.setClientSocket(testSocket);
		


	}
	

}