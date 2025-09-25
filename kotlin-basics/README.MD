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





