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
/**
 *
 * @author Miquel
 */
public class ThreadedServerPetition extends Thread {
   
    BufferedReader bfr;
    PrintWriter pw;
    Socket clientSocket;
    String whoWantsToConnect;
    int indexConnectedClient;
    ArrayList<ThreadedServerPetition> clientList;
    
    public ThreadedServerPetition(Socket recvSock, ArrayList<ThreadedServerPetition> listRecv) throws IOException {
        clientSocket = recvSock;
        bfr = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        pw = new PrintWriter(clientSocket.getOutputStream(), true);
        whoWantsToConnect = null;
        clientList = listRecv;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("Connection made With Client");
            pw.println("Connection made With The Server, Please introduce the ip of the Client that you want to talk");
            whoWantsToConnect = bfr.readLine(); //reads the ip that this client wants to connect
            System.out.println("Received who wants to connect: to " + whoWantsToConnect);
            /*if (whoWantsToConnect == null) System.out.println("Esta vacio");
            if (whoWantsToConnect.equals(null)) System.out.println("Esta vacio null");*/
            boolean waitingConnection = true;
            while (waitingConnection) {
                for (int i = 0; waitingConnection == true && i < clientList.size(); ++i) {
                    if (getClientIp().equals(clientList.get(i).getWhoWantsToConnect()) && this.equals(clientList.get(i)) == false) {
                        System.out.println("acierto");
                        indexConnectedClient = i;
                        waitingConnection = false;
                    }
                }
            }
            System.out.println("Ha salido del bucle");
            pw.println("Connection Established Correctly with " + whoWantsToConnect + ". You Can Write Messages Now");
            String receivedText;
            while ((receivedText = bfr.readLine()) != null) {
                clientList.get(indexConnectedClient).writeTo(receivedText);
            }
            bfr.close();
            pw.close();
            clientSocket.close();
            
        } catch (IOException ex) {
            System.out.println("Socket io error");
        }
    }
    
    public String getClientIp() {
        return clientSocket.getInetAddress().getHostAddress();
    }
    
    public String getWhoWantsToConnect() {
        return whoWantsToConnect;
    }
    
    public void writeTo(String textToWrite) {
        pw.println(textToWrite);
    }
    
}
