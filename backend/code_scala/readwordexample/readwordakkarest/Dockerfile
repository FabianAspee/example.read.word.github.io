FROM hseeberger/scala-sbt:8u302_1.5.5_2.13.6 AS build_image
WORKDIR /home/sbt/src
COPY ["backend/code_scala/readwordexample/build.sbt", "/home/sbt/src"]
COPY --chown=sbt:sbt backend/code_scala/readwordexample/readwordcode /home/sbt/src/readwordcode
COPY --chown=sbt:sbt backend/code_scala/readwordexample/readwordakkarest /home/sbt/src/readwordakkarest

RUN sbt clean
RUN sbt "project readwordcode" compile 
RUN sbt "project readwordakkarest" compile 

# actual container
FROM openjdk:16-alpine
ENV ARTIFACT_NAME='readwordakkarest-0.0.1-SNAPSHOT.jar'
ENV APP_HOME=/usr/app

WORKDIR $APP_HOME
COPY --from=build_image /home/sbt/src/readwordspring/build/libs/$ARTIFACT_NAME .
EXPOSE 8080
ENV mode=-g
ENV host=localhost
ENTRYPOINT exec java -jar ${ARTIFACT_NAME} ${mode} ${host}

# for build this dockerfile -> docker build -t name -f path_from_call