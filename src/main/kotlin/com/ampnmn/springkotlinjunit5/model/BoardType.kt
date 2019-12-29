package com.ampnmn.springkotlinjunit5.model

/**
 * Board type
 */
enum class Type(val size: Int, val maxNumber: Int = size * size) {
    TWO_BY_TWO(2), THREE_BY_THREE(3), FOUR_BY_FOUR(4), FIVE_BY_FIVE(5);

    fun boxTopIndexes(): List<CellIndex> = (1..maxNumber step size).map { x ->
        (1..maxNumber step size).map { y -> CellIndex(x, y) }
    }.flatten()
}
