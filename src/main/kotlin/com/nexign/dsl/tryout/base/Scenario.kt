package com.nexign.dsl.tryout.base

abstract class Scenario : Operation() {
    open val params: Map<String, Any> = mapOf()
    val results: MutableMap<String, Any> = mutableMapOf()

    override val func: Scenario.() -> TransitionCondition
        get() = { START_EXECUTION }

    infix fun run(processFunc: (Map<String, Any>) -> Unit) {
        start(this)
        processFunc(results)
    }
}


