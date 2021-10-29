package com.example.app.executors.implementation;

import com.example.app.asynccruddb.implementation.CrudPlayListImpl;
import org.springframework.stereotype.Component;

/**
 * #project examples-with-java8
 *
 * @author aspeeencinaf and 25/10/21
 **/
@Component
public class ExecutorPlaylistImpl extends CommonImpl{
    public ExecutorPlaylistImpl(CrudPlayListImpl crudOperation){
        super(crudOperation);
    }
}
