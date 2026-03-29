# ==========================
# Render-ready Spring Boot Dockerfile
# ==========================

# Step 1: Use official Temurin JDK 17 (full JDK)
FROM eclipse-temurin:17-jdk

# Step 2: Set working directory inside container
WORKDIR /app

# Step 3: Copy Maven wrapper, pom.xml, and source code
COPY mvnw mvnw
COPY mvnw.cmd mvnw.cmd
COPY pom.xml pom.xml
COPY .mvn .mvn
COPY src src

# Step 4: Make Maven wrapper executable
RUN chmod +x mvnw

# Step 5: Build the jar inside Docker, skip tests to save time
RUN ./mvnw clean package -DskipTests

# Step 6: Copy the built jar to a standard name
RUN cp target/*.jar app.jar

# Step 7: Expose default Spring Boot port
EXPOSE 8080

# Step 8: Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]