/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package echoclientandserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Miquel
 */
public class EchoServerImplementation {
    
    public static void main(String[] args) throws IOException {
        System.out.println("Server Side");
        System.out.println("Basic Echo Program");
        ServerSocket listenSocket = new ServerSocket(8888);
        Socket clientSocket = listenSocket.accept();
        BufferedReader bfr = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(),true);
        String receivedText;
        boolean end = false;
        System.out.println("Connection Established With The Client");
        while (end == false && (receivedText = bfr.readLine()) != null) {
            System.out.println(receivedText);
            pw.println(receivedText);
            if (receivedText.equals("bye")) end = true;
        }
        pw.close();
        bfr.close();
        clientSocket.close();
        listenSocket.close();
    }
}
