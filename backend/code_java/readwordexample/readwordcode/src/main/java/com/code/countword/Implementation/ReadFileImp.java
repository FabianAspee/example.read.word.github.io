package com.code.countword.Implementation;

import com.code.countword.util.InfoWordCount;
import com.code.factory.SubscriberFactory;
import com.code.factory.SubscriberFactoryImp;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReadFileImp extends RecursiveTask<Long> {
    @Serial
    private static final long serialVersionUID = 1L;
    private final SubscriberFactory factory = SubscriberFactoryImp.getInstance();
    private final File files;
    private static final long NUMBER_LINES=100;
    private static final int DEFAULT_TASK=1;
    private static final int TASK=1;
    private final InfoWordCount infoWordCount;

    public ReadFileImp(File file, InfoWordCount infoWordCount) {
        this.files=file;
        this.infoWordCount = infoWordCount;
    }
    private BufferedReader getContentFile() throws FileNotFoundException {
        return new BufferedReader(new FileReader(files));
    }

    private long forkFile(long countLines, List<String> lines){
        int fromIndex=0;
        int toIndex;
        int toIndexAux;
        int numTasks= (int)Math.ceil((double)countLines/ReadFileImp.NUMBER_LINES);
        toIndex=(int)countLines/numTasks;
        toIndexAux=toIndex;
        List<RecursiveTask<Long>> fork = new LinkedList<>();
        for(int iTask = 0;iTask< numTasks;iTask++) {
            CountWordImp counter = factory.getCountWord(lines.subList(fromIndex, toIndex),files,iTask,numTasks,infoWordCount);
            fork.add(counter);
            counter.fork();
            fromIndex=toIndex;
            toIndex=toIndex+toIndexAux;
            if(iTask==(numTasks-2))toIndex=(int)countLines;
        }
        return fork.stream().map((Function<RecursiveTask<Long>, Object>) ForkJoinTask::join).count();
    }

    private Long readAllFile() throws IOException {
        long size = this.files.length();
        BufferedReader read = getContentFile();
        List<String> lines  = read.lines().collect(Collectors.toList());
        read.close();
        long countLines = lines.size();
        if(countLines!=0) {
            if(countLines>100 || size > 500000) {
                return forkFile(countLines, lines);
            }else {
                CountWordImp counter = factory.getCountWord(lines,files,DEFAULT_TASK,TASK,infoWordCount);
                counter.fork();
                return counter.join();
            }
        }
        else {
            return 0L;
        }
    }

    @Override
    protected Long compute() {
        try {
            return readAllFile();
        } catch (IOException e) {
            e.printStackTrace();
            return 0L;
        }
    }
}
