import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
	kotlin("jvm")
	id("io.github.merlinths.companionate")
}

companionate {
	annotations = listOf("io.mths.companionate.sample.Companionate")
}

kotlin {
	compilerOptions {
		languageVersion.set(KotlinVersion.KOTLIN_2_0)
	}
}
