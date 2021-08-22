package com.coderest.routes

import akka.http.scaladsl.server.Route
import ch.megard.akka.http.cors.scaladsl.CorsDirectives.cors
import com.coderest.routes.master.MasterRouteWordCount.routeWordCount
import akka.http.scaladsl.server.Directives.concat
import com.coderest.swagger.SwaggerDocService

object RoutesServer{
    val route: Route = cors() (concat(routeWordCount,SwaggerDocService.routes))

}
