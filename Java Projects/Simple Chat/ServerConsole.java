// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com

import java.io.*;
import client.*;
import common.*;

/**
 * This class constructs the UI for a chat client.  It implements the
 * chat interface in order to activate the display() method.
 * Warning: Some of the code here is cloned in ServerConsole
 *
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @version July 2000
 */
public class ServerConsole  implements ChatIF
{
    //Class variables *************************************************


    final public static int DEFAULT_PORT = 5555;

    //Instance variables **********************************************


    private static EchoServer server;


    //Constructors ****************************************************


    public ServerConsole(int port)
    {
        server=new EchoServer(port);
    }



    public void display(String message)
    {
        System.out.println(message);
    }

    @Override
    public void connectionClosed() {
        System.out.println("Server closed");
    }

    //Class methods ***************************************************

    public void accept()
    {
        try
        {
            BufferedReader fromConsole =
                    new BufferedReader(new InputStreamReader(System.in));
            String message;

            while (true)
            {
                message = fromConsole.readLine();
                server.handleMessageFromServerConsole(message);
            }
        }
        catch (Exception ex)
        {
            System.out.println("Unexpected error while reading from console!");
        }
    }




    public static void main(String[] args)
    {
        int port = 0; //Port to listen on

        try
        {
            port = Integer.parseInt(args[0]); //Get port from command line
        }
        catch(Throwable t)
        {
            port = DEFAULT_PORT; //Set port to 5555
        }

        ServerConsole sc = new ServerConsole(port);

        try
        {
            server.listen();
        }
        catch (Exception ex)
        {
            System.out.println("ERROR - Could not listen for clients!");
        }
        sc.accept();
    }
}