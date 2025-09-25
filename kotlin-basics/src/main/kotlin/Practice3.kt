fun main() {
    val score = 85
    // 기본적인 if-else
    if (score >= 90) {
        println("A등급")
    } else if (score >= 80) {
        println("B등급")
    } else {
        println("C등급")
    }

    // if를 표현식으로 사용 (값 반환)
    val grade = if (score >= 90) "A" else if (score >= 80) "B" else "C"
    println("등급: $grade")

    // 한 줄로 작성 (삼항 연산자 대체)
    val passed = if (score >= 60) "합격" else "불합격"
    println("결과: $passed")

    // 블록을 사용한 if 표현식
    val message = if (score >= 80) {
        println("축하합니다!")
        "우수한 성적입니다"  // 마지막 줄이 반환값
    } else {
        println("더 노력하세요!")
        "보통 성적입니다"
    }
    println("메시지: $message")

    test()
}

// 함수에서 if 표현식 사용
fun getGrade(score: Int) = if (score >= 90) "A" else if (score >= 80) "B" else "C"

fun test() {
    println("함수에서 등급: ${getGrade(95)}")
}