package com.activeexample.activemq;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class SenderActive {

    private JmsTemplate jmsTemplate;

    public SenderActive(JmsTemplate jmsTemplate){
        this.jmsTemplate=jmsTemplate;
    }

    /**
     * sends messages via Direct exchange
     *
     * @param message
     */
    public Consumer<String> sendViaDirectExchange = message ->
            jmsTemplate.convertAndSend(ConnectionMQ.directExchangeName, message);
}
