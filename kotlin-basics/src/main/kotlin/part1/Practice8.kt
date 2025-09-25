package part1

fun main() {
    // 가변 인자 함수 호출
    println("합계: ${sum(1, 2, 3)}")
    println("합계: ${sum(10, 20, 30, 40, 50)}")

    // 배열을 가변 인자로 전달 (스프레드 연산자 *)
    val numbers = intArrayOf(1, 2, 3, 4, 5)
    println("배열 합계: ${sum(*numbers)}")

    // 제네릭 가변 인자 함수
    val stringList = createList("사과", "바나나", "오렌지")
    println("문자열 리스트: $stringList")

    val intList = createList(1, 2, 3, 4, 5)
    println("정수 리스트: $intList")

    // 가변 인자와 일반 매개변수 조합
    printAll("제목", "항목1", "항목2", "항목3")

    // 배열을 스프레드 연산자로 전달
    val items = arrayOf("A", "B", "C", "D")
    printAll("알파벳", *items)

    // 여러 타입 혼합
    printValues(1, "Hello", 3.14, true)
}

// 기본 가변 인자 함수
fun sum(vararg numbers: Int): Int {
    var result = 0
    for (num in numbers) {
        result += num
    }
    return result
}

// 제네릭 가변 인자 함수
fun <T> createList(vararg elements: T): List<T> {
    val result = ArrayList<T>()
    for (element in elements) {
        result.add(element)
    }
    return result
}

// 가변 인자와 일반 매개변수 조합
fun printAll(title: String, vararg messages: String) {
    println("=== $title ===")
    for ((index, message) in messages.withIndex()) {
        println("${index + 1}. $message")
    }
}

// Any 타입으로 여러 타입 받기
fun printValues(vararg values: Any) {
    println("받은 값들:")
    for (value in values) {
        when (value) {
            is Int -> println("정수: $value")
            is String -> println("문자열: $value")
            is Double -> println("실수: $value")
            is Boolean -> println("불린: $value")
            else -> println("기타: $value")
        }
    }
}