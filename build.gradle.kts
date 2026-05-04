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
            url = uri("https://packages.aliyun.com/67053c6149e9309ce56b9e9e/maven/code-gen")
        }
        maven {
            credentials {
                username = aliyunMavenUsername
                password = aliyunMavenPassword
            }
            url = uri("https://packages.aliyun.com/67053c6149e9309ce56b9e9e/maven/cap4k")
        }
        maven {
            credentials {
                username = aliyunMavenUsername
                password = aliyunMavenPassword
            }
            url = uri("https://packages.aliyun.com/67053c6149e9309ce56b9e9e/maven/only-engine")
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
            overrideDirs.from("codegen/bootstrap-templates")
        }
        slots {
            root.from("codegen/bootstrap-slots/root")
            modulePackage("domain").from("codegen/bootstrap-slots/domain-package")
            modulePackage("application").from("codegen/bootstrap-slots/application-package")
            modulePackage("adapter").from("codegen/bootstrap-slots/adapter-package")
            moduleResources("start").from("codegen/bootstrap-slots/start-resources")
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
            files.from("codegen/design/design.json")
        }
        enumManifest {
            enabled.set(true)
            files.from("codegen/enum-manifest/shared-enums.json")
        }
        db {
            enabled.set(true)
            url.set(
                "jdbc:h2:mem:only_danmuku_zero_codegen_v2;MODE=MySQL;DATABASE_TO_UPPER=false;INIT=RUNSCRIPT FROM '$cap4kDogfoodH2Schema'"
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
            artifacts {
                factory.set(true)
                specification.set(true)
                wrapper.set(true)
                unique.set(true)
                enumTranslation.set(true)
            }
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
        designValidator {
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
        conflictPolicy.set("SKIP")
    }
}
