package com.example.app.controller;

import com.example.app.contract.MathematicalOperation;
import com.example.app.memoryoperation.MemoryOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * #project realtimedatabase
 *
 * @author aspeeencinaf and 29/10/21
 **/
@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:29130"})
@RequestMapping(value = "/mathematical/operationvertx")
public record MathematicalImpl(MemoryOperation memoryOperation) implements MathematicalOperation {
    @Override
    public CompletableFuture<Void> calculusFactorial(int factorial) {
        return CompletableFuture.runAsync(()->memoryOperation.factorial(factorial));
    }

    @Override
    public CompletableFuture<Void> calculusFibonacci(int fibonacci) {
        return CompletableFuture.runAsync(()->memoryOperation.fibonacci(fibonacci));
    }
}
