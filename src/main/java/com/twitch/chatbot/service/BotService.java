package com.twitch.chatbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by leptepkt on 24/02/2017.
 */
@Service
public class BotService {
    @Autowired
    private SocketService socketService;

    public void sendMessage(String message) {

    }
}
