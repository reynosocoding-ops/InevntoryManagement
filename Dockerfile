# Stage 1: Build the Spring Boot application
# Use a Maven image that includes JDK for compilation
# This image provides Maven and OpenJDK 21
FROM maven:3.9.6-openjdk-21 AS build

# Set the working directory inside the container for the build stage
WORKDIR /app

# Copy the entire project source code into the build container.
# This includes your pom.xml, src/ directory, etc.
# Docker will respect your .dockerignore file here.
COPY . .

# Run the Maven clean package command to build the executable JAR.
# -DskipTests is often used in CI/CD environments to skip tests during the build,
# assuming tests are run in a separate CI step or locally.
RUN mvn clean package -DskipTests

# Stage 2: Create the final Docker image for runtime
# Use a JRE image (Java Runtime Environment) which is smaller and more secure
# as it doesn't contain development tools like compilers.
FROM openjdk:21-jre-slim

# Security best practice: Create a non-root user and switch to it.
# This reduces potential security risks by not running the application as root.
RUN addgroup --system spring && adduser --system --ingroup spring spring
USER spring

# Set the working directory for the final image.
# This is where your application will run from inside the container.
WORKDIR /app

# Copy the built JAR file from the 'build' stage (the first stage) to the final image.
# The path '/app/target/*.jar' refers to the location of the JAR inside the 'build' stage container.
# The 'app.jar' is the simplified name for the JAR inside the final image.
COPY --from=build /app/target/*.jar app.jar

# Expose the port your Spring Boot application listens on.
# By default, Spring Boot runs on port 8080. This instruction informs Docker
# that the container will listen on this port.
EXPOSE 8080

# Define the command to run the application when the container starts.
# -Djava.security.egd=file:/dev/./urandom is a common JVM argument for Spring Boot
# applications in containers to improve startup time by speeding up entropy generation.
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]