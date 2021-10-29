package com.example.app.vertx.implvertx;

import com.example.app.vertx.configvertx.ConfigurationVertx;
import io.vertx.core.Promise;


public final class ImplVertx extends ConfigurationVertx {

    public ImplVertx(int port) {
        super(port);
        Promise<Void> promise = Promise.promise();
        start(promise);
        promise.future().onSuccess(nothing->enableBridge());
        promise.future().onFailure(nothing->stopVertx());
    }
}
