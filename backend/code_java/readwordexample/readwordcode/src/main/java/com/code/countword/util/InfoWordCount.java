package com.code.countword.util;

import java.io.File;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class InfoWordCount {
    private record InfoWord(String word, Integer total){
        @Override
        public String toString() {
            return "word='" + word + '\'' +
                    ", total=" + total;
        }
    };
    private final ConcurrentHashMap<String,Integer> wordFrequency = new ConcurrentHashMap<>();
    public ConcurrentSkipListMap<String, ConcurrentLinkedQueue<String>> fileFolder = new ConcurrentSkipListMap<>();
    private final int lengthWord;
    private final int qtaWord;
    private final Consumer<String> callRabbit;
    public InfoWordCount(final int lengthWord, final int qtaWord, Consumer<String> callRabbit) {
        this.lengthWord=lengthWord;
        this.qtaWord = qtaWord;
        this.callRabbit = callRabbit;
    }

    public void merge(Map<String, Integer> wordCount, File file, int task, int numTask) {
        check(file).filter(value->value==0).ifPresent(x->
            wordCount.forEach((k, v) ->  this.wordFrequency.merge(k, v,  Integer::sum)));
        sendWordCount();
    }
    public void sendWordCount(){
        CompletableFuture.runAsync(()->{
            var result =  this.wordFrequency
                    .entrySet().stream()
                    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                    .collect(
                            toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                    LinkedHashMap::new)).entrySet().stream().limit(this.qtaWord)
                    .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,LinkedHashMap::new));
            this.callRabbit.accept(result.entrySet().stream().map(value->new InfoWord(value.getKey(),value.getValue())
                    .toString()).collect(Collectors.joining("\n")));
        });
    }
    private Optional<Integer> check(File file) {
        return Optional.of(this.fileFolder.entrySet()
                .stream().filter(map->map.getValue().contains(file.getAbsolutePath()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue)).size());
    }

    public int getLengthWord() {
        return lengthWord;
    }
}
