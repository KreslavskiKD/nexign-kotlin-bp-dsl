package com.nexign.dsl.tryout

import com.nexign.dsl.tryout.arithmeticscenario.ComputePerimeter
import com.nexign.dsl.tryout.arithmeticscenario.ComputeSquare
import com.nexign.dsl.tryout.arithmeticscenario.ValidateOr
import com.nexign.dsl.tryout.base.Scenario

val arithmeticScenario1 = Scenario(
    params = mapOf(
        "a" to 3.9,
        "b" to 28.0,
    ),
    operations = listOf(
        ValidateOr {
            println("Failed to run scenario: " + it.message)
        },
        ComputeSquare(),
    )
)

val arithmeticScenario2 = Scenario(
    params = mapOf(
        "a" to 5.4,
        "b" to 6.6,
    ),
    operations = listOf(
        ValidateOr {
            println("Failed to run scenario: " + it.message)
        },
        ComputeSquare(),
    )
)

val arithmeticScenario3 = Scenario(
    params = mapOf(
        "a" to 41.0,
        "b" to 33.2,
    ),
    operations = listOf(
        ValidateOr {
            println("Failed to run scenario: " + it.message)
        },
        ComputeSquare(),
    )
)

val arithmeticScenarioErr1 = Scenario(
    params = mapOf(
        "a" to 0.5,
        "b" to 25.7,
    ),
    operations = listOf(
        ValidateOr {
            println("Failed to run scenario: " + it.message)
        },
        ComputeSquare(),
        ComputePerimeter(),
    )
)

