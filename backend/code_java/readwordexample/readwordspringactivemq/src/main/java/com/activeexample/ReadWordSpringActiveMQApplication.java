package com.activeexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.activeexample"})
public class ReadWordSpringActiveMQApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReadWordSpringActiveMQApplication.class, args);
    }
}
