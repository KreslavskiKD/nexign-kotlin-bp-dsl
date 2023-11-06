package com.nexign.dsl.tryout.arithmeticscenario

import com.nexign.dsl.tryout.base.Operation
import com.nexign.dsl.tryout.base.Scenario
import com.nexign.dsl.tryout.exceptions.Errors
import com.nexign.dsl.tryout.exceptions.IllegalScenarioArgumentException

class ValidateOr (
    private val orFunc: Scenario.(e :Exception) -> Unit,
): Operation (
    func = {
        var continueExecution = true
        try {
            val a = this.params["a"] as Double
            val b = this.params["b"] as Double
            if (a < 3.0) {
                throw IllegalScenarioArgumentException(Errors.BOUNDS_LESS_ERROR_A)
            }
            if (a > 42.0) {
                throw IllegalScenarioArgumentException(Errors.BOUNDS_MORE_ERROR_A)
            }
            if (b < 3.0) {
                throw IllegalScenarioArgumentException(Errors.BOUNDS_LESS_ERROR_B)
            }
            if (b > 42.0) {
                throw IllegalScenarioArgumentException(Errors.BOUNDS_MORE_ERROR_B)
            }
        } catch (e :IllegalScenarioArgumentException) {
            this.orFunc(e)
            continueExecution = false
        }
        continueExecution
    },
    nestedOperations = listOf()
)