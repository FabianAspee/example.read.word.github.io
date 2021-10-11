package com.kafkaexample.apachekafka;

import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.function.Consumer;

@Service
public class Sender {

    private KafkaTemplate<String, String>  kafkaTemplate;

    public Sender(KafkaTemplate<String, String>  kafkaTemplate){
        this.kafkaTemplate=kafkaTemplate;
    }
    /**
     * sends messages via Direct exchange
     *
     * @param message
     */
    public Consumer<String> sendViaDirectExchange = message ->{
        ListenableFuture<SendResult<String,String>>result = kafkaTemplate.send(ConnectionKafka.directExchangeName, "#direct", message);
        result.addCallback(new KafkaSendCallback<>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println(result.toString() + " result ok");
            }
            @Override
            public void onFailure(KafkaProducerException ex) {
                System.out.println(ex.getLocalizedMessage());
            }

        });
    };

}
