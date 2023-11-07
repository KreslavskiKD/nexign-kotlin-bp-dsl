package com.nexign.dsl.tryout.base

@DslMarker
annotation class ScenarioDSL

@ScenarioDSL
class Scenario(
    val params: Map<String, Any>,
    protected val operations: List<Operation>,
) : Operation(
    nestedOperations = operations,
    func = { ScenarioStatus.CONTINUE },
) {
    val results: MutableMap<String, Any> = mutableMapOf()

    infix fun run(processFunc: (Map<String, Any>) -> Unit) {
        start(this)
        processFunc(results)
    }
}


