# Use Java 17 runtime (Temurin official build, slim variant)
FROM eclipse-temurin:17-jdk:latest

# Set working directory inside container
WORKDIR /app

# Copy the built jar from target/ folder into the container
COPY target/*.jar app.jar

# Expose Spring Boot default port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]