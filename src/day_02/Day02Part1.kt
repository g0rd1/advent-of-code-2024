package day_02

import java.io.File
import java.nio.file.Paths

fun main() {
    val path = Paths.get("").toAbsolutePath().toString() + "/src/day_02/input.txt"
    val input = File(path).readLines()
    val reports = input.map { rawReport ->
        rawReport.split(" ").map { it.toInt() }
    }
    val result = reports.count { report ->
        val increased = report[0] < report[1]
        for (index in 1..<report.size) {
            if (increased) {
                if (report[index - 1] >= report[index]) return@count false
                if (report[index] - report[index - 1] > 3) return@count false
            } else {
                if (report[index] >= report[index - 1]) return@count false
                if (report[index - 1] - report[index] > 3) return@count false
            }
        }
        true
    }
    println(result)
}