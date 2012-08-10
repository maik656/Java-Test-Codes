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
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Miquel
 */
public class ThreadedServerPetition extends Thread {
   
    BufferedReader bfr;
    PrintWriter pw;
    Socket clientSocket;
    String whoWantsToConnect;
    String clientNick;
    ConcurrentHashMap<String,ThreadedServerPetition> clientList;
    
    public ThreadedServerPetition(Socket recvSock, ConcurrentHashMap<String,ThreadedServerPetition> listRecv) throws IOException {
        clientSocket = recvSock;
        bfr = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        pw = new PrintWriter(clientSocket.getOutputStream(), true);
        whoWantsToConnect = new String();
        clientNick = new String();
        clientList = listRecv;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("Connection made With Client");
            pw.println("Connection made With The Server, Please Enter Your Nick");
            clientNick = bfr.readLine();
            whoWantsToConnect = bfr.readLine(); //reads the ip that this client wants to connect
            System.out.println(clientNick + " Entered to the server");
            System.out.println(clientNick + " Wants to talk with " + whoWantsToConnect);
            clientList.put(clientNick,this);
            boolean waitingConnection = true;
            while (clientList.containsKey(whoWantsToConnect) == false) ;
            pw.println("Connection Established Correctly with " + whoWantsToConnect + ". You Can Write Messages Now");
            String receivedText;
            while ((receivedText = bfr.readLine()) != null) clientList.get(whoWantsToConnect).writeTo(receivedText);
            bfr.close();
            pw.close();
            clientSocket.close();
            
        } catch (IOException ex) {
            System.out.println("Socket io error");
        }
    }
    
    public String getClientNick() {
        return clientNick;
    }
    
    public String getWhoWantsToConnect() {
        return whoWantsToConnect;
    }
    
    public void writeTo(String textToWrite) {
        pw.println(textToWrite);
    }
    
}
