/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multiclientchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miquel
 */
public class ThreadedClientListening extends Thread {
    
    BufferedReader bfr;
    String receivedText;
    
    public ThreadedClientListening(BufferedReader parambfr) {
        bfr = parambfr;
    }
    
    @Override
    public void run() {
        try {
            while ((receivedText = bfr.readLine()) != null) {
                System.out.println("Message Received: " + receivedText);
            }
        }
        catch (IOException ex) {
            System.out.println("io error");
        }
    }
}
