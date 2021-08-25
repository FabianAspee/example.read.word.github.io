package com.coderest

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.scaladsl.adapter._
import akka.actor.typed.{ActorSystem, Behavior}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import com.coderest.routes.RoutesServer.route

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

private object ServerConf {
  final case class StartServer()

  private val interface = "0.0.0.0"
  private val port = 8000

  def apply(): Behavior[StartServer] =
    Behaviors.setup {
      context =>
        startHttpServer(route, context.system)
        Behaviors.empty
    }

  def startHttpServer(routes: Route, system: ActorSystem[_]): Unit = {
    // Akka HTTP still needs a classic ActorSystem to start
    implicit val classicSystem: akka.actor.ActorSystem = system.toClassic
    print("test print")
    Http().bindAndHandle(routes, interface, port).onComplete {
      case Success(binding) =>
        val address = binding.localAddress
        print("Server onlines at http://{}:{}/", address.getHostString, address.getPort)
        system.log.info("Server online at http://{}:{}/", address.getHostString, address.getPort)
      case Failure(ex) =>
        print("error onlinew at http://{}:{}/", ex)
        system.log.error("Failed to bind HTTP endpoint, terminating system", ex)
        system.terminate()
    }
  }

}
