package com.ampnmn.springkotlinjunit5.model

/**
 * Cell
 */
data class Cell(val cellIndex: CellIndex, var number: Int) {

    companion object {
        const val EMPTY = 0
    }

    val isEmpty: Boolean get() = number == EMPTY
}
