package com.coderest.util


final case class Response[A](statusCode:Int,payload:Option[A]=None)
