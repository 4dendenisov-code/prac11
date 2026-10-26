package task5

data class WordStats(val word: String, val count: Int, val length: Int)

fun analyzeText(text: String): Map<String, Any> {
    val cleanedText = text.replace(Regex("[^a-zA-Zа-яА-ЯёЁ\\s]"), " ").lowercase()
    val words = cleanedText.split(Regex("\\s+")).filter { it.length >= 3 }

    val totalWords = words.size
    val wordFrequency = words.groupingBy { it }.eachCount()
    val uniqueWords = wordFrequency.size
    val averageWordLength = if (totalWords > 0) words.sumOf { it.length }.toDouble() / totalWords else 0.0
    val longestWord = words.maxByOrNull { it.length } ?: ""

    val top5Words = wordFrequency.entries
        .sortedByDescending { it.value }
        .take(5)
        .map { WordStats(it.key, it.value, it.key.length) }

    return mapOf(
        "totalWords" to totalWords,
        "uniqueWords" to uniqueWords,
        "averageWordLength" to averageWordLength,
        "longestWord" to longestWord,
        "wordFrequency" to wordFrequency,
        "top5Words" to top5Words
    )
}

fun findPhrases(text: String, phraseLength: Int): List<String> {
    val cleanedText = text.replace(Regex("[^a-zA-Zа-яА-ЯёЁ\\s]"), " ").lowercase()
    val words = cleanedText.split(Regex("\\s+")).filter { it.isNotEmpty() }
    if (words.size < phraseLength) return emptyList()

    val phrases = mutableListOf<String>()
    for (i in 0..words.size - phraseLength) {
        phrases.add(words.subList(i, i + phraseLength).joinToString(" "))
    }

    return phrases.groupingBy { it }.eachCount()
        .entries
        .sortedByDescending { it.value }
        .take(3)
        .map { it.key }
}

fun main() {
    val text = "Кот съел рыбу. Кот съел мясо. Собака громко лаяла на кота. Кот съел сметану."
    val stats = analyzeText(text)

    println("Всего слов: ${stats["totalWords"]}")
    println("Уникальных слов: ${stats["uniqueWords"]}")
    println("Средняя длина слова: ${String.format("%.2f", stats["averageWordLength"])}")
    println("Самое длинное слово: ${stats["longestWord"]}")
    println("Частота слов: ${stats["wordFrequency"]}")
    println("Топ-5 слов:")
    @Suppress("UNCHECKED_CAST")
    (stats["top5Words"] as List<WordStats>).forEach { println("  ${it.word} - ${it.count} раз(а)") }

    println("\nТоп-3 биграммы:")
    println(findPhrases(text, 2))
}