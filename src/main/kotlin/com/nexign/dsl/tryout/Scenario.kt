package com.nexign.dsl.tryout

import com.nexign.dsl.tryout.Errors.BOUNDS_LESS_ERROR_A
import com.nexign.dsl.tryout.Errors.BOUNDS_LESS_ERROR_B
import com.nexign.dsl.tryout.Errors.BOUNDS_MORE_ERROR_A
import com.nexign.dsl.tryout.Errors.BOUNDS_MORE_ERROR_B

class Scenario: Operation() {

    fun computeSquare() {
        if (state == State.validated) {
            state = State.computed
            result = a * b
        }
    }

    fun validateOr(func: () -> Unit) {
        if (state == State.validated || state == State.computed) return
        if (state == State.error) {
            func()
            return
        }
        if (a < 3.0) {
            state = State.error
            error = BOUNDS_LESS_ERROR_A
            func()
            return
        }
        if (a > 42.0) {
            state = State.error
            error = BOUNDS_MORE_ERROR_A
            func()
            return
        }
        if (b < 3.0) {
            state = State.error
            error = BOUNDS_LESS_ERROR_B
            func()
            return
        }
        if (b > 42.0) {
            state = State.error
            error = BOUNDS_MORE_ERROR_B
            func()
            return
        }
        state = State.validated
        return
    }

    infix fun process(func: (Double) -> Unit) {
        if (state == State.computed) {
            func(result)
        }
    }
}

fun scenario(init: Scenario.() -> Unit): Scenario {
    val scenario = Scenario()
    scenario.init()
    return scenario
}

