package com.nexign.dsl.tryout.arithmeticscenario

import com.nexign.dsl.tryout.base.Operation

class ComputePerimeter: Operation(
    func = {
        val a = this.params["a"] as Double
        val b = this.params["b"] as Double
        this.results["perimeter"] = 2 * (a + b)

        true
    },
    nestedOperations = listOf()
)