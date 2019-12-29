package com.ampnmn.springkotlinjunit5.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BoardTest {

    private fun Array<Array<Int>>.toPairs() = this.mapIndexed { y, row ->
        row.mapIndexed { x, expectedNumber -> CellIndex(x + 1, y + 1) to expectedNumber }
    }.flatten()

    @Test
    fun isFilled() {
        val notFilledBoard = Board3x3()
        notFilledBoard.init(arrayOf(
                arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1),
                arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1),
                arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1),

                arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1),
                arrayOf(1, 1, 1, 1, 0, 1, 1, 1, 1),
                arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1),

                arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1),
                arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1),
                arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1)
        ))
        assertFalse(notFilledBoard.isFilled())

        val filledBoard = Board3x3()
        filledBoard.init(arrayOf(
                arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1),
                arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1),
                arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1),

                arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1),
                arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1),
                arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1),

                arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1),
                arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1),
                arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1)
        ))
        assertTrue(filledBoard.isFilled())
    }

    @Test
    fun fillAuto_3x3() {
        val board = Board3x3()
        board.init(arrayOf(
                arrayOf(9, 2, 0, 0, 1, 0, 3, 0, 0),
                arrayOf(8, 5, 0, 0, 9, 0, 0, 2, 0),
                arrayOf(0, 0, 3, 0, 0, 0, 0, 0, 0),

                arrayOf(0, 0, 0, 0, 0, 2, 0, 0, 0),
                arrayOf(3, 0, 0, 0, 0, 1, 6, 0, 0),
                arrayOf(1, 9, 7, 0, 0, 0, 2, 5, 0),

                arrayOf(0, 0, 0, 5, 0, 9, 0, 6, 2),
                arrayOf(0, 8, 5, 0, 2, 0, 4, 0, 0),
                arrayOf(0, 0, 9, 7, 4, 0, 0, 3, 0)
        ))

        board.fillAuto()

        arrayOf(
                arrayOf(9, 2, 6, 8, 1, 7, 3, 4, 5),
                arrayOf(8, 5, 1, 3, 9, 4, 7, 2, 6),
                arrayOf(4, 7, 3, 2, 6, 5, 8, 9, 1),

                arrayOf(5, 6, 8, 4, 7, 2, 9, 1, 3),
                arrayOf(3, 4, 2, 9, 5, 1, 6, 8, 7),
                arrayOf(1, 9, 7, 6, 3, 8, 2, 5, 4),

                arrayOf(7, 3, 4, 5, 8, 9, 1, 6, 2),
                arrayOf(6, 8, 5, 1, 2, 3, 4, 7, 9),
                arrayOf(2, 1, 9, 7, 4, 6, 5, 3, 8)
        ).toPairs().forEach { (index, expected) ->
            assertEquals(expected, board[index].number)
        }
    }
}
