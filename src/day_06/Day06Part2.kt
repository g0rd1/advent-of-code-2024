package day_06

import java.io.File
import java.nio.file.Paths

fun main() {
    val path = Paths.get("").toAbsolutePath().toString() + "/src/day_06/input.txt"
    val input = File(path).readLines()
    var maze: MutableList<MutableList<String>> = input
        .map { line -> line.toCharArray().map { it.toString() }.toMutableList() }
        .toMutableList()
    var guardCoordinate: Coordinate? = null
    run {
        (input.indices).forEach { y ->
            (input.indices).forEach { x ->
                if (maze[y][x] == "^") {
                    guardCoordinate = Coordinate(x, y)
                    return@run
                }
            }
        }
    }
    var currentDirection = Direction.UP
    val routeCoordinates = mutableSetOf<Coordinate>()
    var currentCoordinate = guardCoordinate!!
    while (true) {
        val nextCoordinate = currentCoordinate.getShifted(currentDirection, input.size) ?: break
        val (nextX, nextY) = nextCoordinate
        if (maze[nextY][nextX] == "#") {
            currentDirection = currentDirection.getNext()
            continue
        }
        currentCoordinate = nextCoordinate
        routeCoordinates.add(nextCoordinate)
    }
    var result = 0
    routeCoordinates.forEach { routeCoordinate ->
        maze = input
            .map { line -> line.toCharArray().map { it.toString() }.toMutableList() }
            .toMutableList()
        maze[routeCoordinate.y][routeCoordinate.x] = "#"
        val newRoutedCoordinates = mutableSetOf<Pair<Coordinate, Direction>>()
        currentDirection = Direction.UP
        currentCoordinate = guardCoordinate!!
        while (true) {
            val nextCoordinate = currentCoordinate.getShifted(currentDirection, input.size) ?: break
            if ((nextCoordinate to currentDirection) in newRoutedCoordinates) {
                result++
                break
            }
            val (nextX, nextY) = nextCoordinate
            if (maze[nextY][nextX] == "#") {
                currentDirection = currentDirection.getNext()
                continue
            }
            currentCoordinate = nextCoordinate
            newRoutedCoordinates.add(nextCoordinate to currentDirection)
        }
    }

    println(result)
}
