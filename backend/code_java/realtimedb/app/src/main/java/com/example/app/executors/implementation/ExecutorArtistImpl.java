package com.example.app.executors.implementation;

import com.example.app.asynccruddb.implementation.CrudArtistsImpl;
import org.springframework.stereotype.Component;

/**
 * #project examples-with-java8
 *
 * @author aspeeencinaf and 25/10/21
 **/
@Component
public final class ExecutorArtistImpl extends CommonImpl {
    public ExecutorArtistImpl(CrudArtistsImpl crudOperation){
        super(crudOperation);
    }
}
