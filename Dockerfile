# Step 1: Use official Temurin JDK 17
FROM eclipse-temurin:17-jdk

# Step 2: Set working directory
WORKDIR /app

# Step 3: Copy Maven wrapper and pom.xml
COPY mvnw mvnw
COPY mvnw.cmd mvnw.cmd
COPY pom.xml pom.xml
COPY .mvn .mvn
COPY src src

# Step 4: Build the jar inside Docker
RUN ./mvnw clean package -DskipTests

# Step 5: Copy the built jar to app.jar
RUN cp target/*.jar app.jar

# Step 6: Expose Spring Boot port
EXPOSE 8080

# Step 7: Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]