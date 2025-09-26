package part4

fun main() {
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val names = listOf("배제우", "김철수", "이영희", "박민수", "최지우")
    
    println("=== 변환 연산자 ===")
    
    // map - 각 요소 변환
    val doubled = numbers.map { it * 2 }
    println("2배: $doubled")
    
    val nameLengths = names.map { it.length }
    println("이름 길이: $nameLengths")
    
    // mapIndexed - 인덱스와 함께
    val indexed = names.mapIndexed { index, name -> 
        "${index + 1}. $name"
    }
    println("번호 매기기: $indexed")
    
    // flatMap - 중첩 구조 평탄화
    val nestedList = listOf(
        listOf(1, 2, 3),
        listOf(4, 5),
        listOf(6, 7, 8, 9)
    )
    val flattened = nestedList.flatMap { it }
    println("평탄화: $flattened")
    
    // 문자열을 글자로 분해
    val chars = names.flatMap { it.toList() }
    println("모든 글자: $chars")
    
    println("\n=== 필터링 연산자 ===")
    
    // filter - 조건에 맞는 요소만
    val evenNumbers = numbers.filter { it % 2 == 0 }
    println("짝수: $evenNumbers")
    
    val longNames = names.filter { it.length > 2 }
    println("3글자 이상: $longNames")
    
    // filterNot - 조건에 맞지 않는 요소
    val oddNumbers = numbers.filterNot { it % 2 == 0 }
    println("홀수: $oddNumbers")
    
    // partition - 조건으로 두 그룹으로 분리
    val (evens, odds) = numbers.partition { it % 2 == 0 }
    println("짝수: $evens, 홀수: $odds")
    
    // take, drop
    val firstThree = numbers.take(3)
    val lastSeven = numbers.drop(3)
    println("처음 3개: $firstThree")
    println("3개 제외 나머지: $lastSeven")
    
    // takeWhile, dropWhile
    val takeWhileSmall = numbers.takeWhile { it < 5 }
    val dropWhileSmall = numbers.dropWhile { it < 5 }
    println("5 미만까지: $takeWhileSmall")
    println("5 이상부터: $dropWhileSmall")
    
    println("\n=== 집계 연산자 ===")
    
    // reduce - 누적 연산
    val sum = numbers.reduce { acc, n -> acc + n }
    println("합계 (reduce): $sum")
    
    val product = numbers.take(5).reduce { acc, n -> acc * n }
    println("1~5 곱: $product")
    
    // fold - 초기값이 있는 누적
    val sumWithInit = numbers.fold(100) { acc, n -> acc + n }
    println("합계 + 100: $sumWithInit")
    
    // 문자열 연결
    val nameString = names.fold("명단: ") { acc, name -> 
        "$acc$name, "
    }
    println(nameString.dropLast(2))
    
    // 기본 집계 함수들
    println("\n기본 집계:")
    println("합계: ${numbers.sum()}")
    println("평균: ${numbers.average()}")
    println("최대: ${numbers.maxOrNull()}")
    println("최소: ${numbers.minOrNull()}")
    println("개수: ${numbers.count()}")
    println("5보다 큰 개수: ${numbers.count { it > 5 }}")
    
    println("\n=== 그룹화 연산자 ===")
    
    // groupBy - 키로 그룹화
    val groupedByLength = names.groupBy { it.length }
    println("길이별 그룹: $groupedByLength")
    
    val groupedByFirstChar = names.groupBy { it.first() }
    println("첫 글자별: $groupedByFirstChar")
    
    // 학생 성적 그룹화
    val students = listOf(
        Student3("배제우", 95, "A"),
        Student3("김철수", 88, "B"),
        Student3("이영희", 92, "A"),
        Student3("박민수", 78, "C"),
        Student3("최지우", 85, "B")
    )
    
    val byGrade = students.groupBy { it.grade }
    println("\n등급별 학생:")
    byGrade.forEach { (grade, list) ->
        println("$grade: ${list.map { it.name }}")
    }
    
    println("\n=== 정렬 연산자 ===")
    
    // sorted, sortedDescending
    println("before shuffled: $numbers")
    println("after shuffled: ${numbers.shuffled()}")
    val sorted = numbers.sorted()
    val sortedDesc = numbers.sortedDescending()
    println("오름차순: $sorted")
    println("내림차순: $sortedDesc")
    
    // sortedBy, sortedByDescending
    val sortedByLength = names.sortedBy { it.length }
    val sortedByScore = students.sortedByDescending { it.score }
    println("길이순: $sortedByLength")
    println("성적순: ${sortedByScore.map { "${it.name}(${it.score})" }}")
    
    println("\n=== 기타 유용한 연산자 ===")
    
    // distinct - 중복 제거
    val duplicates = listOf(1, 2, 2, 3, 3, 3, 4)
    println("중복 제거: ${duplicates.distinct()}")
    
    // zip - 두 리스트 결합
    val ids = listOf(1, 2, 3)
    val usernames = listOf("배제우", "김철수", "이영희")
    val zipped = ids.zip(usernames)
    println("Zip: $zipped")
    
    // chunked - 청크로 나누기
    val chunked = numbers.chunked(3)
    println("3개씩 나누기: $chunked")
    
    // windowed - 슬라이딩 윈도우
    val windowed = numbers.windowed(3, 2)
    println("윈도우(3, step 2): $windowed")
    
    // any, all, none
    println("\n조건 확인:")
    println("하나라도 5보다 큰가? ${numbers.any { it > 5 }}")
    println("모두 0보다 큰가? ${numbers.all { it > 0 }}")
    println("모두 100보다 작은가? ${numbers.none { it >= 100 }}")
    
    // find, findLast
    val firstEven = numbers.find { it % 2 == 0 }
    val lastOdd = numbers.findLast { it % 2 == 1 }
    println("첫 짝수: $firstEven")
    println("마지막 홀수: $lastOdd")
}

data class Student3(val name: String, val score: Int, val grade: String)
