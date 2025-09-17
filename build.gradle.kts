plugins {
    kotlin("jvm") version "2.2.0"
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "ai.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://packages.jetbrains.team/maven/p/ij/intellij-dependencies")

}

dependencies {
        implementation("ai.koog:koog-agents:0.4.1")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")  // For runBlocking
        
        // Ktor client for HTTP requests to local model
        implementation("io.ktor:ktor-client-core:2.3.7")
        implementation("io.ktor:ktor-client-cio:2.3.7")
        implementation("io.ktor:ktor-client-content-negotiation:2.3.7")
        implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.7")
        
        testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

application {
    // Replace with your package + file name
    mainClass.set("ai.example.MainKt")
}

tasks {
    named<Jar>("jar") {
        // avoid creating duplicate jars
        enabled = false
    }

    withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
        archiveClassifier.set("") // so it replaces the default jar
        mergeServiceFiles()
    }

    named("build") {
        dependsOn("shadowJar")
    }

    named("distZip") {
        dependsOn("shadowJar")
    }

    named("distTar") {
        dependsOn("shadowJar")
    }

    named("startScripts") {
        dependsOn("shadowJar")
    }
}