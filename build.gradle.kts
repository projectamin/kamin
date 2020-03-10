plugins {
    kotlin("multiplatform") version "1.3.70"
    `maven-publish`
}
repositories {
    mavenCentral()
}

group = "org.projectamin.kamin"
version = "0.0.1"

val mingwPath = File(System.getenv("MINGW64_DIR") ?: "C:/msys64/mingw64")

kotlin {
    jvm()
    js {
        browser {

        }
        nodejs {
        }
    }
    // For ARM, should be changed to iosArm32 or iosArm64
    // For Linux, should be changed to e.g. linuxX64
    // For MacOS, should be changed to e.g. macosX64
    // For Windows, should be changed to e.g. mingwX64
    macosX64 {
        binaries {
            framework {  }
        }
    }
    mingwX64 {
        binaries {
            get("debugTest").apply {
                linkerOpts("-L${mingwPath.resolve("lib")}")
            }
            sharedLib {
                linkerOpts("-L${mingwPath.resolve("lib")}")
            }
        }
        compilations.getByName("main") {
            val libxml2 by cinterops.creating {
                defFile(project.file("src/nativeInterop/cinterop/libxml2.def"))
                packageName("libxml2")
                includeDirs(mingwPath.resolve("include/libxml2"), mingwPath.resolve("include"))
            }
        }
    }
    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        jvm().compilations["main"].defaultSourceSet {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }
        jvm().compilations["test"].defaultSourceSet {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }
        js().compilations["main"].defaultSourceSet {
            dependencies {
                implementation(kotlin("stdlib-js"))
                implementation(npm("saxophone", "0.6.1"))
            }
        }
        js().compilations["test"].defaultSourceSet {
            dependencies {
                implementation(kotlin("test-js"))
                implementation(npm("saxophone", "0.6.1"))
            }
        }
    }
}