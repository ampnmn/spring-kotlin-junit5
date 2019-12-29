package com.ampnmn.springkotlinjunit5.controller.form

import com.ampnmn.springkotlinjunit5.model.Board3x3
import com.ampnmn.springkotlinjunit5.model.CellIndex

class Board3x3Form constructor() {
    private fun Array<Array<Int>>.toCellIndexes() = this.mapIndexed { y, it ->
        it.mapIndexed { x, _ -> CellIndex(x + 1, y + 1) }
    }.flatten()

    var board: Array<Array<Int>> = Array(3 * 3) { Array(3 * 3) { 0 } }

    fun setBoard(board: Board3x3) = apply {
        this.board.toCellIndexes().forEach { index ->
            this.board[index.y - 1][index.x - 1] = board[index].number
        }
    }
}
