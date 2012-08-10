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
import java.util.concurrent.ConcurrentHashMap;
/**
 *
 * @author Miquel
 */
public class ThreadedServerPetition extends Thread {
   
    private BufferedReader bfr;
    private PrintWriter pw;
    private Socket clientSocket;
    private String whoWantsToConnect;
    private String clientNick;
    private ConcurrentHashMap<String,ThreadedServerPetition> clientList;
    
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
            initializeConnectionPetition();
            while (clientList.containsKey(whoWantsToConnect) == false) ;           
            handleWritings();
            finalizeConnectionPetition();
            
        } catch (IOException ex) {
            System.out.println("Socket io error");
        }
    }
    
    private void initializeConnectionPetition() throws IOException {
            System.out.println("Connection made With Client");
            pw.println("Connection made With The Server, Please Enter Your Nick");
            clientNick = bfr.readLine();
            whoWantsToConnect = bfr.readLine(); //reads the ip that this client wants to connect
            System.out.println(clientNick + " Entered to the server");
            System.out.println(clientNick + " Wants to talk with " + whoWantsToConnect);
            clientList.put(clientNick,this);        
    }
    
    private void finalizeConnectionPetition() throws IOException {
            bfr.close();
            pw.close();
            clientSocket.close();        
    }
    
    private void handleWritings() throws IOException {
            pw.println("Connection Established Correctly with " + whoWantsToConnect + ". You Can Write Messages Now");
            String receivedText;
            while ((receivedText = bfr.readLine()) != null) clientList.get(whoWantsToConnect).writeTo(receivedText);        
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
