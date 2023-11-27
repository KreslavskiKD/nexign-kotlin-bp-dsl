import com.nexign.dsl.tryout.examples.arithmeticscenario.ArithmeticScenario


fun main(args: Array<String>) {
    val scenario = ArithmeticScenario(a = 12.0, b = 5.5)
    scenario.run {  }

    println(scenario.getScenarioDescription().toText())
}