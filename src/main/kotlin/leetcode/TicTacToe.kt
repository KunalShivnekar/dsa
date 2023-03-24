package leetcode

class TicTacToe(n: Int) {
    var rows: IntArray
    var cols: IntArray
    var diagonal = 0
    var antiDiagonal = 0

    init {
        rows = IntArray(n)
        cols = IntArray(n)
    }

    fun move(row: Int, col: Int, player: Int): Int {
        val currentPlayer = if (player == 1) 1 else -1
        // update currentPlayer in rows and cols arrays
        rows[row] += currentPlayer
        cols[col] += currentPlayer
        // update diagonal
        if (row == col) {
            diagonal += currentPlayer
        }
        //update anti diagonal
        if (col == cols.size - row - 1) {
            antiDiagonal += currentPlayer
        }
        val n = rows.size
        // check if the current player wins
        return if (Math.abs(rows[row]) == n || Math.abs(cols[col]) == n || Math.abs(diagonal) == n || Math.abs(antiDiagonal) == n) {
            player
        } else 0
        // No one wins
    }
}