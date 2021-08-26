package com.code.countword.implementation

import com.code.countword.contract.ReadDirectory
import com.code.countword.util.{AbstractExecutionContext, InfoWordCount}
import com.code.factory.SubscriberFactory

import java.io.File
import scala.annotation.tailrec
import scala.concurrent.Future
import scala.language.postfixOps

class ReadDirectoryImp(infoWordCount: InfoWordCount) extends AbstractExecutionContext with ReadDirectory{
  private val factory = SubscriberFactory
  override def readDirectory(): Future[Option[Long]] = readDirectory(new File("C:\\"))

  override def readDirectory(folder:File): Future[Option[Long]] = {

    @tailrec
    def _readDirectory(files: List[File], listFuture:List[Future[Option[Long]]]=List.empty, count: Int=1):Future[List[Future[Option[Long]]]]= files match {
      case head::tail if head.getAbsolutePath.endsWith(".txt")=>
        println(s"case 1 count: $count name file ${head.getName} num-thread: ${java.lang.Thread.activeCount()}")
        val future = factory.getReadFile(head, infoWordCount).readFile()
        _readDirectory(tail, listFuture:+future, count+1)
      case head::tail if head.isDirectory && Option(head.listFiles).nonEmpty=>
        //println(s"case 2 count: $count name file ${head.getName} num-thread: ${java.lang.Thread.activeCount()}")
        val e = tail ::: head.listFiles.toList
        _readDirectory(tail ::: head.listFiles.toList, listFuture, count+1)
      case head::tail if head.isDirectory && Option(head.listFiles).isEmpty=>
        //println(s"case 3  count: $count name file ${head.getName} num-thread: ${java.lang.Thread.activeCount()}")
        _readDirectory(tail, listFuture, count+1)
      case head::_ if head.isDirectory && Option(head.listFiles).nonEmpty=>
        //println(s"case 4 count: $count name file ${head.getName} num-thread: ${java.lang.Thread.activeCount()}")
        _readDirectory(head.listFiles toList, listFuture, count+1)
      case head::_ if head.getAbsolutePath.endsWith(".txt")=>
        //println(s"case 5 count: $count name file ${head.getName} num-thread: ${java.lang.Thread.activeCount()}")
        val future = factory.getReadFile(head,infoWordCount).readFile()
        Future.successful(listFuture:+future)
      case head::tail if !head.getAbsolutePath.endsWith(".txt")=>
        //println(s"case 6 count: $count name file ${head.getName} num-thread: ${java.lang.Thread.activeCount()}")
         _readDirectory(tail, listFuture, count+1)
      case _=>
        println(s"case final count: $count num-thread: ${java.lang.Thread.activeCount()}")
        Future.successful(listFuture)
    }
   _readDirectory(folder.listFiles toList).map(x=>Future.sequence(x)).flatten
      .flatMap(value=> Future.successful(Option(value.flatten.sum)))
  }
}
