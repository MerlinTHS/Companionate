package io.mths.companionate.cli

import com.google.auto.service.AutoService
import org.jetbrains.kotlin.compiler.plugin.*
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.config.CompilerConfigurationKey

object CompanionateConfigKeys {
	val ANNOTATION = CompilerConfigurationKey.create<List<String>>("annotation qualified name")
}

@OptIn(ExperimentalCompilerApi::class)
@AutoService(CommandLineProcessor::class)
class CompanionateCommandProcessor : CommandLineProcessor {
	companion object {
		val ANNOTATION_OPTION = CliOption(
			"annotation", "<fqname>", "Annotation qualified names",
			required = false, allowMultipleOccurrences = true
		)
	}

    override val pluginId = "companionate"
    override val pluginOptions = listOf(ANNOTATION_OPTION)

	override fun processOption(option: AbstractCliOption, value: String, configuration: CompilerConfiguration) = when (option) {
		ANNOTATION_OPTION ->
			configuration.appendList(CompanionateConfigKeys.ANNOTATION, value)
		else ->
			throw CliOptionProcessingException("Unknown option: ${option.optionName}")
	}
}
