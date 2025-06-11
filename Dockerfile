# --- Stage 1: Build the Maven application ---
# Use a Maven image that includes a JDK, matching your desired Java version
FROM maven:3.9.6-openjdk-21 AS build
# This image provides Maven 3.9.6 AND Eclipse Temurin Java 21 JDK.
# You could also use:
# FROM maven:3.9.6-amazoncorretto-21 AS build
# FROM maven:3.9.6-openjdk-21 AS build
# Choose based on your preference for the base JDK in the build stage.

WORKDIR /app

# Copy the pom.xml first to leverage Docker's layer caching
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline -B

# Copy the entire source code
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# --- Stage 2: Create the runtime image ---
# Use a lightweight JRE image for the final, smaller application image
FROM eclipse-temurin:21-jre-alpine
# This is still the best choice for the final runtime image due to its small size.

# Set the working directory
WORKDIR /app

# Argument for the JAR file name
ARG JAR_FILE=target/*.jar

# Copy the built JAR file from the 'build' stage
COPY --from=build /app/${JAR_FILE} app.jar

# Expose the port your Spring Boot application listens on
EXPOSE 8080

# Command to run your Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]