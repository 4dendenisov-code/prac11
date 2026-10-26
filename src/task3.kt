package task3

fun findSynonyms(dictionary: Map<String, List<String>>, word: String): List<String> {
    return dictionary[word] ?: emptyList()
}

fun addSynonym(dictionary: MutableMap<String, MutableList<String>>, word: String, synonym: String) {
    dictionary.getOrPut(word) { mutableListOf() }.add(synonym)
}

fun getAllWords(dictionary: Map<String, List<String>>): List<String> {
    val words = dictionary.keys.toList()
    val synonyms = dictionary.values.flatten()
    return (words + synonyms).distinct()
}

fun main() {
    val dictionary = mutableMapOf(
        "быстрый" to mutableListOf("стремительный", "скорый", "проворный"),
        "умный" to mutableListOf("сообразительный", "смышленый"),
        "красивый" to mutableListOf("прекрасный", "великолепный", "роскошный"),
        "большой" to mutableListOf("огромный", "гигантский"),
        "маленький" to mutableListOf("крошечный", "миниатюрный")
    )

    println("Синонимы к слову 'умный': ${findSynonyms(dictionary, "умный")}")
    println("Синонимы к слову 'грустный': ${findSynonyms(dictionary, "грустный")}")

    addSynonym(dictionary, "умный", "мудрый")
    addSynonym(dictionary, "грустный", "печальный")

    println("После добавления 'умный': ${findSynonyms(dictionary, "умный")}")
    println("Все уникальные слова: ${getAllWords(dictionary)}")
}