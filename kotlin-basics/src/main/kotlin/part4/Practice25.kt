package part4

fun main() {
    println("=== 시퀀스 vs 컬렉션 ===")
    
    // 컬렉션 (즉시 평가)
    val list = listOf(1, 2, 3, 4, 5)
    val listResult = list
        .map { 
            println("List map: $it")
            it * 2 
        }
        .filter { 
            println("List filter: $it")
            it > 5 
        }
    println("List 결과: $listResult\n")
    
    // 시퀀스 (지연 평가)
    val sequence = listOf(1, 2, 3, 4, 5).asSequence()
    val sequenceResult = sequence
        .map { 
            println("Sequence map: $it")
            it * 2 
        }
        .filter { 
            println("Sequence filter: $it")
            it > 5 
        }
    
    println("아직 실행 안됨!")
    println("Sequence 결과: ${sequenceResult.toList()}")  // 이때 실행
    
    println("\n=== 성능 비교 ===")
    
    // 대용량 데이터에서 첫 번째 조건 만족 요소 찾기
    val largeList = (1..1000000).toList()
    
    // 컬렉션 방식
    val time1 = System.currentTimeMillis()
    val collectionResult = largeList
        .map { it * 2 }
        .filter { it > 1000 }
        .first()
    val time2 = System.currentTimeMillis()
    println("컬렉션 결과: $collectionResult, 시간: ${time2 - time1}ms")
    
    // 시퀀스 방식
    val time3 = System.currentTimeMillis()
    val sequenceResult2 = largeList.asSequence()
        .map { it * 2 }
        .filter { it > 1000 }
        .first()
    val time4 = System.currentTimeMillis()
    println("시퀀스 결과: $sequenceResult2, 시간: ${time4 - time3}ms")
    
    println("\n=== generateSequence ===")
    
    // 무한 시퀀스 생성
    val naturalNumbers = generateSequence(1) { it + 1 }
    val first10 = naturalNumbers.take(10).toList()
    println("자연수 1~10: $first10")
    
    // 피보나치 수열
    val fibonacci = generateSequence(Pair(0, 1)) { 
        Pair(it.second, it.first + it.second) 
    }.map { it.first }
    
    val fib10 = fibonacci.take(10).toList()
    println("피보나치 10개: $fib10")
    
    // 조건부 시퀀스 생성
    val powersOf2 = generateSequence(1) { 
        if (it < 1000) it * 2 else null 
    }
    println("2의 거듭제곱: ${powersOf2.toList()}")
    
    println("\n=== 실용적인 예제 ===")
    
    // 1. 파일 읽기 시뮬레이션 (대용량 파일)
    fun readLargeFile(): Sequence<String> {
        return generateSequence(1) { it + 1 }
            .take(100)
            .map { "Line $it: 데이터..." }
    }
    
    val lines = readLargeFile()
        .filter { it.contains("5") }
        .take(5)
    
    println("필터된 라인:")
    lines.forEach { println(it) }
    
    // 2. 소수 생성기
    fun isPrime(n: Int): Boolean {
        if (n < 2) return false
        return (2..Math.sqrt(n.toDouble()).toInt()).none { n % it == 0 }
    }
    
    val primes = generateSequence(2) { it + 1 }
        .filter { isPrime(it) }
    
    val first10Primes = primes.take(10).toList()
    println("\n처음 10개 소수: $first10Primes")
    
    // 3. 체이닝 최적화
    data class Product(val name: String, val price: Int, val category: String)
    
    val products = listOf(
        Product("노트북", 1500000, "전자제품"),
        Product("마우스", 30000, "전자제품"),
        Product("책", 15000, "도서"),
        Product("펜", 1000, "문구"),
        Product("모니터", 300000, "전자제품")
    )
    
    // 시퀀스로 필터링 체인
    val expensiveElectronics = products.asSequence()
        .filter { it.category == "전자제품" }
        .filter { it.price > 50000 }
        .map { it.name }
        .toList()
    
    println("\n비싼 전자제품: $expensiveElectronics")
    
    // 4. 중간 연산 확인
    val debugSequence = (1..5).asSequence()
        .onEach { println("원본: $it") }
        .map { it * 2 }
        .onEach { println("2배 후: $it") }
        .filter { it > 5 }
        .onEach { println("필터 후: $it") }
    
    println("\n디버깅 시퀀스 실행:")
    debugSequence.toList()
    
    // 5. sequence 빌더
    val customSequence = sequence {
        yield(1)  // 단일 값 생성
        yieldAll(listOf(2, 3, 4))  // 여러 값 생성
        yieldAll(generateSequence(5) { it + 1 }.take(3))  // 다른 시퀀스
    }
    
    println("\n커스텀 시퀀스: ${customSequence.toList()}")
    
    // 6. 무한 스트림 처리
    val randomNumbers = generateSequence { (1..100).random() }
    val average = randomNumbers
        .take(1000)
        .average()
    
    println("\n랜덤 1000개 평균: $average")
}
