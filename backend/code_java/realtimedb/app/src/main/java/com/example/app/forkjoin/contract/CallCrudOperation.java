package com.example.app.forkjoin.contract;

import java.util.List;
import java.util.Optional;

/**
 * #project examples-with-java8
 *
 * @author aspeeencinaf and 25/10/21
 **/
public interface CallCrudOperation {
        Optional<List<String>> readByName(final String  name);
        Optional<List<String>> readAll();
        Optional<Integer> updateByName(final String name);

}
