package org.example.chat;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
    private static String logFileName;

    public static void log(String message, String logType) {
        if(logType.equals("server")) {
            logFileName = "server_log.txt";
        }
        if(logType.equals("chat")) {
            logFileName = "chat_log.txt";
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFileName, true))) {
            writer.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
