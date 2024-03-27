package org.example.chat;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ChatWindow extends JFrame {
    private static  final int WINDOW_HEIGHT = 400;
    private static  final int WINDOW_WIDTH = 700;
    private static  final int WINDOW_POSX = 600;
    private static  final int WINDOW_POSY = 200;
    private final JLabel loginLabel = new JLabel("Login");
    private JTextField loginTextField = new JTextField();
    private final JLabel passwordLabel = new JLabel("Password");
    private JTextField passwordTextField = new JTextField();
    private final JLabel ipAddressLabel = new JLabel("IP address");
    private JTextField ipAddressTextField = new JTextField();
    private final JLabel portLabel = new JLabel("Port");
    private JTextField portTextField = new JTextField();
    private final JButton connectButton = new JButton("Connect");
    private final JButton disconnectButton = new JButton("Disconnect");
    private JPanel upPanel = new JPanel(new GridLayout(5,2));

    private JTextArea chatLog = new JTextArea();
    private JScrollPane chatLogScrollPane = new JScrollPane(chatLog);

    private JTextArea enterTextTextField = new JTextArea();
    private final JButton sendButton = new JButton("Send");
    private JPanel downPanel = new JPanel(new BorderLayout());

    public ChatWindow() {
        setTitle("Chat");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);

        enterTextTextField.setEnabled(false);

        upPanel.add(ipAddressLabel);
        upPanel.add(ipAddressTextField);
        upPanel.add(portLabel);
        upPanel.add(portTextField);
        upPanel.add(loginLabel);
        upPanel.add(loginTextField);
        upPanel.add(passwordLabel);
        upPanel.add(passwordTextField);
        connectButton.addActionListener(e -> {
            ipAddressTextField.setEditable(false);
            portTextField.setEditable(false);
            loginTextField.setEditable(false);
            passwordTextField.setEditable(false);
            logMessage("Connecting to " + ipAddressTextField.getText() + ":" + portTextField.getText() + "...");
            fillChatLog();
            enterTextTextField.setEnabled(true);
            connectButton.setEnabled(false);
            disconnectButton.setEnabled(true);
        });
        upPanel.add(connectButton);
        disconnectButton.setEnabled(false);
        disconnectButton.addActionListener(e -> {
            ipAddressTextField.setEditable(true);
            portTextField.setEditable(true);
            loginTextField.setEditable(true);
            passwordTextField.setEditable(true);
            logMessage("Disconnecting...");
            fillChatLog();
            enterTextTextField.setEnabled(false);
            connectButton.setEnabled(true);
            disconnectButton.setEnabled(false);
        });
        upPanel.add(disconnectButton);

        chatLog.setEditable(false);
        chatLogScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        sendButton.addActionListener(e -> {
            logMessage("Send message from user: " + loginTextField.getText() + " -> " + enterTextTextField.getText());
            enterTextTextField.setText("");
            fillChatLog();
        });
        downPanel.add(enterTextTextField, BorderLayout.CENTER);
        downPanel.add(sendButton, BorderLayout.EAST);

        add(upPanel, BorderLayout.NORTH);
        add(chatLogScrollPane, BorderLayout.CENTER);
        add(downPanel, BorderLayout.SOUTH);
    }

    private void logMessage(String message) {
        Logger.log(message, "chat");
    }

    private void fillChatLog() {
        try {
            StringBuilder content = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader("chat_log.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
            chatLog.setText(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
