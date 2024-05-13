FROM eclipse-temurin:17-jdk AS builder

# 1. download gradle wrapper
COPY /order/gradlew /gradle/src/
COPY /order/gradle/wrapper/* /gradle/src/gradle/wrapper/
WORKDIR /gradle/src
ENV GRADLE_USER_HOME=/gradle
RUN chmod +x gradlew
RUN ./gradlew --version

# 2. gradle build
COPY /order/build.gradle* /order/settings.gradle* /order/gradle.properties* order/gradle/src/
COPY /order/src /gradle/src/src
RUN ./gradlew --no-daemon build -x test

# 3. run tests
RUN ./gradlew --no-daemon test --info

FROM eclipse-temurin:17-jre

VOLUME /tmp
COPY --from=builder /order/gradle/src/build/libs/order-0.0.1-SNAPSHOT-plain.jar app.jar
