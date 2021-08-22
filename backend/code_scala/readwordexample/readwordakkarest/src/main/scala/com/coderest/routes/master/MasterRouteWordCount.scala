package com.coderest.routes.master

import akka.http.scaladsl.server.{Directives, Route}
import com.coderest.routes.subroute.RouteWordCount.wordCountExampleSubRoute
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.{Content, Schema}
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse

import javax.ws.rs.core.MediaType
import javax.ws.rs.{Consumes, POST, Path, Produces}

trait MasterRouteWordCount {

  @Path("/api/readwordexample")
  @POST
  @Consumes(Array(MediaType.APPLICATION_JSON))
  @Produces(Array(MediaType.APPLICATION_JSON))
  @Operation(tags =Array("Read All word of txt files"),summary = "allow you seen all word inside txt file",
    description = "The txt file contains infinite word, this method search and visualized all word that " +
      "contains 5 word and visualized the first ten",
    requestBody = new RequestBody(content = Array(new Content(schema = new Schema(implementation = classOf[Int])))),
    responses = Array(
      new ApiResponse(responseCode = "201", description = "calculus init ok"),
      new ApiResponse(responseCode = "500", description = "Internal server error"))
  )
  def wordCountExample():Route
}

object MasterRouteWordCount  extends Directives with MasterRouteWordCount {
  override def wordCountExample(): Route = path("readwordexample") {
        wordCountExampleSubRoute()
  }
  val routeWordCount: Route =
    wordCountExample()
}