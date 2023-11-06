import com.nexign.dsl.tryout.arithmeticscenario.ComputePerimeter
import com.nexign.dsl.tryout.arithmeticscenario.ComputeSquare
import com.nexign.dsl.tryout.arithmeticscenario.ValidateOr
import com.nexign.dsl.tryout.base.Scenario


fun main(args: Array<String>) {
    val arithmeticScenario = Scenario(
        params = mapOf(
            "a" to 3.9,
            "b" to 28.0,
        ),
        operations = listOf(
            ValidateOr {
                println("Failed to run scenario: " + it.message)
            },
            ComputeSquare(),
            ComputePerimeter(),
        )
    ) run { results ->
        println("Square is " + results["square"])
        println("Perimeter is " + results["perimeter"])
    }

    val arithmeticScenario2 = Scenario(
        params = mapOf(
            "a" to 0.5,
            "b" to 25.7,
        ),
        operations = listOf(
            ValidateOr {
                println("Failed to run scenario: " + it.message)
            },
            ComputeSquare(),
            ComputePerimeter(),
        )
    ) run { results ->
        println("Square is " + results["square"])
    }
}