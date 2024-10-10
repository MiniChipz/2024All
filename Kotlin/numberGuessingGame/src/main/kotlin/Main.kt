fun main() {
    println("Guess a number from 1-10")
    while (true) {
        val answer: Int = kotlin.random.Random.nextInt(1, 11)
        val guess: Int? = readlnOrNull()?.toIntOrNull()

        if (guess in 1..10 && guess != null) {
            if (guess == answer) {
                println("Du gættede rigtigt! Svaret er $answer")
                break
            } else {
                println("Forkert! Prøv igen")
            }
        } else { println("Du skal skrive et tal fra 1-10!") }
    }
}
