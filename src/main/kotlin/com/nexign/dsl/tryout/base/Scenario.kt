package com.nexign.dsl.tryout.base

import com.nexign.dsl.tryout.base.description.OperationDescription
import com.nexign.dsl.tryout.base.description.ScenarioDescription

abstract class Scenario : Operation() {
    open val storage: MutableMap<String, Any> = mutableMapOf()

    override val func: Scenario.() -> TransitionCondition
        get() = { START_EXECUTION }

    infix fun run(processFunc: (Map<String, Any>) -> Unit) {
        start(this)
        processFunc(storage)
    }

    fun getScenarioDescription(): ScenarioDescription {
        val startingOperation = specification[START_EXECUTION]
            ?: throw IllegalStateException("No starting operation in scenario")     // TODO should be some custom exception

        var visited: Set<Operation> = setOf()
        val opsDescrs: MutableMap<Operation, OperationDescription> = mutableMapOf()

        var currentOps = listOf(startingOperation)
        var nextOps = mutableListOf<Operation>()

        opsDescrs[startingOperation] = startingOperation.getOperationDescription()

        while (currentOps.isNotEmpty()) {
            for (op in currentOps) {
                for (nop in op.specification) {
                    if (!visited.contains(nop.value)) {
                        opsDescrs[nop.value] = nop.value.getOperationDescription()
                        visited = visited.plus(nop.value)
                        nextOps.add(nop.value)
                    }

                    opsDescrs[op]?.transitions?.set(nop.key, opsDescrs[nop.value]!!)
                }

            }
            currentOps = nextOps
            nextOps = mutableListOf()
        }


        return ScenarioDescription(
            scenarioName = this.javaClass.simpleName,
            startingOperation = opsDescrs[startingOperation]!!,
            detailedDescription = ""            // here we will need to get some KDoc probably
        )
    }
}

@DslMarker
annotation class ScenarioDSL


