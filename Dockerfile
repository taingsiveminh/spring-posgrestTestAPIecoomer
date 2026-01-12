# Build stage
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /workspace

# Cache deps first
COPY pom.xml .
COPY .mvn/ .mvn/
COPY mvnw mvnw
RUN chmod +x mvnw
RUN ./mvnw -q -DskipTests dependency:go-offline

# Build
COPY src/ src/
RUN ./mvnw -q -DskipTests package


# Run stage
FROM eclipse-temurin:17-jre

WORKDIR /app
COPY --from=build /workspace/target/*.jar /app/app.jar

ENV JAVA_OPTS=""
EXPOSE 8080

# Render sets $PORT; fall back to 8080 for local.
CMD ["sh", "-c", "java $JAVA_OPTS -Dserver.port=${PORT:-8080} -jar /app/app.jar"]
