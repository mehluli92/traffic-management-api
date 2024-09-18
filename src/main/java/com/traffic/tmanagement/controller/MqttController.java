package com.traffic.tmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqttController {

    @Autowired
    private MessageChannel mqttOutboundChannel;

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestParam String message) {
        mqttOutboundChannel.send(MessageBuilder.withPayload(message).build());
        return "Message sent to the broker: " + message;
    }

}
