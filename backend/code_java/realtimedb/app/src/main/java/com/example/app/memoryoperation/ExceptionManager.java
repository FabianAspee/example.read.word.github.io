package com.example.app.memoryoperation;

/**
 * #project realtimedatabase
 *
 * @author aspeeencinaf and 29/10/21
 **/

import com.example.app.functionalinterface.FunctionMathematicalWithTimer;
import org.apache.commons.lang3.tuple.Triple;
import org.apache.commons.math3.util.Pair;

public class ExceptionManager {
    public static int execFunction(FunctionMathematicalWithTimer<Pair<Integer,Integer>,Integer> function,int first, int second){
        try {
            return function.apply(Pair.create(first,second));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int execFunction(FunctionMathematicalWithTimer<Triple<Integer,Integer,Integer>,Integer> function, int first, int second,int third){
        try {
            return function.apply(Triple.of(first,second,third));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
