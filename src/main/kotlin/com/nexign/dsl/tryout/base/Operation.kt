package com.nexign.dsl.tryout.base

open class Operation {
    protected open val specification : Specification = Specification()
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

//    fun getSpecification(): SpecificationNode =
//        SpecificationNode(
//            operationName = this.javaClass.name,
//            nestedOperations = nextOperations.map { it.getSpecification() },
//            detailedDescription = ""            // here we will need to get some KDoc probably
//        )

    infix fun next(op: Operation) : Operation {
        specification[SINGLE_ROUTE()] = op
        return this
    }

    infix fun binary(binaryChoice: BinaryChoice) : Operation {
        specification[YES()] = binaryChoice.yesOperation
        specification[NO()] = binaryChoice.noOperation
        return this
    }
}

fun operation(init: Operation.() -> Unit) : Operation {
    val operation = Operation()
    operation.init()
    return operation
}

class BinaryChoice() {
    lateinit var yesOperation: Operation
    lateinit var noOperation: Operation

    fun yes(op: Operation) {
        yesOperation = op
    }

    fun no(op: Operation) {
        noOperation = op
    }
}

fun choice(init: BinaryChoice.() -> Unit) : BinaryChoice {
    val bc = BinaryChoice()
    bc.init()
    return bc
}
