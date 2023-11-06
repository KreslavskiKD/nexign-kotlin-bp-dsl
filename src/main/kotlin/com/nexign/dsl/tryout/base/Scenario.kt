package com.nexign.dsl.tryout

@DslMarker
annotation class ScenarioDSL

@ScenarioDSL
abstract class Scenario (
    protected val params: Map<String, Any>,
    protected val operations: List<Operation>,
) {
    protected var results: MutableMap<String, Any> = mutableMapOf()

    fun getExecutionHistory() {

    }

    fun generateSpecification() {

    }
}


