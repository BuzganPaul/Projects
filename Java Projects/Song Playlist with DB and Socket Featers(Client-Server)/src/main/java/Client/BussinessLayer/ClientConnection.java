package Client.BussinessLayer;

import java.io.*;
import java.net.Socket;
import java.time.Instant;

import Client.UILayer.LoginPage;


public class ClientConnection extends Thread
    {
        private final Socket socket;
        private final ObjectOutputStream output;
        private final ObjectInputStream input;
        
        
        public ClientConnection(Socket socket) throws IOException
        {
			this.socket = socket;
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            
        }
        
        
        
        public Socket getSocket()
        {
        	return socket;
        }
        
        public ObjectOutputStream getOutput()
        {
        	return output;
        }
        
        public ObjectInputStream getInput()
        {
        	return input;
        }

        @Override
        public void run()
        {
            try
            {
                while (socket.isConnected())
                {
                    // Seems that input.available() is not reliable?
                    boolean serverHasData = socket.getInputStream().available() > 0;
                    if (serverHasData) {
                        String msg = (String) input.readObject();
                        //System.out.println(Instant.now() + " Got from server: " + msg);
                        
                        if(msg.equals("Notification"))
                        {
                        	
                        String msg2 = (String) input.readObject();
                        Notification window = new Notification(msg2);
                        window.frmNotification.setVisible(true);
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
                System.out.println(Instant.now() + " Server disconnected");
            }
            catch (IOException | ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }

        public void sendMessageToServer(String message) throws IOException
        {
            output.writeObject(message);
        }
    

    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        ClientConnection connection = new ClientConnection(new Socket("localhost", 3000));
        connection.start();

        //BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("OPEN CLIENT");
        
        
//        while (true)
//        {
//            System.out.println(Instant.now() + " Type the message to send to the server and press enter:");
//
//            String message = consoleInput.readLine();
//            connection.sendMessageToServer(message);
//        }
        
		LoginPage window = new LoginPage();
		window.frmLoginWindow.setVisible(true);
        
        
    }
}
