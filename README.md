**Traffic Management API**
IoT platform used to send data from the traffic lights to the server using MQTT.

1. Mosquito is used as publisher and subscriber of the messages from and to IoT devices.
   - The current implementation uses username: admin password: 12345678
   - MQTT_BROKER_URL = "tcp://localhost:1883"
   - String MQTT_CLIENT_ID = "spring-boot-client"
Test Mosquitto on windows as follows:
   - cd "C:\Program Files\Mosquitto"
   - start mosquitto: net start mosquitto
   - mosquitto_sub -h localhost -t myTopic -u admin -P 12345678
   - mosquitto_pub -h localhost -t myTopic -m "Hello, from MQTT publisher!" -u admin -P 12345678
   

2. Paho Client library is used to communicate with Mosquito 
