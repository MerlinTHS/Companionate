package io.mths.companionate

import org.jetbrains.kotlin.test.directives.model.SimpleDirectivesContainer

object CompanionateDirectives : SimpleDirectivesContainer() {
	val COMPANIONATE_ANNOTATIONS by stringDirective("Marks symbols for companion generation.")
}
