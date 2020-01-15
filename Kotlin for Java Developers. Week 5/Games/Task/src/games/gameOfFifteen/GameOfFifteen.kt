package games.gameOfFifteen

import board.Cell
import board.Direction
import board.createGameBoard
import games.game.Game

fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game =
        GameOfFifteen(initializer)

class GameOfFifteen(private val initializer: GameOfFifteenInitializer) : Game {

    private val board = createGameBoard<Int?>(4)

    override fun initialize() {
        val values = initializer.initialPermutation

        for (i in 0 until lastIndex()) {
            board[board.getCell((i / board.width) + 1, (i % board.width) + 1)] = values[i]
        }
    }

    override fun canMove() = true

    override fun hasWon(): Boolean {
        var value = 1
        for (i in 0 until lastIndex()) {
            if (this[(i / board.width) + 1, (i % board.width) + 1] != value) {
                return false
            }
            value++
        }

        return true
    }

    override fun processMove(direction: Direction) {
        val nullCell = board.getAllCells().first { get(it.i, it.j) == null }

        var neighbour: Cell?
        board.apply { neighbour = nullCell.getNeighbour(direction.reversed()) }

        if (neighbour == null)
            return

        board[nullCell] = board[neighbour!!]
        board[neighbour!!] = null

    }

    override fun get(i: Int, j: Int): Int? = board[board.getCell(i, j)]

    private fun lastIndex() = board.width * board.width - 1
}


