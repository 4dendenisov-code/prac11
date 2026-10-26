package task1

fun analyzeNumbers(numbers: List<Int>): Map<String, List<Int>> {
    return mapOf(
        "even" to numbers.filter { it % 2 == 0 },
        "odd" to numbers.filter { it % 2 != 0 },
        "divisibleBy3" to numbers.filter { it % 3 == 0 },
        "greaterThan50" to numbers.filter { it > 50 }
    )
}

fun main() {
    val numbers = listOf(12, 45, 67, 88, 91, 24, 33, 50, 76, 15, 99, 3, 42, 60, 8)
    val analysis = analyzeNumbers(numbers)

    for ((key, values) in analysis) {
        println("Категория: $key")
        println("  Числа: $values")
        println("  Количество: ${values.size}")
        println("  Сумма: ${values.sum()}")
        println("-------------------")
    }
}