package part4

fun main() {
    println("=== List (리스트) ===")
    
    // 불변 리스트
    val immutableList = listOf("사과", "바나나", "오렌지")
    println("불변 리스트: $immutableList")
    println("첫 번째: ${immutableList[0]}")
    println("크기: ${immutableList.size}")
    // immutableList.add("포도")  // 오류! 불변이라 추가 불가
    
    // 가변 리스트
    val mutableList = mutableListOf("배제우", "김철수")
    mutableList.add("이영희")
    mutableList.add(0, "박민수")  // 인덱스 0에 추가
    mutableList.removeAt(2)  // 인덱스 2 제거
    println("가변 리스트: $mutableList")
    
    // 리스트 생성 방법들
    // i = 0 ~ 4 -> [0, 2, 4, 6, 8]
    val numbers = List(5) { i -> i * 2 }
    // it = 0 ~ 2 - > ["Item 0", "Item 1", "Item 2"]
    val repeatedList = MutableList(3) { "Item $it" }
    println("생성된 숫자: $numbers")
    println("반복 리스트: $repeatedList")
    
    println("\n=== Set (집합) ===")
    
    // 불변 Set (중복 자동 제거)
    val immutableSet = setOf(1, 2, 3, 2, 1)  // 중복 제거됨
    println("불변 Set: $immutableSet")  // [1, 2, 3]
    
    // 가변 Set
    val mutableSet = mutableSetOf("배제우", "김철수")
    mutableSet.add("이영희")
    mutableSet.add("배제우")  // 중복, 추가 안됨
    println("가변 Set: $mutableSet")
    
    // Set 연산
    val setA = setOf(1, 2, 3, 4)
    val setB = setOf(3, 4, 5, 6)
    println("합집합: ${setA union setB}")
    println("교집합: ${setA intersect setB}")
    println("차집합: ${setA subtract setB}")
    
    println("\n=== Map (맵) ===")
    
    // 불변 Map
    val immutableMap = mapOf(
        "name" to "배제우",
        "age" to 28,
        "city" to "서울"
    )
    println("불변 Map: $immutableMap")
    println("이름: ${immutableMap["name"]}")
    println("나이: ${immutableMap.get("age")}")
    
    // 가변 Map
    val mutableMap = mutableMapOf<String, Any>()
    mutableMap["id"] = 1
    mutableMap["name"] = "배제우"
    mutableMap["score"] = 95.5
    mutableMap.put("grade", "A")
    println("가변 Map: $mutableMap")
    
    // Map 순회
    println("\nMap 순회:")
    for ((key, value) in immutableMap) {
        println("$key: $value")
    }
    
    // Map 메소드들
    println("\nMap 메소드:")
    println("키들: ${mutableMap.keys}")
    println("값들: ${mutableMap.values}")
    println("'name' 포함? ${mutableMap.containsKey("name")}")
    println("28 포함? ${immutableMap.containsValue(28)}")
    println("getOrDefault: 'country': ${immutableMap.getOrDefault("country", "대한민국")}")
    
    println("\n=== 실용적인 예제 ===")
    
    // 학생 성적 관리
    val students = mutableListOf<Student>()
    students.add(Student("배제우", 95))
    students.add(Student("김철수", 88))
    students.add(Student("이영희", 92))
    
    // 평균 계산
    val average = students.map { it.score }.average()
    println("평균 점수: $average")
    
    // 성적순 정렬
    val sortedStudents = students.sortedByDescending { it.score }
    println("성적순: $sortedStudents")
    
    // 그룹화
    val scoreGroups = students.groupBy { 
        when {
            it.score >= 90 -> "A"
            it.score >= 80 -> "B"
            else -> "C"
        }
    }
    println("등급별 그룹: $scoreGroups")
    
    // 중첩 컬렉션
    val matrix = listOf(
        listOf(1, 2, 3),
        listOf(4, 5, 6),
        listOf(7, 8, 9)
    )
    println("\n2D 배열:")
    matrix.forEach { row ->
        println(row.joinToString(" "))
    }
    
    // 컬렉션 변환
    println("\n=== 컬렉션 변환 ===")
    val list = listOf(1, 2, 3)
    val set = list.toSet()
    val map = list.associateWith { it * it }  // 값을 제곱으로 매핑
    println("List → Set: $set")
    println("List → Map: $map")
}

data class Student(val name: String, val score: Int)
