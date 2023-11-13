package com.nexign.dsl.tryout.base

open class TransitionCondition

class SINGLE_ROUTE : TransitionCondition()

class STOP_EXECUTION : TransitionCondition()
class START_EXECUTION : TransitionCondition()

open class BinaryTC : TransitionCondition()

class YES : BinaryTC()
class NO : BinaryTC()