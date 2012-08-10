/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multiclientchat;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author Miquel
 */
public class ThreadedClientListening extends Thread {
    
    BufferedReader bfr;
    String receivedText;
    String friendNick;
    
    public ThreadedClientListening(BufferedReader parambfr) {
        bfr = parambfr;
        friendNick = "The Server";
                
    }
    
    @Override
    public void run() {
        try {
            while ((receivedText = bfr.readLine()) != null) {
                System.out.println("Message Received from " + friendNick + ": " + receivedText);
            }
        }
        catch (IOException ex) {
            System.out.println("io error");
        }
    }
    
    public void setFriendNick(String f) {
        friendNick = f;
    }
}
