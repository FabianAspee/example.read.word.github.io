package com.code.countword.Implementation;

import com.code.countword.util.InfoWordCount;
import com.code.factory.SubscriberFactory;
import com.code.factory.SubscriberFactoryImp;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.RecursiveTask;

public class ReadDirectoryImp extends RecursiveTask<Long>{
    private final InfoWordCount infoWordCount;
    private final SubscriberFactory factory = SubscriberFactoryImp.getInstance();
    public ReadDirectoryImp(InfoWordCount infoWordCount) {
        this.infoWordCount = infoWordCount;
    }

    private Long readDirectoryRecursive(File folder){
        long counter=1L;
        List<RecursiveTask<Long>> task = new ArrayList<>();
       /*
        File[] files = folder.listFiles();
        if(files!=null ) {
            Arrays.stream(files).forEach(t->{
                if(t.isDirectory() && t.canRead())
                {
                    readDirectoryRecursive(t);

                }else{
                    if(t.getAbsolutePath().endsWith(".txt"))
                    {
                        System.out.println(t.getName());
                        ReadFileImp fileImp = factory.getReadFile(t,this.infoWordCount);
                        task.add(fileImp);
                        fileImp.fork();
                        for (RecursiveTask<Long> tas : task) {
                            tas.join();
                        }
                    }
                }});

            return counter;
        }else if(folder.isFile() && folder.getAbsolutePath().endsWith(".txt")) {
            ReadFileImp fileImp = factory.getReadFile(folder,this.infoWordCount);
            task.add(fileImp);
            fileImp.fork();
            for (RecursiveTask<Long> tas : task) {
                tas.join();
            }
            return counter;
        }else {
            return counter;
        }*/
        return Optional.of(folder).map(File::listFiles).map(files->{
            Arrays.stream(files).forEach(file->{
                if(file.isDirectory() && file.canRead()) {
                    readDirectoryRecursive(file);

                }else if(file.getAbsolutePath().endsWith(".txt")) {
                    taskAndMethod(task,factory.getReadFile(file,this.infoWordCount));
                }
            });
            return Optional.of(counter);
        }).orElseGet(()->{
            if(folder.isFile()){
                taskAndMethod(task,factory.getReadFile(folder,this.infoWordCount));
                return Optional.of(counter);
            }
            return Optional.empty();
        }).orElse(counter);

    }
    private void taskAndMethod(List<RecursiveTask<Long>> task, ReadFileImp readFileImp){
        task.add(readFileImp);
        readFileImp.fork();
        task.forEach(RecursiveTask::join);
    }
    @Override
    protected Long compute() {
        return readDirectoryRecursive(new File("C:\\"));
    }
}
