plugins {
    kotlin("jvm")
    id("java-gradle-plugin")
	id("com.gradle.plugin-publish") version "1.1.0"
}

group = "io.github.merlinths"
version = "1.0.0"

dependencies {
    implementation(libs.gradle.plugin.api)
}

tasks.test {
    useJUnitPlatform()
}

gradlePlugin {
	website.set("https://github.com/MerlinTHS/Companionate")
	vcsUrl.set("https://github.com/MerlinTHS/Companionate")

	plugins {
		create("companionateGradlePlugin") {
			displayName = "Companionate Gradle Plugin"
			description = "Plugin to generate companion objects."
			id = "io.github.merlinths.companionate"
			implementationClass = "io.mths.companionate.gradle.CompanionatePlugin"

			tags.set(listOf("Kotlin", "Companionate", "Singletons"))
		}
	}
}


tasks.register("sourcesJar", Jar::class) {
	group = "build"
	description = "Assembles Kotlin sources"

	archiveClassifier.set("sources")
	from(sourceSets.main.get().allSource)
	dependsOn(tasks.classes)
}

publishing {
	publications {
		create<MavenPublication>("maven") {
			groupId = "io.github.merlinths"
			artifactId = "companionate-gradle-plugin"
			version = "1.0.0"

			from(components["kotlin"])
			artifact(tasks["sourcesJar"])

			pom {
				name.set("Companionate")
				description.set("Plugin to generate companion objects.")
				url.set("https://github.com/MerlinTHS/Companionate")

				developers {
					developer {
						name.set("Merlin Eden")
						url.set("https://github.com/MerlinTHS")
					}
				}
			}
		}
	}
}
