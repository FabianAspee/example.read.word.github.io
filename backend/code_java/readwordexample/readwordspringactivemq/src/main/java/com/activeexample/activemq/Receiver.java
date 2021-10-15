package com.activeexample.activemq;

import org.springframework.jms.annotation.JmsListener;

public class Receiver {

    @JmsListener(concurrency = "2", destination = "my_msg", containerFactory = "my_container")
    public void listen1(String in) {
        System.out.println(in + "ok ");
    }
}
