package day_04

import java.io.File
import java.nio.file.Paths

fun main() {
    val path = Paths.get("").toAbsolutePath().toString() + "/src/day_04/input.txt"
    val input = File(path).readLines()

    val inputRotated90 = mutableListOf<String>()
    input.indices.forEach { index ->
        val builder = StringBuilder()
        input.forEach { line ->
            builder.append(line[index])
        }
        inputRotated90.add(builder.toString())
    }
    val inputRotated45 = mutableListOf<String>()
    val inputRotated135 = mutableListOf<String>()
    input.indices.forEach { lineIndex ->
        val builder45 = StringBuilder()
        val builder135 = StringBuilder()
        for (index in lineIndex downTo 0) {
            builder45.append(input[lineIndex - index][index])
            builder135.append(input[lineIndex - index][input.lastIndex - index])
        }
        inputRotated45.add(builder45.toString())
        inputRotated135.add(builder135.toString())
    }
    for (lineIndex in input.lastIndex downTo 1) {
        val builder45 = StringBuilder()
        val builder135 = StringBuilder()
        for (index in lineIndex..<input.size) {
            builder45.append(input[lineIndex + (input.lastIndex - index)][index])
            builder135.append(input[lineIndex + (input.lastIndex - index)][input.lastIndex - index])
        }
        inputRotated45.add(builder45.toString())
        inputRotated135.add(builder135.toString())
    }
    var result = 0
    (input + inputRotated90 + inputRotated45 + inputRotated135).forEach { line ->
        result += Regex("XMAS").findAll(line).count()
        result += Regex("SAMX").findAll(line).count()
    }
    println(result)
}