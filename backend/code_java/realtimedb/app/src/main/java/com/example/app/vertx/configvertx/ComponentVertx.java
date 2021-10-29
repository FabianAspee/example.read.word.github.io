package com.example.app.vertx.configvertx;

import io.vertx.core.Promise;

/**
 * #project realtimedatabase
 *
 * @author aspeeencinaf and 28/10/21
 **/
public interface ComponentVertx {
    ComponentVertx build();
    void start(Promise<Void> startPromise);
    ComponentVertx enableBridge();
    void stopVertx();
    <T> void publicToEventBus(T msg);
}
