# Base image containing Java 17
FROM openjdk:17-jdk-slim

# Working directory in container
WORKDIR /app

# Copy the application JAR file into the container
COPY target/PatientInfoService-0.0.1-SNAPSHOT.jar /app/PatientInfoService.jar

# Copy application.properties into the container
COPY src/main/resources/application.properties /app/application.properties

# Port on which the application is running
EXPOSE 8080

# Command to execute when the container starts
CMD ["java", "-jar", "-Dserver.port=8080", "PatientInfoService.jar"]
