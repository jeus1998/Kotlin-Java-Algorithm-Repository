package part1

fun main() {
    // 기본 함수 호출
    println("3 + 5 = ${add(3, 5)}")

    // 단일 표현식 함수 호출
    println("10 - 3 = ${subtract(10, 3)}")

    // 기본 매개변수 사용
    greet()  // 기본값 사용
    greet("배제우")  // name만 전달
    greet("배제우", 28)  // 모두 전달

    // 명명된 매개변수 사용
    printInfo(name = "배제우", age = 28, city = "서울")
    printInfo(age = 28, city = "부산", name = "배제우")  // 순서 변경 가능
    printInfo(city = "대전", name = "배제우", age = 28)

    // Unit 반환 (void와 유사)
    printMessage("Hello Kotlin")

    // 지역 함수
    calculateAndPrint(10, 20)
}

// 기본 함수 선언
fun add(a: Int, b: Int): Int {
    return a + b
}

// 단일 표현식 함수 (반환 타입 추론 가능)
fun subtract(a: Int, b: Int) = a - b

// 기본 매개변수
fun greet(name: String = "손님", age: Int = 0) {
    if (age > 0) {
        println("안녕하세요, ${name}님! 나이는 ${age}살이시군요.")
    } else {
        println("안녕하세요, ${name}님!")
    }
}

// 명명된 매개변수
fun printInfo(name: String, age: Int, city: String) {
    println("이름: $name, 나이: $age, 도시: $city")
}

// Unit 반환 (명시적으로 작성하지 않아도 됨)
fun printMessage(message: String): Unit {
    println("메시지: $message")
}

// 지역 함수 예제
fun calculateAndPrint(x: Int, y: Int) {
    // 지역 함수 선언
    fun multiply(a: Int, b: Int) = a * b
    fun divide(a: Int, b: Int) = if (b != 0) a / b else 0

    println("$x × $y = ${multiply(x, y)}")
    println("$x ÷ $y = ${divide(x, y)}")
}