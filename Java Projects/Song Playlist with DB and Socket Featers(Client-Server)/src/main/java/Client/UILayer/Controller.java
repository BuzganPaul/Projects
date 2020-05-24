package Client.UILayer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

import Client.BussinessLayer.*;

public class Controller {

	ClientConnection connection;
	ObjectOutputStream output;
	ObjectInputStream input;

	public Controller() {
		try {
			this.connection = new ClientConnection(new Socket("localhost", 3000));
			output = connection.getOutput();
			input = connection.getInput();
			connection.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public Controller(String test) {
	}
	
	

	public String checking(String user, String password) {
		String response = null;
		// connection.start();
		try {
			connection.sendMessageToServer("checking");
			connection.sendMessageToServer(user);
			connection.sendMessageToServer(password);
			try {
				response = (String) input.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(response);
		return response;

	}

	public void addRegular(String user, String password) {

		// connection.start();
		try {
			connection.sendMessageToServer("addRegular");
			connection.sendMessageToServer(user);
			connection.sendMessageToServer(password);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateRegular(String info, String user, String password) {
		// control.updateRegular(info, user, password);

		try {
			connection.sendMessageToServer("updateRegular");
			connection.sendMessageToServer(info);
			connection.sendMessageToServer(user);
			connection.sendMessageToServer(password);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deleteRegular(String info) {
		// control.deleteRegular(info);

		try {
			connection.sendMessageToServer("deleteRegular");
			connection.sendMessageToServer(info);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addSong(String title, String artist, String genre) {
		// control.addSong(title, artist, genre);

		try {
			connection.sendMessageToServer("addSong");
			connection.sendMessageToServer(title);
			connection.sendMessageToServer(artist);
			connection.sendMessageToServer(genre);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateSong(String info, String title, String artist, String genre, int views, String rating) {
		String[] arrOfStr = info.split("-");
		String views2 = String.valueOf(views);

		try {
			connection.sendMessageToServer("updateSong");

			connection.sendMessageToServer(arrOfStr[0]);
			connection.sendMessageToServer(arrOfStr[1]);

			connection.sendMessageToServer(title);
			connection.sendMessageToServer(artist);
			connection.sendMessageToServer(genre);

			connection.sendMessageToServer(views2);
			connection.sendMessageToServer(rating);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// control.updateSong(arrOfStr[0], arrOfStr[1], title, artist, genre, views);
	}

	public void deleteSong(String info) {
		String[] arrOfStr = info.split("-");

		try {
			connection.sendMessageToServer("deleteSong");
			connection.sendMessageToServer(arrOfStr[0]);
			connection.sendMessageToServer(arrOfStr[1]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// control.deleteSong(arrOfStr[0], arrOfStr[1]);
	}

	public String[] getRegs() {
		String nrString = null;
		ArrayList<String> arr = new ArrayList<String>();

		// connection.start();
		try {
			connection.sendMessageToServer("getRegs");
			try {
				nrString = (String) input.readObject();

				for (int i = 0; i < Integer.parseInt(nrString); i++) {
					arr.add((String) input.readObject());
				}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(nrString);

		String str[] = new String[arr.size()];

		for (int j = 0; j < arr.size(); j++) {

			System.out.println(arr.get(j));

			str[j] = arr.get(j);
		}

		return str;
		// return control.getRegs();
	}

	public String[] getSongs() {
		String nrString = null;
		ArrayList<String> arr = new ArrayList<String>();

		// connection.start();
		try {
			connection.sendMessageToServer("getSongs");
			try {
				nrString = (String) input.readObject();

				for (int i = 0; i < Integer.parseInt(nrString); i++) {
					arr.add((String) input.readObject());
				}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(nrString);

		String str[] = new String[arr.size()];

		for (int j = 0; j < arr.size(); j++) {

			System.out.println(arr.get(j));

			str[j] = arr.get(j);
		}

		return str;
		// return control.getSongs();
	}

	public String[] getPlays() {
		String nrString = null;
		ArrayList<String> arr = new ArrayList<String>();

		// connection.start();
		try {
			connection.sendMessageToServer("getPlays");
			try {
				nrString = (String) input.readObject();

				for (int i = 0; i < Integer.parseInt(nrString); i++) {
					arr.add((String) input.readObject());
				}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(nrString);

		String str[] = new String[arr.size()];

		for (int j = 0; j < arr.size(); j++) {

			System.out.println(arr.get(j));

			str[j] = arr.get(j);
		}

		return str;
		// return control.getPlays();
	}

	public Object[][] getRegsTable() {
		String nrString = null;

		Object str[][] = new String[1000][3];

		// connection.start();
		try {
			connection.sendMessageToServer("getRegsTable");
			try {

				nrString = (String) input.readObject();

				for (int i = 0; i < Integer.parseInt(nrString); i++) {

					str[i][0] = (String) input.readObject();
					str[i][1] = (String) input.readObject();
					str[i][2] = (String) input.readObject();
				}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(nrString);

		return str;
		// return control.getRegsTable();
	}

	public Object[][] getSongsTable() {
		String nrString = null;

		Object str[][] = new String[1000][7];

		// connection.start();
		try {
			connection.sendMessageToServer("getSongsTable");
			try {

				nrString = (String) input.readObject();

				for (int i = 0; i < Integer.parseInt(nrString); i++) {

					str[i][0] = (String) input.readObject();
					str[i][1] = (String) input.readObject();
					str[i][2] = (String) input.readObject();
					str[i][3] = (String) input.readObject();
					str[i][4] = (String) input.readObject();
					str[i][5] = (String) input.readObject();
					str[i][6] = (String) input.readObject();
				}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(nrString);

		return str;
		// return control.getSongsTable();
	}

	public Object[][] getPlaysTable() {
		return null;
		// return control.getPlaysTable();
	}

	public String[] getSongsInPlaylist(String playlist) {

		String nrString = null;
		ArrayList<String> arr = new ArrayList<String>();

		// connection.start();
		try {
			connection.sendMessageToServer("getSongsInPlaylist");
			connection.sendMessageToServer(playlist);
			try {
				nrString = (String) input.readObject();

				for (int i = 0; i < Integer.parseInt(nrString); i++) {
					arr.add((String) input.readObject());
				}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(nrString);
		;

		String str[] = new String[arr.size()];

		for (int j = 0; j < arr.size(); j++) {
			str[j] = arr.get(j);
		}

		return str;
	}

	public String[] searchSong(String title, String artist, String genre, boolean views, boolean ratings) {

		String nrString = null;
		ArrayList<String> arr = new ArrayList<String>();

		// connection.start();
		try {
			connection.sendMessageToServer("searchSong");
			connection.sendMessageToServer(title);
			connection.sendMessageToServer(artist);
			connection.sendMessageToServer(genre);
			
			if (views == true) {
				connection.sendMessageToServer("true");
			}
			if (views == false) {
				connection.sendMessageToServer("false");
			}
			
			if (ratings == true) {
				connection.sendMessageToServer("true");
			}
			if (ratings == false) {
				connection.sendMessageToServer("false");
			}

			try {
				nrString = (String) input.readObject();

				for (int i = 0; i < Integer.parseInt(nrString); i++) {
					arr.add((String) input.readObject());
				}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(nrString);
		;

		String str[] = new String[arr.size()];

		for (int j = 0; j < arr.size(); j++) {

			System.out.println(arr.get(j));

			str[j] = arr.get(j);
		}

		return str;

		// return selector.searchSong(title, artist, genre, views);
	}
	
	
	public String[] buildPlaylist(String user, boolean artist, boolean genre) {

		String nrString = null;
		ArrayList<String> arr = new ArrayList<String>();

		// connection.start();
		try {
			connection.sendMessageToServer("buildPlaylist");
			connection.sendMessageToServer(user);
			
			if (artist == true) {
				connection.sendMessageToServer("true");
			}
			if (artist == false) {
				connection.sendMessageToServer("false");
			}
			
			if (genre == true) {
				connection.sendMessageToServer("true");
			}
			if (genre == false) {
				connection.sendMessageToServer("false");
			}

			try {
				nrString = (String) input.readObject();

				for (int i = 0; i < Integer.parseInt(nrString); i++) {
					arr.add((String) input.readObject());
				}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(nrString);
		;

		String str[] = new String[arr.size()];

		for (int j = 0; j < arr.size(); j++) {

			System.out.println(arr.get(j));

			str[j] = arr.get(j);
		}

		return str;

		// return selector.searchSong(title, artist, genre, views);
	}
	

	public void playSong(String info) {
		String[] arrOfStr = info.split("-");

		try {
			connection.sendMessageToServer("playSong");
			connection.sendMessageToServer(arrOfStr[1]);
			connection.sendMessageToServer(arrOfStr[0]);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// selector.playSong(arrOfStr[1], arrOfStr[0]);
	}
	
	public void rateSong(String info, String rate) {
		String[] arrOfStr = info.split("-");

		try {
			connection.sendMessageToServer("rateSong");
			connection.sendMessageToServer(arrOfStr[1]);
			connection.sendMessageToServer(arrOfStr[0]);
			connection.sendMessageToServer(rate);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// selector.playSong(arrOfStr[1], arrOfStr[0]);
	}

	public void deleteFromPlaylist(String song, String playlist) {
		String[] arrOfStr = song.split("-");

		try {
			connection.sendMessageToServer("deleteFromPlaylist");
			connection.sendMessageToServer(arrOfStr[1]);
			connection.sendMessageToServer(arrOfStr[0]);
			connection.sendMessageToServer(playlist);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// selector.deleteEntry(arrOfStr[1], arrOfStr[0], playlist);
	}

	public void addSongToPlaylist(String info, String playlist) {
		String[] arrOfStr = info.split("-");

		try {
			connection.sendMessageToServer("addSongToPlaylist");
			connection.sendMessageToServer(arrOfStr[1]);
			connection.sendMessageToServer(arrOfStr[0]);
			connection.sendMessageToServer(playlist);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// selector.addEntry(arrOfStr[1], arrOfStr[0], playlist);
	}

	public void generateReport(String type, String path, String name, String playlist) {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		ArrayList<String> arr = new ArrayList( Arrays.asList( getSongsInPlaylist(playlist)));
		
		String pathAux = path + "\\" + name + "-" + playlist + "." + type; 
		
		System.out.println(pathAux);
		
		Report reportAux = ReportFactory.getReport(type, pathAux);
		reportAux.generate(arr);

	}

	public void createPlaylist(String username, String namePlaylist) {
		// selector.addPlaylist(namePlaylist, username);

		// connection.start();
		try {
			connection.sendMessageToServer("createPlaylist");
			connection.sendMessageToServer(username);
			connection.sendMessageToServer(namePlaylist);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String[] getPlaylistByReg(String username) {

		String nrString = null;
		ArrayList<String> arr = new ArrayList<String>();

		// connection.start();
		try {
			connection.sendMessageToServer("getPlaylistByReg");
			connection.sendMessageToServer(username);
			try {
				nrString = (String) input.readObject();

				for (int i = 0; i < Integer.parseInt(nrString); i++) {
					arr.add((String) input.readObject());
				}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(nrString);
		;

		String str[] = new String[arr.size()];

		for (int j = 0; j < arr.size(); j++) {

			System.out.println(arr.get(j));

			str[j] = arr.get(j);
		}

		return str;
	}
	
	public String[] getAllUsers(String user) {

		String nrString = null;
		ArrayList<String> arr = new ArrayList<String>();

		// connection.start();
		try {
			connection.sendMessageToServer("getAllUsers");
			connection.sendMessageToServer(user);
			try {
				nrString = (String) input.readObject();

				for (int i = 0; i < Integer.parseInt(nrString); i++) {
					arr.add((String) input.readObject());
				}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(nrString);
		;

		String str[] = new String[arr.size()];

		for (int j = 0; j < arr.size(); j++) {

			System.out.println(arr.get(j));

			str[j] = arr.get(j);
		}

		return str;
	}
	
	
	public String[] getAllSongs() {

		String nrString = null;
		ArrayList<String> arr = new ArrayList<String>();

		// connection.start();
		try {
			connection.sendMessageToServer("getAllSongs");
			try {
				nrString = (String) input.readObject();

				for (int i = 0; i < Integer.parseInt(nrString); i++) {
					arr.add((String) input.readObject());
				}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(nrString);
		;

		String str[] = new String[arr.size()];

		for (int j = 0; j < arr.size(); j++) {

			System.out.println(arr.get(j));

			str[j] = arr.get(j);
		}

		return str;
	}
	
	public void addFriends(String friend1, String friend2)
	{
		try {
			connection.sendMessageToServer("addFriends");
			connection.sendMessageToServer(friend1);
			connection.sendMessageToServer(friend2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteFriends(String friend1, String friend2)
	{
		try {
			connection.sendMessageToServer("deleteFriends");
			connection.sendMessageToServer(friend1);
			connection.sendMessageToServer(friend2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String[] getFriends(String user)
	{
		String nrString = null;
		ArrayList<String> arr = new ArrayList<String>();

		// connection.start();
		try {
			connection.sendMessageToServer("getFriends");
			connection.sendMessageToServer(user);
			try {
				nrString = (String) input.readObject();

				for (int i = 0; i < Integer.parseInt(nrString); i++) {
					arr.add((String) input.readObject());
				}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(nrString);
		;

		String str[] = new String[arr.size()];

		for (int j = 0; j < arr.size(); j++) {

			System.out.println(arr.get(j));

			str[j] = arr.get(j);
		}

		return str;
	}
	
	
	
	public void createMessage(String user, String info, String user2)
	{
		
		String[] arrOfStr = info.split("-");

		// connection.start();
		try {
			connection.sendMessageToServer("createMessage");
			connection.sendMessageToServer(user);
			connection.sendMessageToServer(arrOfStr[1]);
			connection.sendMessageToServer(arrOfStr[0]);
			connection.sendMessageToServer(user2);
			}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	public String[] getMessage(String user)
	{
		String nrString = null;
		ArrayList<String> arr = new ArrayList<String>();

		// connection.start();
		try {
			connection.sendMessageToServer("getMessage");
			connection.sendMessageToServer(user);
			try {
				nrString = (String) input.readObject();

				for (int i = 0; i < Integer.parseInt(nrString); i++) {
					arr.add((String) input.readObject());
				}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(nrString);
		

		String str[] = new String[arr.size()];

		for (int j = 0; j < arr.size(); j++) {

			System.out.println(arr.get(j));

			str[j] = arr.get(j);
		}

		return str;
	}
	
	
	public void deleteMessage(String sender, String info, String receiver)
	{
		
		String[] arrOfStr = info.split("-");

		// connection.start();
		try {
			connection.sendMessageToServer("deleteMessage");
			connection.sendMessageToServer(sender);
			connection.sendMessageToServer(arrOfStr[1]);
			connection.sendMessageToServer(arrOfStr[0]);
			connection.sendMessageToServer(receiver);
			}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	public void signOut(String user)
	{

		// connection.start();
		try {
			connection.sendMessageToServer("SignOut");
			connection.sendMessageToServer(user);
			}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
//	    public static void main(String[] args) throws IOException, ClassNotFoundException
//	    {
//
//	        //BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
//	        System.out.println("OPEN CLIENT");
//			Controller connection = new Controller(new Socket("localhost", 3000));
//			connection.start();
//	        
////	        while (true)
////	        {
////	            System.out.println(Instant.now() + " Type the message to send to the server and press enter:");
////	
////	            String message = consoleInput.readLine();
////	            connection.sendMessageToServer(message);
////	        }
//	        
//			LoginPage window = new LoginPage();
//			window.frmLoginWindow.setVisible(true);
//	        
//	        
//	        //connection.sendMessageToServer("addRegular");
//	        //connection.sendMessageToServer("TEST C-S");
//	        //connection.sendMessageToServer("TEST C-S");
//	        
////	      BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
////	        
////            System.out.println(Instant.now() + "PRESENT YOURSELF TO SERVER: ");
////
////            String message = consoleInput.readLine();
////            connection.sendMessageToServer(message);
////            
////            System.out.println(Instant.now() + "MESSAGE TO SERVER: ");
////
////            String message2 = consoleInput.readLine();
////            connection.sendMessageToServer(message2);
//	        
//	    }
