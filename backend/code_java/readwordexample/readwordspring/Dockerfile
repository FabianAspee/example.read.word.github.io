FROM gradle:7-jdk16-hotspot AS build_image

WORKDIR /home/gradle/src
COPY ["backend/code_java/readwordexample/settings.gradle", "/home/gradle/src"]
COPY --chown=gradle:gradle backend/code_java/readwordexample/readwordcode /home/gradle/src/readwordcode
COPY --chown=gradle:gradle backend/code_java/readwordexample/readwordspring /home/gradle/src/readwordspring

RUN gradle clean
RUN gradle build --stacktrace --no-daemon

# actual container
FROM openjdk:16-alpine
ENV ARTIFACT_NAME='readwordspring-0.0.1-SNAPSHOT.jar'
ENV APP_HOME=/usr/app

WORKDIR $APP_HOME
COPY --from=build_image /home/gradle/src/readwordspring/build/libs/$ARTIFACT_NAME .
EXPOSE 8080
ENV mode=-g
ENV host=localhost
ENTRYPOINT exec java -jar ${ARTIFACT_NAME} ${mode} ${host}

# for build this dockerfile -> docker build -t name -f path_from_call