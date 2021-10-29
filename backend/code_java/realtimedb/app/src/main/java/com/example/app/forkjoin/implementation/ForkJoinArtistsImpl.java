package com.example.app.forkjoin.implementation;

import com.example.app.asynccruddb.implementation.CrudArtistsImpl;
import org.springframework.stereotype.Component;

/**
 * #project examples-with-java8
 *
 * @author aspeeencinaf and 25/10/21
 **/
@Component
public class ForkJoinArtistsImpl extends CommonImpl {
    public ForkJoinArtistsImpl(CrudArtistsImpl crudOperation){
        super(crudOperation);
    }

}
