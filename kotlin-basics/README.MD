# Kotlin 기본 문법 정복 Part1

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
## 5. for 반목문

```text
Kotlin의 for 반복문은 주로 범위나 컬렉션을 순회할 때 사용합니다.
```
- `in` 연산자로 순회
- 범위 연산자: `..`, `downTo`, `step`

```kotlin
// Practice5.kt 파일
fun main() {
    // 배열 순회
    val items = arrayOf("사과", "바나나", "오렌지")
    println("=== 배열 순회 ===")
    for (item in items) {
        println(item)
    }

    // 인덱스와 함께 순회
    println("\n=== 인덱스와 함께 ===")
    for (index in items.indices) {
        println("$index: ${items[index]}")
    }

    // withIndex() 사용
    println("\n=== withIndex() ===")
    for ((index, value) in items.withIndex()) {
        println("$index: $value")
    }

    // 범위 순회 (1부터 5까지)
    println("\n=== 1..5 범위 ===")
    for (i in 1..5) {
        print("$i ")
    }
    println()

    // until 사용 (1부터 4까지, 5 제외)
    println("\n=== 1 until 5 ===")
    for (i in 1 until 5) {
        print("$i ")
    }
    println()

    // step 사용 (2씩 증가)
    println("\n=== 0..10 step 2 ===")
    for (i in 0..10 step 2) {
        print("$i ")
    }
    println()

    // downTo 사용 (감소)
    println("\n=== 10 downTo 1 ===")
    for (i in 10 downTo 1) {
        print("$i ")
    }
    println()

    // 감소하면서 step
    println("\n=== 10 downTo 1 step 2 ===")
    for (i in 10 downTo 1 step 2) {
        print("$i ")
    }
    println()

    // List 순회
    val list = listOf("월", "화", "수", "목", "금")
    println("\n=== List 순회 ===")
    for (day in list) {
        println("$day 요일")
    }

    // 문자열 순회
    println("\n=== 문자열 순회 ===")
    for (char in "Kotlin") {
        print("$char ")
    }
    println()
}
```

## 6. while 반목문

```text
Kotlin의 while과 do-while은 Java와 유사하게 동작합니다.
```
- `while`: 조건을 먼저 확인
- `do-while`: 최소 한 번 실행 후 조건 확인

```kotlin
// Practice6.kt 파일
fun main() {
    // 기본 while 문
    println("=== while 문 ===")
    var count = 1
    while (count <= 5) {
        println("카운트: $count")
        count++
    }

    // 배열과 while 문
    println("\n=== 배열과 while ===")
    val items = Array(5) { i -> (i + 1) * 10 }  // [10, 20, 30, 40, 50]
    var index = 0
    while (index < items.size) {
        println("items[$index] = ${items[index]}")
        index++
    }

    // do-while 문 (최소 한 번은 실행)
    println("\n=== do-while 문 ===")
    var num = 6
    do {
        println("num = $num (최소 한 번은 실행됨)")
        num++
    } while (num <= 5)  // 조건이 false여도 한 번은 실행됨

    // 무한 루프와 break
    println("\n=== break 사용 ===")
    var counter = 0
    while (true) {
        if (counter >= 3) {
            println("3에 도달하여 종료")
            break
        }
        println("무한 루프 카운터: $counter")
        counter++
    }

    // continue 사용
    println("\n=== continue 사용 ===")
    var i = 0
    while (i < 10) {
        i++
        if (i % 2 == 0) {
            continue  // 짝수는 건너뛰기
        }
        print("$i ")  // 홀수만 출력
    }
    println()

    // 중첩 while
    println("\n=== 중첩 while (구구단 일부) ===")
    var x = 2
    while (x <= 4) {
        var y = 1
        while (y <= 3) {
            println("$x × $y = ${x * y}")
            y++
        }
        println()
        x++
    }
}
```

## 7. 함수 선언

```text
Kotlin 함수는 fun 키워드로 선언하며, 간결한 문법을 제공합니다.
```
- 기본 함수 선언
- 단일 표현식 함수
- 기본 매개변수
- 명명된 매개변수

```kotlin
// Practice7.kt 파일
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
```

## 8. 가변 인자 (vararg)

```text
가변 개수의 인자를 받는 함수를 정의할 수 있다.
```
- `vararg` 키워드 사용
- `*` 스프레드 연산자로 배열 전달

```kotlin
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
```




