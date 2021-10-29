package com.example.app.asynccruddb.implementation;

import com.example.app.database.common.CommonClass;
import com.example.app.database.configdb.RetrieveConnection;
import com.example.app.database.table.Artists;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public final class CrudArtistsImpl extends CommonClass {

    private static final Function<Session,Function<String, Optional<List<Artists>>>> callDb = session->query ->
            Optional.of(session.createQuery(query,Artists.class).list());

    private static final Function<Session,Optional<List<Artists>>> getAllArtists = session ->
            callDb.apply(session).apply("FROM Artists");

    private static final Function<Session,Function<String,Optional<List<Artists>>>> getArtistByName = session -> name->
            callDb.apply(session).apply("FROM Artists WHERE name ="+name);

    @Override
    public CompletableFuture<Optional<List<String>>> readByName(final String name) {
        return CompletableFuture.supplyAsync(()-> getArtistByName.apply(RetrieveConnection.getInstance().getSession()).apply(name))
                .thenApplyAsync(artists -> artists.map(artists1 -> artists1.stream().map(Artists::getName).collect(Collectors.toList())));
    }

    @Override
    public CompletableFuture<Optional<List<String>>> readAll() {
        return CompletableFuture.supplyAsync(()->getAllArtists.apply(RetrieveConnection.getInstance().getSession()))
                .thenApplyAsync(artists->artists.map(artisList->artisList.stream().map(Artists::getName).collect(Collectors.toList())));
    }



    @Override
    public CompletableFuture<Optional<Integer>> updateByName(final String name) {
        return null;
    }
}
