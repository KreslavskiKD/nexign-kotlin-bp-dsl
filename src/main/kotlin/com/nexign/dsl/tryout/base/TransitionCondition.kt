package com.nexign.dsl.tryout.base

open class TransitionCondition

object SINGLE_ROUTE : TransitionCondition()

object STOP_EXECUTION : TransitionCondition()
object START_EXECUTION : TransitionCondition()

open class BinaryTC : TransitionCondition()

object YES : BinaryTC()
object NO : BinaryTC()

open class NumberedTC(val number: Int) : TransitionCondition()

object NumberedTCMap {
    val numberedTCs = mutableMapOf<Int, NumberedTC>()
}

fun getNumberedTC(num : Int) : TransitionCondition {
    return if (NumberedTCMap.numberedTCs.containsKey(num)) {
        NumberedTCMap.numberedTCs[num]!!
    } else {
        val ntc = NumberedTC(num)
        NumberedTCMap.numberedTCs[num] = ntc
        ntc
    }
}
