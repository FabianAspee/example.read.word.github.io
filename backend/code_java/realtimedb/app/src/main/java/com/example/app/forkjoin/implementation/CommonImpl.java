package com.example.app.forkjoin.implementation;

import com.example.app.asynccruddb.contract.CrudOperation;
import com.example.app.forkjoin.contract.CallCrudOperation;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ForkJoinPool;

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
        return  ForkJoinPool.commonPool().submit(()->crudOperation.readByName(name).join()).join();
    }

    @Override
    public Optional<List<String>> readAll() {
        return  ForkJoinPool.commonPool().submit(()->crudOperation.readAll().join()).join();
    }

    @Override
    public Optional<Integer> updateByName(final String name) {
        return  ForkJoinPool.commonPool().submit(()->crudOperation.updateByName(name).join()).join();
    }
}
