import kotlin.system.measureNanoTime

fun main() {
    fibonacci()
//    speedTest(1_000_000)
}

fun fibonacci() {
    var fib1: Long = 0
    var fib2: Long = 1
    var total_number: Long = 0L

    var time = measureNanoTime {
        while (total_number < 1000) {
            val fib3: Long = fib1 + fib2
            fib1 = fib2.also { fib2 = fib1 }
            fib2 = fib3
            

            total_number++

            println("Fibber: $fib2")
            println("Total: $total_number")
        }
    }
    time /= 1000
    println("It took: $time microseconds")
}

fun speedTest(amount: Int) {
    var time = measureNanoTime {
        for (i in 1..amount) {

        }
    }
    time /= 1000
    println("It took: $time microseconds\nIt counted to $amount")
}