package com.nexign.dsl.examples.arithmeticscenario

import com.nexign.dsl.base.Scenario
import com.nexign.dsl.base.Specification
import com.nexign.dsl.base.specification

class ArithmeticScenario(store: MutableMap<String, Any>) : Scenario(store) {

    override val specification: Specification = specification(
            ValidateOr() binary {
                yes( ComputeSquare() next ComputePerimeter() next PrintResults())
                no(PrintError())
            }
        )
}