package io.mths.companionate.sample

annotation class Companionate

@Companionate
interface Person {
	companion object
}

fun Person.Companion.greet() {
	println("Hello Companionate!")
}

fun main() {
	Person.greet()
}
