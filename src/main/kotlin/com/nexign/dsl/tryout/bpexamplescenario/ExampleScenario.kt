package com.nexign.dsl.tryout.bpexamplescenario

import com.nexign.dsl.tryout.base.*
import com.nexign.dsl.tryout.bpexamplescenario.mock.Abonent
import com.nexign.dsl.tryout.bpexamplescenario.mock.Action

class ExampleScenario(
    private val abonent: Abonent,
    private val action: Action,
) : Scenario()  {

    // This part can probably be automated in Scenario class
    override val params: Map<String, Any>
        get() = mutableMapOf(
            "abonent" to abonent,
            "action" to action,
        )

    // Not sure if it looks pretty enough
    override val specification: Specification = specification {
        // This first setTransition probably can be lifted in abstract class, but I am not sure yet
        setTransition(START_EXECUTION(),
            GetAbonentInfo() next object : CheckAbonentActions() {
                // May be should be added some infix function for binary choices, because I guess it is a quite frequent thing to appear
                override val specification: Specification = specification {
                    setTransition(YES(), ProlongAction() next notifyAboutActionTimePeriod next end)
                    setTransition(NO(), ActivateAction() next object : WriteOffMoney() {
                        override val specification: Specification = specification {
                            setTransition(YES(), CancelActionActivation() next NotifyAction("error when activating action") next end)
                            setTransition(NO(), NotifyAction("action activation") next notifyAboutActionTimePeriod)
                        }
                    })
                }
            }

        )
    }

    companion object {

        // May be should be a global constant
        val end = object : Operation() {
            override val func: Scenario.() -> TransitionCondition = { STOP_EXECUTION() }
        }
        val notifyAboutActionTimePeriod = NotifyAction("action time period") next end
    }
}