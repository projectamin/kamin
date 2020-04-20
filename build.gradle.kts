plugins {
    kotlin("multiplatform") version "1.3.72"
}

repositories {
    mavenCentral()
}

group = "org.projectamin.kamin"
version = "0.0.1"

val kotlinxVersion = "0.1.16"

kotlin {
    jvm() // Creates a JVM target with the default name 'jvm'
    js()  // JS target named 'js'
    mingwX64("mingw") // Windows (MinGW X64) target named 'mingw'
    linuxX64()
    macosX64()
    ios()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-io:$kotlinxVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        // Default source set for JVM-specific sources and dependencies:
        jvm().compilations["main"].defaultSourceSet {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                implementation("org.jetbrains.kotlinx:kotlinx-io-jvm:$kotlinxVersion")
            }
        }
        // JVM-specific tests and their dependencies:
        jvm().compilations["test"].defaultSourceSet {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }

        js().compilations["main"].defaultSourceSet  {
            dependencies {
                implementation(kotlin("stdlib-js"))
                implementation("org.jetbrains.kotlinx:kotlinx-io-js:$kotlinxVersion")
            }
        }
        js().compilations["test"].defaultSourceSet {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }

        mingwX64("mingw").compilations["main"].defaultSourceSet { /* ... */ }
        mingwX64("mingw").compilations["test"].defaultSourceSet { /* ... */ }

        linuxX64().compilations["main"].defaultSourceSet { /* ... */ }
        linuxX64().compilations["test"].defaultSourceSet { /* ... */ }

        macosX64().compilations["main"].defaultSourceSet { /* ... */ }
        macosX64().compilations["test"].defaultSourceSet { /* ... */ }
    }
}