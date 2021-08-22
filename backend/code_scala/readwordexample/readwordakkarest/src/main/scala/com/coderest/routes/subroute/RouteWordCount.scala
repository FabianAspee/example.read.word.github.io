package com.coderest.routes.subroute

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{get, complete, onComplete}
import akka.http.scaladsl.server.Route
import com.coderest.controller.WordCountController
import com.coderest.routes.exception.SuccessAndFailure.anotherSuccessAndFailure
import com.coderest.util.JsonFormats._
import com.coderest.util.Response

import scala.util.Success

object RouteWordCount{

  def wordCountExampleSubRoute(): Route = get {
    onComplete(WordCountController.readWordExample()){
      case Success(_)=>complete((StatusCodes.Continue,Response(
        StatusCodes.Continue.intValue,Some(StatusCodes.Continue.intValue))))
      case t => anotherSuccessAndFailure(t)
    }
  }
}
