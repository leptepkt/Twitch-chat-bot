package com.twitch.chatbot;

import com.twitch.chatbot.service.SocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatBotApplication implements CommandLineRunner {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SocketService socketService;

	public static void main(String[] args) {
		SpringApplication.run(ChatBotApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		String line;
		while ((line = socketService.getReader().readLine( )) != null) {
			if (line.toLowerCase( ).startsWith("PING ")) {
				// We must respond to PINGs to avoid being disconnected.
				socketService.getWriter().write("PONG " + line.substring(5) + "\r\n");
				socketService.getWriter().flush( );
			}
			else {
				logger.info(line);
			}
		}
	}
}