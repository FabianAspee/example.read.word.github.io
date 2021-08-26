package com.readwordcode2.factory

import com.readwordcode2.countword.implementation.CountWordImp
import com.readwordcode2.countword.implementation.ReadDirectoryImp
import com.readwordcode2.countword.implementation.ReadFileImp
import com.readwordcode2.countword.util.InfoWordCount

import java.io.File
sealed trait SubscriberFactory {


  def getReadDirectory(callRabbit: String=>Unit): ReadDirectoryImp

  def getReadFile(file: File, infoWordCount: InfoWordCount): ReadFileImp

  def getCountWord(task:Int, numTask:Int, infoWordCount: InfoWordCount): CountWordImp
}

object SubscriberFactory extends SubscriberFactory{
  override def getReadDirectory(callRabbit: String => Unit): ReadDirectoryImp = new ReadDirectoryImp(new InfoWordCount(5,10,callRabbit))

  override def getReadFile(file: File, infoWordCount: InfoWordCount): ReadFileImp = new ReadFileImp(file,infoWordCount)

  override def getCountWord(task: Int, numTask: Int,infoWordCount: InfoWordCount): CountWordImp =
    new CountWordImp(task, numTask, infoWordCount)
}
