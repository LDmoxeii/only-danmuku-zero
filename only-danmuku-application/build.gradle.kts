plugins {
    kotlin("jvm") version "2.2.20"
}

group = "edu.only4.danmuku"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":only-danmuku-domain"))
    implementation("com.only4:ddd-core:0.5.0-SNAPSHOT")
    implementation("com.only4:engine-common:0.1.12-SNAPSHOT")
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")
    implementation("org.jetbrains.kotlin:kotlin-reflect:2.2.20")
    implementation("org.springframework:spring-context")
    implementation("org.springframework:spring-web")
}

kotlin {
    jvmToolchain(17)
}
