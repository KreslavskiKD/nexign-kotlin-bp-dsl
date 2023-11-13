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

    override val specification: Specification = specification {
        setTransition(
            START_EXECUTION(), object : ValidateOr() {
                override val specification: Specification = specification {
                    setTransition(YES(), ComputeSquare() next ComputePerimeter() next PrintResults())
                    setTransition(NO(), PrintError())
                }
            }
        )
    }

}