package com.example.readwordspring.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class Receiver {
    /**
     * recive messages from rabbitMq queue
     * @param incomingMessage
     */
    @RabbitListener(queues = "${app.queue.name}")
    public void receivedMessage(String incomingMessage) {
        System.out.println("Received Message From RabbitMQ: " + incomingMessage);
    }
}