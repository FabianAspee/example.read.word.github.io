package com.code.countword.contract

import java.io.File
import scala.concurrent.Future

trait CountWord {
  def countWordFork(lines: List[String], file:File):Future[Option[Long]]
}
