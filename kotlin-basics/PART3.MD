# Kotlin 기본 문법 정복 Part3

## 16. Data 클래스

```text
Data 클래스는 데이터 보관용 클래스로, equals(), hashCode(), toString(), copy() 등을 자동 생성합니다.
```
- `data` 키워드를 사용하여 선언
- 주 생성자에 최소 하나의 매개변수 필요
- 자동 생성되는 메서드들은 주 생성자에 선언된 프로퍼티를 기반으로 함
- `componentN()` 함수도 자동 생성되어, 구조 분해 선언에 사용 가능
- `copy()` 메서드를 사용하여 객체 복사 가능
- `abstract`, `open`, `sealed`, `inner`가 될 수 없음
- Practice16.kt 파일 참고

## 17. Sealed 클래스

```text
Sealed 클래스는 제한된 계층 구조를 표현할 때 사용합니다. when 표현식과 함께 사용하면 강력합니다.
```
- `sealed` 키워드를 사용하여 선언
- 하위 클래스가 제한됨
- when 표현식에서 else 블록이 필요 없음
- 상태 패턴, 결과 타입 등에 유용
- Practice17.kt 파일 참고

## 18. Object와 Companion Object

```text
Object는 싱글톤 패턴을 구현하고, Companion Object는 클래스의 정적 멤버를 정의합니다.
```
- `object`: 싱글톤 객체 생성
- `companion object`: 클래스 내부의 정적 멤버
- 익명 객체는 `object : 인터페이스` 형태로 생성
- Practice18.kt 파일 참고

## 19. Enum 클래스

```text
Enum 클래스는 미리 정의된 상수 집합을 표현합니다.
```
- `enum class` 키워드로 선언
- 프로퍼티와 메소드 추가 가능
- when과 함께 사용하면 강력
- ordinal, name 프로퍼티 자동 제공
- valueOf(), values() 함수 제공
- Practice19.kt 파일 참고

## 20. 클래스 위임 (Delegation)

```text
위임을 통해 상속 없이 기능을 재사용할 수 있습니다. by 키워드를 사용합니다.
```
- `by` 키워드로 위임
- 인터페이스 구현을 다른 객체에 위임
- 프로퍼티 위임 (lazy, observable, vetoable)
- Map 위임
- Practice20.kt 파일 참고
