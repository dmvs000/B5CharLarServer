/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greetingserver;

/**
 *
 * @author SHANKAR
 */
import java.net.Socket;
import java.util.*;

public class HashMapClients {

    // Create a hash map
    public HashMap hm=new HashMap();;
    public void HashMapClients()
    {
      hm = new HashMap();
    }
   public void MapTheClient(String username, Socket socket) {
   
      
      // Put elements to the map
      //hm.put("Zara", new Socket());
      hm.put(username, socket);
   }
      public void iterate(String givenname)
      {
      // Get a set of the entries
      Set set = hm.entrySet();
      // Get an iterator
      Iterator i = set.iterator();
      // Display elements
      while(i.hasNext()) {
         Map.Entry me = (Map.Entry)i.next();
         System.out.print(me.getKey() + ": ");
         System.out.println(me.getValue());
      }
      System.out.println();
      // Deposit 1000 into Zara's account
      return;
   }
      public Socket getSocket(String username)
      {
          Socket sc;
          sc=(Socket)hm.get(username);
          System.out.println("Socket for Connecting to " +username+ " : "+sc);
          return sc;
          
      }
      public void RemoveNow(String username)
      {
          hm.remove(username);
          System.out.println("Successfully removed");
          iterate(username);
      }
}