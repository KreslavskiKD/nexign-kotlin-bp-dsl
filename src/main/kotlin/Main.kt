import com.nexign.dsl.tryout.examples.arithmeticscenario.ArithmeticScenario
import com.nexign.dsl.tryout.examples.bpscenario.ExampleScenario
import com.nexign.dsl.tryout.examples.bpscenario.mock.Abonent
import com.nexign.dsl.tryout.examples.bpscenario.mock.Action


fun main(args: Array<String>) {
    val scenario = ArithmeticScenario(a = 12.0, b = 5.5)
    scenario.run {  }

    println(scenario.getScenarioDescription().toText())

    val scenario2 = ExampleScenario(abonent = Abonent("abonent"), action = Action("action"))
    scenario2.run {  }
    println("\n\n" + scenario2.getScenarioDescription().toText())
}