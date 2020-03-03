import kotlin.math.absoluteValue

//Легенда
//Вы решили мотивировать пользователей генерировать больше контента (желательно платного).
// И внедрить следующую схему: чем на большую сумму скачано контента, созданного пользователем,
// тем меньше %, который берёт платформа с продаж.
//
//Условия следующие:
//
//Если предыдущая сумма продаж от 0 до 1 000, то % составляет 30% от суммы
//Если предыдущая сумма продаж от 1 001 до 10 000, то % составляет 25% от суммы
//Если предыдущая сумма продаж от 10 001 до 50 000, то % составляет 20% от суммы
//Если предыдущая сумма продаж от 50 001, то % составляет 15% от суммы
//Эксклюзивные авторы (т.е. авторы, публикующие контент только в нашей соц.сети, дополнительно получают -5% к налогам). Например, эксклюзивный автор, продавший на 11 000, будет платить не 20%, а 15%.
//
//Напишите функцию (назовите её calculateFee), которая на вход принимает стоимость текущей продажи, предыдущую сумму продаж и флаг эксклюзивности (можете задать его Boolean флагом exclusive).
//
//Пример вызова функции:
//
//val amount = 200 // стоимость текущей продажи
//val total = 11_000 // сумма предыдущих продаж
//val fee = calculateFee(200, 11_000)  // exclusive по умолчанию = false
//println(fee) // 40
//Продемонстрируйте вызов с выставленным флагом эксклюзивности и без него.

// Курс:      KT-7. Разработчик на Kotlin
// Задание:   Введение в язык, основные концепции, рабочее окружение
// Задача:    1 - Расчёт процента
// Файл:      hw1.kt
// Слушатель: Журкин Алексей Михайлович
// Дата:      03.03.2020
// Версия:    1.0

fun getPriceFee(totalPrice: Int) : Double {
    if (totalPrice < 0)
        throw IllegalArgumentException("Total price must equal or more then zero")

    return when (totalPrice) {
            in 0..1000 -> 0.3
            in 1001..10000 -> 0.25
            in 10001..50000 -> 0.2
            else -> 0.15
        }
}

fun getExclusiveFee(exclusive: Boolean=false): Double {
    val EXCLUSIVE_FEE = 0.05
    val REGULAR_FEE = 0.0
    return if (exclusive) EXCLUSIVE_FEE else REGULAR_FEE
}

fun calculateFee(amount: Int, totalPrice: Int, exclusive:Boolean=false): Double {
    if (amount < 0)
        throw IllegalArgumentException("Amount price must equal or more then zero")
    if (totalPrice < 0)
        throw IllegalArgumentException("Total price price must equal or more then zero")

    return amount*(getPriceFee(totalPrice)-getExclusiveFee(exclusive))
}

fun main() {

    println("Курс:      KT-7. Разработчик на Kotlin")
    println("Задание:   Введение в язык, основные концепции, рабочее окружение")
    println("Задача:    1 - Расчёт процента")
    println("Файл:      hw1.kt")
    println("Слушатель: Журкин Алексей Михайлович")
    println("Дата:      03.03.2020")
    println("Версия:    1.0\n")

    val amount = 200 // стоимость текущей продажи
    val total = 11_000 // сумма предыдущих продаж
    val fee = calculateFee(200, 11_000)  // exclusive по умолчанию = false
    println(fee) // 40


    println("\nPositive tests")
    println("1.  OK: ${(getPriceFee(0) - 0.3).absoluteValue < 0.001}")
    println("2.  OK: ${(getPriceFee(1000) - 0.3).absoluteValue < 0.001}")
    println("3.  OK: ${(getPriceFee(1001) - 0.25).absoluteValue < 0.001}")
    println("4.  OK: ${(getPriceFee(10000) - 0.25).absoluteValue < 0.001}")
    println("5.  OK: ${(getPriceFee(10001) - 0.2).absoluteValue < 0.001}")
    println("6.  OK: ${(getPriceFee(50000) - 0.2).absoluteValue < 0.001}")
    println("7.  OK: ${(getPriceFee(50001) - 0.15).absoluteValue < 0.001}")

    println("8.  OK: ${(calculateFee(0, 11_000) - 0.0).absoluteValue < 0.001}")
    println("9.  OK: ${(calculateFee(1000, 0) - 300.0).absoluteValue < 0.001}")
    println("10. OK: ${(calculateFee(1000, 1000) - 300.0).absoluteValue < 0.001}")
    println("11. OK: ${(calculateFee(1000, 1001) - 250.0).absoluteValue < 0.001}")
    println("12. OK: ${(calculateFee(1000, 10000) - 250.0).absoluteValue < 0.001}")
    println("13. OK: ${(calculateFee(1000, 10001) - 200.0).absoluteValue < 0.001}")
    println("14. OK: ${(calculateFee(1000, 50000) - 200.0).absoluteValue < 0.001}")
    println("15. OK: ${(calculateFee(1000, 50001) - 150.0).absoluteValue < 0.001}")

    println("16. OK: ${(calculateFee(0, 11_000, true) - 0.0).absoluteValue < 0.001}")
    println("17. OK: ${(calculateFee(1000, 0, true) - 250.0).absoluteValue < 0.001}")
    println("18. OK: ${(calculateFee(1000, 1000, true) - 250.0).absoluteValue < 0.001}")
    println("19. OK: ${(calculateFee(1000, 1001, true) - 200.0).absoluteValue < 0.001}")
    println("20. OK: ${(calculateFee(1000, 10000, true) - 200.0).absoluteValue < 0.001}")
    println("21. OK: ${(calculateFee(1000, 10001, true) - 150.0).absoluteValue < 0.001}")
    println("22. OK: ${(calculateFee(1000, 50000, true) - 150.0).absoluteValue < 0.001}")
    println("23. OK: ${(calculateFee(1000, 50001,true) - 100.0).absoluteValue < 0.001}")


    println("24. OK: ${(getExclusiveFee(false) - 0.0).absoluteValue < 0.001}")
    println("25. OK: ${(getExclusiveFee(true) - 0.05).absoluteValue < 0.001}")

    println("\nNegative tests")
    try {
        getPriceFee(-1)
    } catch (e: IllegalArgumentException) {
        println("1. OK: ${e.message == "Total price must equal or more zero"}")
    }

    try {
        calculateFee(-1, 11_000)
    } catch (e: IllegalArgumentException) {
        println("2. OK: ${e.message == "Amount price must equal or more zero"}")
    }

    try {
        calculateFee(1000, -1)
    } catch (e: IllegalArgumentException) {
        println("3. OK: ${e.message == "Total price price must equal or more zero"}")
    }
}