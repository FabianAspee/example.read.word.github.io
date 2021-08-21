package com.code.factory;

import com.code.countword.Implementation.CountWordImp;
import com.code.countword.Implementation.ReadDirectoryImp;
import com.code.countword.Implementation.ReadFileImp;
import com.code.countword.util.InfoWordCount;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;

public class SubscriberFactoryImp implements SubscriberFactory{
    private static final SubscriberFactory instance = new SubscriberFactoryImp();
    private SubscriberFactoryImp(){}

    @Override
    public ReadDirectoryImp getReadDirectory(Consumer<String> callRabbit) {
        return new ReadDirectoryImp(new InfoWordCount(5,10, callRabbit));
    }

    @Override
    public ReadFileImp getReadFile(File file, InfoWordCount infoWordCount) {
        return new ReadFileImp(file, infoWordCount);
    }

    @Override
    public CountWordImp getCountWord(List<String> lines, File file, int task, int numTasks, InfoWordCount infoWordCount) {
        return new CountWordImp(lines, file,task,numTasks, infoWordCount);
    }

    public static SubscriberFactory getInstance(){
        return instance;
    }
}
