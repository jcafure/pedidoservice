FROM openjdk
ADD target/pedidoservice-0.0.1-SNAPSHOT.jar pedidoservice.jar
ENTRYPOINT ["java", "-jar", "pedidoservice.jar"]
EXPOSE 9000