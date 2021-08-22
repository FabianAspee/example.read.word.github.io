package com.coderest.swagger


import com.coderest.routes.master.MasterRouteWordCount
import com.github.swagger.akka.SwaggerHttpService
import com.github.swagger.akka.model.{Contact, Info}
import io.swagger.v3.oas.models.ExternalDocumentation


object SwaggerDocService extends SwaggerHttpService {
  override val apiClasses: Set[Class[_]] = Set(MasterRouteWordCount.getClass)
  override val host = "localhost:8080"
  override val apiDocsPath = "api-docs"
  override val info: Info = Info(description = "Information of all method availability for consume"
    ,"1.0","Read Word Count",contact = Option(Contact("Aspee Fabian","","fabian.aspeeencina@studio.unibo.it")))
  override val externalDocs: Option[ExternalDocumentation] = Some(new ExternalDocumentation().description("Find out more about").url("http://swagger.io"))
  override val schemes:List[String] = List("http")
  //override val securitySchemeDefinitions = Map("basicAuth" -> new BasicAuthDefinition())
  override val unwantedDefinitions = Seq("Function1", "Function1RequestContextFutureRouteResult")
}