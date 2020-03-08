// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import ocsf.client.*;
import common.*;
import ocsf.server.AbstractServer;

import java.io.*;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class ChatClient extends AbstractClient
{
  //Instance variables **********************************************
  
  /**
   * The interface type variable.  It allows the implementation of 
   * the display method in the client.
   */
  ChatIF clientUI; 

  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the chat client.
   *
   * @param host The server to connect to.
   * @param port The port number to connect on.
   * @param clientUI The interface type variable.
   */
  
  public ChatClient(String host, int port, ChatIF clientUI) 
    throws IOException 
  {
    super(host, port); //Call the superclass constructor
    this.clientUI = clientUI;
    openConnection();
  }

  
  //Instance methods ************************************************
    
  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
   */
  public void handleMessageFromServer(Object msg) 
  {
    clientUI.display(msg.toString());
  }

  /**
   * This method handles all data coming from the UI            
   *
   * @param message The message from the UI.    
   */
  public void handleMessageFromClientUI(String message)
  {
    try
    {
      switch (message){
        case "#quit":
          quit();
          break;
        case "#logoff":
          closeConnection();
          break;
        case "#sethost":
          if(!isConnected()){
            System.out.print("Type in the host: ");
            BufferedReader fromConsole =
                    new BufferedReader(new InputStreamReader(System.in));
            String host;
            host = fromConsole.readLine();
            setHost(host);
          }else{
            System.out.println("Can't set the host, logoff first");
          }
          break;
        case "#setport":
          if(!isConnected()){
            System.out.print("Type in the port: ");
            BufferedReader fromConsole =
                    new BufferedReader(new InputStreamReader(System.in));
            String port;
            port = fromConsole.readLine();
            setPort(Integer.valueOf(port));
          }else{
            System.out.println("Can't set the port, logoff first");
          }
          break;
        case "#login":
          if(!isConnected()) {
            openConnection();
          }else{
            System.out.println("Already connected");
          }
          break;
        case "#gethost":
          System.out.println(getHost());
          break;
        case "#getport":
          System.out.println(getPort());
          break;
        default:
          if(isConnected()) {
            sendToServer(message);
          }else{
            System.out.println("Not connected to the server");
          }
      }
    }

    catch(IOException e)
    {
      clientUI.display
        ("Could not send message to server.  Terminating client.");
      quit();
    }
  }




  @Override
  protected void connectionException(Exception exception) {
    System.out.println("Server closed");
    quit();
  }

  /**
   * This method terminates the client.
   */
  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) {}
    System.exit(0);
  }


}
//End of ChatClient class
