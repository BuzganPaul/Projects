package BussinessLayer;

import java.util.ArrayList;

import DataLayer.Playlist;
import DataLayer.PlaylistMapper;
import DataLayer.Regular;
import DataLayer.RegularMapper;
import DataLayer.Song;
import DataLayer.SongMapper;
import DataLayer.SongPlaylist;
import DataLayer.SongPlaylistMapper;

public class ControllerToDBOperation {
	
	ArrayList<Regular> regularList = new ArrayList<Regular>();
	ArrayList<Song> songList = new ArrayList<Song>();
	ArrayList<Playlist> playlistList = new ArrayList<Playlist>();
	ArrayList<SongPlaylist> entriesList = new ArrayList<SongPlaylist>();
	
	RegularMapper RM = new RegularMapper();
	SongMapper SM = new SongMapper();
	PlaylistMapper PM = new PlaylistMapper();
	SongPlaylistMapper EM = new SongPlaylistMapper();
	
	
	public void refreshList()
	{
		regularList = RM.listRegular();
		songList = SM.listSong();
		playlistList = PM.listPlaylist();
		entriesList = EM.listSongPlaylist();
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
        Object str[][] = new Object[songList.size()][5]; 
        
        for (int j = 0; j < songList.size(); j++) { 
  
            str[j][0] = String.valueOf(songList.get(j).getSongID());
            str[j][1] = songList.get(j).getSongArtist();
            str[j][2] = songList.get(j).getSongName(); 
            str[j][3] = songList.get(j).getGenre();
            str[j][4] = String.valueOf(songList.get(j).getViews());
            
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
		RM.addRegular(username, password);
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
		
		RM.updateRegular(RegularID, username, password);
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
		
		RM.deleteRegular(RegularID);
		refreshList();
	}
	
	
	public void addSong(String title, String artist, String genre)
	{
		refreshList();
		SM.addSong(title, artist, genre);
		refreshList();
		
	}
	
	public void updateSong(String ID1, String ID2, String title, String artist, String genre, int views)
	{
		refreshList();
		int SongID = 0;
		for(Song aux : songList)
		{
			if(aux.getSongArtist().equals(ID1) && aux.getSongName().equals(ID2))
			{
				SongID = aux.getSongID();
			}
		}
		
		SM.updateSong(SongID, title, artist, genre, views);
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
		SM.deleteSong(SongID);
		refreshList();
	}
	
	
	

}
