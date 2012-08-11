/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multiclientchat;

import javax.swing.*;

/**
 *
 * @author Miquel
 */
public class ConnectionDialog extends JDialog{
    
    public ConnectionDialog(JFrame pf) {
        super(pf);
        this.setTitle("Mi dialogo de conexion");
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        JLabel l = new JLabel("Hola com estem");
        p.add(l);
        this.add(p);
        this.setModal(true);
        this.pack();
        
    }
}
