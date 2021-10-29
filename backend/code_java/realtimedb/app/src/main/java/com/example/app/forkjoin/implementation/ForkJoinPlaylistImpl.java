package com.example.app.forkjoin.implementation;

import com.example.app.asynccruddb.implementation.CrudPlayListImpl;
import org.springframework.stereotype.Component;

/**
 * #project examples-with-java8
 *
 * @author aspeeencinaf and 25/10/21
 **/
@Component
public class ForkJoinPlaylistImpl extends CommonImpl{
    public ForkJoinPlaylistImpl(CrudPlayListImpl crudPlayList){
        super(crudPlayList);
    }
}
