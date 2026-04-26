plugins {
    kotlin("jvm") version "2.2.20"
    kotlin("plugin.spring") version "2.2.20"
    id("org.springframework.boot") version "3.5.6"
}

group = "edu.only4.danmuku"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation(project(":only-danmuku-adapter"))
    implementation(project(":only-danmuku-application"))
    implementation(project(":only-danmuku-domain"))
}

kotlin {
    jvmToolchain(17)
}
