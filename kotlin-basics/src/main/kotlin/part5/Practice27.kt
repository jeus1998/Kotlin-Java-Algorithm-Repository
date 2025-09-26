package part5

fun main() {
    println("=== 기본 예외 처리 ===")
    // try-catch
    try {
        val result = 10 / 0
        println("결과: $result")
    } catch (e: ArithmeticException) {
        println("산술 오류: ${e.message}")
    }
    
    // try-catch-finally
    try {
        val text = readText()
        println("읽은 텍스트: $text")
    } catch (e: Exception) {
        println("오류 발생: ${e.message}")
    } finally {
        println("정리 작업 수행")
    }
    
    println("\n=== try를 표현식으로 사용 ===")
    
    val number: Int? = try {
        "123".toInt()
    } catch (e: NumberFormatException) {
        null
    }
    println("변환된 숫자: $number")
    
    val result = try {
        riskyOperation()
        "성공"
    } catch (e: Exception) {
        "실패: ${e.message}"
    }
    println("작업 결과: $result")
    
    println("\n=== throw 표현식 ===")
    
    // throw 표현식 예제
    fun requireNotNull(value: String?): String {
        return value ?: throw IllegalArgumentException("값이 null입니다")
    }
    
    try {
        val name: String? = null
        val validName = requireNotNull(name)
        println("이름: $validName")
    } catch (e: IllegalArgumentException) {
        println("예외 처리: ${e.message}")
    }
    
    println("\n=== 커스텀 예외 ===")
    
    try {
        validateAge(-5)
    } catch (e: InvalidAgeException) {
        println("나이 검증 실패: ${e.message}")
    }
    
    try {
        processUser("", 150)
    } catch (e: ValidationException) {
        when (e) {
            is EmptyNameException -> println("이름 오류: ${e.message}")
            is InvalidAgeException -> println("나이 오류: ${e.message}")
        }
    }
    
    println("\n=== runCatching ===")
    
    val result1 = runCatching {
        "123".toInt()
    }
    
    if (result1.isSuccess) {
        println("성공: ${result1.getOrNull()}")
    } else {
        println("실패: ${result1.exceptionOrNull()?.message}")
    }
    
    // 체이닝
    val result2 = runCatching { "abc".toInt() }
        .recover { 0 }  // 실패 시 기본값
        .map { it * 2 }  // 성공 시 변환
        .getOrDefault(100)  // 최종 기본값
    
    println("체이닝 결과: $result2")
    
    println("\n=== 실용적인 예제 ===")
    
    // 1. 안전한 나누기
    fun safeDivide(a: Int, b: Int): kotlin.Result<Int> {
        return runCatching {
            require(b != 0) { "0으로 나눌 수 없습니다" }
            a / b
        }
    }
    
    val divResult = safeDivide(10, 2)

    divResult.fold(
        onSuccess = { println("나누기 결과: $it") },
        onFailure = { println("나누기 실패: ${it.message}") }
    )
    
    // 2. 여러 예외 처리
    fun parseAndCalculate(input: String): Int {
        return try {
            val numbers = input.split(",").map { it.trim().toInt() }
            numbers.sum()
        } catch (e: NumberFormatException) {
            println("숫자 형식 오류")
            0
        } catch (e: Exception) {
            println("알 수 없는 오류: ${e.message}")
            0
        }
    }
    
    println("계산 결과: ${parseAndCalculate("1,2,3")}")
    println("계산 결과: ${parseAndCalculate("1,a,3")}")
    
    // 3. 리소스 자동 정리 (use)
    class Resource : AutoCloseable {
        init { println("리소스 열기") }
        fun work() { println("작업 수행") }
        override fun close() { println("리소스 닫기") }
    }
    
    Resource().use { resource ->
        resource.work()
        // 예외가 발생해도 자동으로 close() 호출됨
    }
}

// 커스텀 예외 클래스들
open class ValidationException(message: String) : Exception(message)
class InvalidAgeException(age: Int) : ValidationException("유효하지 않은 나이: $age")
class EmptyNameException : ValidationException("이름이 비어있습니다")

// 헬퍼 함수들
fun readText(): String {
    // 파일 읽기 시뮬레이션
    if ((0..1).random() == 0) {
        throw RuntimeException("파일을 읽을 수 없습니다")
    }
    return "파일 내용"
}

fun riskyOperation() {
    if ((0..1).random() == 0) {
        throw RuntimeException("위험한 작업 실패")
    }
}

fun validateAge(age: Int) {
    if (age < 0 || age > 120) {
        throw InvalidAgeException(age)
    }
}

fun processUser(name: String, age: Int) {
    if (name.isEmpty()) throw EmptyNameException()
    if (age < 0 || age > 120) throw InvalidAgeException(age)
    println("사용자 처리: $name, $age")
}
