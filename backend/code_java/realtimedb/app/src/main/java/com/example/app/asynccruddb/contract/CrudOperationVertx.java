package com.example.app.asynccruddb.contract;

import java.util.concurrent.CompletableFuture;

/**
 * #project realtimedatabase
 *
 * @author aspeeencinaf and 29/10/21
 **/
public interface CrudOperationVertx {
    CompletableFuture<Void> readByNameAndSendVertx(final String  name);
    CompletableFuture<Void> readAllAndSendVertx();
}
