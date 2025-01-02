package day_06

import java.io.File
import java.nio.file.Paths

fun main() {
    val path = Paths.get("").toAbsolutePath().toString() + "/src/day_06/input.txt"
    val input = File(path).readLines()
    val maze: MutableList<MutableList<String>> = input
        .map { line -> line.toCharArray().map { it.toString() }.toMutableList() }
        .toMutableList()
    var currentCoordinate: Coordinate? = null
    run {
        (input.indices).forEach { y ->
            (input.indices).forEach { x ->
                if (maze[y][x] == "^") {
                    currentCoordinate = Coordinate(x, y)
                    return@run
                }
            }
        }
    }
    var currentDirection = Direction.UP
    while (true) {
        val (currentX, currentY) = currentCoordinate!!
        maze[currentY][currentX] = "X"
        val nextCoordinate = currentCoordinate!!.getShifted(currentDirection, input.size) ?: break
        val (nextX, nextY) = nextCoordinate
        if (maze[nextY][nextX] == "#") {
            currentDirection = currentDirection.getNext()
            continue
        }
        currentCoordinate = nextCoordinate
    }
    println(maze.flatten().count { it == "X" })
    println(maze.joinToString("\n"))
}

fun Coordinate.getShifted(direction: Direction, size: Int): Coordinate? {
    val (x, y) = this
    return when (direction) {
        Direction.LEFT -> if (x - 1 < 0) null else Coordinate(x - 1, y)
        Direction.RIGHT -> if (x + 1 >= size) null else Coordinate(x + 1, y)
        Direction.DOWN -> if (y + 1 >= size) null else Coordinate(x, y + 1)
        Direction.UP -> if (y - 1 < 0) null else Coordinate(x, y - 1)
    }
}

fun Direction.getNext(): Direction {
    return when (this) {
        Direction.LEFT -> Direction.UP
        Direction.RIGHT -> Direction.DOWN
        Direction.UP -> Direction.RIGHT
        Direction.DOWN -> Direction.LEFT
    }
}

data class Coordinate(val x: Int, val y: Int)

enum class Direction {
    LEFT, RIGHT, UP, DOWN;
}