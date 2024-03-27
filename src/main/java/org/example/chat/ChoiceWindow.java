package org.example.chat;

import javax.swing.*;

public class ChoiceWindow extends JFrame {
    private static  final int WINDOW_HEIGHT = 75;
    private static  final int WINDOW_WIDTH = 275;
    private static  final int WINDOW_POSX = 600;
    private static  final int WINDOW_POSY = 200;
    private JButton runServer = new JButton("Run Server");
    private JButton runClient = new JButton("Run Client");
    JPanel mainPanel = new JPanel();

    public ChoiceWindow() {
        setTitle("Start Chat");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);

        runClient.addActionListener(e -> {
            new ChatWindow();
            dispose();
        });

        runServer.addActionListener(e -> {
            new ServerWindow();
            dispose();
        });

        mainPanel.add(runServer);
        mainPanel.add(runClient);
        add(mainPanel);
    }
}
