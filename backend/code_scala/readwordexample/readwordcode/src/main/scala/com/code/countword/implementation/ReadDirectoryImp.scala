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
        println("case 1", count)
        val future = factory.getReadFile(head, infoWordCount).readFile()
        _readDirectory(tail, listFuture:+future, count+1)
      case head::tail if head.isDirectory && Option(head.listFiles).nonEmpty=>
        println("case 2", count)
        _readDirectory(tail ::: head.listFiles.toList, listFuture, count+1)
      case head::tail if head.isDirectory && Option(head.listFiles).isEmpty=>
        println("case 3", count)
        _readDirectory(tail, listFuture, count+1)
      case head::_ if head.isDirectory && Option(head.listFiles).nonEmpty=>
        println("case 4", count)
        _readDirectory(head.listFiles toList, listFuture, count+1)
      case head::_ if head.getAbsolutePath.endsWith(".txt")=>
        println("case 5", count)
        val future = factory.getReadFile(head,infoWordCount).readFile()
        Future.successful(listFuture:+future)

      case head::tail if head.isFile=>
        println("case 6", count)
        val future = factory.getReadFile(head,infoWordCount).readFile()
        _readDirectory(tail, listFuture:+future, count+1)
      case head::tail if !head.getAbsolutePath.endsWith(".txt")=>
        println("case 7", count)
        _readDirectory(tail, listFuture, count+1)
      case _=>
        println("case final", count)
        Future.successful(listFuture)
    }
   _readDirectory(folder.listFiles toList).map(x=>Future.sequence(x)).flatten
      .flatMap(value=> Future.successful(Option(value.flatten.sum)))
  }
}
