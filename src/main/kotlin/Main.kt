import com.nexign.dsl.examples.arithmeticscenario.ArithmeticScenario
import com.nexign.dsl.examples.bpscenario.ExampleScenario
import com.nexign.dsl.examples.bpscenario.mock.Abonent
import com.nexign.dsl.examples.bpscenario.mock.Action


fun main(args: Array<String>) {
    val scenario = ArithmeticScenario(
        mutableMapOf(
            "a" to 12.0,
            "b" to 5.5
        )
    )
    scenario.run {  }

    println(scenario.getScenarioDescription().toText())

    scenario.getLastRun().forEach {
        println(it.description)
    }

//    val scenario2 = ExampleScenario(
//        mutableMapOf(
//            "abonent" to Abonent("abonent"),
//            "action" to Action("action"),
//        )
//    )
//    scenario2.run {  }
//    println("\n\n" + scenario2.getScenarioDescription().toText())
}
