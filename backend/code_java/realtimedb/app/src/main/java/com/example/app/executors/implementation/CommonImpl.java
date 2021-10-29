package com.example.app.executors.implementation;

import com.example.app.asynccruddb.contract.CrudOperation;
import com.example.app.executors.config.ExecutorSingleton;
import com.example.app.executors.contract.CallCrudOperation;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * #project examples-with-java8
 *
 * @author aspeeencinaf and 25/10/21
 **/
abstract class CommonImpl implements CallCrudOperation {
    private final CrudOperation crudOperation;
    protected CommonImpl(CrudOperation crudOperation){
        this.crudOperation=crudOperation;
    }
    @Override
    public Optional<List<String>> readByName(final String name) {
        return CompletableFuture.supplyAsync(()->
                execOperation(parameter->ExecutorSingleton.getInstance().getExecutor()
                        .submit(()->crudOperation.readByName(parameter).join()),name)).join();
    }

    @Override
    public Optional<List<String>> readAll() {
        return  CompletableFuture.supplyAsync(()->
                execOperation(()->ExecutorSingleton.getInstance().getExecutor()
                        .submit(()->crudOperation.readAll().join()))).join();
    }

    @Override
    public Optional<Integer> updateByName(final String name) {
        return  CompletableFuture.supplyAsync(()->
                execOperation(parameter->ExecutorSingleton.getInstance().getExecutor()
                        .submit(()->crudOperation.updateByName(parameter).join()),name)).join();
    }

    private <A> Optional<A> execOperation(Function<String, Future<Optional<A>>> optionalFunction, String str){
       return execOperation(optionalFunction.apply(str));
    }

    private <A> Optional<A> execOperation(Supplier<Future<Optional<A>>> optionalFunction){
       return execOperation(optionalFunction.get());
    }

    private <A> Optional<A> execOperation(Future<Optional<A>> future){
        try {
            return future.get() ;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        return Optional.empty();
    }
}
