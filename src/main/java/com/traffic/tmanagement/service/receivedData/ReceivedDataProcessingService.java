package com.traffic.tmanagement.service.receivedData;

import org.springframework.stereotype.Service;

@Service
public class ReceivedDataProcessingService {
    public void processMessage(String topic, String payload) {
        //process received data here
        System.out.println("Data from the service: " + topic + " payLoad is: " + payload);
    }
}
