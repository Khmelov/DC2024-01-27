FROM eclipse-temurin:21-jdk-alpine AS compressor
WORKDIR /app
COPY target/*.jar /app/discussion.jar
RUN jar xf discussion.jar
RUN jdeps --ignore-missing-deps -q  \
    --recursive  \
    --multi-release 21  \
    --print-module-deps  \
    --class-path 'BOOT-INF/lib/*'  \
    discussion.jar > deps.info
RUN jlink \
    --add-modules $(cat deps.info) \
    --strip-debug \
    --compress 2\
    --no-header-files \
    --no-man-pages \
    --output /compressed-jre

FROM alpine:3.20.3
ENV JAVA_HOME=/user/java/jdk-21
ENV PATH=$JAVA_HOME/bin:$PATH
COPY --from=compressor /compressed-jre $JAVA_HOME
WORKDIR /app
COPY --from=compressor /app/discussion.jar /app
EXPOSE 24130
ENTRYPOINT ["java", "-jar", "discussion.jar"]