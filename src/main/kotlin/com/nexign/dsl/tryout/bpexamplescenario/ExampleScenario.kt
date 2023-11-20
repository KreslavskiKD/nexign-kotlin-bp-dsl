package com.nexign.dsl.tryout.bpexamplescenario

import com.nexign.dsl.tryout.base.*
import com.nexign.dsl.tryout.bpexamplescenario.mock.Abonent
import com.nexign.dsl.tryout.bpexamplescenario.mock.Action

class ExampleScenario(
    private val abonent: Abonent,
    private val action: Action,
) : Scenario()  {

    // This part can probably be automated in Scenario class, but I am not yet sure how
    override val params: Map<String, Any>
        get() = mutableMapOf(
            "abonent" to abonent,
            "action" to action,
        )

    // Made it a little prettier, than before
    override val specification: Specification = specification (
            GetAbonentInfo() next CheckAbonentActions() binary {
                yes(ProlongAction() next notifyAboutActionTimePeriod next end)
                no(ActivateAction() next WriteOffMoney() multiple {
                    +(YES() to (CancelActionActivation() next NotifyAction("error when activating action") next end))
                    +(NO() to (NotifyAction("action activation") next notifyAboutActionTimePeriod))
                    // or
                    +(1 to (CancelActionActivation() next NotifyAction("error when activating action") next end))
                    +(2 to (NotifyAction("action activation") next notifyAboutActionTimePeriod))
                    +(3 to end)
                    // ... and so on
                })
            }
    )


    companion object {

        // May be should be a global constant
        val end = object : Operation() {
            override val func: Scenario.() -> TransitionCondition = { STOP_EXECUTION() }
        }

        val notifyAboutActionTimePeriod = NotifyAction("action time period") next end
    }
}
