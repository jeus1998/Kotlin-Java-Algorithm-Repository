package part5

fun main() {
    println("=== 산술 연산자 오버로딩 ===")
    
    val v1 = Vector(3.0, 4.0)
    val v2 = Vector(1.0, 2.0)
    
    val sum = v1 + v2
    val diff = v1 - v2
    val scaled = v1 * 2.0
    val divided = v1 / 2.0
    
    println("v1: $v1")
    println("v2: $v2")
    println("v1 + v2 = $sum")
    println("v1 - v2 = $diff")
    println("v1 * 2 = $scaled")
    println("v1 / 2 = $divided")
    
    println("\n=== 단항 연산자 ===")
    
    val counter = Counter(5)
    println("초기값: ${counter.value}")
    println("+counter = ${(+counter).value}")
    println("-counter = ${(-counter).value}")
    
    var mutableCounter = Counter(10)
    println("증가 전: ${mutableCounter.value}")
    mutableCounter++
    println("증가 후: ${mutableCounter.value}")
    mutableCounter--
    println("감소 후: ${mutableCounter.value}")
    
    println("\n=== 비교 연산자 ===")
    
    val p1 = Person2("배제우", 28)
    val p2 = Person2("김철수", 30)
    val p3 = Person2("이영희", 25)
    
    println("$p1 > $p2 = ${p1 > p2}")
    println("$p1 < $p2 = ${p1 < p2}")
    println("$p2 >= $p3 = ${p2 >= p3}")
    
    val people = listOf(p1, p2, p3).sorted()
    println("나이순 정렬: $people")
    
    println("\n=== 인덱스 연산자 ===")
    
    val matrix = Matrix2x2(
        1.0, 2.0,
        3.0, 4.0
    )
    
    println("Matrix[0,0] = ${matrix[0, 0]}")
    println("Matrix[1,1] = ${matrix[1, 1]}")
    
    matrix[0, 1] = 5.0
    println("수정 후 Matrix[0,1] = ${matrix[0, 1]}")
    
    println("\n=== in 연산자 ===")
    
    val range = IntRange(1, 10)
    println("5 in 1..10 = ${5 in range}")
    println("15 in 1..10 = ${15 in range}")
    
    val circle = Circle(0.0, 0.0, 5.0)
    val point1 = Point(3.0, 4.0)
    val point2 = Point(10.0, 0.0)
    
    println("$point1 in circle = ${point1 in circle}")
    println("$point2 in circle = ${point2 in circle}")
    
    println("\n=== 범위 연산자 ===")
    
    val date1 = Date(2024, 1, 1)
    val date2 = Date(2024, 12, 31)
    val dateRange = date1..date2
    
    val testDate = Date(2024, 6, 15)
    println("$testDate in 2024 = ${testDate in dateRange}")
    
    println("\n=== 복합 할당 연산자 ===")
    
    val wallet = Wallet(1000.0)
    println("초기 잔액: ${wallet.balance}")
    
    wallet += 500.0
    println("500 추가: ${wallet.balance}")
    
    wallet -= 200.0
    println("200 차감: ${wallet.balance}")
    
    wallet *= 2.0
    println("2배 증가: ${wallet.balance}")
    
    println("\n=== 호출 연산자 ===")
    
    val multiplier = Multiplier(3)
    println("3 × 5 = ${multiplier(5)}")
    println("3 × 10 = ${multiplier(10)}")
    
    val function = Function { x: Int -> x * x }
    println("제곱: ${function(5)}")
}

// 1. 산술 연산자 오버로딩
data class Vector(val x: Double, val y: Double) {
    operator fun plus(other: Vector) = Vector(x + other.x, y + other.y)
    operator fun minus(other: Vector) = Vector(x - other.x, y - other.y)
    operator fun times(scalar: Double) = Vector(x * scalar, y * scalar)
    operator fun div(scalar: Double) = Vector(x / scalar, y / scalar)
}

// 2. 단항 연산자
data class Counter(var value: Int) {
    operator fun unaryPlus() = Counter(+value)
    operator fun unaryMinus() = Counter(-value)
    operator fun inc() = Counter(value + 1)
    operator fun dec() = Counter(value - 1)
}

// 3. 비교 연산자
data class Person2(val name: String, val age: Int) : Comparable<Person2> {
    override fun compareTo(other: Person2): Int = age.compareTo(other.age)
}

// 4. 인덱스 연산자
class Matrix2x2(
    private var a11: Double, private var a12: Double,
    private var a21: Double, private var a22: Double
) {
    operator fun get(row: Int, col: Int): Double {
        return when {
            row == 0 && col == 0 -> a11
            row == 0 && col == 1 -> a12
            row == 1 && col == 0 -> a21
            row == 1 && col == 1 -> a22
            else -> throw IndexOutOfBoundsException()
        }
    }
    
    operator fun set(row: Int, col: Int, value: Double) {
        when {
            row == 0 && col == 0 -> a11 = value
            row == 0 && col == 1 -> a12 = value
            row == 1 && col == 0 -> a21 = value
            row == 1 && col == 1 -> a22 = value
            else -> throw IndexOutOfBoundsException()
        }
    }
}

// 5. in 연산자
data class Point(val x: Double, val y: Double)

class Circle(val centerX: Double, val centerY: Double, val radius: Double) {
    operator fun contains(point: Point): Boolean {
        val distance = Math.sqrt(
            Math.pow(point.x - centerX, 2.0) + 
            Math.pow(point.y - centerY, 2.0)
        )
        return distance <= radius
    }
}

// 6. 범위 연산자
data class Date(val year: Int, val month: Int, val day: Int) : Comparable<Date> {
    override fun compareTo(other: Date): Int {
        return when {
            year != other.year -> year.compareTo(other.year)
            month != other.month -> month.compareTo(other.month)
            else -> day.compareTo(other.day)
        }
    }
    
    operator fun rangeTo(other: Date) = DateRange(this, other)
}

class DateRange(val start: Date, val end: Date) {
    operator fun contains(date: Date): Boolean {
        return date >= start && date <= end
    }
}

// 7. 복합 할당 연산자
class Wallet(var balance: Double) {
    operator fun plusAssign(amount: Double) {
        balance += amount
    }
    
    operator fun minusAssign(amount: Double) {
        balance -= amount
    }
    
    operator fun timesAssign(factor: Double) {
        balance *= factor
    }
}

// 8. 호출 연산자
class Multiplier(private val factor: Int) {
    operator fun invoke(value: Int): Int = value * factor
}

class Function<T, R>(private val f: (T) -> R) {
    operator fun invoke(value: T): R = f(value)
}
