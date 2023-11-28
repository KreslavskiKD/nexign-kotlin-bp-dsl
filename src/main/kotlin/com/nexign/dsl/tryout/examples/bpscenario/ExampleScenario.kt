package com.nexign.dsl.tryout.examples.bpscenario

import com.nexign.dsl.tryout.base.*
import com.nexign.dsl.tryout.examples.bpscenario.mock.Abonent
import com.nexign.dsl.tryout.examples.bpscenario.mock.Action

class ExampleScenario(
    private val abonent: Abonent,
    private val action: Action,
) : Scenario()  {

    // This part can probably be automated in Scenario class, but I am not yet sure how
    override val storage: MutableMap<String, Any> = mutableMapOf(
            "abonent" to abonent,
            "action" to action,
        )

    override val specification: Specification = specification (
            GetAbonentInfo() next CheckAbonentActions() binary {
                yes(ProlongAction() next notifyAboutActionTimePeriod next end)
                no(ActivateAction() next WriteOffMoney() multiple {
                    +(YES to (CancelActionActivation() next NotifyAction("error when activating action") next end))
                    +(NO to (NotifyAction("action activation") next notifyAboutActionTimePeriod))
                })
            }
    )


    companion object {

        // May be should be a global constant
        val end = object : Operation() {
            override val func: Scenario.() -> TransitionCondition = { STOP_EXECUTION }
        }

        val notifyAboutActionTimePeriod = NotifyAction("action time period") next end
    }
}
