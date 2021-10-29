package com.example.app.functionalinterface;

import java.io.IOException;

/**
 * #project realtimedatabase
 *
 * @author aspeeencinaf and 29/10/21
 **/
@FunctionalInterface
public interface FunctionMathematicalWithTimer<T, R> {
    R apply(T t) throws InterruptedException;
}

