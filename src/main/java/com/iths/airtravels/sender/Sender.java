package com.iths.airtravels.sender;

import com.iths.airtravels.config.JmsConfig;
import com.iths.airtravels.model.MessageObject;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class Sender {

    JmsTemplate jmsTemplate;

    public Sender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage() {

        System.out.println("Sending message...");
        MessageObject messageObject = new MessageObject(UUID.randomUUID(), "new member!", LocalDateTime.now());
        jmsTemplate.convertAndSend(JmsConfig.MEMBER_QUEUE, messageObject);
        System.out.println("Message sent!");

    }

}