package com.nexign.dsl.tryout.base

import com.nexign.dsl.tryout.specification.SpecificationNode

open class Operation (
    protected val nestedOperations: List<Operation>,
    protected val func: Scenario.() -> Boolean
) {
    fun start(scenario: Scenario): Boolean {
        var cont = scenario.func()
        if (!cont) {
            return false
        }
        for (op in nestedOperations) {
            cont = op.start(scenario)
            if (!cont) {
                return false
            }
        }
        return true
    }

    fun getSpecification(): SpecificationNode =
        SpecificationNode(
            operationName = this.javaClass.name,
            nestedOperations = nestedOperations.map { it.getSpecification() },
            detailedDescription = ""            // here we will need to get some KDoc probably
        )
}