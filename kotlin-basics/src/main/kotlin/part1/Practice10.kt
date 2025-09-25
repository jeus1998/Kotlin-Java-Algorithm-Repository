package part1

fun main() {
    // 중위 함수 호출 (두 가지 방식)
    println("=== 중위 함수 기본 ===")
    println("일반 호출: ${5.multiply(3)}")
    println("중위 호출: ${5 multiply 3}")

    // 문자열 중위 함수
    println("\n=== 문자열 중위 함수 ===")
    println("Hello" concat "World")
    println("Kotlin" repeat 3)

    // Boolean 중위 함수
    println("\n=== Boolean 중위 함수 ===")
    val result1 = true and false
    val result2 = true or false
    println("true and false = $result1")
    println("true or false = $result2")

    // 리스트 중위 함수
    println("\n=== 리스트 중위 함수 ===")
    val list1 = listOf(1, 2, 3)
    val list2 = listOf(4, 5, 6)
    println("리스트 결합: ${list1 merge list2}")
    println("리스트 결합2: ${list1 + list2}")

    // Pair 생성 (표준 라이브러리의 to)
    println("\n=== Pair 생성 ===")
    val pair:Pair<String, String> = "key" to "value"
    println("Pair: $pair")

    // 커스텀 클래스와 중위 함수
    println("\n=== 커스텀 클래스 ===")
    val person1 = Person("배제우", 28)
    val person2 = Person("이영희", 23)

    person1 greet person2
    println("나이 비교: ${person1 isOlderThan person2}")

    // 체이닝
    println("\n=== 체이닝 ===")
    val chainResult = 10 power 2 plus 5
    println("(10^2) + 5 = $chainResult")

    // 우선순위 테스트
    println("\n=== 우선순위 ===")
    val priority = 3 + 2 multiply 2  // + 연산이 먼저
    println("3 + 2 multiply 2 = $priority")  // (3 + 2) * 2 = 10
}

// Int 확장 중위 함수
infix fun Int.multiply(value: Int): Int = this * value

infix fun Int.power(exponent: Int): Int {
    var result = 1
    repeat(exponent) { result *= this }
    return result
}

infix fun Int.plus(value: Int): Int = this + value

// String 확장 중위 함수
infix fun String.concat(other: String): String = "$this $other"

infix fun String.repeat(times: Int): String {
    val sb = StringBuilder()
    repeat(times) { sb.append(this) }
    return sb.toString()
}

// Boolean 확장 중위 함수
infix fun Boolean.and(other: Boolean): Boolean = this && other
infix fun Boolean.or(other: Boolean): Boolean = this || other

// List 확장 중위 함수
infix fun <T> List<T>.merge(other: List<T>): List<T> {
    return this + other
}

// 커스텀 클래스
class Person(val name: String, val age: Int) {
    // 멤버 중위 함수
    infix fun greet(other: Person) {
        println("${this.name}이(가) ${other.name}에게 인사합니다!")
    }

    infix fun isOlderThan(other: Person): Boolean {
        return this.age > other.age
    }
}