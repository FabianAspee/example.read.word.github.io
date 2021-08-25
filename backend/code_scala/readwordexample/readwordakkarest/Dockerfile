FROM hseeberger/scala-sbt:16.0.1_1.5.5_2.13.6 AS build_image
WORKDIR /home/sbt/src
COPY ["backend/code_scala/readwordexample/build.sbt", "/home/sbt/src"]
COPY ["backend/code_scala/readwordexample/project/build.properties", "/home/sbt/src/project/"]
COPY ["backend/code_scala/readwordexample/project/plugins.sbt", "/home/sbt/src/project/"]
COPY --chown=sbt:sbt backend/code_scala/readwordexample/readwordcode /home/sbt/src/readwordcode
COPY --chown=sbt:sbt backend/code_scala/readwordexample/readwordakkarest /home/sbt/src/readwordakkarest

RUN sbt clean
RUN sbt "project readwordcode" compile 
RUN sbt "project readwordakkarest" compile 
RUN sbt "project readwordakkarest" assembly 

# actual container
FROM openjdk:16-alpine
ENV ARTIFACT_NAME='akka-rest-server-scala.jar'
ENV APP_HOME=/usr/app

WORKDIR $APP_HOME
COPY --from=build_image /home/sbt/src/readwordakkarest/target/$ARTIFACT_NAME .
EXPOSE 8000
ENV mode=-g
ENV host=localhost
ENTRYPOINT exec java -jar ${ARTIFACT_NAME} ${mode} ${host}

# for build this dockerfile -> docker build -t name -f path_from_call