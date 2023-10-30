import com.nexign.dsl.tryout.scenario

fun main(args: Array<String>) {

    scenario {
        consume(25.0, 4.0)
        validateOr {
            println(error)
        }
        computeSquare()
    } process { result ->
        println(result)
    }

    scenario {
        consume(1.0, 4.0)
        validateOr {
            println(error)
        }
        computeSquare()
    } process { result ->
        println(result)
    }

}