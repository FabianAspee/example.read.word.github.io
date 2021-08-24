FROM gradle:7-jdk16-hotspot AS build_image

WORKDIR /home/gradle/src
COPY ["settings.gradle", "/home/gradle/src"]
COPY --chown=gradle:gradle / /home/gradle/src

RUN gradle clean
RUN gradle build --no-daemon

# actual container
FROM openjdk:16-alpine
ENV ARTIFACT_NAME='readwordspring-0.0.1-SNAPSHOT.jar'
ENV APP_HOME=/usr/app

WORKDIR $APP_HOME
COPY --from=build_image /home/gradle/src/build/libs/$ARTIFACT_NAME .
EXPOSE 8080
ENV mode=-g
ENV host=localhost
ENTRYPOINT exec java -jar ${ARTIFACT_NAME} ${mode} ${host}