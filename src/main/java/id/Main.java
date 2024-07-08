package id;

import javax.swing.*;
import java.awt.event.*;

public class Main extends JFrame {
    static JMenuBar menuBar;
    static JMenu algorithMenu, helpMenu;
    static JMenuItem algorithm2, help1;

    Main() {
        // Initialize components
        menuBar = new JMenuBar();
        algorithMenu = new JMenu("Menu");
        helpMenu = new JMenu("Help");
        algorithm2 = new JMenuItem("Sorting Techniques");
        help1 = new JMenuItem("How it works");

        // Add menu items to menus
        algorithMenu.add(algorithm2);
        helpMenu.add(help1);

        // Add menus to menu bar
        menuBar.add(algorithMenu);
        menuBar.add(helpMenu);

        // Create listener and add it to menu items
        ListenerClass listener = new ListenerClass();
        algorithm2.addActionListener(listener);
        help1.addActionListener(listener);

        // Set the menu bar for the frame
        setJMenuBar(menuBar);

        // Set frame properties
        setTitle("Project 2024 by Tokhir Rasulov");
        setSize(700, 500);
        setLocation(200, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    class ListenerClass implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Log the action event source
            System.out.println("Action event source: " + e.getSource());

            // Here you can implement additional actions when a menu item is clicked
            setVisible(false);
        }
    }}
