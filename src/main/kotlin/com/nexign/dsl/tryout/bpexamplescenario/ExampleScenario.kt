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
            GetAbonentInfo() next CheckAbonentActions() binary choice {
                yes(ProlongAction() next notifyAboutActionTimePeriod next end)
                no(ActivateAction() next WriteOffMoney() binary choice {
                    yes(CancelActionActivation() next NotifyAction("error when activating action") next end)
                    no(NotifyAction("action activation") next notifyAboutActionTimePeriod)
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
