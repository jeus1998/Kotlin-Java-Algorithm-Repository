# Kotlin 기본 문법 정복 Part5 

### 26. 제네릭 (Generics)
```text
제네릭은 타입을 매개변수화하여 코드의 재사용성을 높입니다.
```
- 제네릭 클래스: `class Box<T>`
- 제네릭 함수: `fun <T> genericFunc()`
- 타입 제약: `<T : Number>`
- 변성: `in` (반공변), `out` (공변)
- 스타 프로젝션: `<*>`
- Practice26.kt 참고

### 27. 예외 처리 (Exception Handling)
```text
Kotlin의 예외 처리는 Java와 유사하지만, 모든 예외가 Unchecked Exception입니다.
```
- `try-catch-finally` 블록
- `try`는 표현식으로 사용 가능
- `throw`는 표현식
- 커스텀 예외 클래스
- `runCatching` 함수
- Practice27.kt 참고

### 28. 정규 표현식 (Regex)
```text
정규 표현식을 사용하여 문자열 패턴을 매칭하고 처리합니다.
```
- `Regex` 클래스
- 패턴 매칭: `matches()`, `containsMatchIn()`
- 찾기: `find()`, `findAll()`
- 치환: `replace()`, `replaceFirst()`
- 분할: `split()`
- Practice28.kt 참고

### 29. 타입 별칭과 인라인 클래스
```text
타입 별칭은 기존 타입에 별명을 붙이고, 인라인 클래스는 성능 최적화를 위해 사용합니다.
```
- 타입 별칭: `typealias`
- 인라인 클래스: `value class`
- 함수 타입 별칭
- 제네릭 타입 별칭
- Practice29.kt 참고

### 30. 연산자 오버로딩
```text
연산자를 커스텀 타입에 대해 재정의할 수 있습니다.
```
- 산술 연산자: `plus`, `minus`, `times`, `div`
- 비교 연산자: `compareTo`
- 인덱스 연산자: `get`, `set`
- 범위 연산자: `rangeTo`
- `in` 연산자: `contains`
- Practice30.kt 참고
