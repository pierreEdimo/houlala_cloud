FROM openjdk:17
LABEL maintainers="pierre Edimo Nkoe"
ADD target/stock-service-0.0.2-SNAPSHOT.jar stockservice.jar
ENTRYPOINT ["java", "-jar", "stockservice.jar"]