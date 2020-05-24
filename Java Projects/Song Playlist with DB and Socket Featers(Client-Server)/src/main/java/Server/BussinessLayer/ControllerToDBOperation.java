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

public class ControllerToDBOperation {
	
	ArrayList<Regular> regularList = new ArrayList<Regular>();
	ArrayList<Song> songList = new ArrayList<Song>();
	ArrayList<Playlist> playlistList = new ArrayList<Playlist>();
	ArrayList<SongPlaylist> entriesList = new ArrayList<SongPlaylist>();
	
	RegularMapper rm = new RegularMapper();
	SongMapper sm = new SongMapper();
	PlaylistMapper pm = new PlaylistMapper();
	SongPlaylistMapper em = new SongPlaylistMapper();
	
	public ControllerToDBOperation()
	{
		
	}
	
	public ControllerToDBOperation(RegularMapper rm, SongMapper sm, PlaylistMapper pm, SongPlaylistMapper em)
	{
		this.rm = rm;
		this.sm = sm;
		this.pm = pm;
		this.em = em;
	}
	
	
	public void refreshList()
	{
		regularList = rm.listRegular();
		songList = sm.listSong();
		playlistList = pm.listPlaylist();
		entriesList = em.listSongPlaylist();
	}
	
	public String[] getRegs()
	{
		refreshList();
        String str[] = new String[regularList.size()]; 
        
        for (int j = 0; j < regularList.size(); j++) { 
  
            str[j] = regularList.get(j).getUsernameReg(); 
        } 
  
        return str; 
	}
	
	public String[] getSongs()
	{
		refreshList();
        String str[] = new String[songList.size()]; 
        
        for (int j = 0; j < songList.size(); j++) { 
  
            str[j] = songList.get(j).getSongArtist() + "-" + songList.get(j).getSongName(); 
        } 
  
        return str; 
		
	}
	
	public Object[][] getPlaysTable()
	{
		refreshList();
        Object str[][] = new Object[playlistList.size()][3]; 

        for (int j = 0; j < playlistList.size(); j++) { 

            str[j][0] =  String.valueOf(playlistList.get(j).getPlaylistID()); 
            str[j][1] = playlistList.get(j).getPlaylistName(); 
            str[j][2] = playlistList.get(j).getRegularID(); 
        } 
  
        return str; 
	}
	
	public Object[][] getRegsTable()
	{
		refreshList();
        Object str[][] = new String[regularList.size()][3]; 
        
        
        for (int j = 0; j < regularList.size(); j++) { 
        	str[j][0] =  String.valueOf(regularList.get(j).getRegularID());
            str[j][1] = regularList.get(j).getUsernameReg(); 
            str[j][2] = regularList.get(j).getPasswordReg();
            
        } 
  
        return str; 
	}
	
	public Object[][] getSongsTable()
	{
		refreshList();
        Object str[][] = new Object[songList.size()][7]; 
        
        for (int j = 0; j < songList.size(); j++) { 
  
            str[j][0] = String.valueOf(songList.get(j).getSongID());
            str[j][1] = songList.get(j).getSongArtist();
            str[j][2] = songList.get(j).getSongName(); 
            str[j][3] = songList.get(j).getGenre();
            str[j][4] = String.valueOf(songList.get(j).getViews());
            str[j][5] = String.valueOf(songList.get(j).getRating());
            str[j][6] = String.valueOf(songList.get(j).getNrRatings());
            
        } 
  
        return str; 
		
	}
	
	public String[] getPlays()
	{
		refreshList();
        String str[] = new String[playlistList.size()]; 

        for (int j = 0; j < playlistList.size(); j++) { 

            str[j] = playlistList.get(j).getPlaylistName(); 
        } 
  
        return str; 
	}
	
	public void addRegular(String username, String password)
	{
		refreshList();
		rm.addRegular(username, password);
		refreshList();
		
	}
	
	public void updateRegular(String ID, String username, String password)
	{
		refreshList();
		int RegularID = 0;
		for(Regular aux : regularList)
		{
			if(aux.getUsernameReg().equals(ID))
			{
				RegularID = aux.getRegularID();
			}
		}
		
		rm.updateRegular(RegularID, username, password);
		refreshList();
	}
	
	public void deleteRegular(String username)
	{
		refreshList();
		int RegularID = 0;
		for(Regular aux : regularList)
		{
			if(aux.getUsernameReg().equals(username))
			{
				RegularID = aux.getRegularID();
			}
		}
		
		rm.deleteRegular(RegularID);
		refreshList();
	}
	
	
	public void addSong(String title, String artist, String genre)
	{
		refreshList();
		sm.addSong(title, artist, genre);
		refreshList();
		
	}
	
	public void updateSong(String ID1, String ID2, String title, String artist, String genre, int views, float rating)
	{
		refreshList();
		int SongID = 0;
		int nrRatings = 0;
		for(Song aux : songList)
		{
			if(aux.getSongArtist().equals(ID1) && aux.getSongName().equals(ID2))
			{
				SongID = aux.getSongID();
				nrRatings = aux.getNrRatings();
			}
		}
		
		sm.updateSong(SongID, title, artist, genre, views, rating, nrRatings);
		refreshList();
	}
	
	public void deleteSong(String artist, String title)
	{
		refreshList();
		int SongID = 0;
		for(Song aux : songList)
		{
			if(aux.getSongName().equals(title) && aux.getSongArtist().equals(artist))
			{
				SongID = aux.getSongID();
			}
		}
		sm.deleteSong(SongID);
		refreshList();
	}
	
	
	

}
