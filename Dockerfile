# Build stage
FROM maven:3.8.6-eclipse-temurin-17-alpine as build

COPY src /home/app/src
COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean package
COPY docker /home/app/docker

# Package stage
FROM eclipse-temurin:17-jre-alpine

COPY --from=build /home/app/docker /
COPY --from=build /home/app/target/*.jar /opt/czertainly/app.jar

WORKDIR /opt/czertainly

EXPOSE 8080

ENTRYPOINT ["/opt/czertainly/entry.sh"]