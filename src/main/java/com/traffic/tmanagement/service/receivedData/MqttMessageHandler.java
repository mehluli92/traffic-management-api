package com.traffic.tmanagement.service.receivedData;

import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
public class MqttMessageHandler implements MessageHandler {
    /*
    It takes data from the MqttConfig class.
    The received data is delegated to the service class, receivedDataProcessingService.
    */

    private final ReceivedDataProcessingService receivedDataProcessingService;

    public MqttMessageHandler(ReceivedDataProcessingService receivedDataProcessingService){
        this.receivedDataProcessingService = receivedDataProcessingService;
    }


    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        String topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString();
        String payload = message.getPayload().toString();

        //Delegate business logic to the service class here
        receivedDataProcessingService.processMessage(topic, payload);
    }
}
