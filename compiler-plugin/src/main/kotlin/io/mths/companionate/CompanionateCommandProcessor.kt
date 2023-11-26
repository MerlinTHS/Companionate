package io.mths.companionate

import com.google.auto.service.AutoService
import org.jetbrains.kotlin.compiler.plugin.AbstractCliOption
import org.jetbrains.kotlin.compiler.plugin.CommandLineProcessor
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi

@OptIn(ExperimentalCompilerApi::class)
@AutoService(CommandLineProcessor::class)
class CompanionateCommandProcessor : CommandLineProcessor {
    override val pluginId = "companionate"
    override val pluginOptions: Collection<AbstractCliOption> = emptyList()
}