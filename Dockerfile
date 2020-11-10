FROM openjdk:8

RUN apt-get update && apt-get install -y maven
COPY . /project
RUN  cd /project && mvn package

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar","/project/target/comexport-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080/tcp
