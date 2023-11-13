package com.nexign.dsl.tryout.base

@DslMarker
annotation class SpecificationDSL

@SpecificationDSL
class Specification : HashMap<TransitionCondition, Operation>() {

    fun setTransition(tc: TransitionCondition, operation: Operation) {
        this[tc] = operation
    }
}

fun specification(init: Specification.() -> Unit) : Specification {
    val spec = Specification()
    spec.init()
    return spec
}