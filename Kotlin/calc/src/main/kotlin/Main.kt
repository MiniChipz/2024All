fun main() {
    println("Hvad skal udregnes?")
    while (true) {
        val input = StringBuilder(readln())
        val symbols: Array<String> = arrayOf("+", "-", "*", "/")
        var symbol: Char? = null
        for (i in input) {
            if (i.toString() in symbols) {
                symbol = i
                break
            }
        }
        if (symbol == null) {
            println("Du skal skrive et regne stykke")
        } else {
            val parts = input.split(" ")

            val numbers: List<Int?> = parts.filter { it != symbol.toString() && it.isNotBlank() }
                .map { it.toIntOrNull() }
            if (numbers.size == 2 && numbers[0] != null && numbers[1] != null) {
                val num1: Int = numbers[0]!!
                val num2: Int = numbers[1]!!

                val facit = when (symbol) {
                    '+' -> num1 + num2
                    '-' -> num1 - num2
                    '*' -> num1 * num2
                    '/' -> if (num2 != 0) num1 / num2 else "Kan ikke dividere med 0"
                    else -> "Du skal skrive et regne stykke"
                }
                println("$num1 $symbol $num2 = $facit")
            }
        }
    }
}