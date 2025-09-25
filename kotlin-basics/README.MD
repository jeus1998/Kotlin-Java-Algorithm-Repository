# Kotlin 기본 문법 정복

## 1. 변수 선언 (Variables)

```text
Kotlin에서는 val(읽기 전용)과 var(읽기/쓰기 가능) 두 가지 방식으로 변수를 선언합니다.
```
- `val` : 불변 변수 (read-only)
- `var` : 가변 변수 (read-write)
- 타입 추론 가능

```kotlin
// Practice1.kt 파일
fun main() {
    // val - 읽기 전용 변수
    val name: String = "배제우"
    val age = 28  // 타입 추론

    // var - 읽기/쓰기 가능 변수
    var address: String = "서울"
    var count = 0

    println("이름: $name")
    println("나이: $age")
    println("주소: $address")

    // val은 재할당 불가
    // name = "홍길동"  // 컴파일 오류!

    // var는 재할당 가능
    address = "부산"
    count = count + 1

    println("변경된 주소: $address")
    println("카운트: $count")
}
```

## 2. Null 안전성 (Null Safety)

```text
Kotlin은 null 안전성을 언어 차원에서 지원합니다. ?를 사용해 nullable 타입을 선언합니다.
```
- `?`: nullable 타입
- `?.`: 안전 호출 연산자
- `!!`: null 아님 단언 연산자
- `?:`: 엘비스 연산자

```kotlin
// Practice2.kt 파일
fun main() {
    // Nullable 변수 선언
    var nullableName: String? = null
    var nonNullName: String = "배제우"

    // 안전 호출 연산자 (?.)
    println("nullable 길이: ${nullableName?.length}")  // null 반환
    println("non-null 길이: ${nonNullName.length}")    // 3 반환

    // 엘비스 연산자 (?:)
    val length = nullableName?.length ?: 0
    println("길이 (기본값 0): $length")

    // non-null 단언 연산자 (!!) - 주의해서 사용
    nullableName = "배제우"
    val forceLength = nullableName!!.length  // null이면 NPE 발생
    println("강제 길이: $forceLength")

    // 안전한 타입 캐스팅
    val obj: Any = "문자열"
    val str: String? = obj as? String
    val num: Int? = obj as? Int

    println("문자열 캐스팅: $str")  // "문자열"
    println("숫자 캐스팅: $num")    // null
}
```

### Any
```text
코틀린에서 Any는 모든 클래스의 최상위 타입으로, 자바의 Object와 유사합니다.
하지만 Any는 non-null 타입이고, null을 포함하는 타입의 최상위는 Any?입니다. 
즉, 코틀린에서 모든 객체, 심지어 Int 같은 원시 타입도 Any의 하위 타입입니다.

Any의 주요 특징
- 최상위 타입: 모든 클래스가 명시하지 않아도 기본적으로 Any를 상속
- 내장 메서드: equals(), hashCode(), toString() 세 가지 메서드는 Any에 정의되어 있고, 
  코틀린의 모든 객체에서 사용 가능.
- 자바와 차이: 자바의 Object에는 동기화 관련 메서드(wait, notify 등)가 있지만, 코틀린의 Any에는 없음.
- non-null: Any는 null 값을 포함할 수 없음. null을 허용하려면 Any?를 사용해야 함.
```

## 3. 조건문 - if 표현식

```kotlin
// Practice3.kt 파일

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
```

## 4. when 표현식
```text
when은 Java의 switch와 유사하지만 더 강력하고 유연한 조건문입니다.
```
- 다양한 조건 지원
- 값 반환 가능
- break 불필요

```kotlin
// Practice4.kt 파일
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
```






