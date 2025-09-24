package function

/**
 * 중위 함수(Infix Function)
 * - infix 키워드를 사용하여 정의한다.
 * - 멤버 함수 또는 확장 함수에만 사용할 수 있다.
 * - 단일 파라미터만 허용한다.
 * - 함수 호출 시 "점(.)"과 "괄호()"를 생략할 수 있다.
 * - 주로 가독성을 높이기 위해 사용된다.
 */
fun main() {
    println(3 times "배제우 ") // 배제우 배제우 배제우
    val result = 3 multiply 4
    println(result) // 12
}

infix fun Int.times(str: String): String = str.repeat(this)

infix fun Int.multiply(value: Int): Int {
    return this * value
}