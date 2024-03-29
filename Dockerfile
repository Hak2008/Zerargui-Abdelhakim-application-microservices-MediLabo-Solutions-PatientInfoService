# Base image containing Java 17
FROM openjdk:17-jdk-slim

# Définition du mot de passe root
ENV MYSQL_ROOT_PASSWORD=PayMyBuddy07.

# Définition du nom de la base de données
ENV MYSQL_DATABASE=patientinfoservice

# Définition de l'hôte de la base de données
ENV DB_HOST=mysql_db

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
