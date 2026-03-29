# Step 1: Use Java runtime
FROM openjdk:17-jdk-slim

# Step 2: Set working directory
WORKDIR /app

# Step 3: Copy the built jar file into the container
COPY target/*.jar app.jar

# Step 4: Expose the default Spring Boot port
EXPOSE 8080

# Step 5: Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]