buildscript {
	repositories { mavenLocal() }

	dependencies {
		classpath("io.github.merlinths:gradle-plugin:1.0.0")
	}
}

plugins {
    kotlin("multiplatform") version "1.9.20" apply false
	id("io.github.merlinths.companionate") version "1.0.0"
}

allprojects {
	group = "io.github.merlinths"
	version = "1.0.0"

    repositories {
        mavenCentral()
		mavenLocal()
    }
}
