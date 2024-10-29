FROM eclipse-temurin:23-jdk-alpine AS compressor
WORKDIR /app
COPY target/*.jar /app/publisher-service.jar
RUN jar xf publisher-service.jar
RUN jdeps --ignore-missing-deps -q  \
    --recursive  \
    --multi-release 23  \
    --print-module-deps  \
    --class-path 'BOOT-INF/lib/*'  \
    publisher-service.jar > deps.info
RUN jlink \
    --add-modules $(cat deps.info) \
    --strip-debug \
    --compress 2 \
    --no-header-files \
    --no-man-pages \
    --output /compressed-jre

FROM alpine:3.20.3
ENV JAVA_HOME=/user/java/jdk-23
ENV PATH=$JAVA_HOME/bin:$PATH
COPY --from=compressor /compressed-jre $JAVA_HOME
WORKDIR /app
COPY --from=compressor /app/publisher-service.jar /app
EXPOSE 24110
ENTRYPOINT ["java", "-jar", "publisher-service.jar"]