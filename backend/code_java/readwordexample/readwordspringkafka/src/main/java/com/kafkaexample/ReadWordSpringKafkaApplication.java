package com.kafkaexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.kafkaexample"})
public class ReadWordSpringKafkaApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReadWordSpringKafkaApplication.class, args);
    }
}
