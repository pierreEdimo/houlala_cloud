FROM openjdk:17
LABEL maintainers="pierre Edimo Nkoe"
ADD target/gateway-0.0.2-SNAPSHOT.jar gateway.jar
ENTRYPOINT ["java", "-jar", "gateway.jar"]