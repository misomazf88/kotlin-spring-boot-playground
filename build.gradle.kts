import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

object Versions {

    const val KT_LINT = "0.40.0"
    const val SLEUTH = "3.1.4"
    const val SPRING_BOOT = "2.4.0"
    const val JACOCO = "0.8.7"
}

plugins {
    kotlin("jvm") version "1.6.0"
    jacoco
}

buildscript {

    val kotlinVersion = "1.6.0"
    val springBootVersion = "2.7.0"

    repositories {
        mavenCentral()
        maven(url = "https://repo.spring.io/snapshot")
        maven(url = "https://repo.spring.io/milestone")
    }

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
        classpath("org.jlleitschuh.gradle:ktlint-gradle:9.2.1")
        classpath(kotlin("gradle-plugin"))
        classpath(kotlin("allopen", kotlinVersion))
        classpath(kotlin("noarg", kotlinVersion))
        classpath("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:3.1")
    }
}

subprojects {
    group = "com.dermo.app.ammj"
}

allprojects {

    val ktlint by configurations.creating

    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring")
    apply(plugin = "kotlin-allopen")
    apply(plugin = "jacoco")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply("sonar.gradle")

    jacoco {
        toolVersion = Versions.JACOCO
    }

    repositories {
        mavenCentral()
        maven(url = "https://repo.spring.io/snapshot")
        maven(url = "https://repo.spring.io/milestone")
    }

    java.sourceCompatibility = JavaVersion.VERSION_1_8

    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation(kotlin("reflect"))
        implementation(kotlin("stdlib-jdk8"))
        implementation("org.springframework.cloud:spring-cloud-starter-sleuth:${Versions.SLEUTH}")
        ktlint("com.pinterest:ktlint:${Versions.KT_LINT}")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        implementation("org.flywaydb:flyway-core")
        runtimeOnly("org.postgresql:postgresql")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa") {
            exclude(module= "spring-boot-starter-tomcat")
        }
        implementation("org.projectlombok:lombok")
        implementation("org.springframework.boot:spring-boot-starter-cache")
        implementation("org.springframework.boot:spring-boot-starter-validation:2.5.6")
        implementation ("com.google.code.gson:gson:2.8.5")
        implementation("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:3.1")
    }

    val outputDir = "${project.buildDir}/reports/ktlint/"
    val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "**/*.kt"))
    val ktlintCheck by tasks.creating(JavaExec::class) {
        inputs.files(inputFiles)
        outputs.dir(outputDir)
        description = "Check Kotlin code style."
        classpath = ktlint
        main = "com.pinterest.ktlint.Main"
        args = listOf("src/**/*.kt")
    }

    val ktlintFormat by tasks.creating(JavaExec::class) {
        inputs.files(inputFiles)
        outputs.dir(outputDir)
        description = "Fix Kotlin code style deviations."
        classpath = ktlint
        main = "com.pinterest.ktlint.Main"
        args = listOf("-F", "src/**/*.kt")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
        dependsOn(ktlintFormat)
    }

    tasks.getByName<Jar>("jar") {
        enabled = true
    }
    tasks.getByName<BootJar>("bootJar") {
        mainClass.set("com.dermo.app.ammj.app.ApplicationKt")
        archiveClassifier.set("boot")
    }

    tasks.jacocoTestReport {
        reports {
            xml.required.set(true)
        }
    }

    tasks.test {
        useJUnitPlatform()
        finalizedBy("jacocoTestReport")
    }

}
