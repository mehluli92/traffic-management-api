package com.traffic.tmanagement.config;

import com.traffic.tmanagement.service.receivedData.MqttMessageHandler;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessagingException;

@Configuration
public class MqttConfig {
    private static final String MQTT_BROKER_URL = "tcp://localhost:1883";  // Replace with your broker URL
    private static final String MQTT_CLIENT_ID = "spring-boot-client"; // Unique Client ID

    @Bean
    public MqttPahoClientFactory mqttPahoClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{MQTT_BROKER_URL});
        options.setUserName("admin"); // if broker requires login
        options.setPassword("12345678".toCharArray()); // convert password to char array
        options.setCleanSession(true);
        factory.setConnectionOptions(options);
        return factory;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(MQTT_CLIENT_ID + "_inbound", mqttPahoClientFactory(), "#");
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(2); // QoS 1 ensures at least once delivery
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler(MqttMessageHandler mqttMessageHandler) {
        return mqttMessageHandler;
//        return new MessageHandler() {
//            @Override
//            public void handleMessage(Message<?> message) throws MessagingException {
//
//                String topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString();
//                if(topic.equals("myTopic")){
//                    System.out.println("This is our topic");
//                }
//                System.out.println(message.getPayload());
//            }
//        };
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(MQTT_CLIENT_ID + "_outbound", mqttPahoClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic("#");
        return messageHandler;
    }
}
