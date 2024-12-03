package day_01

import java.io.File
import java.nio.file.Paths
import kotlin.math.abs

fun main() {
    val path = Paths.get("").toAbsolutePath().toString() + "/src/day_01/input.txt"
    val input = File(path).readLines()
    val firstValues = mutableListOf<Int>()
    val secondValues = mutableListOf<Int>()
    input.forEach { rawPair ->
        val (first, second) = rawPair.split("   ").let { it[0].toInt() to it[1].toInt() }
        firstValues.add(first)
        secondValues.add(second)
    }
    var result = 0
    val secondValueToCount: Map<Int, Int> = secondValues.groupingBy { it }.eachCount()
    firstValues.forEach {
        result += (secondValueToCount[it] ?: 0) * it
    }
    println(result)
}