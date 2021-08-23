package com.code.countword.util

import java.io.File
import java.util.concurrent.{ConcurrentHashMap, ConcurrentLinkedQueue, ConcurrentMap, ConcurrentSkipListMap}
import scala.concurrent.Future
import scala.jdk.CollectionConverters.CollectionHasAsScala

class InfoWordCount(lengthWord: Int, qtaWord: Int, print: String=>Unit) extends AbstractExecutionContext {
  private final case class InfoWord(string: String, total: Int){
    override def  toString: String = s"word= $string total= $total"
  }
  private val wordFrequency: ConcurrentMap[String, Int] = new ConcurrentHashMap[String, Int]
  private val fileFolder: ConcurrentSkipListMap[String, ConcurrentLinkedQueue[String]]  = new ConcurrentSkipListMap[String,ConcurrentLinkedQueue[String]]

  def merge(map: Map[String, Int], file: File, task:Int, numTask: Int):Unit={
    check(file).filter(_==0).foreach(_=>map.foreach(k=>
      wordFrequency.merge(k._1,k._2,(x,y)=>x+y)))
    sendWordCount()
  }
  def check(file: File):Option[Long]=
    Option(fileFolder.entrySet().stream().filter(map=>map.getValue.contains(file.getAbsolutePath))
      .map(value=> (value.getKey,value.getValue)).count)

  def sendWordCount():Unit={
    val result = wordFrequency.entrySet().asScala.map(value=>value.getKey->value.getValue)
      .toSeq.sortWith(_._2>_._2)
    val r = result.take(qtaWord).map(value=>InfoWord(value._1,value._2)).mkString("\n")
    print(r)
  }
  def getLengthWord: Int = lengthWord
}
