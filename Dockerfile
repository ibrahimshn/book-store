FROM maven:3.8.1-openjdk-11 AS build
RUN mkdir /buildApp
COPY . /buildApp
WORKDIR /buildApp
RUN mvn clean package -DskipTests


FROM adoptopenjdk/openjdk11
RUN mkdir /app
COPY --from=build /buildApp/target/book-0.0.1-SNAPSHOT.jar /app/java-application.jar
WORKDIR /app
CMD "java" "-DMYSQL_HOST=mysqldb" "-jar" "java-application.jar"