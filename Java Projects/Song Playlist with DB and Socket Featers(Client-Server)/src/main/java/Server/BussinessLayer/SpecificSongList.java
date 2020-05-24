package Server.BussinessLayer;

import java.util.ArrayList;

import Server.DataLayer.Playlist;
import Server.DataLayer.PlaylistMapper;
import Server.DataLayer.Regular;
import Server.DataLayer.RegularMapper;
import Server.DataLayer.Song;
import Server.DataLayer.SongMapper;
import Server.DataLayer.SongPlaylist;
import Server.DataLayer.SongPlaylistMapper;

public abstract class SpecificSongList implements SongList{
	
	ArrayList<Regular> regularList = new ArrayList<Regular>();
	ArrayList<Song> songList = new ArrayList<Song>();
	ArrayList<Playlist> playlistList = new ArrayList<Playlist>();
	ArrayList<SongPlaylist> entriesList = new ArrayList<SongPlaylist>();

	RegularMapper rm = new RegularMapper();
	SongMapper sm = new SongMapper();
	PlaylistMapper pm = new PlaylistMapper();
	SongPlaylistMapper em = new SongPlaylistMapper();
	
	ArrayList<String> criteria= new ArrayList<String>();
	
	ArrayList<String> contains= new ArrayList<String>();
	
	public abstract void updateCriteria(String user);
	
	public abstract void updateList();
	
	public void refreshList() {
		regularList = rm.listRegular();
		songList = sm.listSong();
		playlistList = pm.listPlaylist();
		entriesList = em.listSongPlaylist();
	}

	public ArrayList<Regular> getRegularList() {
		return regularList;
	}

	public void setRegularList(ArrayList<Regular> regularList) {
		this.regularList = regularList;
	}

	public ArrayList<Song> getSongList() {
		return songList;
	}

	public void setSongList(ArrayList<Song> songList) {
		this.songList = songList;
	}

	public ArrayList<Playlist> getPlaylistList() {
		return playlistList;
	}

	public void setPlaylistList(ArrayList<Playlist> playlistList) {
		this.playlistList = playlistList;
	}

	public ArrayList<SongPlaylist> getEntriesList() {
		return entriesList;
	}

	public void setEntriesList(ArrayList<SongPlaylist> entriesList) {
		this.entriesList = entriesList;
	}

	public RegularMapper getRM() {
		return rm;
	}

	public void setRM(RegularMapper rM) {
		rm = rM;
	}

	public SongMapper getSM() {
		return sm;
	}

	public void setSM(SongMapper sM) {
		sm = sM;
	}

	public PlaylistMapper getPM() {
		return pm;
	}

	public void setPM(PlaylistMapper pM) {
		pm = pM;
	}

	public SongPlaylistMapper getEM() {
		return em;
	}

	public void setEM(SongPlaylistMapper eM) {
		em = eM;
	}

	public ArrayList<String> getCriteria() {
		return criteria;
	}

	public void setCriteria(ArrayList<String> criteria) {
		this.criteria = criteria;
	}

	public ArrayList<String> getContains() {
		return contains;
	}

	public void setContains(ArrayList<String> contains) {
		this.contains = contains;
	}

}
