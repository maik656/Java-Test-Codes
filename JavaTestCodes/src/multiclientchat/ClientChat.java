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
    
    private Socket clientSocket;
    private BufferedReader inputKeyboard;
    private PrintWriter pw;
    private BufferedReader bfr;
    private String ipServer;
    private String clientNick;
    private String friendNick;
    private ThreadedClientListening tcl;
    
    public ClientChat() {
        
    }
    
    private void initialize() throws IOException {
        initializeConnection();
        handleWritings();
        finalizeConnection();        
    }
    
    private void initializeConnection() throws IOException {
        inputKeyboard = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please introduce the ip of the Chat Server");
        ipServer = inputKeyboard.readLine();
        
        clientSocket = new Socket(ipServer, 8888);
        
        bfr = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        tcl = new ThreadedClientListening(bfr);
        tcl.start();
        
        pw = new PrintWriter(clientSocket.getOutputStream(),true);
                    
        clientNick = inputKeyboard.readLine();
        pw.println(clientNick);
        
        
        System.out.println("Please enter the nick of the person you want to talk");
        friendNick = inputKeyboard.readLine();        
        pw.println(friendNick);
        
        tcl.setFriendNick(friendNick);        
    }
    
    private void handleWritings() throws IOException {
        String textToSend;
        while((textToSend = inputKeyboard.readLine()) != null) pw.println(textToSend);       
    }
    
    private void finalizeConnection() throws IOException {
        clientSocket.close();
        bfr.close();
        pw.close();
    }
    
    public static void main(String[] args) throws IOException  {
        System.out.println("Client Side");
        System.out.println("Basic Chat Program");
        new ClientChat().initialize();
    }
}
