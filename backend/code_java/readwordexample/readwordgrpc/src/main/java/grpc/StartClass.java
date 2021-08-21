package grpc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class StartClass {
    public static void main(String[] ar){
        var lista = List.of(1,2,3,4);
        var e = lista.stream().limit(5).collect(Collectors.toList());
        var e2 = lista.stream().limit(50).collect(Collectors.toList());

        Optional<Integer> s = Optional.empty();
        Optional<Integer> ee = Stream.of(1, 2, 3, 4, 5).filter(x-> false).findFirst();
        System.out.println(ee);
        var xs = Optional.of(s.map(x->Optional.of(1)).orElseGet(()->{
            if(1== new Random().nextInt()) {
                return Optional.of(3);
            }
            return Optional.empty();
        })).orElse(Optional.of(12));

        System.out.println(xs);
    }
}
