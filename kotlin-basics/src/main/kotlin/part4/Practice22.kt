package part4

fun main() {
    println("=== 람다식 기본 ===")
    
    // 기본 람다식
    val sum = { x: Int, y: Int -> x + y }
    println("5 + 3 = ${sum(5, 3)}")
    
    // 타입 추론
    val multiply: (Int, Int) -> Int = { x, y -> x * y }
    println("4 × 6 = ${multiply(4, 6)}")
    
    // 단일 매개변수는 it 사용 가능
    val double: (Int) -> Int = { it * 2 }
    println("7 × 2 = ${double(7)}")
    
    // 매개변수 없는 람다
    val greeting = { println("안녕하세요!") }
    greeting()
    
    // 여러 줄 람다 (마지막 줄이 반환값)
    val calculator = { x: Int, y: Int, op: String ->
        println("계산: $x $op $y")
        when (op) {
            "+" -> x + y
            "-" -> x - y
            "*" -> x * y
            "/" -> if (y != 0) x / y else 0
            else -> 0
        }
    }
    println("결과: ${calculator(10, 5, "+")}")
    
    println("\n=== 고차 함수 ===")
    
    // 함수를 매개변수로 받는 고차 함수
    fun operate(x: Int, y: Int, operation: (Int, Int) -> Int): Int {
        return operation(x, y)
    }
    
    val result1 = operate(10, 5, sum)
    val result2 = operate(10, 5) { a, b -> a - b }  // 람다를 직접 전달
    println("고차 함수 결과1: $result1")
    println("고차 함수 결과2: $result2")
    
    // 함수를 반환하는 고차 함수
    fun getOperation(type: String): (Int, Int) -> Int {
        return when (type) {
            "add" -> { x, y -> x + y }
            "subtract" -> { x, y -> x - y }
            else -> { x, y -> 0 }
        }
    }
    
    val addFunc = getOperation("add")
    println("함수 반환: ${addFunc(15, 25)}")
    
    println("\n=== 실용적인 예제 ===")
    
    // 1. 리스트 처리
    val numbers = listOf(1, 2, 3, 4, 5)
    
    val doubled = numbers.map { it * 2 }
    val filtered = numbers.filter { it > 2 }
    val sum2 = numbers.reduce { acc, n -> acc + n }
    
    println("원본: $numbers")
    println("2배: $doubled")
    println("2보다 큰 수: $filtered")
    println("합계: $sum2")
    
    // 2. 커스텀 고차 함수
    fun <T> List<T>.customFilter(predicate: (T) -> Boolean): List<T> {
        val result = mutableListOf<T>()
        for (item in this) {
            if (predicate(item)) {
                result.add(item)
            }
        }
        return result
    }
    
    val names = listOf("배제우", "김", "이영희", "박")
    val longNames = names.customFilter { it.length >= 2 }
    println("\n2글자 이상 이름: $longNames")
    
    // 3. 함수 조합
    fun compose(f: (Int) -> Int, g: (Int) -> Int): (Int) -> Int {
        return { x -> f(g(x)) }
    }
    
    val addOne = { x: Int -> x + 1 }
    val multiplyTwo = { x: Int -> x * 2 }
    val combined = compose(addOne, multiplyTwo)  // (x * 2) + 1
    
    println("\n함수 조합: 5 → ${combined(5)}")  // (5 * 2) + 1 = 11
    
    // 4. 지연 실행
    fun executeWithTiming(action: () -> Unit) {
        val start = System.currentTimeMillis()
        action()
        val end = System.currentTimeMillis()
        println("실행 시간: ${end - start}ms")
    }
    
    executeWithTiming {
        println("작업 수행 중...")
        Thread.sleep(100)
        println("작업 완료!")
    }
    
    // 5. 조건부 실행 (inline 함수를 main 밖으로 이동)
    val age = 28
    runIf(age >= 20) {
        println("\n성인입니다!")
    }
    
    // 6. 에러 처리
    fun tryOperation(operation: () -> Int): Int? {
        return try {
            operation()
        } catch (e: Exception) {
            println("오류 발생: ${e.message}")
            null
        }
    }
    
    val result = tryOperation {
        println("\n위험한 작업 시도...")
        10 / 2  // 성공
    }
    println("결과: $result")
    
    // 7. 함수 참조
    fun isEven(n: Int) = n % 2 == 0
    
    val evenNumbers = numbers.filter(::isEven)  // 함수 참조
    println("\n짝수: $evenNumbers")
}

// 조건부 실행을 위한 inline 함수
inline fun runIf(condition: Boolean, action: () -> Unit) {
    if (condition) action()
}
