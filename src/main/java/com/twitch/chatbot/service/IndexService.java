package com.twitch.chatbot.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IndexService {
    @Autowired
    private RestTemplate restTemplate;

    public String getPrizePool() {
        String html = restTemplate.getForObject("http://www.dota2.com/international/battlepass/", String.class);
        Document document = Jsoup.parse(html);
        Elements element = document.select("h1[class='PrizePool']");
        return element.get(0).text();
    }
}
