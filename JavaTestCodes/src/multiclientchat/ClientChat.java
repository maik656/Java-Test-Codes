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
public class ClientChat {
    
    public static void main(String[] args) throws IOException {
        Socket clientSocket;
    
        BufferedReader bfr;
        PrintWriter pw;
        ThreadedClientListening tcl;
        
        System.out.println("Client Side");
        System.out.println("Basic Chat Program");
    
        BufferedReader inputKeyboard = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please introduce the ip of the Chat Server");
        String ipServer = inputKeyboard.readLine();
        
        clientSocket = new Socket(ipServer, 8888);
        
        bfr = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        tcl = new ThreadedClientListening(bfr);
        tcl.start();
        
        pw = new PrintWriter(clientSocket.getOutputStream(),true);
                    
        String clientNick = inputKeyboard.readLine();
        pw.println(clientNick);
        
        tcl.setFriendNick(clientNick);
        
        System.out.println("Please enter the nick of the person you want to talk");
        String friendNick = inputKeyboard.readLine();        
        pw.println(friendNick);
        
        
        String textToSend;
        while((textToSend = inputKeyboard.readLine()) != null) {
            pw.println(textToSend);            
        }
        clientSocket.close();
        bfr.close();
        pw.close();
    }
}
