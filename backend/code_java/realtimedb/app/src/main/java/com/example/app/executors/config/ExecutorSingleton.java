package com.example.app.executors.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * #project examples-with-java8
 *
 * @author aspeeencinaf and 25/10/21
 **/
public class ExecutorSingleton {
    private static final ExecutorSingleton instance = new ExecutorSingleton();
    private static final ExecutorService executor = Executors.newCachedThreadPool();
    private ExecutorSingleton(){}
    public static ExecutorSingleton getInstance() {
        return instance;
    }
    public ExecutorService getExecutor(){
        return executor;
    }
}
