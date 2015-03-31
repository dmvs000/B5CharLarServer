/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greetingserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author SHANKAR
 */
public class SendOutBoundMessage implements Runnable {
    Socket sc;
    String Msg;
    public SendOutBoundMessage(Socket sc,String Msg)
    {
        this.sc=sc;
        this.Msg=Msg;
    }
    public void run()
    {
        try
        {
        String ServerSays;
        String ClientSays;
        System.out.println("New Thread Started for handling OutBoundMessages");
        OutputStream outMsg = sc.getOutputStream();
        DataOutputStream out1 = new DataOutputStream(outMsg);
        out1.writeUTF("ReceiveMsg");
        out1.writeUTF(Msg);
        /*System.out.println("Msg Notification Sent. Waiting for client to respond");
        InputStream inFrom = sc.getInputStream();
        DataInputStream in1 = new DataInputStream(inFrom);
        ClientSays=in1.readUTF();
        //out1.writeUTF(Msg);
        if(ClientSays.equals("ReceiveMsg-Ack"))
        {
           out1.writeUTF(Msg);
        }
        ClientSays=in1.readUTF();
        if(ClientSays.equals("Received"))
        {
            System.out.println("Successfully Sent.");
        }*/
        }
        catch(Exception e)
        {
            System.out.println("Exception at SendOutBoundMessage server");
            System.out.println(e);
        }
        System.out.println("SendOutBoundMessages Closed");
    }
    
}
