package com.example.app.controller;

import com.example.app.contract.PlaylistOperationVertx;
import com.example.app.vertx.configvertx.ConfigurationVertx;
import com.example.app.vertx.implvertx.ImplVertx;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * #project examples-with-java8
 *
 * @author aspeeencinaf and 25/10/21
 **/
@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:29130"})
@RequestMapping(value = "/example/playlistvertx")
public final class PlaylistImplVertx implements PlaylistOperationVertx {
    private final ConfigurationVertx vertx;

    public PlaylistImplVertx() {
        this.vertx = new ImplVertx(8888);
    }

    @Override
    public void readPlaylistByName(String name) {

    }

    @Override
    public void readAllPlaylist() {
        vertx.publicToEventBus("playList");
    }
}
