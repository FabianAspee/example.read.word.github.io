package com.example.app.controller;

import com.example.app.asynccruddb.contract.CrudOperation;
import com.example.app.asynccruddb.contract.CrudOperationVertx;
import com.example.app.asynccruddb.implementation.CrudArtistsImpl;
import com.example.app.contract.ArtistsOperationVertx;
import com.example.app.executors.contract.CallCrudOperation;
import com.example.app.executors.implementation.ExecutorArtistImpl;
import com.example.app.forkjoin.implementation.ForkJoinArtistsImpl;
import com.example.app.vertx.configvertx.ComponentVertx;
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
    private final CrudOperationVertx crudOperationVertx;
    public ArtistsImplVertx(CrudArtistsImpl crudOperationVertx){
        this.crudOperationVertx=crudOperationVertx;
    }

    @Override
    public void readArtistByName(String name) {
        crudOperationVertx.readByNameAndSendVertx(name);
    }

    @Override
    public void readAllArtists() {
        crudOperationVertx.readAllAndSendVertx();
    }
}
