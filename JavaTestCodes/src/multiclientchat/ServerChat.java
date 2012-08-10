/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multiclientchat;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Miquel
 */
public class ServerChat {
    
    ConcurrentHashMap<String,ThreadedServerPetition> clientList;
    
    public void begin() throws IOException {
        clientList = new ConcurrentHashMap<String,ThreadedServerPetition> ();
        ServerSocket listenSocket = new ServerSocket(8888);
        
        System.out.println("Server Side");
        System.out.println("Basic Chat Program");
        boolean listening = true;
        while(listening) {
            ThreadedServerPetition tsp = new ThreadedServerPetition(listenSocket.accept(), clientList);
            tsp.start();
        }
        listenSocket.close();
    }
    
    public static void main(String[] args) throws IOException {
        new ServerChat().begin();
    }

}
