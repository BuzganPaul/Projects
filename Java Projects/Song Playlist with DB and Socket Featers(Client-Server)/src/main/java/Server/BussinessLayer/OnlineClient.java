package Server.BussinessLayer;

import java.net.Socket;

public class OnlineClient{
	
	private Socket clientSocket;
	private String client;
	
	
	public OnlineClient(Socket clientSocket, String client) {
		super();
		this.clientSocket = clientSocket;
		this.client = client;
	}


	public Socket getClientSocket() {
		return clientSocket;
	}


	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}


	public String getClient() {
		return client;
	}


	public void setClient(String client) {
		this.client = client;
	}
	
	
	
		
	}
