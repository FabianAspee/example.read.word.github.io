package com.example.readwordspring.controller;

import com.code.countword.Implementation.ReadDirectoryImp;
import com.code.factory.SubscriberFactoryImp;
import com.example.readwordspring.contract.ReadWordContract;
import com.example.readwordspring.rabbitmq.Sender;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ForkJoinPool;

@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:29130"})
@EnableAutoConfiguration
public record ReadWordController(Sender senderService) implements ReadWordContract {
    @Override
    public void readWordExample() {
        var fork = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        ReadDirectoryImp readDirectoryImp = SubscriberFactoryImp.getInstance().getReadDirectory(senderService.sendViaDirectExchange);
        fork.invoke(readDirectoryImp);
    }

    @Override
    public void readWord(Byte[] bytesFiles) {

    }

}
