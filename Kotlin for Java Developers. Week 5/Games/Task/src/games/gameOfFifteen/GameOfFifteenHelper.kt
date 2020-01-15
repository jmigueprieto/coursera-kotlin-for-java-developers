package games.gameOfFifteen

/*
 * This function should return the parity of the permutation.
 * true - the permutation is even
 * false - the permutation is odd
 * https://en.wikipedia.org/wiki/Parity_of_a_permutation

 * If the game of fifteen is started with the wrong parity, you can't get the correct result
 *   (numbers sorted in the right order, empty cell at last).
 * Thus the initial permutation should be correct.
 */
fun isEven(permutation: List<Int>): Boolean {
    var inversions = 0
    for (i in 0 until permutation.size - 1) {
        for (j in i + 1 until permutation.size) {
            if (permutation[j] < permutation[i]) {
                inversions++
            }
        }
    }

    return inversions % 2 == 0
}