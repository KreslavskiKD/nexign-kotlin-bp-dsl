package com.nexign.dsl.tryout

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class ScenarioTest {
    private val delta = 0.000005

    @Test
    fun testResult1() {
        val expected = 100.0
        scenario {
            consume(25.0, 4.0)
            validateOr {
                println(error)
                assert(false) { error }
            }
            computeSquare()
        } process { result ->
            assertEquals(expected, result, delta)
        }
    }

    @Test
    fun testResult2() {
        val expected = 47.9856
        scenario {
            consume(15.38, 3.12)
            validateOr {
                println(error)
                assert(false) { error }
            }
            computeSquare()
        } process { result ->
            assertEquals(expected, result, delta)
        }
    }

    @Test
    fun testResult3() {
        val expected = 357.6552
        scenario {
            consume(8.54, 41.88)
            validateOr {
                println(error)
                assert(false) { error }
            }
            computeSquare()
        } process { result ->
            assertEquals(expected, result, delta)
        }
    }

    @Test
    fun testError1() {
        val expected = Errors.BOUNDS_LESS_ERROR_A
        scenario {
            consume(1.24, 41.88)
            validateOr {
                println(error)
                assertEquals(expected, error)
            }
            computeSquare()
        } process { result ->
            assert(false)
        }
    }

    @Test
    fun testError2() {
        val expected = Errors.BOUNDS_LESS_ERROR_B
        scenario {
            consume(8.45, 0.26)
            validateOr {
                println(error)
                assertEquals(expected, error)
            }
            computeSquare()
        } process { result ->
            assert(false)
        }
    }

    @Test
    fun testError3() {
        val expected = Errors.BOUNDS_MORE_ERROR_B
        scenario {
            consume(8.84, 42.08)
            validateOr {
                println(error)
                assertEquals(expected, error)
            }
            computeSquare()
        } process { result ->
            assert(false)
        }
    }

    @Test
    fun testError4() {
        val expected = Errors.BOUNDS_MORE_ERROR_A
        scenario {
            consume(121.777, 41.88)
            validateOr {
                println(error)
                assertEquals(expected, error)
            }
            computeSquare()
        } process { result ->
            assert(false)
        }
    }
}