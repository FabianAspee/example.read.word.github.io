package com.coderest

import akka.actor.typed.ActorSystem
import com.coderest.ServerConf.StartServer

object Main{
  def main(args: Array[String]): Unit = {
    ActorSystem[StartServer](ServerConf(), "AkkaHttpServer")
  }
}
