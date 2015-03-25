/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greetingserver;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author SHANKAR
 */
public class ClientSocketConnections {
    String username;
    String SessionId;
    Socket clientsocket;
    ClientSocketConnections(String username, String SessionId, Socket socket)
    {
        this.username=username;
        this.SessionId=SessionId;
        this.clientsocket=clientsocket;
    }
}
