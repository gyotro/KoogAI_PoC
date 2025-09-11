plugins {
    kotlin("jvm") version "2.2.0"
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
        testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}