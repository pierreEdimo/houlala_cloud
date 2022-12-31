FROM openjdk:17
LABEL maintainers="pierre Edimo Nkoe"
ADD target/api-gateway.jar gateway.jar
ENTRYPOINT ["java", "-jar", "gateway.jar"]