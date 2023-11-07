package com.nexign.dsl.tryout.base

import com.nexign.dsl.tryout.specification.SpecificationNode

open class Operation (
    protected val nestedOperations: List<Operation>,
    protected val func: Scenario.() -> ScenarioStatus
) {
    fun start(scenario: Scenario): ScenarioStatus {
        var cont = scenario.func()
        if (cont == ScenarioStatus.STOP) return cont
        for (op in nestedOperations) {
            cont = op.start(scenario)
            if (cont == ScenarioStatus.STOP) return cont
        }
        return ScenarioStatus.CONTINUE
    }

    fun getSpecification(): SpecificationNode =
        SpecificationNode(
            operationName = this.javaClass.name,
            nestedOperations = nestedOperations.map { it.getSpecification() },
            detailedDescription = ""            // here we will need to get some KDoc probably
        )
}