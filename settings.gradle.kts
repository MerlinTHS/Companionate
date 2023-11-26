rootProject.name = "Companionate"
include("gradle-plugin")
include("compiler-plugin")
include("demo")

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

dependencyResolutionManagement {
    versionCatalogs { libs; testLibs }
}

val kotlinVersion = "1.9.20"
val MutableVersionCatalogContainer.libs get() = create("libs") {
    library("compiler", "org.jetbrains.kotlin", "kotlin-compiler").version(kotlinVersion)
    library("auto-service", "com.google.auto.service", "auto-service").version("1.1.1")
    library("gradle-plugin-api", "org.jetbrains.kotlin", "kotlin-gradle-plugin-api").version(kotlinVersion)
}

val MutableVersionCatalogContainer.testLibs get() = create("testLibs") {
    library("internal-framework", "org.jetbrains.kotlin", "kotlin-compiler-internal-test-framework").version(kotlinVersion)
}
