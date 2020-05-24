package Server.BussinessLayer;

import java.util.ArrayList;

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

public class FriendsManager {

	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
		notifyAllObservers();
	}

	public void attach(Observer observer) {
		observers.add(observer);
	}

	public void notifyAllObservers() {
		for (Observer observer : observers) {
			observer.update();
		}
	}

	ArrayList<Regular> regularList = new ArrayList<Regular>();
	ArrayList<Song> songList = new ArrayList<Song>();
	ArrayList<Playlist> playlistList = new ArrayList<Playlist>();
	ArrayList<SongPlaylist> entriesList = new ArrayList<SongPlaylist>();

	ArrayList<Friends> friendList = new ArrayList<Friends>();
	ArrayList<Message> messageList = new ArrayList<Message>();

	RegularMapper rm = new RegularMapper();
	SongMapper sm = new SongMapper();
	PlaylistMapper pm = new PlaylistMapper();
	SongPlaylistMapper em = new SongPlaylistMapper();

	FriendsMapper fm = new FriendsMapper();
	MessageMapper mm = new MessageMapper();
	
	public FriendsManager()
	{
		
	}
	
	public FriendsManager(RegularMapper rm, SongMapper sm, PlaylistMapper pm, SongPlaylistMapper em, FriendsMapper fm, MessageMapper mm)
	{
		this.rm = rm;
		this.sm = sm;
		this.pm = pm;
		this.em = em;
		this.fm = fm;
		this.mm = mm;
	}

	public void refreshList() {
		regularList = rm.listRegular();
		songList = sm.listSong();
		playlistList = pm.listPlaylist();
		entriesList = em.listSongPlaylist();

		friendList = fm.listFriends();
		messageList = mm.listMessage();
	}

	public String[] getAllSongs() {
		refreshList();

		String[] toRet = new String[songList.size()];

		int i = 0;

		for (Song aux : songList) {
			toRet[i] = aux.getSongArtist() + "-" + aux.getSongName();
			i++;
		}

		return toRet;
	}

	public String[] getAllUsers(String userToEvade) {
		refreshList();

		String[] toRet = new String[regularList.size() - 1];

		int i = 0;

		for (Regular aux : regularList) {
			if (!aux.getUsernameReg().equals(userToEvade)) {
				toRet[i] = aux.getUsernameReg();
				i++;
			}
		}

		return toRet;
	}

	public void addFriends(String user1, String user2) {
		refreshList();

		int RegularID1 = 0;
		int RegularID2 = 0;

		for (Regular aux : regularList) {
			if (aux.getUsernameReg().equals(user1)) {
				RegularID1 = aux.getRegularID();
			}
		}

		for (Regular aux : regularList) {
			if (aux.getUsernameReg().equals(user2)) {
				RegularID2 = aux.getRegularID();
			}
		}

		fm.addFriends(RegularID1, RegularID2);
		refreshList();
	}
	
	public void deleteFriends(String user1, String user2) {
		refreshList();

		int RegularID1 = 0;
		int RegularID2 = 0;
		int friendsID = 0;

		for (Regular aux : regularList) {
			if (aux.getUsernameReg().equals(user1)) {
				RegularID1 = aux.getRegularID();
			}
		}

		for (Regular aux : regularList) {
			if (aux.getUsernameReg().equals(user2)) {
				RegularID2 = aux.getRegularID();
			}
		}
		
		for (Friends aux : friendList) {
			if ((aux.getRegularID1() == RegularID1 && aux.getRegularID2() == RegularID2) || (aux.getRegularID1() == RegularID2 && aux.getRegularID2() == RegularID1)){
				friendsID = aux.getFriendsID();
			}
		}

		fm.deleteFriends(friendsID);
		refreshList();
	}

	public String[] getFriends(String user) {
		refreshList();

		int RegularID1 = 0;

		for (Regular aux : regularList) {
			if (aux.getUsernameReg().equals(user)) {
				RegularID1 = aux.getRegularID();
				break;
			}
		}

		String[] toRet = new String[songList.size()];

		int i = 0;

		for (Friends aux : friendList) {
			if (aux.getRegularID1() == RegularID1) {
				for (Regular aux2 : regularList) {
					if (aux.getRegularID2() == aux2.getRegularID() && !(aux2.getUsernameReg().equals(user))) {
						toRet[i] = aux2.getUsernameReg();
						i++;
					}
				}
			}
		}

		for (Friends aux : friendList) {
			if (aux.getRegularID2() == RegularID1) {
				for (Regular aux2 : regularList) {
					if (aux.getRegularID1() == aux2.getRegularID() && !(aux2.getUsernameReg().equals(user))) {
						toRet[i] = aux2.getUsernameReg();
						i++;
					}
				}
			}
		}

		return toRet;

	}

	public void createMessage(String user, String title, String artist, String receiver) {
		refreshList();

		int RegularID1 = 0;
		int RegularID2 = 0;

		int FriendsID = 0;

		int SongID = 0;

		for (Regular aux : regularList) {
			if (aux.getUsernameReg().equals(user)) {
				RegularID1 = aux.getRegularID();
				break;
			}
		}

		for (Regular aux : regularList) {
			if (aux.getUsernameReg().equals(receiver)) {
				RegularID2 = aux.getRegularID();
				break;
			}
		}

		for (Friends aux : friendList) {
			if ((aux.getRegularID1() == RegularID1 && aux.getRegularID2() == RegularID2)
					|| (aux.getRegularID1() == RegularID2 && aux.getRegularID2() == RegularID1)) {
				FriendsID = aux.getFriendsID();
				break;
			}
		}

		for (Friends aux : friendList) {
			if ((aux.getRegularID1() == RegularID1 && aux.getRegularID2() == RegularID2)
					|| (aux.getRegularID1() == RegularID2 && aux.getRegularID2() == RegularID1)) {
				FriendsID = aux.getFriendsID();
				break;
			}
		}

		for (Song aux : songList) {
			if (aux.getSongArtist().equals(artist) && aux.getSongName().equals(title)) {
				SongID = aux.getSongID();
				break;
			}
		}

		mm.addMessage(FriendsID, SongID);

	}

	public void deleteMessage(String user, String title, String artist, String receiver) {
		refreshList();

		int RegularID1 = 0;
		int RegularID2 = 0;

		int FriendsID = 0;

		int SongID = 0;

		int MessageID = 0;

		for (Regular aux : regularList) {
			if (aux.getUsernameReg().equals(user)) {
				RegularID1 = aux.getRegularID();
				break;
			}
		}

		for (Regular aux : regularList) {
			if (aux.getUsernameReg().equals(receiver)) {
				RegularID2 = aux.getRegularID();
				break;
			}
		}

		for (Friends aux : friendList) {
			if ((aux.getRegularID1() == RegularID1 && aux.getRegularID2() == RegularID2)
					|| (aux.getRegularID1() == RegularID2 && aux.getRegularID2() == RegularID1)) {
				FriendsID = aux.getFriendsID();
				break;
			}
		}

		for (Friends aux : friendList) {
			if ((aux.getRegularID1() == RegularID1 && aux.getRegularID2() == RegularID2)
					|| (aux.getRegularID1() == RegularID2 && aux.getRegularID2() == RegularID1)) {
				FriendsID = aux.getFriendsID();
				break;
			}
		}

		for (Song aux : songList) {
			if (aux.getSongArtist().equals(artist) && aux.getSongName().equals(title)) {
				SongID = aux.getSongID();
				break;
			}
		}

		for (Message aux : messageList) {
			if (aux.getFriendsID() == FriendsID && aux.getSongID() == SongID) {
				MessageID = aux.getMessageID();
				break;
			}
		}

		mm.deleteMessage(MessageID);

	}

	public String[] getMessages(String user) {
		refreshList();

		int RegularID1 = 0;

		ArrayList<Integer> matchedFriends = new ArrayList<Integer>();

		for (Regular aux : regularList) {
			if (aux.getUsernameReg().equals(user)) {
				RegularID1 = aux.getRegularID();
				break;
			}
		}

		for (Friends aux : friendList) {
			if (aux.getRegularID1() == RegularID1 || aux.getRegularID2() == RegularID1) {
				matchedFriends.add(aux.getFriendsID());
			}
		}

		String[] toRet = new String[songList.size()];

		int i = 0;

		for (Message aux : messageList) {

			for (Integer aux2 : matchedFriends) {
				if (aux.getFriendsID() == aux2) {
					for (Friends aux3 : friendList) {
						if (aux3.getFriendsID() == aux2) {
							String save = null;

							for (Song aux4 : songList) {
								if (aux4.getSongID() == aux.getSongID()) {

									String save1 = null;
									String save2 = null;

									for (Regular auxi : regularList) {
										if (aux3.getRegularID1() == auxi.getRegularID()) {
											save1 = auxi.getUsernameReg();
										}
										if (aux3.getRegularID2() == auxi.getRegularID()) {
											save2 = auxi.getUsernameReg();
										}
									}

									save = save1 + ":" + aux4.getSongArtist() + "-" + aux4.getSongName() + ":" + save2;
								}
							}

							toRet[i] = save;

							i++;
						}
					}
				}
			}

		}

		return toRet;
	}

}
