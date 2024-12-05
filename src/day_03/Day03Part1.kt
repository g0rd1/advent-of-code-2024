package day_03

import java.io.File
import java.nio.file.Paths

fun main() {
    val path = Paths.get("").toAbsolutePath().toString() + "/src/day_03/input.txt"
    val input = File(path).readText()
    val mulPatter = Regex("mul\\((\\d{1,3}),(\\d{1,3})\\)")
    val mulCommandsMathResults = mulPatter.findAll(input)
    var result = 0
    mulCommandsMathResults.forEach { matchResult ->
        val x: Int = matchResult.groupValues[1].toInt()
        val y: Int = matchResult.groupValues[2].toInt()
        result += x * y
    }
    println(result)
}