package com.example.app.controller;

import com.example.app.asynccruddb.contract.CrudOperation;
import com.example.app.asynccruddb.implementation.CrudArtistsImpl;
import com.example.app.contract.ArtistsOperation;
import com.example.app.executors.contract.CallCrudOperation;
import com.example.app.executors.implementation.ExecutorArtistImpl;
import com.example.app.forkjoin.implementation.ForkJoinArtistsImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * #project examples-with-java8
 *
 * @author aspeeencinaf and 25/10/21
 **/
@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:29130"})
@RequestMapping(value = "/example/artist")
public final class ArtistsImpl implements ArtistsOperation {
    private final CrudOperation crudOperation;
    private final CallCrudOperation callCrudOperationExecutor;
    private final com.example.app.forkjoin.contract.CallCrudOperation callCrudOperationForkJoin;
    public ArtistsImpl(CrudArtistsImpl crudOperation, ExecutorArtistImpl callCrudOperationExecutor, ForkJoinArtistsImpl callCrudOperationForkJoin){
        this.crudOperation=crudOperation;
        this.callCrudOperationExecutor = callCrudOperationExecutor;
        this.callCrudOperationForkJoin = callCrudOperationForkJoin;
    }
    @Override
    public CompletableFuture<Optional<List<String>>> readArtistByName(String name) {
        return crudOperation.readByName(name);
    }

    @Override
    public CompletableFuture<Optional<List<String>>> readAllArtist() {
        return crudOperation.readAll();
    }

    @Override
    public CompletableFuture<Optional<Integer>> updateArtistByName(final String name) {
        return crudOperation.updateByName(name);
    }

    @Override
    public Optional<List<String>> readArtistByNameExec(String name) {
        return callCrudOperationExecutor.readByName(name);
    }

    @Override
    public Optional<List<String>> readAllArtistExec() {
        return callCrudOperationExecutor.readAll();
    }

    @Override
    public Optional<Integer> updateArtistByNameExec(String name) {
        return callCrudOperationExecutor.updateByName(name);
    }

    @Override
    public Optional<List<String>> readArtistByNameForkJoin(String name) {
        return callCrudOperationForkJoin.readByName(name);
    }

    @Override
    public Optional<List<String>> readAllArtistForkJoin() {
        return callCrudOperationForkJoin.readAll();
    }

    @Override
    public Optional<Integer> updateArtistByNameForkJoin(String name) {
        return callCrudOperationForkJoin.updateByName(name);
    }

}
