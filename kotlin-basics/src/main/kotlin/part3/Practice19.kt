package part3

fun main() {
    println("=== 기본 Enum ===")
    
    val today = DayOfWeek.WEDNESDAY
    println("오늘: $today")
    println("한글명: ${today.koreanName}")
    println("평일? ${today.isWeekday()}")
    
    // 모든 값 출력 with values(): Array<DayOfWeek>
    // ordinal: 열거형 상수의 순서(0부터 시작)
    // values()는 매번 새로운 배열을 생성
    println("\n모든 요일:")
    DayOfWeek.values().forEach { day ->
        println("${day.ordinal}: $day (${day.koreanName})")
    }

    // 모든 값 출력 with entries - Kotlin 1.9+,
    // values()와 다르게 미리 만들어진 불변 리스트를 반환 List<DayOfWeek>
    val result = DayOfWeek.entries
    result.forEach({ day ->
        println("${day.ordinal}: $day (${day.koreanName})")
    })

    // enum valueOf 잘못된 값 처리
    try {
        DayOfWeek.valueOf("WRONG_DAY")
    }
    catch (e: Exception) {
        println("error: ${e.message}")
    }
    
    println("\n=== 주문 상태 ===")
    
    val order = Order("ORD-001", OrderStatus.PENDING)
    processOrder(order)
    
    // 상태 변경 시뮬레이션
    val statuses = listOf(
        OrderStatus.PENDING,
        OrderStatus.CONFIRMED,
        OrderStatus.SHIPPED,
        OrderStatus.DELIVERED
    )
    
    statuses.forEach { status ->
        println("상태: ${status.description} (코드: ${status.code})")
        if (status.canCancel()) {
            println("  취소 가능")
        } else {
            println("  취소 불가")
        }
    }
    
    println("\n=== 색상 Enum ===")
    
    val color = Color.GREEN
    println("색상: $color")
    println("RGB: ${color.rgb}")
    println("Hex: ${color.toHex()}")
    
    // when과 함께 사용
    val signal = when(color) {
        Color.RED -> "정지"
        Color.YELLOW -> "주의"
        Color.GREEN -> "진행"
        else -> "알 수 없음"
    }
    println("신호: $signal")
    
    println("\n=== 회원 등급 ===")
    
    val grades = listOf(
        MemberGrade.BRONZE,
        MemberGrade.SILVER,
        MemberGrade.GOLD,
        MemberGrade.PLATINUM
    )
    
    grades.forEach { grade ->
        val price = 100000
        val finalPrice = grade.calculatePrice(price)
        println("${grade.gradeName}: ${price}원 → ${finalPrice}원 (${grade.discountRate}% 할인)")
        println("  혜택: ${grade.benefits}")
    }
    
    println("\n=== Enum valueOf ===")
    
    val dayName = "FRIDAY"
    val day = DayOfWeek.valueOf(dayName)
    println("$dayName → $day (${day.koreanName})")
    
    // 안전한 변환
    val invalidName = "INVALID"
    val safeDay = try {
        DayOfWeek.valueOf(invalidName)
    } catch (e: IllegalArgumentException) {
        println("'$invalidName'는 유효하지 않은 요일입니다")
        null
    }
}

// 1. 기본 Enum
enum class DayOfWeek(val koreanName: String) {
    MONDAY("월요일"),
    TUESDAY("화요일"),
    WEDNESDAY("수요일"),
    THURSDAY("목요일"),
    FRIDAY("금요일"),
    SATURDAY("토요일"),
    SUNDAY("일요일");
    companion object {
        val WEEKEND_DAYS = setOf(SATURDAY, SUNDAY)
    }
    fun isWeekday(): Boolean {
        return this !in WEEKEND_DAYS
    }
}

// 2. 주문 상태 Enum
enum class OrderStatus(val code: String, val description: String) {
    PENDING("P", "주문 대기"),
    CONFIRMED("C", "주문 확인"),
    SHIPPED("S", "배송 중"),
    DELIVERED("D", "배송 완료"),
    CANCELLED("X", "주문 취소");
    
    fun canCancel(): Boolean {
        return this in listOf(PENDING, CONFIRMED)
    }
    
    fun nextStatus(): OrderStatus? {
        return when(this) {
            PENDING -> CONFIRMED
            CONFIRMED -> SHIPPED
            SHIPPED -> DELIVERED
            else -> null
        }
    }
}

data class Order(val id: String, val status: OrderStatus)

fun processOrder(order: Order) {
    when(order.status) {
        OrderStatus.PENDING -> println("${order.id}: 주문 검토 중...")
        OrderStatus.CONFIRMED -> println("${order.id}: 상품 준비 중...")
        OrderStatus.SHIPPED -> println("${order.id}: 배송 시작!")
        OrderStatus.DELIVERED -> println("${order.id}: 배송 완료!")
        OrderStatus.CANCELLED -> println("${order.id}: 주문이 취소되었습니다")
    }
}

// 3. RGB 색상 Enum
enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF),
    YELLOW(0xFFFF00),
    BLACK(0x000000),
    WHITE(0xFFFFFF);
    
    fun toHex(): String {
        return "#${rgb.toString(16).padStart(6, '0').uppercase()}"
    }
}

// 4. 회원 등급 (복잡한 프로퍼티와 메소드)
enum class MemberGrade(
    val gradeName: String,
    val discountRate: Int,
    val benefits: List<String>
) {
    BRONZE("브론즈", 5, listOf("기본 할인")),
    SILVER("실버", 10, listOf("기본 할인", "무료 배송")),
    GOLD("골드", 15, listOf("기본 할인", "무료 배송", "우선 고객 서비스")),
    PLATINUM("플래티넘", 20, listOf("기본 할인", "무료 배송", "우선 고객 서비스", "VIP 라운지"));
    
    fun calculatePrice(originalPrice: Int): Int {
        return originalPrice * (100 - discountRate) / 100
    }
}
