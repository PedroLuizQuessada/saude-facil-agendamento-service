FROM maven:3.9-eclipse-temurin-21 AS dependencies
RUN git clone --branch master --depth 1 https://github.com/PedroLuizQuessada/saude-facil-stub.git /tmp/saude-facil-stub
WORKDIR /tmp/saude-facil-stub
RUN mvn install -DskipTests
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B

FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY --from=dependencies /root/.m2 /root/.m2
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre AS execution
WORKDIR /home/root
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]