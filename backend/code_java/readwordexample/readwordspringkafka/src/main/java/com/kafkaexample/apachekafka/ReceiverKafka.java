package com.kafkaexample.apachekafka;

import org.springframework.kafka.annotation.KafkaListener;

public class ReceiverKafka {


    @KafkaListener(groupId = "group", topics = ConnectionKafka.directExchangeName)
    public void listen1(String in) {
        System.out.println(in + "ok ");
    }

}
