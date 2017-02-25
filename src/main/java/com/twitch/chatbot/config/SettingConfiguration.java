package com.twitch.chatbot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by leptepkt on 25/02/2017.
 */
@Component
public class SettingConfiguration {
    @Value("${twitch.server}")
    private String server;
    @Value("${twitch.nick}")
    private String nick;
    @Value("${twitch.pass}")
    private String pass;
    @Value("${twitch.channel}")
    private String channel;
    @Value("${twitch.port}")
    private int port;

    @Value("${setting.predict.teamA}")
    private String teamAName;
    @Value("${setting.predict.teamB}")
    private String teamBName;

    public String getServer() {
        return server;
    }

    public String getNick() {
        return nick;
    }

    public String getPass() {
        return pass;
    }

    public String getChannel() {
        return channel;
    }

    public int getPort() {
        return port;
    }

    public String getTeamAName() {
        return teamAName;
    }

    public String getTeamBName() {
        return teamBName;
    }
}
