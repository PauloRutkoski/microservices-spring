FROM openjdk:11
VOLUME /tmp
EXPOSE 8888
ADD ./target/hr-config-server-1.0.0-SNAPSHOT.jar hr-config-server.jar
ENTRYPOINT ["java","-jar","/hr-config-server.jar"]