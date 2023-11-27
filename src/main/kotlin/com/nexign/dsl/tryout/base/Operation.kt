package com.nexign.dsl.tryout.base

import com.nexign.dsl.tryout.base.description.OperationDescription

open class Operation {
    open val specification : Specification = Specification()
    protected open val func : Scenario.() -> TransitionCondition = { TransitionCondition() }

    fun start(scenario: Scenario) {
        val condition = scenario.func()
        if (condition is STOP_EXECUTION) {
            return
        }
        if (specification[condition] != null) {
            specification[condition]!!.start(scenario)
        } else {
            throw IllegalArgumentException() // TODO change to custom
        }
        return
    }

    fun getOperationDescription() : OperationDescription =
        OperationDescription(
            operationName = this.javaClass.simpleName,
            transitions = linkedMapOf(),
            detailedDescription = "",
        )

    infix fun next(op: Operation) : Operation {
        if (specification[SINGLE_ROUTE] == null) {
            specification[SINGLE_ROUTE] = op
        } else {
            var curLastOp = specification[SINGLE_ROUTE]
            var nextOp = curLastOp?.specification?.get(SINGLE_ROUTE)

            while (nextOp != null) {
                curLastOp = nextOp
                nextOp = curLastOp.specification[SINGLE_ROUTE]
            }

            if (curLastOp != null) {
                curLastOp.specification[SINGLE_ROUTE] = op
            }
        }

        return this
    }

    infix fun binary(init: BinaryChoice.() -> Unit) : Operation {
        val binaryChoice = BinaryChoice()
        binaryChoice.init()
        specification[YES] = binaryChoice.yesOperation
        specification[NO] = binaryChoice.noOperation
        return this
    }

    infix fun multiple(init: MultipleChoiceBuilder.() -> Unit) : Operation {
        val mc = MultipleChoiceBuilder()
        mc.init()
        for (p in mc.choices) {
            specification[p.first] = p.second
        }
        return this
    }
}

fun operation(init: Operation.() -> Unit) : Operation {
    val operation = Operation()
    operation.init()
    return operation
}

class BinaryChoice {
    lateinit var yesOperation: Operation
    lateinit var noOperation: Operation

    fun yes(op: Operation) {
        yesOperation = op
    }

    fun no(op: Operation) {
        noOperation = op
    }
}

class MultipleChoiceBuilder {
    val choices = mutableListOf<Pair<TransitionCondition, Operation>>()

    operator fun Pair<TransitionCondition, Operation>.unaryPlus() {
        choices += this
    }

    operator fun Pair<Int, Operation>.unaryMinus() {
        choices += Pair(NumberedTCMap.getNumberedTC(this.first), this.second)
    }
}