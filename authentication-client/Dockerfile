FROM openjdk:17
LABEL maintainers="pierre Edimo Nkoe"
ADD target/authentication-client-0.0.2-SNAPSHOT.jar authentication.jar
ENTRYPOINT ["java", "-jar", "authentication.jar"]