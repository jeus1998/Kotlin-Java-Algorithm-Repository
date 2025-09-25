package part3

fun main() {
    println("=== Data 클래스 기본 ===")
    val user1 = User(1, "배제우", "baejewo@email.com")
    val user2 = User(1, "배제우", "baejewo@email.com")
    val user3 = User(2, "김철수", "kimcs@email.com")

    // toString() 자동 생성
    println("user1: $user1")
    println("user2: $user2")
    println("user3: $user3")

    // equals() 자동 생성 (내용 비교)
    println("\n=== equals() 비교 ===")
    println("user1 == user2: ${user1 == user2}")  // true (내용이 같음)
    println("user1 === user2: ${user1 === user2}")  // false (다른 객체)
    println("user1 == user3: ${user1 == user3}")  // false

    // hashCode() 자동 생성
    println("\n=== hashCode() ===")
    println("user1 hashCode: ${user1.hashCode()}")
    println("user2 hashCode: ${user2.hashCode()}")  // user1과 같음
    println("user3 hashCode: ${user3.hashCode()}")

    // copy() 자동 생성
    println("\n=== copy() 사용 ===")
    val user4 = user1.copy()
    val user5 = user1.copy(name = "이영희")  // 일부만 변경
    val user6 = user1.copy(id = 10, email = "new@email.com")

    println("원본: $user1")
    println("전체 복사: $user4")
    println("이름만 변경: $user5")
    println("id, email 변경: $user6")

    // componentN() - 구조 분해
    println("\n=== 구조 분해 선언 ===")
    val (id, name, email) = user1 // 내부적으로 component1(), component2(), component3() 호출
    println("id: $id, name: $name, email: $email")
    println("id: ${user1.component1()}, name: ${user1.component2()}, email: ${user1.component3()}")

    // 실용적인 예제
    println("\n=== 실용적인 예제 ===")
    val product1 = Product("노트북", 1500000, "전자제품")
    val product2 = product1.copy(price = 1400000)  // 할인 적용

    println("원가: $product1")
    println("할인가: $product2")
    println("할인액: ${product1.price - product2.price}원")

    // Collection과 함께 사용
    println("\n=== Collection과 사용 ===")
    val users = listOf(
        User(1, "배제우", "bae@email.com"),
        User(2, "김철수", "kim@email.com"),
        User(3, "이영희", "lee@email.com")
    )

    val userMap = users.associateBy { it.id }
    println("User Map: $userMap")

    val userSet = setOf(
        User(1, "배제우", "bae@email.com"),
        User(1, "배제우", "bae@email.com"),  // 중복 (자동 제거)
        User(2, "김철수", "kim@email.com")
    )
    println("User Set 크기: ${userSet.size}")  // 2개만 저장됨
}

// 1. 기본 Data 클래스
data class User(
    val id: Int,
    val name: String,
    val email: String
)

// 2. 기본값이 있는 Data 클래스
data class Product(
    val name: String,
    val price: Int,
    val category: String = "미분류"
)