package example;

import example.com.akkaimpl.ClusterMain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class GeneralMain {
    public static void main(String[] args){
        pipo();
        //ClusterMain.main(args);
    }
    static Consumer<String> metodino = System.out::println;
    static Function<String, Function<String,String[]>> juan = juan2-> juan2::split;

    static BiConsumer<String,Consumer<String>> biConsumerBiConsumer = (s, stringConsumerBiConsumer)->
            stringConsumerBiConsumer.accept(String.join("", juan.apply(s).apply("")));


    static BiConsumer<String, Consumer<String>> auxMetodino = (s, stringConsumer) -> stringConsumer.accept(s);
    static BiConsumer<List<String>,Consumer<String>> e = (strings, stringConsumer) -> strings.forEach(stringConsumer);

    static Function<String,Function<String, List<String>>> getAllTurbine= session -> query->
            Arrays.stream(session.split(query)).toList();


    static BiConsumer<String,BiConsumer<String,Consumer<String>>> splitWord = (s, stringConsumerBiConsumer) -> stringConsumerBiConsumer.accept(s,metodino);


    public static void pipo(){

        auxMetodino = (String a, Consumer<String> e)->e.accept(a);
        splitWord.accept("juanito",auxMetodino);
        var e  = getAllTurbine.apply("11").apply("");
        var w = juan.apply("juanito").apply("");
        Arrays.stream(juan.apply("juanito").apply("")).forEach(metodino);

    }
}
