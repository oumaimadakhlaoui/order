FROM eclipse-temurin:17-jdk AS builder

# 1. download gradle wrapper
COPY gradlew /gradle/src/
COPY gradle/wrapper/* /gradle/src/gradle/wrapper/
WORKDIR /gradle/src
ENV GRADLE_USER_HOME=/gradle
RUN chmod +x gradlew
RUN ./gradlew --version

# 2. gradle build
COPY build.gradle* settings.gradle* gradle.properties* /gradle/src/
COPY src /gradle/src/src
RUN ./gradlew --no-daemon build -x test

# 3. run tests
RUN ./gradlew --no-daemon test --info

FROM eclipse-temurin:17-jre

VOLUME /tmp
COPY --from=builder /gradle/src/build/libs/order-0.0.1-SNAPSHOT-plain.jar app.jar
