package com.coderest.routes.exception

import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute
import com.coderest.util.Response
import com.coderest.util.JsonFormats._
import scala.util.{Failure, Success, Try}

object SuccessAndFailure {
  val timeoutResponse: HttpResponse = HttpResponse(
    StatusCodes.EnhanceYourCalm,
    entity = "Unable to serve response within time limit, please enhance your calm.")
  def anotherSuccessAndFailure[A](result:Try[A]): StandardRoute =result match {
    case Success(None) => complete(StatusCodes.NotFound,Response[Int](StatusCodes.NotFound.intValue))
    case Success((None,_)) => complete(StatusCodes.NotFound,Response[Int](StatusCodes.NotFound.intValue))
    case Success(Some(List())| List())  =>    complete(StatusCodes.NotFound,Response[Int](StatusCodes.NotFound.intValue))
    case Success(Some(StatusCodes.NotFound)) =>    complete(StatusCodes.NotFound,Response[Int](StatusCodes.NotFound.intValue))
    case Success(Some(StatusCodes.BadRequest)) => complete(StatusCodes.BadRequest,Response[Int](StatusCodes.BadRequest.intValue))
    case Success(Some(StatusCodes.BadRequest)) => complete(StatusCodes.BadRequest,Response[Int](StatusCodes.BadRequest.intValue))
    case Success(Some(StatusCodes.BadRequest)) => complete(StatusCodes.BadRequest,Response[Int](StatusCodes.BadRequest.intValue))
    case Success(Some(StatusCodes.BadRequest)) => complete(StatusCodes.BadRequest,Response[Int](StatusCodes.BadRequest.intValue))
    case Success(Some(StatusCodes.BadRequest)) => complete(StatusCodes.BadRequest,Response[Int](StatusCodes.BadRequest.intValue))
    case Success(Some(StatusCodes.BadRequest)) => complete(StatusCodes.BadRequest,Response[Int](StatusCodes.BadRequest.intValue))
    case Success(Some(StatusCodes.BadRequest)) => complete(StatusCodes.BadRequest,Response[Int](StatusCodes.BadRequest.intValue))
    case Success(Some(StatusCodes.BadRequest)) => complete(StatusCodes.BadRequest,Response[Int](StatusCodes.BadRequest.intValue))
    case Success(Some(StatusCodes.BadRequest)) => complete(StatusCodes.BadRequest,Response[Int](StatusCodes.BadRequest.intValue))
    case Success(Some(StatusCodes.EnhanceYourCalm)) => complete(StatusCodes.EnhanceYourCalm,Response[Int](StatusCodes.EnhanceYourCalm.intValue))
    case Success(Some(StatusCodes.BadRequest)) => complete(StatusCodes.BadRequest,Response[Int](StatusCodes.BadRequest.intValue))
    case Success(Some(StatusCodes.BadRequest)) => complete(StatusCodes.BadRequest,Response[Int](StatusCodes.BadRequest.intValue))
    case Success(Some(StatusCodes.Continue)) => complete(StatusCodes.Continue,Response[Int](StatusCodes.Continue.intValue))
    case Success(Some(StatusCodes.Continue)) => complete(StatusCodes.Continue,Response[Int](StatusCodes.Continue.intValue))
    case Success(Some(StatusCodes.Continue)) => complete(StatusCodes.Continue,Response[Int](StatusCodes.Continue.intValue))
    case Success(Some(StatusCodes.Continue)) => complete(StatusCodes.Continue,Response[Int](StatusCodes.Continue.intValue))
    case t => failure(t)
  }

  private def failure[A](result:Try[A]): StandardRoute =result match {
    case Success(_) => complete(StatusCodes.InternalServerError,Response[Int](StatusCodes.InternalServerError.intValue))
    case Failure(_) => complete(StatusCodes.InternalServerError,Response[Int](StatusCodes.InternalServerError.intValue))
  }
}