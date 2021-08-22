package com.code.countword.contract

import scala.concurrent.Future

trait ReadFile {
  def readFile():Future[Option[Long]]
}
