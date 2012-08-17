/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multiclientchat;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Miquel
 */
public class ConnectionDialog extends JDialog{
    
    
    private JPanel mainConnectionPanel;
    private JPanel buttonConnectionPanel;
    private JLabel connectionLabel;
    
    private JSeparator titleSeparator;
    
    private JButton acceptButton;
    private JButton cancelButton;
    
    private Socket clientSocket;
    
    private JTextField campoServer;
    private JTextField campoNick;
    
    public ConnectionDialog(JFrame pf, Socket clSock) {
        super(pf,true); //set parent and set modal to true
        clientSocket = clSock;
        this.setTitle("Connection Configuration");
        mainConnectionPanel = new JPanel();
        mainConnectionPanel.setBorder(new EmptyBorder(10,10,10,10));
        mainConnectionPanel.setLayout(new BoxLayout(mainConnectionPanel,BoxLayout.Y_AXIS));
        this.setContentPane(mainConnectionPanel);
        connectionLabel = new JLabel("Connection Settings:");
        mainConnectionPanel.add(connectionLabel);
        mainConnectionPanel.add(Box.createRigidArea(new Dimension(0,5)));
        
        titleSeparator = new JSeparator(SwingConstants.HORIZONTAL);
        titleSeparator.setMaximumSize(new Dimension(titleSeparator.getMaximumSize().width,titleSeparator.getPreferredSize().height));
        System.out.println(titleSeparator.getPreferredSize().height);
        mainConnectionPanel.add(titleSeparator);
        mainConnectionPanel.add(Box.createRigidArea(new Dimension(0,5)));
        mainConnectionPanel.add(new JLabel("Server IP Address:"));
        mainConnectionPanel.add(Box.createRigidArea(new Dimension(0,5)));
        campoServer = new JTextField();
        campoServer.setAlignmentX(LEFT_ALIGNMENT);
        mainConnectionPanel.add(campoServer);
        campoServer.setMaximumSize(new Dimension(campoServer.getMaximumSize().width,campoServer.getPreferredSize().height));
        mainConnectionPanel.add(Box.createRigidArea(new Dimension(0,5)));
        mainConnectionPanel.add(new JLabel("Nick:"));
        mainConnectionPanel.add(Box.createRigidArea(new Dimension(0,5)));
        campoNick = new JTextField();
        campoNick.setAlignmentX(LEFT_ALIGNMENT);
        campoNick.setMaximumSize(new Dimension(campoServer.getMaximumSize().width,campoServer.getPreferredSize().height));
        mainConnectionPanel.add(campoNick);
        mainConnectionPanel.add(Box.createRigidArea(new Dimension(0,5)));
        mainConnectionPanel.add(Box.createVerticalGlue());
        buttonConnectionPanel = new JPanel();
        acceptButton = new JButton("Accept");
        cancelButton = new JButton("Cancel");
        addButtonListeners();
        buttonConnectionPanel.add(acceptButton);
        buttonConnectionPanel.add(cancelButton);
        buttonConnectionPanel.setAlignmentX(LEFT_ALIGNMENT);
        buttonConnectionPanel.setMaximumSize(new Dimension(buttonConnectionPanel.getMaximumSize().width,buttonConnectionPanel.getPreferredSize().height));
        mainConnectionPanel.add(buttonConnectionPanel);              
        this.pack();
        this.setMinimumSize(this.getSize());       
    }
    
    private void addButtonListeners() {
        acceptButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean socketWellConfigured = false;
                try {
                    if (clientSocket != null && clientSocket.isClosed() == false) clientSocket.close();
                    if (campoServer.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(new JFrame(),
                                    "Incorrect Server IP Address.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        clientSocket = new Socket(campoServer.getText(),8888);
                        socketWellConfigured = true;
                    }
                } catch (UnknownHostException ex) {
                    JOptionPane.showMessageDialog(new JFrame(),
                                    "Incorrect Server IP Address.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    Logger.getLogger(ConnectionDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (socketWellConfigured) {
                    if (campoNick.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(new JFrame(),
                                    "Empty Nick. Please Introduce a Nick.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);                        
                    }
                }
            }
            
        });

        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
            
        });
    }
    
}
