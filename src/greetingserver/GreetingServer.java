// File Name GreetingServer.java
// Server's Main Routine.
package greetingserver;
import java.net.*;
import java.io.*;
import java.util.*;

public class GreetingServer
{
   ReadXMLFile rxf;
   private ServerSocket serverSocket;
   public GreetingServer(int port) throws IOException
   {
	  try
         {
		 serverSocket = new ServerSocket(port);
		 System.out.println("No Listening Time Out Has Beend Specified. Press Ctrl+C to exit the server.");
     System.out.println("");
         System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
			while(true)
			{
				//Waiting for the client to connect
				//Starting a new threrad for every new client connected.
				Socket socket = serverSocket.accept();//accept method for accepting incoming connections.
				ClientThread cT=new ClientThread(socket);
				new Thread(cT).start();
			}
			
         }catch(IOException e)
         {
            e.printStackTrace();
         }
   }

   //Pass a port number to listen upon..at command line.
   //Port Number listening only for testing. Using SRV lookups for AllReals
   public static void main(String [] args)
   {
      //int port = Integer.parseInt(args[0]);
      try
      {
        System.out.println("Server Started.");
         new GreetingServer(5000);
      }
	  catch(IOException e)
      {
         e.printStackTrace();
      }
	  //This function never ends.
   }
   class ClientThread implements Runnable
    {
        Socket threadSocket;
         
        //This constructor will be passed the socket.
        public ClientThread(Socket socket)
        {
			//To be executed first after starting this thread.
            //Here we set the socket to a local variable so we can use it later
            threadSocket = socket;
        }
         
        public void run()
        {
                boolean stopRequested;
                JAXBUnmarshall jaxbun = new JAXBUnmarshall();
                
			//Main executive function of this thread.
            try {
              stopRequested=false;
              OutputStream outToClient = threadSocket.getOutputStream();
              DataOutputStream out = new DataOutputStream(outToClient);
              InputStream inFromClient = threadSocket.getInputStream();
                System.out.println("You have connected at: " + new Date());
                System.out.println("TCP/IP Connection has been established Succesfully."); 
                while (!stopRequested) {
                DataInputStream in =
                        new DataInputStream(inFromClient);
	       String clientsays=in.readUTF();
                System.out.println(clientsays);
                System.out.println("UnMarshalling");
                jaxbun.UnMarshall(clientsays);
        /*if(clientsays.equals("<stream>"))
        {
          System.out.println("Received Stream. The Client is trying to authenticate.");
          out.writeUTF("<stream>");
          System.out.println("<stream> object sent. Waitng for the client to send initiating headers.");
        }
        else
        { 
          stopRequested=true;
          System.out.println("Exiting the Thread.");
        }
        */
        }
            } catch(IOException exception) {
                System.out.println("Error: " + exception);
            }
        }
        
    }
}