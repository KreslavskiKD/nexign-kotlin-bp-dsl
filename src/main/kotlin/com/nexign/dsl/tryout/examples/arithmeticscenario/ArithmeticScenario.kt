package com.nexign.dsl.tryout.examples.arithmeticscenario

import com.nexign.dsl.tryout.base.*

class ArithmeticScenario(
    private val a: Double,
    private val b: Double,
) : Scenario() {

    override val storage: MutableMap<String, Any> = mutableMapOf(
            "a" to a,
            "b" to b,
        )

    override val specification: Specification = specification(
            ValidateOr() binary {
                yes( ComputeSquare() next ComputePerimeter() next PrintResults())
                no(PrintError())
            }
        )
}