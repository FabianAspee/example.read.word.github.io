package com.example.app.contract;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.CompletableFuture;

/**
 * #project realtimedatabase
 *
 * @author aspeeencinaf and 29/10/21
 **/
public interface MathematicalOperation {
    @PostMapping(value = "/mathematical/factorial")
    CompletableFuture<Void> calculusFactorial(@RequestParam(value = "number", defaultValue = "0") final int factorial);
    @PostMapping("/mathematical/fibonacci")
    CompletableFuture<Void> calculusFibonacci(@RequestBody final int fibonacci);
}
