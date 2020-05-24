package Server.BussinessLayer;

import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;
import java.util.ArrayList;

import Client.BussinessLayer.ReportFactory;



public class ConnectionServer {
	
	ControllerToDBOperation control = new ControllerToDBOperation();
	PlaylistManager selector = new PlaylistManager();
	ReportFactory reportFactory = new ReportFactory();
	
	
	
	public ConnectionServer()
	{
		
	}
	
	public ConnectionServer(ControllerToDBOperation a, PlaylistManager b, ReportFactory c)
	{
		control = a;
		selector = b;
		reportFactory = c;
	}

	
	
	//CONNECTION ON SERVER SIDE
	
	static class Connection extends Thread {
        private final Socket clientSocket;
        
        ControllerToDBOperation control = new ControllerToDBOperation();
        LoginChecker checker = new LoginChecker();
        PlaylistManager selector = new PlaylistManager();
        FriendsManager friendly = new FriendsManager();
        
        NotificationMaster notifier = new NotificationMaster(friendly);
        
        PlaylistBuilder builder = new PlaylistBuilder();
        

        public Connection(Socket clientSocket)
        {
            this.clientSocket = clientSocket;
        }
        


        @Override
        public void run()
        {
            try(ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream()))
            {
            	
            	OnlineClient auxOC = new OnlineClient(clientSocket, "not SURE");
            	
            	
                while (clientSocket.isConnected())
                {
//                    if (System.currentTimeMillis() - lastSentMessageMillis > 10000)
//                    {
//                        System.out.println(Instant.now() + " Sending the notification to client");
//                        output.writeObject("Notification");
//                        lastSentMessageMillis = System.currentTimeMillis();
//                    }

                    // Seems that input.available() is not reliable?
                    boolean clientHasData = clientSocket.getInputStream().available() > 0;
                    if (clientHasData) {
                        String msg = (String) input.readObject();
                        
                        System.out.println("Primit de la client:   "+msg);
                        
                        switch (msg) {
                        
                        	case "addRegular":
                        		
                        		String user = (String) input.readObject();
                        		String password = (String) input.readObject();
                        		System.out.println("Add regular request!");
                        		control.addRegular(user, password);
                        		System.out.println(user + " " + password);
                        		break;
                        		
                        	case "checking":
                        		
                        		String user1 = (String) input.readObject();
                        		String password1 = (String) input.readObject();

                        		String response  = checker.checking(user1, password1);
                        		System.out.println(user1 + "   "+ password1 + "  " +response);
                        		
                        		if(response.equals("Regular"))
                        		{
                        			auxOC.setClient(user1);
                        			NotificationMaster.clients.add(auxOC);
                        		}
                        		
                        		output.writeObject(response);
                        		break;
                        		
                        	case "getPlaylistByReg":
                        		String msg3 = (String) input.readObject();
                        		System.out.println("Get playlist for: " +msg3);
                        		
                        		ArrayList<String> auxPlayListByReg = selector.getByRegular(msg3);
                        		Integer countPlaylist = auxPlayListByReg.size();
                        		
                        		output.writeObject(countPlaylist.toString());
                        		
                        		for(String aux : auxPlayListByReg)
                        		{
                        			output.writeObject(aux);
                        		}
                        		break;
                        		
                        	case "getSongsInPlaylist":
                        		String msg4 = (String) input.readObject();
                        		System.out.println("Get playlist for: " +msg4);
                        		
                        		ArrayList<String> auxSongsByPlaylist = selector.getSongInPlaylist(msg4);
                        		Integer countSongsPlaylist = auxSongsByPlaylist.size();
                        		
                        		output.writeObject(countSongsPlaylist.toString());
                        		
                        		for(String aux : auxSongsByPlaylist)
                        		{
                        			output.writeObject(aux);
                        		}
                        		break;
                        		
                        	case "createPlaylist":
                        		String msg5 = (String) input.readObject();
                        		String msg6 = (String) input.readObject();
                        		System.out.println("Create playlist for: " +msg5+ " with name: "+ msg6);
                        		
                        		selector.addPlaylist(msg6, msg5);

                        		break;
                        	case "addSongToPlaylist":
                        		String msg7 = (String) input.readObject();
                        		String msg8 = (String) input.readObject();
                        		String msg9 = (String) input.readObject();
                        		System.out.println("Add song: " +msg7 +" "+ msg8+ " in playlist: "+ msg9);
                        		
                        		selector.addEntry(msg7, msg8, msg9);

                        		break;
                        		
                        	case "playSong":
                        		String msg10 = (String) input.readObject();
                        		String msg11 = (String) input.readObject();
                        		System.out.println("Play song: " +msg10 +" "+ msg11);
                        		
                        		selector.playSong(msg10, msg11);

                        		break;
                        		
                        	case "rateSong":
                        		String msgrate = (String) input.readObject();
                        		String msgrate2 = (String) input.readObject();
                        		String msgrate3 = (String) input.readObject();
                        		System.out.println("Rate song: " +msgrate +" "+ msgrate2 + " with " + msgrate3);
                        		
                        		selector.rateSong(msgrate, msgrate2, Integer.parseInt(msgrate3));

                        		break;
                        		
                        	case "deleteFromPlaylist":
                        		String msg12 = (String) input.readObject();
                        		String msg13 = (String) input.readObject();
                        		String msg14 = (String) input.readObject();
                        		System.out.println("Delete song: " +msg12 +" "+ msg13+ " in playlist: "+ msg14);
                        		
                        		selector.deleteEntry(msg12, msg13, msg14);

                        		break;
                        		
                        	case "searchSong":
                        		String msg15 = (String) input.readObject();
                        		String msg16 = (String) input.readObject();
                        		String msg17 = (String) input.readObject();
                        		String msg18 = (String) input.readObject();
                        		String msgratings = (String) input.readObject();
                        		
                        		boolean views = false;
                        		
                        		if(msg18.equals("true"))
                        		{
                        			views = true;
                        		}
                        		
                        		boolean ratings = false;
                        		
                        		if(msgratings.equals("true"))
                        		{
                        			ratings = true;
                        		}
                        		
                        		System.out.println("Search song: " +msg15 +" "+ msg16+ " in genre: "+ msg17 + " views: " + views+ " ratings: " + ratings);
                        		
                        		
                        		
                        		String[] auxSearchedSongs = selector.searchSong(msg15, msg16, msg17, views, ratings);
                        		Integer countSongsSearched = auxSearchedSongs.length;
                        		
                        		output.writeObject(countSongsSearched.toString());
                        		
                        		for(int i = 0; i < countSongsSearched; i++)
                        		{
                        			output.writeObject(auxSearchedSongs[i]);
                        		}
                        		break;
                        		
                        	case "buildPlaylist":
                        		String buildUser = (String) input.readObject();
                        		String buildArtist = (String) input.readObject();
                        		String buildGenre = (String) input.readObject();
                        		
                        		boolean artistCheck = false;
                        		
                        		if(buildArtist.equals("true"))
                        		{
                        			artistCheck = true;
                        		}
                        		
                        		boolean genreCheck = false;
                        		
                        		if(buildGenre.equals("true"))
                        		{
                        			genreCheck = true;
                        		}
                        		
                        		System.out.println("Build playlist: " +buildUser + " artist: "+ buildArtist + " genre: " + buildGenre);
                        		
                        		
                        		
                        		String[] auxBuilderSongs = builder.getBuildedPlaylist(buildUser, artistCheck, genreCheck);
                        		Integer countBuilder = auxBuilderSongs.length;
                        		
                        		output.writeObject(countBuilder.toString());
                        		
                        		for(int i = 0; i < countBuilder; i++)
                        		{
                        			System.out.println("Built playlist: " + auxBuilderSongs[i]);
                        			output.writeObject(auxBuilderSongs[i]);
                        		}
                        		break;
                        		
                        	case "getRegs":
                        		System.out.println("Get regulars. ");
                        		
                        		String[] auxRegs = control.getRegs();
                        		Integer countRegs = auxRegs.length;
                        		
                        		output.writeObject(countRegs.toString());
                        		
                        		for(int i = 0; i < countRegs; i++)
                        		{
                        			output.writeObject(auxRegs[i]);
                        		}
                        		break;
                        	case "getSongs":
                        		System.out.println("Get songs. ");
                        		
                        		String[] auxSongs = control.getSongs();
                        		Integer countSongs = auxSongs.length;
                        		
                        		output.writeObject(countSongs.toString());
                        		
                        		for(int i = 0; i < countSongs; i++)
                        		{
                        			output.writeObject(auxSongs[i]);
                        		}
                        		break;
                        		
                        	case "getPlays":
                        		System.out.println("Get playlists. ");
                        		
                        		String[] auxPlays = control.getPlays();
                        		Integer countPlays = auxPlays.length;
                        		
                        		output.writeObject(countPlays.toString());
                        		
                        		for(int i = 0; i < countPlays; i++)
                        		{
                        			output.writeObject(auxPlays[i]);
                        		}
                        		break;
                        		
                        	case "updateRegular":
                        		
                        		String infoUpdate = (String) input.readObject();
                        		String userUpdate = (String) input.readObject();
                        		String passwordUpdate = (String) input.readObject();
                        		System.out.println("Update regular request!");
                        		control.updateRegular(infoUpdate, userUpdate, passwordUpdate);
                        		System.out.println(userUpdate + " " + passwordUpdate);
                        		break;
                        		
                        	case "deleteRegular":
                        		
                        		String infodelete = (String) input.readObject();
                        		System.out.println("Delete regular request!");
                        		control.deleteRegular(infodelete);
                        		System.out.println(infodelete);
                        		break;
                        		
                        	case "addSong":
                        		
                        		String addTitle = (String) input.readObject();
                        		String addArtist = (String) input.readObject();
                        		String addGenre = (String) input.readObject();
                        		System.out.println("Add song request!");
                        		control.addSong(addTitle, addArtist, addGenre);
                        		System.out.println(addTitle + addArtist + addGenre);
                        		break;
                        		
                        	case "updateSong":
                        		
                        		String old1 = (String) input.readObject();
                        		String old2 = (String) input.readObject();
                        		
                        		String updateTitle = (String) input.readObject();
                        		String updateArtist = (String) input.readObject();
                        		String updateGenre = (String) input.readObject();
                        		String updateViews = (String) input.readObject();
                        		String updateRatings = (String) input.readObject();
                        		
                        		
                        		System.out.println("Update song request!");
                        		control.updateSong(old1, old2, updateTitle, updateArtist, updateGenre, Integer.parseInt(updateViews), Integer.parseInt(updateRatings));
                        		
                        		
                        		System.out.println(updateTitle + updateArtist + updateGenre);
                        		break;
                        		
                        	case "deleteSong":
                        		
                        		String old3 = (String) input.readObject();
                        		String old4 = (String) input.readObject();
                        		System.out.println("Delete song request!");
                        		control.deleteSong(old3, old4);
                        		System.out.println(old3 + old4);
                        		break;
                        		
                        	case "getRegsTable":
                        		
                        		System.out.println("Table regulars request!");
                        		Object[][] aux = control.getRegsTable();
                        		
                        		Integer tableSize1 = aux.length;
                        		
                        		output.writeObject(tableSize1.toString());
                        		
                        		for(int i =0; i < aux.length; i++)
                        		{
                        			output.writeObject((String)aux[i][0]);
                        			output.writeObject((String)aux[i][1]);
                        			output.writeObject((String)aux[i][2]);
                        		}
                        		
                        		break;
                        		
                        	case "getSongsTable":
                        		
                        		System.out.println("Table songs request!");
                        		Object[][] aux2 = control.getSongsTable();
                        		
                        		Integer tableSize2 = aux2.length;
                        		
                        		output.writeObject(tableSize2.toString());
                        		
                        		for(int i =0; i < aux2.length; i++)
                        		{
                        			output.writeObject((String)aux2[i][0]);
                        			output.writeObject((String)aux2[i][1]);
                        			output.writeObject((String)aux2[i][2]);
                        			output.writeObject((String)aux2[i][3]);
                        			output.writeObject((String)aux2[i][4]);
                        			output.writeObject((String)aux2[i][5]);
                        			output.writeObject((String)aux2[i][6]);
                        		}
                        		
                        		break;
                        		
                        	case "getAllSongs":
                        		
                        		System.out.println("All songs request!");
                        		String[] aux3 = friendly.getAllSongs();
                        		
                        		Integer countSongs2 = aux3.length;
                        		
                        		output.writeObject(countSongs2.toString());
                        		
                        		for(int i =0; i < aux3.length; i++)
                        		{
                        			output.writeObject((String)aux3[i]);
                        		}
                        		
                        		break;
                        		
                        	case "getAllUsers":
                        		
                        		String userToEvade = (String) input.readObject();
                        		
                        		System.out.println("All users request!");
                        		String[] aux4 = friendly.getAllUsers(userToEvade);
                        		
                        		Integer countUsers2 = aux4.length;
                        		
                        		output.writeObject(countUsers2.toString());
                        		
                        		for(int i =0; i < aux4.length; i++)
                        		{
                        			output.writeObject((String)aux4[i]);
                        		}
                        		
                        		break;
                        		
                        	case "getFriends":
                        		
                        		String userFriends = (String) input.readObject();
                        		
                        		System.out.println("Friend request for:" + userFriends);
                        		String[] aux5 = friendly.getFriends(userFriends);
                        		
                        		Integer countFriends = aux5.length;
                        		
                        		output.writeObject(countFriends.toString());
                        		
                        		for(int i =0; i < aux5.length; i++)
                        		{
                        			output.writeObject((String)aux5[i]);
                        		}
                        		
                        		break;
                        		
                        	case "addFriends":
                        		
                        		String friend1 = (String) input.readObject();
                        		String friend2 = (String) input.readObject();
                        		
                        		System.out.println("Add friend request for:" + friend1 + friend2);
                        		friendly.addFriends(friend1, friend2);
                        		
                        		break;
                        		
                        	case "deleteFriends":
                        		
                        		String friendDel1 = (String) input.readObject();
                        		String friendDel2 = (String) input.readObject();
                        		
                        		System.out.println("Delete friends for:" + friendDel1 + friendDel2);
                        		friendly.deleteFriends(friendDel1, friendDel2);
                        		
                        		break;
                        		
                        	case "createMessage":
                        		
                        		String sender = (String) input.readObject();
                        		String titleAux = (String) input.readObject();
                        		String artistAux = (String) input.readObject();
                        		String receiver = (String) input.readObject();
                        		
                        		System.out.println("Create message for:" + sender + receiver);
                        		notifier.setOutput(output);
                        		friendly.createMessage(sender, titleAux, artistAux, receiver);
                        		
                        		for(OnlineClient auxiliary : NotificationMaster.clients)
                        		{
                        			if(auxiliary.getClient().equals(receiver))
                        			{
                        				friendly.setState(sender);
                        			}
                        		}
                        		
                        		break;
                        	
                        	case "getMessage":
                        		
                        		String userMessage = (String) input.readObject();
                        		
                        		String[] aux6 = friendly.getMessages(userMessage);

                        		
                        		System.out.println("Get message request for:" + userMessage);
                        		
                        		Integer countMessages = aux6.length;
                        		
                        		output.writeObject(countMessages.toString());
                        		
                        		for(int i =0; i < aux6.length; i++)
                        		{
                        			output.writeObject((String)aux6[i]);
                        		}
                        		
                        		
                        		break;
                        		
                        	case "deleteMessage":
                        		
                        		String senderDelete = (String) input.readObject();
                        		String songTitleDelete = (String) input.readObject();
                        		String songArtistDelete = (String) input.readObject();
                        		String receiveDelete = (String) input.readObject();
                        		
                        		friendly.deleteMessage(senderDelete, songTitleDelete, songArtistDelete, receiveDelete);
                        		

                        		System.out.println("Delete message request!");

                        		
                        		
                        		break;
                        		
                        	case "SignOut":
                        		
                        		String userSO = (String) input.readObject();

                        		OnlineClient toRemove =null; 
                        		
                              for(OnlineClient auxSO : NotificationMaster.clients)
                              {
                              	if(auxSO.getClient().equals(userSO))
                              	{
                              		toRemove = auxSO;
                              	}
                              }
                              
                              NotificationMaster.clients.remove(toRemove);
                                
                        	  System.out.println("Sign out request: " + userSO);		
                        		
                        		break;
                        	
                        	
                        }
                    }

                    try
                    {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                
                
                System.out.println(Instant.now() + " Client disconnected?");
            }
            catch (IOException | ClassNotFoundException e)
            {
                e.printStackTrace();
            }

            // cleanup
            try
            {
                clientSocket.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
	
	
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
    	
    	System.out.println("OPEN THE SERVER");
    	
    	
    	
        try (ServerSocket socket = new ServerSocket(3000))
        {
            while (true)
            {
                System.out.println(Instant.now() + " Waiting for client...");
                Socket clientSocket = socket.accept();
                new Connection(clientSocket).start();
                
            }
        }
    }
	
	
	
}