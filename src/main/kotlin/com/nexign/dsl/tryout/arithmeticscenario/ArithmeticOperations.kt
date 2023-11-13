package com.nexign.dsl.tryout.arithmeticscenario

import com.nexign.dsl.tryout.base.*
import com.nexign.dsl.tryout.exceptions.Errors
import com.nexign.dsl.tryout.exceptions.IllegalScenarioArgumentException

class ComputePerimeter: Operation() {
    override val specification: Specification = specification {
        setTransition(SINGLE_ROUTE(), PrintResults())
    }

    override val func: Scenario.() -> TransitionCondition = {
        this.results["perimeter"] = (this.params["a"] as Double + this.params["b"] as Double) * 2
        SINGLE_ROUTE()
    }
}

class ComputeSquare: Operation() {
    override val specification: Specification = specification {
        setTransition(SINGLE_ROUTE(), ComputePerimeter())
    }

    override val func: Scenario.() -> TransitionCondition = {
        this.results["perimeter"] = (this.params["a"] as Double * this.params["b"] as Double)
        SINGLE_ROUTE()
    }
}

open class ValidateOr: Operation () {

    override val func : Scenario.() -> TransitionCondition = {
        var continueExecution: TransitionCondition = YES()
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
        } catch (e : IllegalScenarioArgumentException) {
            this.results["error"] = e.message!!
            continueExecution = NO()
        }
        continueExecution
    }
}

class PrintResults: Operation() {
    override val func: Scenario.() -> TransitionCondition = {
        println(this.results["square"])
        STOP_EXECUTION()
    }
}

class PrintError: Operation() {
    override val func: Scenario.() -> TransitionCondition = {
        println(this.results["error"])
        STOP_EXECUTION()
    }
}