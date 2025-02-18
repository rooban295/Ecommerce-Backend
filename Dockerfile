# Use OpenJDK as the base image
FROM eclipse-temurin:17

# Set the working directory in the container
WORKDIR /app

# Copy the built Spring Boot JAR file into the container
COPY target/E-commerce-backend-0.0.1-SNAPSHOT.jar app.jar

# Expose the application's port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
