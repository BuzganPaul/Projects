package UILayer;

import java.util.ArrayList;

import BussinessLayer.ControllerToDBOperation;
import BussinessLayer.PlaylistManager;
import BussinessLayer.Report;
import BussinessLayer.ReportFactory;


public class Controller {
	
	ControllerToDBOperation control = new ControllerToDBOperation();
	PlaylistManager selector = new PlaylistManager();
	ReportFactory reportFactory = new ReportFactory();
	
	public Controller()
	{
		
	}
	
	public Controller(ControllerToDBOperation a, PlaylistManager b, ReportFactory c)
	{
		control = a;
		selector = b;
		reportFactory = c;
	}
	
	public void addRegular(String user, String password)
	{
		control.addRegular(user, password);
	}
	
	public void updateRegular(String info, String user, String password)
	{
		control.updateRegular(info, user, password);
	}
	
	public void deleteRegular(String info)
	{
		control.deleteRegular(info);
	}
	
	public void addSong(String title, String artist, String genre)
	{
		control.addSong(title, artist, genre);
	}
	
	public void updateSong(String info, String title, String artist, String genre, int views)
	{
		String[] arrOfStr = info.split("-"); 
		control.updateSong(arrOfStr[0], arrOfStr[1], title, artist, genre, views);
	}
	
	public void deleteSong(String info)
	{
		String[] arrOfStr = info.split("-");
		control.deleteSong(arrOfStr[0], arrOfStr[1]);
	}
	
	public String[] getRegs()
	{
		return control.getRegs();
	}
	
	public String[] getSongs()
	{
		return control.getSongs();
	}
	
	public String[] getPlays()
	{
		return control.getPlays();
	}
	
	public Object[][] getRegsTable()
	{
		return control.getRegsTable();
	}
	
	public Object[][] getSongsTable()
	{
		return control.getSongsTable();
	}
	
	public Object[][] getPlaysTable()
	{
		return control.getPlaysTable();
	}
	
	public String[] getSongsInPlaylist(String playlist)
	{

		ArrayList<String> arr = selector.getSongInPlaylist(playlist);
        String str[] = new String[arr.size()]; 

        for (int j = 0; j < arr.size(); j++) { 
            str[j] = arr.get(j); 
        } 
  
        return str; 
	}
	
	
	public String[] searchSong(String title, String artist, String genre, boolean views)
	{
		return selector.searchSong(title, artist, genre, views);
	}
	
	public void playSong(String info)
	{
		String[] arrOfStr = info.split("-"); 
		selector.playSong(arrOfStr[1], arrOfStr[0]);
	}
	
	public void deleteFromPlaylist(String song, String playlist)
	{
		String[] arrOfStr = song.split("-"); 
		selector.deleteEntry(arrOfStr[1], arrOfStr[0], playlist);
	}
	
	public void addSongToPlaylist(String info, String playlist)
	{
		String[] arrOfStr = info.split("-"); 
		selector.addEntry(arrOfStr[1], arrOfStr[0], playlist);
	}
	
	public void generateReport(String type, String path, String name, String playlist)
	{
		ArrayList<String> arr = selector.getSongInPlaylist(playlist);
		
		String pathAux = path + "\\" + name + "-" + playlist + "." + type; 
		
		System.out.println(pathAux);
		
		Report reportAux = reportFactory.getReport(type, pathAux);
		reportAux.generate(arr);
		
	}
	
	public void createPlaylist(String username, String namePlaylist)
	{
		selector.addPlaylist(namePlaylist, username);
	}
	
	public String[] getPlaylistByReg(String username)
	{
		
		ArrayList<String> arr = selector.getByRegular(username);
		
        String str[] = new String[arr.size()]; 
        
       
        // ArrayList to Array Conversion 
        for (int j = 0; j < arr.size(); j++) { 
  
            // Assign each value to String array 
            str[j] = arr.get(j); 
        } 
        
        
		return str;
	}
}
