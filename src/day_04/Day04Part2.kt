package day_04

import java.io.File
import java.nio.file.Paths

fun main() {
    val path = Paths.get("").toAbsolutePath().toString() + "/src/day_04/input.txt"
    val input = File(path).readLines()
    var result = 0
    (0..input.size - 3).forEach { lineIndex ->
        (0..input.size - 3).forEach { index ->
            val line1 = "${input[lineIndex][index]}${input[lineIndex + 1][index + 1]}${input[lineIndex + 2][index + 2]}"
            val line2 = "${input[lineIndex][index + 2]}${input[lineIndex + 1][index + 1]}${input[lineIndex + 2][index]}"
            if ((line1 == "MAS" || line1 == "SAM") && (line2 == "MAS" || line2 == "SAM")) result++
        }
    }
    println(result)
}