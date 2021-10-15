package com.readwordcode2.countword.implementation

import com.readwordcode2.countword.contract.ReadDirectory
import com.readwordcode2.countword.util.{AbstractExecutionContext, InfoWordCount}
import com.readwordcode2.factory.SubscriberFactory

import java.io.File
import scala.annotation.tailrec
import scala.concurrent.Future
import scala.language.postfixOps

class ReadDirectoryImp(infoWordCount: InfoWordCount) extends AbstractExecutionContext with ReadDirectory{
  private val factory = SubscriberFactory
  override def readDirectory(): Future[Option[Long]] = readDirectory(new File("C:\\"))

  override def readDirectory(folder:File): Future[Option[Long]] = {
    var count = 0;
    var count_file = 0;
    def ReadAllRecursiveTask(paths: List[File]):Future[Option[Long]]= {
     val result = paths.map(path => {
       count = count + 1
       try {
         path match {
           case route if route.isDirectory =>
             Future {
               ReadAllRecursiveTask(route.listFiles() toList)
             }.flatten
           case route if route.getAbsolutePath.endsWith(".txt") =>
             println(s"num thread ${java.lang.Thread.activeCount()} coun files $count_file directory run $count")
             count_file = count_file + 1
             Future {SubscriberFactory.getReadFile(route,infoWordCount).readFile()}.flatten
           case _ => Future.successful(Option(1L))

         }
       }
       catch {
         case _: Throwable => Future.successful(Option(1L));
       }
     })
      Future.sequence(result).map(values=>values.flatten.reduceOption(_+_))
    }
      ReadAllRecursiveTask(folder.listFiles() toList)
    }
}
