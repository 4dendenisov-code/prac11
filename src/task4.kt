package task4

data class Student(val name: String, val group: String, val scores: List<Int>)

fun getAverageScore(student: Student): Double {
    return student.scores.average()
}

fun classReport(students: List<Student>) {
    println("1. Студенты по среднему баллу (по убыванию):")
    students.sortedByDescending { getAverageScore(it) }.forEach {
        println("  ${it.name} (${it.group}) - ${String.format("%.2f", getAverageScore(it))}")
    }

    val grouped = students.groupBy { it.group }

    println("\n2. Лучший студент в каждой группе:")
    grouped.mapValues { (_, list) -> list.maxByOrNull { getAverageScore(it) } }
        .forEach { (group, student) ->
            println("  $group: ${student?.name} (${String.format("%.2f", student?.let { getAverageScore(it) } ?: 0.0)})")
        }

    println("\n3. Средний балл по каждой группе:")
    grouped.mapValues { (_, list) -> list.flatMap { it.scores }.average() }
        .forEach { (group, avg) -> println("  $group: ${String.format("%.2f", avg)}") }

    println("\n4. Отличники (средний балл >= 4.0):")
    students.filter { getAverageScore(it) >= 4.0 }.forEach { println("  ${it.name}") }

    println("\n5. Должники (есть хотя бы одна двойка):")
    students.filter { it.scores.any { score -> score == 2 } }.forEach { println("  ${it.name}") }
}

fun main() {
    val students = listOf(
        Student("Иван", "A", listOf(5, 4, 5, 4)),
        Student("Мария", "A", listOf(5, 5, 5, 5)),
        Student("Петр", "A", listOf(3, 2, 4, 3)),
        Student("Анна", "B", listOf(4, 4, 3, 5)),
        Student("Сергей", "B", listOf(2, 3, 2, 4)),
        Student("Ольга", "B", listOf(5, 4, 5, 5))
    )
    classReport(students)
}