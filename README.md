## Eureka Server Setup

1. **Add Dependency**  
   Add `spring-cloud-starter-netflix-eureka-server` in `pom.xml`.

2. **Enable Eureka Server**  
   Use `@EnableEurekaServer` in your main application class.

3. **Configure `application.properties`**    

4. **Run the Application**    
Start the Spring Boot application to run the Eureka Server. 

**Here Ankit Rajput Setup Demo**  
![Image](https://github.com/user-attachments/assets/9d9ecf8a-8583-4b85-be08-9bbb3caba2c0)

![Image](https://github.com/user-attachments/assets/7f905aa1-0a4a-4aa9-aa00-a707783280ac) 

**Pom.xml file view** 
![Image](https://github.com/user-attachments/assets/c00124ad-c7e3-4421-b971-633dd8f56b20)
 

 **Application.properties Configuration I did** 
 ```bash
 spring.application.name=ServiceRegistory 
server.port=8761
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
eureka.server.wait-time-in-ms-when-sync-empty=0
 ```
---
---- 



## SpringCloud-and-RabbitMQ-Learning 

**RabbitMQ UI interface showing queue details and exchange mediums** 
![Image](https://github.com/user-attachments/assets/44317c43-9ae2-428a-b8f8-f241593a5763)