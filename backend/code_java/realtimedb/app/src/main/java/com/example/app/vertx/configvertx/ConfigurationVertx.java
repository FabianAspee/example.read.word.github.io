package com.example.app.vertx.configvertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.ext.bridge.BridgeEventType;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.sockjs.SockJSBridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = {"http://127.0.0.1:8888/eventbus/info?*"})
public class ConfigurationVertx extends AbstractVerticle implements ComponentVertx{
    private final int port;
    public ConfigurationVertx(int port){
        this.port = port;
    }


    @Override
    public void start(Promise<Void> startPromise) {
        vertx = Vertx.vertx();
        vertx.createHttpServer().requestHandler(req -> req.response()
                .putHeader("content-type", "text/plain")
                .end("Hello from Vert.x!")).listen(port,"127.0.0.1", http -> {
            if (http.succeeded()) {
                startPromise.complete();
                System.out.println("HTTP server started on localhost port 8888");
            } else {
                startPromise.fail(http.cause());
                System.out.println("ABBIAMO FALITO");
            }
        });
    }
    @Override
    public void enableBridge(){
        Router router = Router.router(vertx);
        SockJSHandler sockJSHandler = SockJSHandler.create(vertx);

        SockJSBridgeOptions options = new SockJSBridgeOptions()
                .addInboundPermitted(new PermittedOptions().setAddress("some-address"))
                .addOutboundPermitted(new PermittedOptions().setAddress("some-address"));

        Router subRouter = sockJSHandler.bridge(options, event -> {

            // You can also optionally provide a handler like this which will be passed any events that occur on the bridge
            // You can use this for monitoring or logging, or to change the raw messages in-flight.
            // It can also be used for fine grained access control.

            if (event.type() == BridgeEventType.SOCKET_CREATED) {
                System.out.println("A socket was created");
            }

            // This signals that it's ok to process the event
            event.complete(true);

        });
        // mount the bridge on the router
        router.mountSubRouter("/eventbus", subRouter);

    }
    @Override
    public <T> void publicToEventBus(T msg){
        vertx.eventBus().publish("some-address",msg);
    }
    @Override
    public void stopVertx(){
       vertx.close();
    }

}