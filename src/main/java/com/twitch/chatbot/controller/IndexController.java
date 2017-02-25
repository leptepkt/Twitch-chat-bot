package com.twitch.chatbot.controller;

import com.twitch.chatbot.config.SettingConfiguration;
import com.twitch.chatbot.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by leptepkt on 25/02/2017.
 */
@RestController
public class IndexController {
    @Autowired
    private BotService botService;
    @Autowired
    private SettingConfiguration settingConfiguration;

    @RequestMapping("/")
    public ResponseEntity<String> index() {
        return new ResponseEntity<String>("Number of executed command: " + botService.getNumberOfExecutedCommand(), HttpStatus.OK);
    }

    @RequestMapping("/notify")
    public ResponseEntity<String> noti() {
//        botService.sendMessage("I'm a available. Type !mmr to display your real MMR or !dick to display how long is your dick");
        botService.sendMessage("I'm back. Type !vote " + settingConfiguration.getTeamAName() + " to vote for " + settingConfiguration.getTeamAName()
                + ". Type !vote " + settingConfiguration.getTeamBName() + " to vote for " + settingConfiguration.getTeamBName()
                + ". Type !predict to show current viewer's prediction");

        return new ResponseEntity<String>("Notified", HttpStatus.OK);
    }

    @RequestMapping("/start")
    public ResponseEntity<String> start() {
        return new ResponseEntity<String>("Started", HttpStatus.OK);
    }

    @RequestMapping("/stop")
    public ResponseEntity<String> stop() {
        return new ResponseEntity<String>("Stopped", HttpStatus.OK);
    }
}
