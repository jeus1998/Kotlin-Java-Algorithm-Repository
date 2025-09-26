package part5

fun main() {
    println("=== 타입 별칭 (Type Alias) ===")
    
    // 기본 타입 별칭
    val users: UserMap = mapOf(
        1 to "배제우",
        2 to "김철수"
    )
    println("사용자 맵: $users")
    
    // 함수 타입 별칭
    val operation: MathOperation = { a, b -> a + b }
    val result = performOperation(10, 5, operation)
    println("연산 결과: $result")
    
    // 중첩된 컬렉션 타입 별칭
    val matrix: Matrix<Int> = listOf(
        listOf(1, 2, 3),
        listOf(4, 5, 6)
    )
    printMatrix(matrix)
    
    println("\n=== 인라인 클래스 (Value Class) ===")
    
    // 인라인 클래스 사용
    val userId = UserId(123)
    val email = Email("baejewo@example.com")
    val password = Password("secretPass123!")
    
    println("User ID: ${userId.value}")
    println("Email: ${email.value}")
    println("Password strength: ${password.strength()}")
    
    // 타입 안전성
    // sendEmail(userId)  // 오류! UserId는 Email이 아님
    sendEmail(email)  // OK
    
    println("\n=== 인라인 클래스의 장점 ===")
    
    // 1. 타입 안전성
    val price = Price(1000.0)
    val quantity = Quantity(5)
    val total = calculateTotal(price, quantity)
    println("총액: ${total.value}")
    
    // 2. 성능 (boxing 없음)
    val meters = Distance(100.0)
    val doubled = meters.double()
    println("두 배 거리: ${doubled.value}m")
    
    println("\n=== 실용적인 예제 ===")
    
    // 1. 도메인 모델
    val order = Order(
        id = OrderId("ORD-001"),
        customerId = CustomerId(1001),
        amount = Money(50000.0)
    )
    
    processOrder(order)
    
    // 2. 단위 변환
    val celsius = Temperature(25.0)
    val fahrenheit = celsius.toFahrenheit()
    println("\n온도 변환: ${celsius.value}°C = ${fahrenheit}°F")
    
    // 3. 검증이 있는 인라인 클래스
    try {
        val validEmail = ValidatedEmail("baejewo@example.com")
        println("유효한 이메일: ${validEmail.value}")
        
        val invalidEmail = ValidatedEmail("invalid-email")  // 예외 발생
    } catch (e: Exception) {
        println("이메일 검증 실패: ${e.message}")
    }
    
    // 4. 복잡한 타입 별칭
    val eventHandlers: EventHandlerMap = mutableMapOf()
    eventHandlers["click"] = mutableListOf(
        { println("클릭 핸들러 1") },
        { println("클릭 핸들러 2") }
    )
    
    eventHandlers["click"]?.forEach { it() }
}

// 타입 별칭 정의
typealias UserMap = Map<Int, String>
typealias MathOperation = (Int, Int) -> Int
typealias Matrix<T> = List<List<T>>
typealias EventHandler = () -> Unit
typealias EventHandlerMap = MutableMap<String, MutableList<EventHandler>>

// 타입 별칭을 사용하는 함수
fun performOperation(a: Int, b: Int, operation: MathOperation): Int {
    return operation(a, b)
}

fun <T> printMatrix(matrix: Matrix<T>) {
    println("Matrix:")
    matrix.forEach { row ->
        println(row.joinToString(" "))
    }
}

// 인라인 클래스 정의
@JvmInline
value class UserId(val value: Int)

@JvmInline
value class Email(val value: String) {
    init {
        require(value.contains("@")) { "유효한 이메일이 아닙니다" }
    }
}

@JvmInline
value class Password(val value: String) {
    fun strength(): String {
        return when {
            value.length >= 12 -> "강함"
            value.length >= 8 -> "보통"
            else -> "약함"
        }
    }
}

@JvmInline
value class Price(val value: Double) {
    operator fun times(quantity: Quantity): Price {
        return Price(value * quantity.value)
    }
}

@JvmInline
value class Quantity(val value: Int)

@JvmInline
value class Distance(val value: Double) {
    fun double(): Distance = Distance(value * 2)
    fun toKm(): Double = value / 1000
}

@JvmInline
value class Temperature(val value: Double) {
    fun toFahrenheit(): Double = value * 9/5 + 32
    fun toCelsius(): Double = (value - 32) * 5/9
}

// 비즈니스 로직용 인라인 클래스
@JvmInline
value class OrderId(val value: String)

@JvmInline
value class CustomerId(val value: Int)

@JvmInline
value class Money(val value: Double) {
    fun format(): String = "$${String.format("%.2f", value)}"
}

// 검증이 있는 인라인 클래스
@JvmInline
value class ValidatedEmail(val value: String) {
    init {
        require(isValidEmail(value)) { "유효하지 않은 이메일: $value" }
    }
    
    companion object {
        private fun isValidEmail(email: String): Boolean {
            return email.contains("@") && email.contains(".")
        }
    }
}

// 도메인 모델
data class Order(
    val id: OrderId,
    val customerId: CustomerId,
    val amount: Money
)

// 헬퍼 함수들
fun sendEmail(email: Email) {
    println("이메일 전송: ${email.value}")
}

fun calculateTotal(price: Price, quantity: Quantity): Price {
    return price * quantity
}

fun processOrder(order: Order) {
    println("주문 처리:")
    println("  주문 ID: ${order.id.value}")
    println("  고객 ID: ${order.customerId.value}")
    println("  금액: ${order.amount.format()}")
}
