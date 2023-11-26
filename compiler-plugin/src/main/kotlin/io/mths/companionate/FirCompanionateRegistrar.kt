package io.mths.companionate

import io.mths.companionate.generators.CompanionGenerator
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar

class FirCompanionateRegistrar : FirExtensionRegistrar() {
    override fun ExtensionRegistrarContext.configurePlugin() {
        + ::CompanionGenerator
    }
}