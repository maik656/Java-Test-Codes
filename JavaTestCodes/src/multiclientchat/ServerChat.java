/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multiclientchat;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author Miquel
 */
public class ServerChat {
    
    public static void main(String[] args) throws IOException {
        ServerSocket listenSocket = new ServerSocket(8888);
        
        boolean listening = true;
        while(listening) {
            new ThreadedServerPetition(listenSocket.accept()).start();
        }
        listenSocket.close();
    }

}
