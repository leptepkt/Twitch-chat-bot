package com.twitch.chatbot.service;

import com.twitch.chatbot.config.SettingConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by leptepkt on 24/02/2017.
 */
@Service
public class BotService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SocketService socketService;
    @Autowired
    private SettingConfiguration settingConfiguration;

    public void sendMessage(String message) {
        try {
            socketService.getWriter().write("PRIVMSG #" + settingConfiguration.getChannel() + " :" + message + "\r\n");
            socketService.getWriter().flush();
        } catch (IOException e) {
            logger.error("Cannot send message: " + e.getMessage());
        }
    }

    public String getUsername(String content) {
        return content.substring(1, content.indexOf("!"));
    }
}
