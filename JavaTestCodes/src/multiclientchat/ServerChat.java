/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multiclientchat;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Miquel
 */
public class ServerChat {
    
    private ConcurrentHashMap<String,ThreadedServerPetition> clientList;
    ServerSocket listenSocket; 
    
    public void ServerChat() {
        
    }
    
    public void initialize() throws IOException {
        clientList = new ConcurrentHashMap<String,ThreadedServerPetition> ();
        listenSocket = new ServerSocket(8888);      
        boolean listening = true;
        while(listening) {
            ThreadedServerPetition tsp = new ThreadedServerPetition(listenSocket.accept(), clientList);
            tsp.start();
        }
        listenSocket.close();
    }
    
    public static void main(String[] args) throws IOException {
        System.out.println("Server Side");
        System.out.println("Basic Chat Program");
        new ServerChat().initialize();
    }

}
