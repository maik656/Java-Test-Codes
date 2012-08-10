/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multiclientchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Miquel
 */
public class ThreadedServerPetition extends Thread {
   
    BufferedReader bfr;
    PrintWriter pw;
    Socket clientSocket;
    
    public ThreadedServerPetition(Socket recvSock) throws IOException {
        bfr = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        pw = new PrintWriter(clientSocket.getOutputStream());
        clientSocket = recvSock;
    }
    
    @Override
    public void run() {
        
    }
}
