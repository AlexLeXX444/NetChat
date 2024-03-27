package org.example.chat;

import javax.swing.*;
import java.awt.*;

public class ServerWindow extends JFrame {
    private static  final int WINDOW_HEIGHT = 100;
    private static  final int WINDOW_WIDTH = 400;
    private static  final int WINDOW_POSX = 500;
    private static  final int WINDOW_POSY = 150;

    private boolean serverRunning = false;
    JButton startServerButton = new JButton("Start Server");
    JButton stopServerButton = new JButton("Stop Server");
    JPanel serverStatusPanel = new JPanel();

    public ServerWindow() {
        setTitle("Chat Server");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        serverStatusPanel.setPreferredSize(new Dimension(100, 50));
        updateStatusPanel();

        startServerButton.addActionListener(e -> {
            if (!serverRunning) {
                updateServerStatus(true);
            } else {
                new ErrorWindow("Server is already running.");
            }
        });

        stopServerButton.addActionListener(e -> {
            if (serverRunning) {
                updateServerStatus(false);
            } else {
                new ErrorWindow("Server is already not running.");
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.add(startServerButton);
        mainPanel.add(serverStatusPanel);
        mainPanel.add(stopServerButton);

        add(mainPanel);

        setVisible(true);
    }

    private void updateStatusPanel() {
        serverStatusPanel.removeAll();

        JPanel statusRectangle = new JPanel();
        statusRectangle.setPreferredSize(new Dimension(20, 20));
        statusRectangle.setBackground(serverRunning ? Color.GREEN : Color.RED);

        serverStatusPanel.add(statusRectangle);

        serverStatusPanel.revalidate();
        serverStatusPanel.repaint();
    }

    private void updateServerStatus(boolean running) {
        serverRunning = running;
        updateStatusPanel();
        logServerStatus();
    }

    private void logServerStatus() {
        String statusMessage = serverRunning ? "Server started." : "Server stopped.";
        Logger.log(statusMessage, "server");
    }
}
