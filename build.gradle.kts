import com.only4.cap4k.plugin.pipeline.api.BootstrapMode

val aliyunMavenUsername = providers.gradleProperty("aliyun.maven.username").orNull
    ?: error("Missing required Gradle property 'aliyun.maven.username' for Cap4k Maven repository.")
val aliyunMavenPassword = providers.gradleProperty("aliyun.maven.password").orNull
    ?: error("Missing required Gradle property 'aliyun.maven.password' for Cap4k Maven repository.")
val cap4kDogfoodH2Schema = layout.projectDirectory
    .file("docs/dogfood/h2/only_danmuku.h2.schema.sql")
    .asFile
    .absolutePath
    .replace("\\", "/")

plugins {
    kotlin("jvm") version "2.2.20" apply false
    id("com.only4.cap4k.plugin.pipeline") version "0.5.0-SNAPSHOT"
}

group = "edu.only4"
version = "0.0.1-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
        maven {
            url = uri("https://maven.aliyun.com/repository/public")
        }
        maven {
            credentials {
                username = aliyunMavenUsername
                password = aliyunMavenPassword
            }
            url = uri("https://packages.aliyun.com/67053c6149e9309ce56b9e9e/maven/cap4k")
        }
    }
}

// [cap4k-bootstrap:managed-begin:root-host]
cap4k {
    bootstrap {
        enabled.set(true)
        preset.set("ddd-multi-module")
        conflictPolicy.set("OVERWRITE")
        mode.set(BootstrapMode.IN_PLACE)
        projectName.set("only-danmuku-zero")
        basePackage.set("edu.only4.danmuku")
        modules {
            domainModuleName.set("only-danmuku-domain")
            applicationModuleName.set("only-danmuku-application")
            adapterModuleName.set("only-danmuku-adapter")
            startModuleName.set("only-danmuku-start")
        }
        templates {
            preset.set("ddd-default-bootstrap")
        }
    }
}
// [cap4k-bootstrap:managed-end:root-host]

cap4k {
    project {
        basePackage.set("edu.only4.danmuku")
        domainModulePath.set("only-danmuku-domain")
        applicationModulePath.set("only-danmuku-application")
        adapterModulePath.set("only-danmuku-adapter")
    }
    types {
        registryFile.set("docs/dogfood/cap4k-pipeline-type-registry.json")
    }
    sources {
        designJson {
            enabled.set(true)
            files.from("iterate/drawing_board.json")
        }
        db {
            enabled.set(true)
            url.set(
                "jdbc:h2:mem:only_danmuku_zero_codegen;MODE=MySQL;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false;INIT=RUNSCRIPT FROM '$cap4kDogfoodH2Schema'"
            )
            username.set("sa")
            password.set("secret")
            schema.set("PUBLIC")
            includeTables.set(emptyList())
            excludeTables.set(emptyList())
        }
    }
    generators {
        aggregate {
            enabled.set(true)
        }
        designCommand {
            enabled.set(true)
        }
        designQuery {
            enabled.set(true)
        }
        designQueryHandler {
            enabled.set(true)
        }
        designClient {
            enabled.set(true)
        }
        designClientHandler {
            enabled.set(true)
        }
        designApiPayload {
            enabled.set(true)
        }
        designDomainEvent {
            enabled.set(true)
        }
        designDomainEventHandler {
            enabled.set(true)
        }
    }
    templates {
        conflictPolicy.set("OVERWRITE")
    }
}
