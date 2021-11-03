package com.example.app.logger;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public abstract class LoggerImpl<T> {
    private final Logger logger;
    protected LoggerImpl(Class<T> classToLogger) {
        this.logger = LogManager.getLogger(classToLogger);
    }
    protected void info(String str){
        System.out.println(str);
        this.logger.info(str);
    }
    protected void warning(String str){
        this.logger.warn(str);
    }
    protected void error(String str){
        this.logger.error(str);
    }
}
