package com.example.app.controller;

import com.example.app.asynccruddb.contract.CrudOperationVertx;
import com.example.app.asynccruddb.implementation.CrudPlayListImpl;
import com.example.app.contract.PlaylistOperationVertx;
import com.example.app.vertx.configvertx.ComponentVertx;
import com.example.app.vertx.configvertx.ConfigurationVertx;
import com.example.app.vertx.implvertx.ImplVertx;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

/**
 * #project examples-with-java8
 *
 * @author aspeeencinaf and 25/10/21
 **/
@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:29130"})
@RequestMapping(value = "/example/playlistvertx")
public final class PlaylistImplVertx implements PlaylistOperationVertx {
    private final CrudOperationVertx crudOperationVertx;
    public PlaylistImplVertx(CrudPlayListImpl crudOperationVertx){
        this.crudOperationVertx=crudOperationVertx;
    }
    @Override
    public void readPlaylistByName(String name) {
        crudOperationVertx.readByNameAndSendVertx(name);
    }

    @Override
    public void readAllPlaylist() {
        crudOperationVertx.readAllAndSendVertx();
    }
}
