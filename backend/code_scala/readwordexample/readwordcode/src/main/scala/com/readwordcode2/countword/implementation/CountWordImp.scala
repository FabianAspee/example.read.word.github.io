package com.readwordcode2.countword.implementation

import com.readwordcode2.countword.contract.CountWord
import com.readwordcode2.countword.util.{AbstractExecutionContext, InfoWordCount}

import java.io.File
import scala.concurrent.Future

class CountWordImp(task:Int, numTask:Int, infoWordCount: InfoWordCount) extends AbstractExecutionContext with CountWord{
  override def countWordFork(lines: List[String], file: File):Future[Option[Long]]=Future{
    lines.flatMap(line=>wordsIn(line).filter(word=>word.length>=infoWordCount.getLengthWord))
      .filter(word=>word.nonEmpty).groupBy(value=>value).map(value=>value._1->value._2.size)}.map(result=>{
      infoWordCount.merge(result,file, task, numTask)
      Option(1L)
  })

  private def wordsIn(line: String): Array[String] =
    line.trim.split("(\\s|\\p{Punct})+")


}
