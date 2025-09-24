package flowcontrol

import kotlin.random.Random

/**
 * when
 */
fun main() {
    // 0 ~ 99 사이의 랜덤 정수
    val score = Random.nextInt(0, 100)

    when(score) {
        // 단일 값 매칭
        0 -> println("0")
        // 여러 값 매칭
        1, 2, 3, 4, 5 -> println("1~5")
        // 범위 표현
        in 6..10 -> println("6~10")
        // 나머지
        else -> println("나머지")
    }

    println("--------------------")

    val name = "배제우"
    val age = 28

    // 분기 처리에 여러 변수를 사용 가능
    // 가장 먼저 매칭되는 분기만 실행
    when {
        name == "배제우" -> println("내 이름은 배제우")
        age == 28 -> println("내 나이는 28")
    }

}