package com.twitch.chatbot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;

/**
 * Created by AnhNN13 on 2/24/2017.
 */
@Service
public class SocketService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${twitch.server}")
    private String server = "irc.chat.twitch.tv";
    @Value("${twitch.nick}")
    private String nick = "chat_bot";
    @Value("${twitch.pass}")
    private String pass = "oauth:wpwkuxtis8595rajmp0ui83nxrne9d";
    @Value("${twitch.channel}")
    private String channel = "leptepkt";
    @Value("${twitch.port}")
    private int port = 6667;

    private Socket socket;
    private BufferedWriter writer;
    private BufferedReader reader;

    public SocketService() {
        try {
            socket = new Socket(server, port);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            writer.write("PASS " + pass + "\r\n");
            writer.write("NICK " + nick + "\r\n");
            writer.write("JOIN #" + channel + "\r\n");
            writer.flush();

            String line;
            while ((line = reader.readLine( )) != null) {
                logger.info(line);
                if (line.contains("End of /NAMES list")) {
                    logger.info("Connected to IRC");
                    break;
                }
            }

        } catch (IOException e) {
            logger.error("Cannot connect to server: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        try {
            writer.write("PRIVMSG #" + channel + " :" + message);
            writer.flush();
        } catch (IOException e) {
            logger.error("Cannot connect to server: " + e.getMessage());
        }
    }

    public BufferedWriter getWriter() {
        return writer;
    }

    public BufferedReader getReader() {
        return reader;
    }
}
