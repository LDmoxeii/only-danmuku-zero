pluginManagement {
    plugins {
        kotlin("jvm") version "2.2.20"
        kotlin("plugin.spring") version "2.2.20"
        id("org.springframework.boot") version "3.5.6"
    }
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven {
            credentials {
                username = providers.gradleProperty("aliyun.maven.username").orNull
                    ?: error("Missing required Gradle property 'aliyun.maven.username' for Cap4k Maven repository.")
                password = providers.gradleProperty("aliyun.maven.password").orNull
                    ?: error("Missing required Gradle property 'aliyun.maven.password' for Cap4k Maven repository.")
            }
            url = uri("https://packages.aliyun.com/67053c6149e9309ce56b9e9e/maven/cap4k")
        }
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven {
            url = uri("https://maven.aliyun.com/repository/public")
        }
        maven {
            credentials {
                username = providers.gradleProperty("aliyun.maven.username").orNull
                    ?: error("Missing required Gradle property 'aliyun.maven.username' for Cap4k Maven repository.")
                password = providers.gradleProperty("aliyun.maven.password").orNull
                    ?: error("Missing required Gradle property 'aliyun.maven.password' for Cap4k Maven repository.")
            }
            url = uri("https://packages.aliyun.com/67053c6149e9309ce56b9e9e/maven/cap4k")
        }
    }
}

// [cap4k-bootstrap:managed-begin:root-host]
rootProject.name = "only-danmuku-zero"
// [cap4k-bootstrap:managed-end:root-host]
