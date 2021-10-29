package com.example.app.memoryoperation;

import com.example.app.functionalinterface.FunctionMathematicalWithTimer;
import com.example.app.vertx.implvertx.ImplVertx;
import org.apache.commons.lang3.tuple.Triple;
import org.apache.commons.math3.util.Pair;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static org.apache.coyote.http11.Constants.a;

/**
 * #project realtimedatabase
 *
 * @author aspeeencinaf and 29/10/21
 **/
@Component
public class MemoryOperationImpl extends ExceptionManager implements MemoryOperation {

    static final FunctionMathematicalWithTimer<Pair<Integer,Integer>,Integer> function = MemoryOperationImpl::factorial;
    static final FunctionMathematicalWithTimer<Triple<Integer,Integer,Integer>,Integer> functionT = MemoryOperationImpl::fibonacci;
    @Override
    public void factorial(int factorial){
        ImplVertx.getInstance().sendMessage("Init calculus factorial: "+factorial);
        ImplVertx.getInstance().sendMessage("Final factorial is: "+execFunction(function,factorial,1));
    }
    private static int factorial(Pair<Integer, Integer> integerIntegerPair) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        ImplVertx.getInstance().sendMessage("calculus factorial: "+integerIntegerPair.getFirst());
        return  integerIntegerPair.getFirst()==0 || integerIntegerPair.getFirst()==1?integerIntegerPair.getSecond():
                factorial(Pair.create(integerIntegerPair.getFirst()-1,integerIntegerPair.getSecond()*integerIntegerPair.getFirst()));
    }
    @Override
    public void fibonacci(int fibonacci) {
        ImplVertx.getInstance().sendMessage("Init calculus fibonacci: "+fibonacci);
        ImplVertx.getInstance().sendMessage("Final factorial is: "+execFunction(functionT,fibonacci,0,1));
    }
    private static int fibonacci(Triple<Integer,Integer,Integer> triple) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        ImplVertx.getInstance().sendMessage("calculus factorial: "+triple.getLeft());
        return triple.getLeft()==0?triple.getMiddle():(triple.getLeft()==1?triple.getRight():fibonacci(Triple.of(triple.getLeft()
                - 1, triple.getRight(), triple.getMiddle() + triple.getRight())));

    }
}
