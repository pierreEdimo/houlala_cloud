FROM openjdk:17
LABEL maintainers="pierre Edimo Nkoe"
ADD target/inventory-service-0.0.2-SNAPSHOT.jar inventory-service.jar
ENTRYPOINT ["java", "-jar", "inventory-service.jar"]