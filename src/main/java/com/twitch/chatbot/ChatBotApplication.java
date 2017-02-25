package com.twitch.chatbot;

import com.twitch.chatbot.service.BotService;
import com.twitch.chatbot.service.SocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class ChatBotApplication implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SocketService socketService;
    @Autowired
    private BotService botService;

    private Random random = new Random();

    public static void main(String[] args) {
        SpringApplication.run(ChatBotApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        String line;
        int count = 0;
        while ((line = socketService.getReader().readLine()) != null) {
            if (line.startsWith("PING ")) {
                // We must respond to PINGs to avoid being disconnected.
                socketService.getWriter().write("PONG " + line.substring(5) + "\r\n");
                socketService.getWriter().flush();
                logger.info("PONG");
            } else {
                if (line.contains(":!mmr")) {
                    int mmr = random.nextInt(9001);
                    botService.sendMessage(botService.getUsername(line) + "'s MMR is " + mmr);
                    count++;
                    botService.setNumberOfExecutedCommand(count);
                }
                if (line.contains(":!dick")) {
                    int dick = 3 + random.nextInt(23);
                    if (dick <= 10) {
                        botService.sendMessage(botService.getUsername(line) + "'s dick is " + dick + " cm 4Head");
                    } else if (dick <= 15) {
                        botService.sendMessage(botService.getUsername(line) + "'s dick is " + dick + " cm cmonBruh");
                    } else if (dick <= 20) {
                        botService.sendMessage(botService.getUsername(line) + "'s dick is " + dick + " cm SeemsGood");
                    } else {
                        botService.sendMessage(botService.getUsername(line) + "'s dick is " + dick + " cm PogChamp");
                    }
                    count++;
                    botService.setNumberOfExecutedCommand(count);
                }

                if (line.contains(":!botstatus")) {
                    botService.sendMessage("I'm a available. Type !mmr to display your real MMR or !dick to display how long is your dick");
                }
            }
        }
    }
}