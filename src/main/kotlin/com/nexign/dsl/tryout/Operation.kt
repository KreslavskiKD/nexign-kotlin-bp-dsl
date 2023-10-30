package com.nexign.dsl.tryout

@DslMarker
annotation class ScenarioDSL

@ScenarioDSL
abstract class Operation {
    protected var a: Double = 0.0
    protected var b: Double = 0.0

    protected var state: State = State.initial

    var error: String = ""
        protected set
    var result: Double = 0.0
        protected set

    fun consume(a: Double, b: Double) {
        this.a = a
        this.b = b
        state = State.modified
    }
}


