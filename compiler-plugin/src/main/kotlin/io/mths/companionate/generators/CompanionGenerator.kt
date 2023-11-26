package io.mths.companionate.generators

import org.jetbrains.kotlin.GeneratedDeclarationKey
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin
import org.jetbrains.kotlin.fir.extensions.*
import org.jetbrains.kotlin.fir.extensions.predicate.LookupPredicate
import org.jetbrains.kotlin.fir.plugin.createCompanionObject
import org.jetbrains.kotlin.fir.plugin.createDefaultPrivateConstructor
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.name.SpecialNames
import org.jetbrains.kotlin.name.SpecialNames.DEFAULT_NAME_FOR_COMPANION_OBJECT
import org.jetbrains.kotlin.utils.addToStdlib.runIf

class CompanionGenerator(session: FirSession, annotations: List<String>) : FirDeclarationGenerationExtension(session) {
	object Key : GeneratedDeclarationKey() {
		override fun toString() = "CompanionGeneratorKey"
	}

	private val annotatedPredicate = LookupPredicate.create { annotated(annotations.map(::FqName)) }

	override fun FirDeclarationPredicateRegistrar.registerPredicates() {
		register(annotatedPredicate)
	}

    override fun generateNestedClassLikeDeclaration(owner: FirClassSymbol<*>, name: Name, context: NestedClassGenerationContext): FirClassLikeSymbol<*>? =
        runIf(name == DEFAULT_NAME_FOR_COMPANION_OBJECT) { createCompanionObject(owner, Key).symbol }

    override fun generateConstructors(context: MemberGenerationContext): List<FirConstructorSymbol> {
        val constructor = createDefaultPrivateConstructor(context.owner, Key)
        return listOf(constructor.symbol)
    }

    override fun getCallableNamesForClass(classSymbol: FirClassSymbol<*>, context: MemberGenerationContext): Set<Name> {
		if (!classSymbol.isCompanion) return emptySet()

        val origin = classSymbol.origin as? FirDeclarationOrigin.Plugin
        return runIf(origin?.key == Key) { setOf(SpecialNames.INIT) }.orEmpty()
    }

    override fun getNestedClassifiersNames(classSymbol: FirClassSymbol<*>, context: NestedClassGenerationContext): Set<Name> =
        runIf(classSymbol matches annotatedPredicate && !classSymbol.isSingleton) {
            setOf(DEFAULT_NAME_FOR_COMPANION_OBJECT)
        }.orEmpty()

	private infix fun FirClassSymbol<*>.matches(predicate: LookupPredicate): Boolean =
		session.predicateBasedProvider.matches(predicate, this)
}

private val FirClassSymbol<*>.isCompanion get() =
	isSingleton && with(classId) {
		isNestedClass && shortClassName == DEFAULT_NAME_FOR_COMPANION_OBJECT
	}

private val FirClassSymbol<*>.isSingleton get() =
	classKind == ClassKind.OBJECT
