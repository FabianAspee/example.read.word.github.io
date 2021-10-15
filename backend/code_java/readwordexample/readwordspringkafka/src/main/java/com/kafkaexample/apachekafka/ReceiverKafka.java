package com.kafkaexample.apachekafka;

import org.springframework.kafka.annotation.KafkaListener;

public class ReceiverKafka {


    @KafkaListener(groupId = "group", concurrency = "2", topics = ConnectionKafka.directExchangeName)
    public void listen1(String in) {
        System.out.println(in + "ok ");
    }
    @KafkaListener(groupId = "group2", concurrency = "2", topics = ConnectionKafka.directExchangeName)
    public void listen2(String in) {
        System.out.println(in + "ok2");
    }
}
