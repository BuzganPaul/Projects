package Server.BussinessLayer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class NotificationMaster extends Observer{
	
	public static ArrayList<OnlineClient>  clients= new  ArrayList<OnlineClient>();
	
	ObjectOutputStream outputNotification;
	
	   public NotificationMaster(FriendsManager subject){
		      this.subject = subject;
		      this.subject.attach(this);
		   }
	
	
	public static void printClients()
	{
		for(OnlineClient aux : clients)
		{
			System.out.println("Client: " + aux.getClient() + "   cu socketul: " + aux.getClientSocket().getInetAddress().toString());
		}
	}
	
	public void setOutput(ObjectOutputStream o)
	{
		this.outputNotification = o;
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		
		for(OnlineClient aux : clients)
		{
			if(aux.getClient().equals(subject.getState()))
			{
				//aux.getClientSocket().
				try {
					outputNotification.writeObject("Notification");
					outputNotification.writeObject(aux.getClient());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		

		
	}

}
