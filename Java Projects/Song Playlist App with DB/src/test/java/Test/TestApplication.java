package Test;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.*;

import BussinessLayer.ControllerToDBOperation;
import BussinessLayer.PlaylistManager;
import BussinessLayer.ReportFactory;
import UILayer.Controller;

public class TestApplication  {
	
	@Mock
	PlaylistManager testPlaylistManager = new PlaylistManager();
	
	@Mock
	ControllerToDBOperation testOperation = new ControllerToDBOperation();
	
	@Mock
	ReportFactory testReport = new ReportFactory();
	
	
	
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();


    @Test
    public void test()  {
    	
    	Controller testController = new Controller(testOperation, testPlaylistManager, testReport);
    	
    	String[] searchedSongs = {"Song1", "Song2"};
    	
    	String[] blackSabath = {"Black Sabath-Paranoid", "Black Sabath-Iron Man", "Black Sabath-Sweet Leaf"};
    	
    	String[] orderedSongs = {"Ordered Song1", "Ordered Song2", "Ordered Song3"};
    	
    	ArrayList<String> playlist = new ArrayList<String>();
    	playlist.add("Playlist1");
    	playlist.add("Playlist2");
    	
    	ArrayList<String> blackSabathSongs = new ArrayList<String>();
    	blackSabathSongs.add("Black Sabath-Paranoid");
    	blackSabathSongs.add("Black Sabath-Iron Man");
    	blackSabathSongs.add("Black Sabath-Sweet Leaf");
    	
    	
    	String playlistName = "Name Playlist";
    	
    	String playlistUser = "Name User";
    	
    	
    	
    	
    	
    	when((testPlaylistManager.searchSong("Name", "Artist", "Genre", false))).thenReturn(searchedSongs);
    	when((testPlaylistManager.searchSong("Name", "Artist", "Genre", true))).thenReturn(orderedSongs);
    	when((testPlaylistManager.searchSong(null, "Black Sabath", null, true))).thenReturn(blackSabath);
    	
    	when((testPlaylistManager.getByRegular("Name User"))).thenReturn(playlist);
    	
    	when((testPlaylistManager.getSongInPlaylist("Playlist Black Sabath"))).thenReturn(blackSabathSongs);
    	
    	doNothing().when(testPlaylistManager).addPlaylist(isA(String.class), isA(String.class));
    	
    	doNothing().when(testPlaylistManager).playSong(isA(String.class), isA(String.class));
    	
    	doNothing().when(testPlaylistManager).addEntry(isA(String.class), isA(String.class), isA(String.class));
    	
    	doNothing().when(testPlaylistManager).deleteEntry(isA(String.class), isA(String.class), isA(String.class));
    	
    	
    	assertEquals(testController.searchSong("Name", "Artist", "Genre", false)[0], "Song1");
    	assertEquals(testController.searchSong("Name", "Artist", "Genre", false)[1], "Song2");
    	
    	
    	assertEquals(testController.searchSong("Name", "Artist", "Genre", true)[0], "Ordered Song1");
    	assertEquals(testController.searchSong("Name", "Artist", "Genre", true)[1], "Ordered Song2");
    	
    	assertEquals(testController.searchSong(null, "Black Sabath", null, true)[0], "Black Sabath-Paranoid");
    	assertEquals(testController.searchSong(null, "Black Sabath", null, true)[1], "Black Sabath-Iron Man");
    	
    	assertEquals(testController.getPlaylistByReg("Name User")[0], "Playlist1");
    	assertEquals(testController.getPlaylistByReg("Name User")[1], "Playlist2");
    	
    	assertEquals(testController.getSongsInPlaylist("Playlist Black Sabath")[0], "Black Sabath-Paranoid");
    	assertEquals(testController.getSongsInPlaylist("Playlist Black Sabath")[1], "Black Sabath-Iron Man");
    	assertEquals(testController.getSongsInPlaylist("Playlist Black Sabath")[2], "Black Sabath-Sweet Leaf");
    	
    	
    	//verify(testPlaylistManager, times(1)).addPlaylist("Name Test", "Playlist");
    	//verify(testPlaylistManager, times(1)).playSong("Name Test", "Artist Test");
    	//verify(testPlaylistManager, times(1)).addEntry("Name Test", "Artist Test","Playlist");
    	//verify(testPlaylistManager, times(1)).deleteEntry("Name Test", "Artist Test", "Playlist");
    	
    }


}