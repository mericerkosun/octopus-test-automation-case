# Use Maven with JDK 21 on Alpine (reliable for Chromium on ARM64)
FROM maven:3.9.6-eclipse-temurin-21-alpine

# Install Chromium, ChromeDriver and required libraries for Alpine
RUN apk add --no-cache \
    chromium \
    chromium-chromedriver \
    nss \
    freetype \
    harfbuzz \
    ca-certificates \
    ttf-freefont \
    udev

# Chromium path on Alpine
ENV CHROME_BIN=/usr/bin/chromium-browser

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY . .

# Default command: run all tests in headless mode
CMD ["mvn", "test", "-Dheadless=true"]
