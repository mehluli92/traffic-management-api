**Traffic Management API**
IoT platform used to send data from the traffic lights to the server using MQTT. 

The front-end is made in Next js and allows interaction with this platform. User management, role management and traffic management functions have been also included.  

1. Mosquito is used as publisher and subscriber of the messages from and to IoT devices.
   - The current implementation does not require a password from Mosquito
   - MQTT_BROKER_URL = "tcp://localhost:1883"
   - String MQTT_CLIENT_ID = "spring-boot-client"
2. Paho Client library is used to communicate with Mosquito

Happy coding!
