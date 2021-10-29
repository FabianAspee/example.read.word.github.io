package com.example.app.asynccruddb.implementation;

import com.example.app.database.common.CommonClass;
import com.example.app.database.configdb.RetrieveConnection;
import com.example.app.database.table.Artists;
import com.example.app.vertx.implvertx.ImplVertx;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

@Component
public final class CrudArtistsImpl extends CommonClass {

    private static final Function<Session,Function<String, Optional<List<Artists>>>> callDb = session->query ->
            Optional.of(session.createQuery(query,Artists.class).list());

    private static final Function<Session,Optional<List<Artists>>> getAllArtists = session ->
            callDb.apply(session).apply("FROM Artists");

    private static final Function<Session,Function<String,Optional<List<Artists>>>> getArtistByName = session -> name->
            callDb.apply(session).apply("FROM Artists WHERE name ="+name);

    private static final BiConsumer<Session,String> artistByName = (session,name)->
            callDb.apply(session).apply("FROM Artists WHERE name ="+name).ifPresent(x->x.forEach(value-> ImplVertx.getInstance().sendMessage(value)));

    private static final Consumer<Session> allArtists = session->
            callDb.apply(session).apply("FROM Artists").ifPresent(x->x.forEach(value-> ImplVertx.getInstance().sendMessage(value.getName())));

    @Override
    public CompletableFuture<Optional<List<String>>> readByName(final String name) {
        return CompletableFuture.supplyAsync(()-> getArtistByName.apply(RetrieveConnection.getInstance().getSession()).apply(name))
                .thenApplyAsync(artists -> artists.map(artists1 -> artists1.stream().map(Artists::getName).toList()));
    }

    @Override
    public CompletableFuture<Optional<List<String>>> readAll() {
        return CompletableFuture.supplyAsync(()->getAllArtists.apply(RetrieveConnection.getInstance().getSession()))
                .thenApplyAsync(artists->artists.map(artisList->artisList.stream().map(Artists::getName).toList()));
    }

    @Override
    public CompletableFuture<Optional<Integer>> updateByName(final String name) {
        return null;
    }

    @Override
    public CompletableFuture<Void> readByNameAndSendVertx(String name) {
        return CompletableFuture.runAsync(()->artistByName.accept(RetrieveConnection.getInstance().getSession(),name));
    }

    @Override
    public CompletableFuture<Void> readAllAndSendVertx() {
        return CompletableFuture.runAsync(()->allArtists.accept(RetrieveConnection.getInstance().getSession()));
    }
}
