package com.nexign.dsl.tryout

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class ScenarioTest {
    private val delta = 0.000005

    @Test
    fun testResultGroup() {
        for (scenarioPair in listOf(
            Pair(arithmeticScenario1, 109.2),
            Pair(arithmeticScenario2, 35.64),
            Pair(arithmeticScenario3, 1361.2),
        )) {
            scenarioPair.first.run { results ->
                assertEquals(results["square"] as Double, scenarioPair.second, delta)
            }
        }
    }

    @Test
    fun testResult2() {

    }

    @Test
    fun testResult3() {

    }

    @Test
    fun testError1() {

    }

    @Test
    fun testError2() {

    }

    @Test
    fun testError3() {

    }

    @Test
    fun testError4() {

    }
}