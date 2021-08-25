package com.coderest

import akka.actor.typed.ActorSystem
import com.code.factory.SubscriberFactory
import com.coderest.ServerConf.StartServer
import com.coderest.rabbit.Sender

object Main{
  def main(args: Array[String]): Unit = {
      ActorSystem[StartServer](ServerConf(), "AkkaHttpServer")
  }
}
