package com.twitch.chatbot.service;

import com.twitch.chatbot.config.SettingConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private SettingConfiguration settingConfiguration;

    private Socket socket;
    private BufferedWriter writer;
    private BufferedReader reader;

    @Autowired
    public SocketService(SettingConfiguration settingConfiguration) {
        this.settingConfiguration = settingConfiguration;

        try {
            socket = new Socket(settingConfiguration.getServer(), settingConfiguration.getPort());
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            writer.write("PASS " + settingConfiguration.getPass() + "\r\n");
            writer.write("NICK " + settingConfiguration.getNick() + "\r\n");
            writer.write("JOIN #" + settingConfiguration.getChannel() + "\r\n");
            writer.flush();

            String line;
            while ((line = reader.readLine()) != null) {
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

    public BufferedWriter getWriter() {
        return writer;
    }

    public BufferedReader getReader() {
        return reader;
    }


}
