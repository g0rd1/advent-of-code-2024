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
        report.getSublists().forEach { subReport ->
            val increased = subReport[0] < subReport[subReport.size - 1]
            for (index in 1..<subReport.size) {
                if (increased) {
                    if (subReport[index - 1] >= subReport[index] || subReport[index] - subReport[index - 1] > 3) {
                        return@forEach
                    }
                } else {
                    if (subReport[index] >= subReport[index - 1] || subReport[index - 1] - subReport[index] > 3) {
                        return@forEach
                    }
                }
            }
            return@count true
        }
        false
    }
    println(result)
}

private fun <T> List<T>.getSublists(): List<List<T>> {
    return indices.map { index1 ->
        buildList {
            this@getSublists.forEachIndexed { index2, y ->
                if (index1 != index2) add(y)
            }
        }
    }
}