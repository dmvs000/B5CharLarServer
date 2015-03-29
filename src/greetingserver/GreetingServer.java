// File Name GreetingServer.java
// Server's Main Routine.
package greetingserver;
import java.net.*;
import java.io.*;
import java.util.*;

public class GreetingServer
{
   
   private ServerSocket serverSocket;
   private String username;
   public HashMapClients hmc=new HashMapClients();
   private ArrayList<ClientSocketConnections> connections=new ArrayList<ClientSocketConnections>();
   public GreetingServer(int port) throws IOException
   {
       
	  try
         {
		 serverSocket = new ServerSocket(port);
		 System.out.println("No Listening Time Out Has Beend Specified.");
                 System.out.println(" Press Ctrl+C to exit the server.");
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
                MySQLAccessUserAuth userauth=new MySQLAccessUserAuth();
                
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
                switch(clientsays)
                {
                    case "authenticate":
                            boolean validity;
                            AuthenticateClient ac=new AuthenticateClient();
                            System.out.println("Requesting Client for Credentials..");
                            out.writeUTF("Credentials - 063");
                            SessionIdentifierGenerator sig=new SessionIdentifierGenerator();
                            String Id=sig.nextSessionId();
                            out.writeUTF(Id);
                            clientsays=in.readUTF();
                            System.out.println("client credentials");
                            System.out.println(clientsays);
                            System.out.println("Authenticating user");
                            JAXBUserAuthUnmarshall juau=new JAXBUserAuthUnmarshall();
                            validity=juau.UnMarshall(clientsays,Id);
                            if(validity)
                            {
                                out.writeUTF("AuthSuccess");
                                String Ip = threadSocket.getRemoteSocketAddress().toString();
                                //storing client details for this session
                                System.out.println(Ip);
                                juau.SessionDetails(Ip);
                                username=juau.GetData();
                                //On Successful Authentication. Storing the sockets in ArrayList.
                                ClientSocketConnections csc=new ClientSocketConnections(username,Id,threadSocket);
                                connections.add(csc);
                                hmc.MapTheClient(username, threadSocket);
                                hmc.iterate(username);
                                System.out.println("Auth Success, Beeak Switch");
                            }
                            else
                            {
                                out.writeUTF("FA");
                            }
                            break;
                    case "presence":
                            
                            break;
                    case "register":
                            System.out.println("In Register case");
                            boolean Valid=false;
                            out.writeUTF("reg-Ack");
                            clientsays=in.readUTF();
                            System.out.println("Parameters Received from Client");
                            System.out.println(clientsays);
                            JAXBUserRegisterUnmarshall juru=new JAXBUserRegisterUnmarshall();
                            if(juru.UnMarshall(clientsays))
                            {
                                System.out.println("User Registration Successful");
                                out.writeUTF("Success");
                            }
                            else
                            {
                                System.out.println("User Registration Failed");
                                out.writeUTF("Failed");
                            }
                            break;
                    case "OutMessage":
                            System.out.println("In OutMessage Case");
                            out.writeUTF("MsgSend-Ack");
                            JAXBUnmarshall jum=new JAXBUnmarshall();
                            String Msg=in.readUTF();
                            jum.UnMarshall(Msg);
                            String utosend;
                            utosend=jum.getTo();
                            System.out.println("Message to Be Sent" +utosend);
                            HashMapClients hmc=new HashMapClients();
                            Socket tosendsocket=hmc.getSocket(utosend);
                            OutputStream outMsg = tosendsocket.getOutputStream();
                            DataOutputStream out1 = new DataOutputStream(outMsg);
                            out1.writeUTF("ReceiveMsg");
                            System.out.println("Msg Notification Sent. Waiting for server to respond");
                            InputStream inFrom = tosendsocket.getInputStream();
                            DataInputStream in1 = new DataInputStream(inFrom);
                            if(in1.equals("ReceiveMsg-Ack"))
                            {
                                out1.writeUTF(Msg);
                    
                                //JAXBUnmarshall ju=new JAXBUnmarshall();
                                //ju.UnMarshall(in1);
                            }
                            //InputStream inFromClient = threadSocket.getInputStream();
                            
                            break;
                    case "requestRoster":
                            break;
                    case "LogOff":
                            break;
                }
                //System.out.println("UnMarshalling");
                //jaxbun.UnMarshall(clientsays);
                //userauth.ConnectCheck("abc","abc");
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
