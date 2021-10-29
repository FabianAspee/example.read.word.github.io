package com.example.app.controller;

import com.example.app.asynccruddb.contract.CrudOperation;
import com.example.app.asynccruddb.implementation.CrudPlayListImpl;
import com.example.app.contract.PlaylistOperation;
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
@RequestMapping(value = "/example/playlist")
public final class PlaylistImpl implements PlaylistOperation {
    private final CrudOperation crudOperation;
    private final CallCrudOperation callCrudOperationExecutor;
    private final com.example.app.forkjoin.contract.CallCrudOperation callCrudOperationForkJoin;
    public PlaylistImpl(CrudPlayListImpl crudOperation, ExecutorArtistImpl callCrudOperationExecutor, ForkJoinArtistsImpl callCrudOperationForkJoin){
        this.crudOperation=crudOperation;
        this.callCrudOperationExecutor = callCrudOperationExecutor;
        this.callCrudOperationForkJoin = callCrudOperationForkJoin;
    }
    @Override
    public CompletableFuture<Optional<List<String>>> readPlaylistByName(String name) {
        return crudOperation.readByName(name);
    }

    @Override
    public CompletableFuture<Optional<List<String>>> readAllPlaylist() {
        return crudOperation.readAll();
    }

    @Override
    public CompletableFuture<Optional<Integer>> updatePlaylistByName(String name) {
        return crudOperation.updateByName(name);
    }

    @Override
    public Optional<List<String>> readPlaylistByNameExec(String name) {
        return callCrudOperationExecutor.readByName(name);
    }

    @Override
    public Optional<List<String>> readAllPlaylistExec() {
        return callCrudOperationExecutor.readAll();
    }

    @Override
    public Optional<Integer> updatePlaylistByNameExec(String name) {
        return callCrudOperationExecutor.updateByName(name);
    }

    @Override
    public Optional<List<String>> readPlaylistByNameForkJoin(String name) {
        return callCrudOperationForkJoin.readByName(name);
    }

    @Override
    public Optional<List<String>> readAllPlaylistForkJoin() {
        return callCrudOperationForkJoin.readAll();
    }

    @Override
    public Optional<Integer> updateAPlaylistByNameForkJoin(String name) {
        return callCrudOperationForkJoin.updateByName(name);
    }
}
