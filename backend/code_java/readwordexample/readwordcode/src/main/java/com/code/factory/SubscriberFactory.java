package com.code.factory;

import com.code.countword.Implementation.CountWordImp;
import com.code.countword.Implementation.ReadDirectoryImp;
import com.code.countword.Implementation.ReadFileImp;
import com.code.countword.util.InfoWordCount;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;

public interface SubscriberFactory {

    ReadDirectoryImp getReadDirectory(Consumer<String> callRabbit);

    ReadFileImp getReadFile(File file, InfoWordCount infoWordCount);

    CountWordImp getCountWord(List<String> lines, File file, int task, int numTask, InfoWordCount infoWordCount);
}
