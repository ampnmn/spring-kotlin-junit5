package com.ampnmn.springkotlinjunit5.model

/**
 * Board
 */
abstract class Board(boardType: BoardType) {
    private val cells: MutableList<Cell> = mutableListOf()
    private val size = boardType.size
    private val maxNumber = boardType.maxNumber
    private val boxTopIndexes = boardType.boxTopIndexes()

    /**
     * Access to cell with Board[x, y]
     */
    private operator fun get(x: Int, y: Int): Cell = cells.find { it.cellIndex == CellIndex(x, y) }
            ?: throw IllegalArgumentException("x:$x,y:$y")

    private operator fun set(x: Int, y: Int, number: Int) {
        this[x, y].number = number
    }

    /**
     * Access to cell with Board[CellIndex]
     */
    operator fun get(cellIndex: CellIndex): Cell = cells.find { it.cellIndex == cellIndex }
            ?: throw IllegalArgumentException("x:$cellIndex.x,y:$cellIndex.y")


    private operator fun set(cellIndex: CellIndex, number: Int) {
        this[cellIndex.x, cellIndex.y] = number
    }

    /**
     * Initialize using a two-dimensional array of numbers
     */
    fun init(value: Array<Array<Int>>) {
        if (cells.isNotEmpty()) throw IllegalStateException("Already initialized")
        value.forEachIndexed { y, row ->
            row.forEachIndexed { x, number ->
                cells.add(Cell(CellIndex(x + 1, y + 1), number))
            }
        }
        if (cells.size != maxNumber * maxNumber) throw IllegalArgumentException("Invalid number of cells:${cells.size}")
    }

    /**
     * Fill empty cells fully automatically
     */
    fun fillAuto() {
        var hasChange = false
        findSuggestions().filter { (_, numbers) -> numbers.size == 1 }
                .forEach { (index, numbers) ->
                    hasChange = true
                    this[index] = numbers.first()
                }
        if (!hasChange || isFilled()) return else fillAuto()
    }

    /**
     * Get the cell index that is still empty
     */
    private fun emptyCellIndices() = cells.filter { it.isEmpty }.map { it.cellIndex }

    /**
     * Find suggestions
     */
    private fun findSuggestions(): Map<CellIndex, List<Int>> {
        return emptyCellIndices().map { index ->
            (1..maxNumber)
                    .filterNot { number -> number in verticalCells(index.x) }
                    .filterNot { number -> number in horizontalCells(index.y) }
                    .filterNot { number -> number in boxCells(index.x, index.y) }
                    .map { index to it }
        }.flatten().groupBy(
                { (index, _) -> index }, { (_, number) -> number }
        )
    }

    fun isFilled(): Boolean = cells.none { it.isEmpty }

    private fun verticalCells(x: Int) = (1..maxNumber).map { y -> this[x, y].number }

    private fun horizontalCells(y: Int) = (1..maxNumber).map { x -> this[x, y].number }

    private fun boxCells(x: Int, y: Int) = boxTopIndexes.last { it.x <= x && it.y <= y }.let { boxTop ->
        (boxTop.x until boxTop.x + size).map { x ->
            (boxTop.y until boxTop.y + size).map { y -> this[x, y].number }
        }.flatten()
    }
}
