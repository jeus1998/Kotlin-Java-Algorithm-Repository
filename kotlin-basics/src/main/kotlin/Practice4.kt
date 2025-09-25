fun main() {
    val x = 5
    // 기본 when 사용
    when (x) {
        1 -> println("x는 1")
        2 -> println("x는 2")
        3, 4 -> println("x는 3 또는 4")
        5 -> println("x는 5")
        else -> println("x는 그 외의 값")
    }

    val day = 3
    // when을 표현식으로 사용
    val dayName = when (day) {
        1 -> "월요일"
        2 -> "화요일"
        3 -> "수요일"
        4 -> "목요일"
        5 -> "금요일"
        6, 7 -> "주말"
        else -> "잘못된 날짜"
    }
    println("오늘은 $dayName")

    val score = 85
    // 범위 연산자 사용
    val grade = when (score) {
        in 90..100 -> "A"
        in 80..89 -> "B"
        in 70..79 -> "C"
        in 60..69 -> "D"
        else -> "F"
    }
    println("점수 $score -> 등급 $grade")

    // 조건식 사용 (인자 없는 when)
    val temperature = 25
    val weather = when {
        temperature < 0 -> "매우 추움"
        temperature < 10 -> "추움"
        temperature < 20 -> "선선함"
        temperature < 30 -> "따뜻함"
        else -> "더움"
    }
    println("온도 $temperature°C -> $weather")

    // 타입 체크
    val obj: Any = "Hello"
    when (obj) {
        is String -> println("문자열이고 길이는 ${obj.length}")
        is Int -> println("정수입니다")
        is Double -> println("실수입니다")
        else -> println("알 수 없는 타입")
    }

    println(checkType("Kotlin"))
}

// 함수와 함께 사용
fun checkType(x: Any) = when (x) {
    is String -> "문자열"
    is Int -> "정수"
    is Boolean -> "불린"
    else -> "알 수 없음"
}