package com.readwordcode2.countword.contract

import scala.concurrent.Future

trait ReadFile {
  def readFile():Future[Option[Long]]
}
