package com.readwordcode2.countword.implementation

import com.readwordcode2.countword.contract.ReadFile
import com.readwordcode2.countword.util.{AbstractExecutionContext, InfoWordCount}
import com.readwordcode2.factory.SubscriberFactory

import java.io.{BufferedReader, File, FileNotFoundException, FileReader}
import scala.annotation.tailrec
import scala.concurrent.Future
import scala.jdk.CollectionConverters.CollectionHasAsScala
import scala.language.postfixOps

class ReadFileImp(file: File, infoWordCount: InfoWordCount) extends AbstractExecutionContext with ReadFile{
  private val factory = SubscriberFactory

  private val NUMBER_LINES = 100
  private val DEFAULT_TASK = 1
  private val TASK = 1
  override def readFile(): Future[Option[Long]] = Future{
      readAllFile()
    }.flatten

  @throws[FileNotFoundException]
  private def getContentFile = new BufferedReader(new FileReader(file))

  private def readAllFile():Future[Option[Long]]={
    val sizeFile = file.length
    Future{getContentFile}.map({
      read =>
        val newRes = read.lines.toList
        read.close()
        newRes
    }).collect({
      case lines if lines.size()>0=>
        val countLines = lines.size
        countLines match {
          case qtaLines if qtaLines>0 && (countLines>100 || sizeFile > 500000)=>
            forkFile(countLines,lines.asScala toList)
          case qtaLines if qtaLines>0 =>factory.getCountWord(DEFAULT_TASK,TASK, infoWordCount)
            .countWordFork(lines.asScala toList,file)
          case _ =>Future.successful(Option(0L))

      case _ =>Future.successful(Option(0L))
      }
    }).flatten
}
  private def forkFile(countLines: Int, lines: List[String]):Future[Option[Long]]=Future{
    val numTasks:Int = Math.ceil(countLines/NUMBER_LINES) toInt
    val toIndex = countLines/numTasks
    @tailrec
    def _callCountWord(toIndexAux:Int, toIndex: Int=0, task: Int=0, fromIndex:Int=0):Future[Option[Long]]= task match {
      case iTask if iTask<numTasks && iTask==(numTasks-2)=>
        callCountWord(lines,fromIndex,toIndex,iTask,numTasks)
        _callCountWord(toIndex,countLines,task+1,toIndex)
      case iTask if iTask<numTasks=>
        callCountWord(lines,fromIndex,toIndex,iTask,numTasks)
        _callCountWord(toIndexAux,toIndexAux+toIndex,task+1,toIndex)
      case _ => Future.successful(Option(1))
    }
    _callCountWord(toIndex ,toIndex)
  }.flatten

  private def callCountWord(lines: List[String], fromIndex:Int, toIndex:Int, iTask:Int, numTasks:Int): Future[Option[Long]] = {
    Future{
      factory.getCountWord(iTask,numTasks, infoWordCount).countWordFork(lines.slice(fromIndex, toIndex),file)
    }.flatten
  }
}
