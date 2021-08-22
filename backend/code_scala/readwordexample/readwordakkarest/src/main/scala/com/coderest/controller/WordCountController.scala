package com.coderest.controller

import com.coderest.rabbit.Sender
import com.code.factory.SubscriberFactory

import scala.concurrent.Future

sealed trait WordCountController{
  def readWordExample():Future[Option[Long]]
}
object WordCountController extends WordCountController{

  override def readWordExample():Future[Option[Long]] =
    SubscriberFactory.getReadDirectory(Sender.sendViaDirectExchange).readDirectory()

}
