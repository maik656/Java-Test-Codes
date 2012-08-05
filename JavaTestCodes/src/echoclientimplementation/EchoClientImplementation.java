package echoclientimplementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author Miquel
 * Program that implements a client that connects to Echo server.
 * Created to learn how a program can establish a connection to a server program using a Socket and how to send and receive data from the server through the Socket.
 * You have to enable simple TCP/IP services in Windows to run correctly this program.
 */
public class EchoClientImplementation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Socket clientSocket; //Create a client socket using Socket class
        InputStreamReader isr;
        BufferedReader bfr;
        PrintWriter pw;       
        clientSocket = new Socket(InetAddress.getLocalHost().getHostName(),7); //Initialize client socket. We want to connect to localhost at port 7
        isr = new InputStreamReader(clientSocket.getInputStream()); //get inputStream from the client socket and use it in an inputStreamReader
        bfr = new BufferedReader(isr); //use the previous inputStreamReader in a BufferedReader
        pw = new PrintWriter(clientSocket.getOutputStream(),true); //get outputStream from the client socket and use it in a print writer
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        System.out.println("Basic Echo Program, Please Write Characters And Press Enter To Echo:");
        while ((userInput = stdIn.readLine()) != null) {
            pw.println(userInput);
            System.out.println("echo: " + bfr.readLine());
        }
        pw.close();
        bfr.close();
        stdIn.close();
        clientSocket.close();      
    }
}