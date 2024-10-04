import kotlin.math.sqrt

// Класс Точка
data class Point(val x: Double, val y: Double)

// Класс Треугольник
class Triangle(val p1: Point, val p2: Point, val p3: Point) {

    // Функция для вычисления длины стороны треугольника
    private fun distance(p1: Point, p2: Point): Double {
        return sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y))
    }

    // Функция для вычисления площади треугольника по формуле Герона
    private fun area(): Double {
        val a = distance(p1, p2)
        val b = distance(p2, p3)
        val c = distance(p3, p1)
        val s = (a + b + c) / 2
        return sqrt(s * (s - a) * (s - b) * (s - c))
    }

    // Функция для вычисления радиуса вписанной окружности
    fun inradius(): Double {
        val a = distance(p1, p2)
        val b = distance(p2, p3)
        val c = distance(p3, p1)
        val p = (a + b + c) / 2  // Полупериметр
        val A = area()           // Площадь
        return A / p
    }

    // Функция для вычисления координат центра вписанной окружности
    fun incentre(): Point {
        val a = distance(p1, p2)
        val b = distance(p2, p3)
        val c = distance(p3, p1)

        // Координаты центра вписанной окружности
        val xCenter = (a * p3.x + b * p1.x + c * p2.x) / (a + b + c)
        val yCenter = (a * p3.y + b * p1.y + c * p2.y) / (a + b + c)

        return Point(xCenter, yCenter)
    }
}

// Функция для безопасного ввода числового значения
fun getDoubleInput(prompt: String): Double {
    while (true) {
        print(prompt)
        val input = readLine()
        try {
            return input?.toDouble() ?: throw IllegalArgumentException("Некорректный ввод")
        } catch (e: NumberFormatException) {
            println("Ошибка: введите числовое значение.")
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
}

fun main() {
    // Ввод координат вершин треугольника
    println("Введите координаты треугольника:")
    val x1 = getDoubleInput("x1: ")
    val y1 = getDoubleInput("y1: ")
    val x2 = getDoubleInput("x2: ")
    val y2 = getDoubleInput("y2: ")
    val x3 = getDoubleInput("x3: ")
    val y3 = getDoubleInput("y3: ")

    // Создание объекта Triangle
    val triangle = Triangle(Point(x1, y1), Point(x2, y2), Point(x3, y3))

    // Вычисление центра и радиуса вписанной окружности
    val incentre = triangle.incentre()
    val radius = triangle.inradius()

    // Вывод результатов
    println("Координаты центра вписанной окружности: (${incentre.x}, ${incentre.y})")
    println("Радиус вписанной окружности: $radius")
}
