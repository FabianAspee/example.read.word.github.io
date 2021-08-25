name := "scalaWordCount"

version := "1.0"

ThisBuild /scalaVersion := "2.13.6"
ThisBuild /crossPaths := false
ThisBuild /Test / parallelExecution := false

lazy val readwordcode = project.settings(
  name := "word-count-code-scala",
  mainClass := Some("com.code.Main"),
  scalacOptions ++= compilerOptions,
  assemblySettings
)

lazy val readwordakkagrpc = project.settings(
  name := "akka-grpc-server-scala", 
  libraryDependencies ++= Seq(
    libraries.akkaHttp,
    libraries.akkaActor,
    libraries.akkaStream,
    libraries.sprayJson
  ),
  scalacOptions ++= compilerOptions,
  assemblySettings
)

lazy val readwordakkarest = project.settings(
  Compile / mainClass := Some("com.coderest.Main"),
  name := "akka-rest-server-scala",
  mainClass := Some("com.coderest.Main"),
  libraryDependencies ++= Seq(
    libraries.rabbit,
    libraries.akkaHttp,
    libraries.akkaActor,
    libraries.akkaStream,
    libraries.sprayJson,
    libraries.swaggerAkkaHttp,
    libraries.swaggerScala ,
    libraries.swaggerEnum,
    libraries.jacksonModule,
    libraries.iterator,
    libraries.coreSwagger,
    libraries.coreAnnotations,
    libraries.modelSwagger,
    libraries.jaxrs2Swagger,
    libraries.megard,
    libraries.bcryp,
    libraries.javax
  ),
  scalacOptions ++= compilerOptions,
  assemblySettings
).dependsOn(readwordcode)

lazy val compilerOptions = Seq(
  "-encoding","utf8",
  "-explaintypes",
  "-deprecation",
  "-unchecked",
  "-Xsource:2.13.0",
  "-Ywarn-dead-code",
  "-language:postfixOps",
  "-Wunused:nowarn",
  "-language:implicitConversions",
  "-feature"
)


lazy val libraries = new {
  val akkaHttpVersion = "10.1.12"
  val akkaVersion     = "2.6.5"
  val logBackVersion  = "1.2.3"
  val rabbitVersion  = "5.9.0"
  val swaggerVersion ="2.1.1"
  val jacksonVersion = "2.11.0"
  val javaxVersion   ="2.0.1"
  val swaggerAkkaHttpVersion="2.1.0"
  val swaggerEnumeratunVersion = "2.0.0"
  val iteratorVersion = "1.7.1"
  val megarVersion    = "0.4.3"
  val bcrypVersion    ="0.3m"
  val akkaHttp       = "com.typesafe.akka"        %% "akka-http"                % akkaHttpVersion
  val akkaActor      ="com.typesafe.akka"         %% "akka-actor-typed"         % akkaVersion
  val akkaStream     = "com.typesafe.akka"        %% "akka-stream"              % akkaVersion
  val logBack        = "ch.qos.logback"           % "logback-classic"           % logBackVersion
  val sprayJson      = "com.typesafe.akka"        %% "akka-http-spray-json"     % akkaHttpVersion
  val rabbit         = "com.rabbitmq"              % "amqp-client"              % rabbitVersion
  val javax          ="javax.ws.rs"               % "javax.ws.rs-api"           % javaxVersion
  val swaggerAkkaHttp="com.github.swagger-akka-http" %% "swagger-akka-http"     % swaggerAkkaHttpVersion
  val swaggerScala   ="com.github.swagger-akka-http" %% "swagger-scala-module"  % swaggerVersion
  val swaggerEnum    ="com.github.swagger-akka-http" %% "swagger-enumeratum-module" % swaggerEnumeratunVersion
  val jacksonModule  = "com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion
  val iterator       ="pl.iterators" %% "kebs-spray-json"                       % iteratorVersion
  val coreSwagger    = "io.swagger.core.v3" % "swagger-core"                    % swaggerVersion
  val coreAnnotations ="io.swagger.core.v3" % "swagger-annotations"              % swaggerVersion
  val modelSwagger   ="io.swagger.core.v3" % "swagger-models"                   % swaggerVersion
  val jaxrs2Swagger  ="io.swagger.core.v3" % "swagger-jaxrs2"                   % swaggerVersion
  val megard         = "ch.megard"                %% "akka-http-cors"           % megarVersion
  val bcryp          ="org.mindrot"               %  "jbcrypt"                  % bcrypVersion
}
lazy val assemblySettings = Seq(
  assembly / assemblyJarName := name.value + ".jar",
  assembly / assemblyMergeStrategy := {
    case PathList("test", "resources", "application.conf") =>
      MergeStrategy.discard
    case "module-info.class" => MergeStrategy.discard
    case x =>
      val oldStrategy = (assembly / assemblyMergeStrategy).value
      oldStrategy(x)
  },
  scalacOptions ++= compilerOptions,
  cleanFiles += baseDirectory.value / "temp",
  assembly / test := {}
)