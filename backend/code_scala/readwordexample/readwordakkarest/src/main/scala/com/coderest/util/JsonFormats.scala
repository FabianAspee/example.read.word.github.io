package com.coderest.util

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, JsonFormat, RootJsonFormat}

object JsonFormats extends SprayJsonSupport with DefaultJsonProtocol{

  implicit def responseJsonFormat[V: JsonFormat]: RootJsonFormat[Response[V]] = jsonFormat2(Response.apply[V])

}
