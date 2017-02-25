package com.twitch.chatbot.controller;

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
    @RequestMapping("/")
    public ResponseEntity<String> index() {
        return new ResponseEntity<String>("Welcome to Twitch chatbot", HttpStatus.OK);
    }
}
