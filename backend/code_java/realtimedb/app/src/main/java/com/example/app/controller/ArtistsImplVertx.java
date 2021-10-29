package com.example.app.controller;

import com.example.app.contract.ArtistsOperationVertx;
import com.example.app.vertx.configvertx.ConfigurationVertx;
import com.example.app.vertx.implvertx.ImplVertx;
import io.vertx.core.Promise;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * #project realtimedatabase
 *
 * @author aspeeencinaf and 28/10/21
 **/
@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:29130"})
@RequestMapping(value = "/example/artistvertx")
public final class ArtistsImplVertx implements ArtistsOperationVertx {
    private final ConfigurationVertx vertx;

    public ArtistsImplVertx() {
        this.vertx = new ImplVertx(8888);
    }

    @Override
    public void readArtistByName(String name) {


    }

    @Override
    public void readAllArtists() {
        vertx.publicToEventBus("pedrito");
    }
}
