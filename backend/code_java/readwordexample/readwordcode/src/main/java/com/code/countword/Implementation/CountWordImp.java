package com.code.countword.Implementation;

import com.code.countword.util.InfoWordCount;

import java.io.File;
import java.io.Serial;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.RecursiveTask;

import static java.util.stream.Collectors.toMap;

public class CountWordImp extends RecursiveTask<Long> {
    @Serial
    private static final long serialVersionUID = 1L;
    private final List<String> lines;
    private final File file;
    private final InfoWordCount infoWordCount;
    private final int numTask;
    private final int task;
    public CountWordImp(List<String> lines, File file, int task, int numTask, InfoWordCount infoWordCount){
        this.lines=lines;
        this.file=file;
        this.numTask=numTask;
        this.task=task;
        this.infoWordCount=infoWordCount;
    }

    @Override
    protected Long compute() {
        Map<String, Integer> wordCount = lines.stream().flatMap(line -> Arrays.stream(wordsIn(line))
                .filter(word -> word.length() >= this.infoWordCount.getLengthWord()))
                .filter(word -> word.length() > 0)
                .map(word -> new AbstractMap.SimpleEntry<>(word, 1))
                .collect(toMap(Map.Entry::getKey,Map.Entry::getValue, Integer::sum));
        this.infoWordCount.merge(wordCount, this.file,this.task,this.numTask);
        return 1L;
    }
    private String[] wordsIn(String line) {
        String[] s = line.trim().split("(\\s|\\p{Punct})+");
        return line.trim().split("(\\s|\\p{Punct})+");

    }

}
