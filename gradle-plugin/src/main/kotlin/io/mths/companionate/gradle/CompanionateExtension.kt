package io.mths.companionate.gradle

import org.gradle.api.Project

open class CompanionateExtension {
	var enabled = true
	var annotations = emptyList<String>()
}

internal val Project.companionateExtension: CompanionateExtension
	get() = extensions.findByType(CompanionateExtension::class.java)
		?: CompanionateExtension()
