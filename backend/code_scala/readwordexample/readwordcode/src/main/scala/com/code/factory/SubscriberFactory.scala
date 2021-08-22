package com.code.factory

import com.code.countword.implementation.CountWordImp
import com.code.countword.implementation.ReadDirectoryImp
import com.code.countword.implementation.ReadFileImp
import com.code.countword.util.InfoWordCount

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
