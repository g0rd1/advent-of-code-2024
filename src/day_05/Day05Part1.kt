package day_05

import java.io.File
import java.nio.file.Paths

fun main() {
    val path = Paths.get("").toAbsolutePath().toString() + "/src/day_05/input.txt"
    val input = File(path).bufferedReader().lineSequence()
    val pageNumberToAfterPages = mutableMapOf<Int, MutableSet<Int>>()
    var pageRules = true
    var result = 0
    input.forEach globalForEach@{ line ->
        if (line.isEmpty()) {
            pageRules = false
            return@globalForEach
        }
        if (pageRules) {
            val (beforeNumber, afterNumber) = line.split("|").map { it.toInt() }
            pageNumberToAfterPages.getOrPut(beforeNumber) { mutableSetOf(afterNumber) }.add(afterNumber)
        } else {
            val pageNumbers = line.split(",").map { it.toInt() }
            pageNumbers.indices.forEach { index ->
                val pageNumber = pageNumbers[index]
                val afterPages = pageNumberToAfterPages[pageNumber] ?: return@forEach
                (0..<index).forEach { innerIndex ->
                    if (pageNumbers[innerIndex] in afterPages) {
                        return@globalForEach
                    }
                }
            }
            result += pageNumbers[pageNumbers.size / 2]
        }
    }
    println(result)
}