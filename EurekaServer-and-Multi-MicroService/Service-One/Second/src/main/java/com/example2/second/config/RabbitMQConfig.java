package com.example2.second.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // ✅ Fix: Mark as a configuration class
public class RabbitMQConfig {
    public static final String QUEUE_NAME = "SecondQueue";
    public static final String EXCHANGE_NAME = "second.exchange";

    @Bean
    public Queue bookingQueue() {
	   return new Queue(QUEUE_NAME, true);  // ✅ Queue is durable
    }

    @Bean
    public TopicExchange bookingExchange() {
	   return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue bookingQueue, TopicExchange bookingExchange) {  // ✅ Fix parameter names
	   return BindingBuilder.bind(bookingQueue).to(bookingExchange).with("second.#");
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
	   return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
	   RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
	   rabbitTemplate.setMessageConverter(jsonMessageConverter());
	   return rabbitTemplate;
    }
}
