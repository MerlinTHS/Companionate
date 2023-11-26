rootProject.name = "Companionate"
include(":compiler-plugin")
include("compiler-plugin")
include("annotations")

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
    versionCatalogs { libs }
}

val kotlinVersion = "1.9.20"
val MutableVersionCatalogContainer.libs get() = create("libs") {
    library("compiler-embeddable", "org.jetbrains.kotlin", "kotlin-compiler-embeddable").version(kotlinVersion)
    library("auto-service", "com.google.auto.service", "auto-service").version("1.1.1")
}

val MutableVersionCatalogContainer.testLibs get() = create("testLibs") {
    library("internal-framework", "org.jetbrains.kotlin", "kotlin-compiler-internal-test-framework").version(kotlinVersion)
}