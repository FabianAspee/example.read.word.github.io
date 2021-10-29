package com.example.app.vertx.implvertx;

import com.example.app.vertx.configvertx.ComponentVertx;
import com.example.app.vertx.configvertx.ConfigurationVertx;
import io.vertx.core.Promise;

public final class ImplVertx implements OperationVertx{
    private static final ImplVertx implVertx  = new ImplVertx();
    private final ComponentVertx componentVertx;
    private ImplVertx() {
        Promise<Void> promise = Promise.promise();
        this.componentVertx = ConfigurationVertx.newBuilder()
                .enableBridge()
                .build();
        componentVertx.start(promise);
        promise.future().onSuccess(nothing->System.out.println("Ready"));
        promise.future().onFailure(nothing->componentVertx.stopVertx());
    }

    @Override
    public <T> void sendMessage(T msg) {
        componentVertx.publicToEventBus(msg);
    }

    public static ImplVertx getInstance(){
        return implVertx;
    }

}
