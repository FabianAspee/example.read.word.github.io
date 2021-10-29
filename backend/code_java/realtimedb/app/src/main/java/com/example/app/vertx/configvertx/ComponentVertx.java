package com.example.app.vertx.configvertx;

/**
 * #project realtimedatabase
 *
 * @author aspeeencinaf and 28/10/21
 **/
public interface ComponentVertx {
    void enableBridge();
    void stopVertx();
    <T> void publicToEventBus(T msg);
}
