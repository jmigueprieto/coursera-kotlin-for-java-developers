package mastermind


data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    val secretArray = secret.toCharArray()
    val guessArray = guess.toCharArray()
    val rightPosition = 'R'
    val wrongPosition = 'W'

    secretArray.forEachIndexed { i, c ->
        if (guessArray[i] == c) {
            secretArray[i] = rightPosition
            guessArray[i] = rightPosition
        }
    }

    secretArray.forEachIndexed { i, c ->
        if (c != 'R' && guessArray.contains(c)) {
            secretArray[i] = wrongPosition
            guessArray[guessArray.indexOf(c)] = wrongPosition
        }
    }

    return Evaluation(secretArray.count { it == rightPosition },
            secretArray.count { it == wrongPosition })
}
