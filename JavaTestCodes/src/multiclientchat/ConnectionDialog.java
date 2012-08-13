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
    
    public ConnectionDialog(JFrame pf) {
        super(pf);
        this.setTitle("Connection Configuration");
        JPanel p = new JPanel();
        p.setBorder(new EmptyBorder(10,10,10,10));
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        JLabel l = new JLabel("Hola com estem");
        l.setFont(new Font(l.getFont().getFamily(),Font.BOLD,l.getFont().getSize()));
        p.add(l);
        p.add(Box.createRigidArea(new Dimension(0,5)));
        //JTextField campoServer = new JTextField();
        //p.add(campoServer);
        this.add(p);
        this.setModal(true);
        this.pack();
        
    }
}
