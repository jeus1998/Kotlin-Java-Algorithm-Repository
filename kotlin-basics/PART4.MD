# Kotlin 기본 문법 정복 Part4 

### 21. 컬렉션 (List, Set, Map)
```text
Kotlin은 불변(Immutable)과 가변(Mutable) 컬렉션을 구분합니다.
```
- 불변: `List`, `Set`, `Map` - 읽기만 가능
- 가변: `MutableList`, `MutableSet`, `MutableMap` - 읽기/쓰기 가능
- 컬렉션 생성 함수: `listOf()`, `mutableListOf()`, `setOf()`, `mapOf()` 등
- 컬렉션 연산: `union`, `intersect`, `subtract` 등
- Practice21.kt 참고

### 22. 람다식과 고차 함수
```text
람다는 이름 없는 함수이며, 고차 함수는 함수를 매개변수로 받거나 반환하는 함수입니다.
```
- 람다 표현식: `{ 매개변수 -> 본문 }`
- 단일 매개변수는 `it`으로 참고 가능
- 고차 함수: 함수를 매개변수로 받거나 반환
- 함수 참고: `::함수명`
- 인라인 함수로 성능 최적화
- Practice22.kt 참고

### 23. 스코프 함수 (let, run, with, apply, also)
```text
스코프 함수는 객체의 컨텍스트 내에서 코드 블록을 실행합니다.
```
- `let`: it으로 참고, 람다 결과 반환 (null 체크에 유용)
- `run`: this로 참고, 람다 결과 반환 (객체 초기화와 계산)
- `with`: this로 참고, 람다 결과 반환 (여러 메소드 호출)
- `apply`: this로 참고, 객체 자신 반환 (객체 설정)
- `also`: it으로 참고, 객체 자신 반환 (부수 효과)
- Practice23.kt 참고

### 24. 컬렉션 연산자
```text
Kotlin은 컬렉션을 다루는 다양한 함수형 연산자를 제공합니다.
```
- 변환: `map`, `flatMap`, `groupBy`, `associate`
- 필터링: `filter`, `filterNot`, `partition`, `take`, `drop`
- 집계: `reduce`, `fold`, `sum`, `average`, `count`
- 정렬: `sorted`, `sortedBy`, `sortedDescending`
- 기타: `distinct`, `zip`, `chunked`, `windowed`
- Practice24.kt 참고

### 25. 시퀀스 (Sequence)
```text
시퀀스는 지연 평가를 통해 대용량 데이터를 효율적으로 처리합니다.
```
- `asSequence()`: 컬렉션을 시퀀스로 변환
- `generateSequence()`: 시퀀스 생성
- 지연 평가로 성능 최적화 (중간 컬렉션 생성 없음)
- 무한 시퀀스 생성 가능
- `sequence` 빌더로 커스텀 시퀀스 생성
- Practice25.kt 참고
