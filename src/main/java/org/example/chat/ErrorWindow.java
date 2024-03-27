package org.example.chat;

import javax.swing.*;
import java.awt.*;

public class ErrorWindow extends JFrame {
    private static  final int WINDOW_HEIGHT = 150;
    private static  final int WINDOW_WIDTH = 300;
    private static  final int WINDOW_POSX = 500;
    private static  final int WINDOW_POSY = 150;

    JLabel textArea = new JLabel();
    JButton okButton = new JButton("OK");
    JPanel buttonPanel = new JPanel();

    public ErrorWindow(String errorMessage) {
        setTitle("Error");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        textArea.setText(errorMessage);
        textArea.setHorizontalAlignment(SwingConstants.CENTER);

        okButton.addActionListener(e -> {
            dispose();
        });

        buttonPanel.add(okButton);

        add(textArea, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
