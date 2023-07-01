FROM maven:3.6.3-jdk-11 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11-jdk-slim-sid
COPY --from=build /target/ServeurADELI-0.0.1-SNAPSHOT.war demo.war
EXPOSE 8080
ENTRYPOINT ["java", "-war", "demo.war"]
