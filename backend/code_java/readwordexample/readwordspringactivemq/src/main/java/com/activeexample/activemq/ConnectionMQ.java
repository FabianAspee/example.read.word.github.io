package com.activeexample.activemq;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectionMQ{
    public static final String queueName = "demo-activemq";
    public static final String topicExchangeName = "topic-exchange";
    public static final String headerExchangeName = "demo-headers-exchange";
    public static final String directExchangeName = "demo-direct-exchange";
    public static final String fanoutExchangeName = "demo-fanout-exchange";

}
