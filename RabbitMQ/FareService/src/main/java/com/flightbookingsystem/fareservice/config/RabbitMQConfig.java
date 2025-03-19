package com.flightbookingsystem.fareservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String BOOKING_QUEUE = "bookingQueue";
    public static final String FARE_QUEUE = "fareQueue";
    public static final String FARE_EXCHANGE = "fareExchange";

    @Bean
    public Queue bookingQueue() {
        return new Queue(BOOKING_QUEUE, true);
    }

    @Bean
    public Queue fareQueue() {
        return new Queue(FARE_QUEUE, true);
    }

    @Bean
    public TopicExchange fareExchange() {
        return new TopicExchange(FARE_EXCHANGE);
    }

    @Bean
    public Binding bindingFare(Queue fareQueue, TopicExchange fareExchange) {
        return BindingBuilder.bind(fareQueue).to(fareExchange).with("fare.#");
    }

    // ✅ Fix: Configure JSON message converter
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // ✅ Fix: Ensure RabbitMQ uses the JSON message converter
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
