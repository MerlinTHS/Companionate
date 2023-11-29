package io.mths.companionate.gradle

import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilerPluginSupportPlugin
import org.jetbrains.kotlin.gradle.plugin.SubpluginArtifact
import org.jetbrains.kotlin.gradle.plugin.SubpluginOption

class CompanionatePlugin : KotlinCompilerPluginSupportPlugin {

    override fun applyToCompilation(kotlinCompilation: KotlinCompilation<*>): Provider<List<SubpluginOption>> =
        kotlinCompilation.createProvider { companionateExtension.toPluginOptions() }

    override fun apply(target: Project) {
		target.extensions.create("companionate", CompanionateExtension::class.java)
	}

    override fun getCompilerPluginId() = "companionate"

    override fun getPluginArtifact() = SubpluginArtifact(
        groupId = "io.github.merlinths", artifactId = "companionate-compiler-plugin", version = "1.0.0"
    )

    override fun isApplicable(kotlinCompilation: KotlinCompilation<*>) = true
}

internal fun CompanionateExtension.toPluginOptions() =
	listOf(SubpluginOption("enabled", enabled.toString())) +
		annotations.map { annotation -> SubpluginOption("annotation", annotation) }

internal fun <T> KotlinCompilation<*>.createProvider(supply: Project.() -> T) =
	with(target.project) { provider { supply() } }
