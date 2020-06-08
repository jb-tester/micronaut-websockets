FROM openjdk:14-alpine
COPY target/micronaut-websockets-*.jar micronaut-websockets.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "micronaut-websockets.jar"]