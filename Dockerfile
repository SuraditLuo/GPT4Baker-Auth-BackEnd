FROM openjdk:18
RUN addgroup -S spring && adduser -S sping -G spring
EXPOSE 8080

ENV JAVA_PROFILE prod
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

ADD target/spring-boot-docker.jar spring-boot-docker.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=${JAVA_PROFILE}",\
            "-cp","app:app/lib/*","Authentication.rest.GPT4BakerApplication"]