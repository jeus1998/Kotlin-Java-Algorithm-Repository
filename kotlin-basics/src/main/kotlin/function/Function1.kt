package function

/**
 * "함수 선언"(Function Declaration)
 * - Basic function declaration
 * - Single-expression function
 */
fun main() {
    println(basicFunc("배제우"))  // 3
    println(simpleFunc("배제우")) // 3
}

// 기본 함수 선언 방식
fun basicFunc(name: String): Int {
    return name.length
}

// 한줄에 선언 가능, 한줄 선언 방식에서는 반환 타입 생략 가능
fun simpleFunc(name: String) = name.length

