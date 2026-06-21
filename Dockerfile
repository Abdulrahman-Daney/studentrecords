# Step 1: Use Java 21 as the base image
FROM eclipse-temurin:25-jdk-alpine

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the built jar file into the container
COPY target/studentrecords-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Tell Docker which port the app uses
EXPOSE 9090

# Step 5: Command to run the app
ENTRYPOINT ["java", "-jar", "app.jar"]