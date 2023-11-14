package com.nexign.dsl.tryout.arithmeticscenario

import com.nexign.dsl.tryout.base.*

class ArithmeticScenario(
    private val a: Double,
    private val b: Double,
) : Scenario() {

    override val params: Map<String, Any>
        get() = mutableMapOf(
            "a" to a,
            "b" to b,
        )

    override val specification: Specification = specification(
            ValidateOr() binary choice {
                yes( ComputeSquare() next ComputePerimeter() next PrintResults())
                no(PrintError())
            }
        )

}