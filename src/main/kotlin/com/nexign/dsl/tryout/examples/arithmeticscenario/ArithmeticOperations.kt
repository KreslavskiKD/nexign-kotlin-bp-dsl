package com.nexign.dsl.tryout.examples.arithmeticscenario

import com.nexign.dsl.tryout.base.*
import com.nexign.dsl.tryout.base.exceptions.IllegalScenarioArgumentException

class ComputePerimeter: Operation() {

    override val func: Scenario.() -> TransitionCondition = {
        this.storage["perimeter"] = (this.storage["a"] as Double + this.storage["b"] as Double) * 2
        SINGLE_ROUTE
    }
}

class ComputeSquare: Operation() {

    override val func: Scenario.() -> TransitionCondition = {
        this.storage["square"] = (this.storage["a"] as Double * this.storage["b"] as Double)
        SINGLE_ROUTE
    }
}

open class ValidateOr: Operation () {

    override val func : Scenario.() -> TransitionCondition = {
        var continueExecution: TransitionCondition = YES
        try {
            val a = this.storage["a"] as Double
            val b = this.storage["b"] as Double
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
            this.storage["error"] = e.message!!
            continueExecution = NO
        }
        continueExecution
    }
}

class PrintResults: Operation() {
    override val func: Scenario.() -> TransitionCondition = {
        println("square = " + this.storage["square"])
        println("perimeter = " + this.storage["perimeter"])
        STOP_EXECUTION
    }
}

class PrintError: Operation() {
    override val func: Scenario.() -> TransitionCondition = {
        println(this.storage["error"])
        STOP_EXECUTION
    }
}