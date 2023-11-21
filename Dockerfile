FROM eclipse-temurin:17-jdk-focal
WORKDIR /app

COPY gradle ./gradle
COPY gradlew build.gradle settings.gradle ./
COPY src ./src
CMD ["./gradlew","bootRun"]