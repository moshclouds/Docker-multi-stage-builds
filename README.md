# 🚀 Docker Multi-Stage Builds with Java (Spring Boot)

<img width="1536" height="1024" alt="Image" src="https://github.com/user-attachments/assets/d76f8bf6-4796-4216-b561-95b6b8350cfc" />

---
Welcome to the official repository for exploring **Docker Multi-Stage Builds** with a real-world Spring Boot example! This project demonstrates how to optimize Docker image size and performance using a two-stage build process.

🔗 **Live Blog Post**: [Docker Multi-Stage Builds Demystified](https://medium.com/@moshclouds/docker-multi-stage-builds-demystified-<your-blog-slug>)  
📦 **Repo URL**: https://github.com/moshclouds/Docker-multi-stage-builds

---

## 📁 Project Structure
```
moshclouds-docker-multi-stage-builds/
├── Dockerfile                  # Multi-stage Docker build file
├── pom.xml                     # Maven project descriptor
├── .dockerignore              # Excludes unnecessary files from Docker context
├── mvnw, mvnw\.cmd              # Maven wrapper for consistent builds
├── src/
│   ├── main/java               # Application source code
│   ├── resources/             # Configuration (e.g., application.properties)
│   └── test/java              # Unit tests
└── .mvn/wrapper               # Maven wrapper properties
```


## 🛠️ Multi-Stage Dockerfile Explained

This project uses **two stages** in the Dockerfile:

### 🔨 Stage 1: Build the Application

```Dockerfile
FROM maven:3.9.6-eclipse-temurin-21 as build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests
````

* **Image**: Uses official Maven with JDK 21
* **Caching**: Downloads dependencies before copying source to cache Maven layers
* **Result**: Compiles the project and packages it into a `.jar`

---

### 🚀 Stage 2: Run the Application

```Dockerfile
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8890
ENTRYPOINT ["java", "-jar", "app.jar"]
```

* **Image**: Lightweight Alpine with JDK 21
* **Copy**: Only the JAR from the build stage is added
* **Runtime**: Starts the Spring Boot app with exposed port `8890`

✅ **Result**: Smaller, secure, and production-optimized Docker image

---

## 🧪 Run the Project Locally

### Step 1: Build the Docker Image

```bash
docker build -t moshclouds/multi-stage-app .
```

### Step 2: Run the Container

```bash
docker run -p 8890:8890 moshclouds/multi-stage-app
```

🧭 Visit: `http://localhost:8890/`
You should see: `Server is up and running 🚀`

---

## ✨ Why Multi-Stage Builds?

* ✅ Smaller Image Sizes
* ✅ Secure – no build tools in final image
* ✅ Clean separation of concerns
* ✅ Better CI/CD pipeline efficiency

---

## 📌 Tech Stack

* Java 21
* Spring Boot
* Maven
* Docker

---

## 📬 Contributing

If you'd like to contribute or enhance this project, feel free to fork the repo and make a pull request.



Thank you for checking out this project! Follow me on [Medium](https://medium.com/@moshclouds) for more developer insights.

