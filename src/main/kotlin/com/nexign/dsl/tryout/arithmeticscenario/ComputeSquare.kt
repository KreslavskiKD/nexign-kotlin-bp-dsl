package com.nexign.dsl.tryout.arithmeticscenario

import com.nexign.dsl.tryout.base.Operation
import com.nexign.dsl.tryout.base.ScenarioStatus

class ComputeSquare: Operation (
    func = {
        val a = this.params["a"] as Double
        val b = this.params["b"] as Double
        this.results["square"] = a * b

        ScenarioStatus.CONTINUE
    },
    nestedOperations = listOf()
)