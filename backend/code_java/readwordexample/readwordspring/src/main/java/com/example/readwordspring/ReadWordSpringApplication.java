package com.example.readwordspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.example","com.activeexample","com.kafkaexample"})
public class ReadWordSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadWordSpringApplication.class, args);
    }

}
