plugins {
    java
    id("com.github.johnrengelman.shadow") version "8.1.1"
}
tasks {
    shadowJar {
        archiveClassifier.set("all")  // Adds "-all" to the JAR name for clarity
        // Additional configurations if needed
    }
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    manifest {
        attributes["Main-Class"] = "Main"
    }
}

group = "org.swing"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}