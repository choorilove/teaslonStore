package com.example.teastore.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TelegramNotificationService {

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.chat.id}")
    private String chatId;
    @Value("${telegram.chat.id2}")
    private String chatId2;

    public void sendOrderNotification(String message) {
        String url = String.format("https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s",
                botToken, chatId, message);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(url, String.class);
        String url2 = String.format("https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s",
                botToken, chatId2, message);
        RestTemplate restTemplate2 = new RestTemplate();
        restTemplate2.getForObject(url2, String.class);
    }
}
