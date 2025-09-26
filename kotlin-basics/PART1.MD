# Kotlin 기본 문법 정복 Part1

## 1. 변수 선언 (Variables)

```text
Kotlin에서는 val(읽기 전용)과 var(읽기/쓰기 가능) 두 가지 방식으로 변수를 선언합니다.
```
- `val` : 불변 변수 (read-only)
- `var` : 가변 변수 (read-write)
- 타입 추론 가능
- Practice1.kt 파일 참고

## 2. Null 안전성 (Null Safety)

```text
Kotlin은 null 안전성을 언어 차원에서 지원합니다. ?를 사용해 nullable 타입을 선언합니다.
```
- `?`: nullable 타입
- `?.`: 안전 호출 연산자
- `!!`: null 아님 단언 연산자
- `?:`: 엘비스 연산자
- Practice2.kt 파일 참고

### Any
```text
코틀린에서 Any는 모든 클래스의 최상위 타입으로, 자바의 Object와 유사합니다.
하지만 Any는 non-null 타입이고, null을 포함하는 타입의 최상위는 Any?입니다. 
```

## 3. 조건문 - if 표현식

```text
if문은 조건에 따라 코드를 실행하며, 표현식으로도 사용 가능합니다.
```
- 값 반환 가능 
- 삼항 연산자 대체
- 블록 사용 시 마지막 줄이 반환값
- Practice3.kt 파일 참고

## 4. when 표현식

```text
when은 Java의 switch와 유사하지만 더 강력하고 유연한 조건문입니다.
```
- 다양한 조건 지원
- 값 반환 가능
- break 불필요
- Practice4.kt 파일 참고

## 5. for 반복문

```text
Kotlin의 for 반복문은 주로 범위나 컬렉션을 순회할 때 사용합니다.
```
- `in` 연산자로 순회
- 범위 연산자: `..`, `downTo`, `step`
- Practice5.kt 파일 참고

## 6. while 반복문

```text
Kotlin의 while과 do-while은 Java와 유사하게 동작합니다.
```
- `while`: 조건을 먼저 확인
- `do-while`: 최소 한 번 실행 후 조건 확인
- Practice6.kt 파일 참고

## 7. 함수 선언

```text
Kotlin 함수는 fun 키워드로 선언하며, 간결한 문법을 제공합니다.
```
- 기본 함수 선언
- 단일 표현식 함수
- 기본 매개변수
- 명명된 매개변수
- Practice7.kt 파일 참고

## 8. 가변 인자 (vararg)

```text
가변 개수의 인자를 받는 함수를 정의할 수 있습니다.
```
- `vararg` 키워드 사용
- `*` 스프레드 연산자로 배열 전달
- Practice8.kt 파일 참고

## 9. 확장 함수 (Extension Functions)

```text
기존 클래스에 새로운 함수를 추가할 수 있습니다. 상속 없이 클래스 기능을 확장합니다.
```
- `fun 타입.함수명()` 형식으로 선언
- `this` 키워드로 수신 객체 참조
- Practice9.kt 파일 참고

## 10. 중위 함수 (Infix Functions)

```text
중위 표기법으로 호출할 수 있는 함수입니다. 더 읽기 쉬운 코드를 작성할 수 있습니다.
```
- `infix` 키워드 사용
- 매개변수가 하나여야 함
- 멤버 함수나 확장 함수에만 적용 가능
- Practice10.kt 파일 참고
