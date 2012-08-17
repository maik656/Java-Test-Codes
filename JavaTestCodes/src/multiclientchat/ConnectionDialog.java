/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multiclientchat;

import java.awt.Dimension;
import java.awt.Font;
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
    
    public ConnectionDialog(JFrame pf) {
        super(pf,true); //set parent and set modal to true
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
        JTextField campoServer = new JTextField();
        campoServer.setAlignmentX(LEFT_ALIGNMENT);
        mainConnectionPanel.add(campoServer);
        campoServer.setMaximumSize(new Dimension(campoServer.getMaximumSize().width,campoServer.getPreferredSize().height));
        mainConnectionPanel.add(Box.createRigidArea(new Dimension(0,5)));
        mainConnectionPanel.add(new JLabel("Nick:"));
        mainConnectionPanel.add(Box.createRigidArea(new Dimension(0,5)));
        JTextField campoNick = new JTextField();
        campoNick.setAlignmentX(LEFT_ALIGNMENT);
        campoNick.setMaximumSize(new Dimension(campoServer.getMaximumSize().width,campoServer.getPreferredSize().height));
        mainConnectionPanel.add(campoNick);
        mainConnectionPanel.add(Box.createVerticalGlue());
        buttonConnectionPanel = new JPanel();
        JButton acceptButton = new JButton("Accept");
        JButton cancelButton = new JButton("Cancel");
        buttonConnectionPanel.add(acceptButton);
        buttonConnectionPanel.add(cancelButton);
        buttonConnectionPanel.setAlignmentX(LEFT_ALIGNMENT);
        buttonConnectionPanel.setMaximumSize(new Dimension(buttonConnectionPanel.getMaximumSize().width,buttonConnectionPanel.getPreferredSize().height));
        mainConnectionPanel.add(buttonConnectionPanel);
        
        //p.add(campoServer);
        this.pack();
        
    }
}
