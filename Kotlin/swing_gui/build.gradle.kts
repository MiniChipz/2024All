plugins {
    kotlin("jvm") version "2.0.10"
}

group = "org.swing"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.formdev:flatlaf:3.5.1")
}

tasks.test {
    useJUnitPlatform()
}