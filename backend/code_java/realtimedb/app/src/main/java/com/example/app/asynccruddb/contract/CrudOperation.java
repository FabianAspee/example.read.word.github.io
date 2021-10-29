package com.example.app.asynccruddb.contract;

import org.hibernate.Session;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * #project examples-with-java8
 *
 * @author aspeeencinaf and 22/10/21
 **/
public interface CrudOperation {

    <B> Optional<B> executionFunction(Function<Session,B> function);
    CompletableFuture<Optional<List<String>>> readByName(final String  name);
    CompletableFuture<Optional<List<String>>> readAll();
    CompletableFuture<Optional<Integer>> updateByName(final String name);

}
