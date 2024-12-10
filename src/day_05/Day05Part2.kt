package day_05

import java.io.File
import java.nio.file.Paths

fun main() {
    val path = Paths.get("").toAbsolutePath().toString() + "/src/day_05/input.txt"
    val input = File(path).bufferedReader().lineSequence()
    val pageNumberToAfterPages = mutableMapOf<Int, MutableSet<Int>>()
    var pageRules = true
    val unsortedPagesLists = mutableListOf<List<Int>>()
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
                        unsortedPagesLists.add(pageNumbers)
                        return@globalForEach
                    }
                }
            }
        }
    }
    var result = 0
    unsortedPagesLists.forEach { unsortedPages ->
        val sortedPages = unsortedPages.sortedWith { o1, o2 ->
            val afterPages1 = pageNumberToAfterPages[o1] ?: emptySet()
            val afterPages2 = pageNumberToAfterPages[o2] ?: emptySet()
            when {
                o2 in afterPages1 -> -1
                o1 in afterPages2 -> 1
                else -> 0
            }
        }
        result += sortedPages[sortedPages.size / 2]
    }
    println(result)
}