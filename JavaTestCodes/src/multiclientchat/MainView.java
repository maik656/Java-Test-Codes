/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multiclientchat;

import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author Miquel
 */
public class MainView {
    
    private JFrame initialView;
    
    private JMenuBar mainMenu;
    
    public MainView() {
        initializeComponents();
    }
    
    private void initializeComponents() {
        initializeInitialFrame();
        initializeMainMenu();
        
        ConnectionDialog cd = new ConnectionDialog(initialView);
        initialView.pack();
        initialView.setVisible(true);
        cd.setVisible(true);
    }
    
    private void initializeInitialFrame() {
        initialView = new JFrame("Simple Chat");
        initialView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension d = new Dimension(800,600);
        initialView.setPreferredSize(d);
    }
    
    private void initializeMainMenu() {
        mainMenu = new JMenuBar();
        initialView.setJMenuBar(mainMenu);
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitOption = new JMenuItem("Exit");
        
        fileMenu.add(exitOption);
        mainMenu.add(fileMenu);
    }
}
