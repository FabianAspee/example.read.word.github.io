package com.example.readwordspring.controller;

import com.activeexample.activemq.SenderActive;
import com.code.countword.Implementation.ReadDirectoryImp;
import com.code.factory.SubscriberFactoryImp;
import com.example.readwordspring.contract.ReadWordContract;
import com.example.readwordspring.rabbitmq.SenderRabbitMQ;
import com.kafkaexample.apachekafka.Sender;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.ForkJoinPool;

@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:29130"})
@EnableAutoConfiguration
public record ReadWordController(SenderRabbitMQ senderService, SenderActive senderActive, Sender senderKafka) implements ReadWordContract {
    @Override
    public void readWordExample() {
        var fork = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        ReadDirectoryImp readDirectoryImp = SubscriberFactoryImp.getInstance().getReadDirectory(senderKafka.sendViaDirectExchange);
        fork.invoke(readDirectoryImp);
    }

    @Override
    public void readWordExampleActiveMq(Byte[] bytesFiles) {
        var fork = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        ReadDirectoryImp readDirectoryImp = SubscriberFactoryImp.getInstance().getReadDirectory(senderActive.sendViaDirectExchange);
        fork.invoke(readDirectoryImp);

    }

    @Override
    public void readWordExampleKafka(Byte[] bytesFiles) {
        var fork = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        ReadDirectoryImp readDirectoryImp = SubscriberFactoryImp.getInstance().getReadDirectory(senderActive.sendViaDirectExchange);
        fork.invoke(readDirectoryImp);
    }

    @Override
    public void readWord(Byte[] bytesFiles) {

    }

}
