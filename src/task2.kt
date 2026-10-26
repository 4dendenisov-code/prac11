package task2

data class Product(val id: Int, val name: String, val price: Double, val category: String, val rating: Double)

fun shopReport(products: List<Product>) {
    println("1. Товары по цене (по убыванию):")
    products.sortedByDescending { it.price }.forEach { println("  ${it.name} - ${it.price}₽") }

    println("\n2. Товары с рейтингом >= 4.5:")
    products.filter { it.rating >= 4.5 }.forEach { println("  ${it.name} (${it.rating})") }

    val grouped = products.groupBy { it.category }

    println("\n3. Группировка по категориям:")
    grouped.forEach { (category, items) ->
        println("  $category: ${items.map { it.name }}")
    }

    println("\n4. Средняя цена по категориям:")
    grouped.mapValues { (_, items) -> items.map { it.price }.average() }
        .forEach { (category, avgPrice) -> println("  $category: ${String.format("%.2f", avgPrice)}₽") }

    println("\n5. Самый дорогой товар в каждой категории:")
    grouped.mapValues { (_, items) -> items.maxByOrNull { it.price } }
        .forEach { (category, product) -> println("  $category: ${product?.name} (${product?.price}₽)") }

    println("\n6. Общая статистика:")
    println("  Всего товаров: ${products.size}")
    println("  Общая стоимость: ${products.sumOf { it.price }}₽")
}

fun main() {
    val products = listOf(
        Product(1, "Ноутбук", 75000.0, "electronics", 4.8),
        Product(2, "Смартфон", 45000.0, "electronics", 4.6),
        Product(3, "Наушники", 5000.0, "electronics", 4.3),
        Product(4, "Хлеб", 50.0, "food", 4.9),
        Product(5, "Сыр", 800.0, "food", 4.7),
        Product(6, "Яблоки", 120.0, "food", 4.2),
        Product(7, "Футболка", 1500.0, "clothes", 4.1),
        Product(8, "Джинсы", 3500.0, "clothes", 4.5)
    )
    shopReport(products)
}