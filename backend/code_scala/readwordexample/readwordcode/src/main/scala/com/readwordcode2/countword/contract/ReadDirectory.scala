package com.readwordcode2.countword.contract

import java.io.File
import scala.concurrent.Future

trait ReadDirectory {

  def readDirectory(): Future[Option[Long]]

  def readDirectory(folder: File): Future[Option[Long]]
}
