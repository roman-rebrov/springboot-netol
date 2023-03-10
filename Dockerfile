
FROM openjdk:17-oracle

EXPOSE 8081

COPY target/springboot-conditional-0.0.1-SNAPSHOT.jar  app.jar

CMD ["java", "-jar", "app.jar"]