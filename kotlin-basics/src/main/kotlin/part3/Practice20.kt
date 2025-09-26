package part3

import kotlin.properties.Delegates

fun main() {
    println("=== 클래스 위임 기본 ===")
    
    // 기본 구현체
    val basicPrinter = BasicPrinter()
    basicPrinter.print("Hello")
    
    // 위임을 사용한 래퍼 클래스
    val wrappedPrinter = WrappedPrinter(basicPrinter)
    wrappedPrinter.print("World")
    
    println("\n=== 선택적 오버라이드 ===")
    
    val decoratedPrinter = DecoratedPrinter(basicPrinter)
    decoratedPrinter.print("Kotlin")
    
    println("\n=== 프로퍼티 위임 - lazy ===")
    
    val config = Configuration()
    println("설정 객체 생성됨")
    println("Database URL: ${config.databaseUrl}")  // 이때 초기화
    println("Database URL (재접근): ${config.databaseUrl}")  // 캐시된 값 사용
    
    println("\n=== 프로퍼티 위임 - observable ===")
    
    val user = ObservableUser()
    println("초기 이름: ${user.name}, 초기 나이: ${user.age}")
    user.name = "배제우"
    user.age = 28
    user.age = 29
    println("최종 이름: ${user.name}, 최종 나이: ${user.age}")
    
    println("\n=== 프로퍼티 위임 - vetoable ===")
    
    val account = BankAccountWithVeto()
    println("초기 잔액: ${account.balance}")
    account.balance = 5000  // 승인
    account.balance = -100  // 거부
    println("최종 잔액: ${account.balance}")
    
    println("\n=== Map 위임 ===")
    
    val userData = mapOf(
        "name" to "배제우",
        "age" to 28,
        "email" to "bae@email.com"
    )
    val userFromMap = UserData(userData)
    println("Map에서 생성된 사용자: $userFromMap")
    
    println("\n=== 실용적인 예제 - 로깅 ===")
    
    val repository = UserRepository()
    val loggingRepository = LoggingUserRepository(repository)
    
    loggingRepository.save("user1")
    loggingRepository.delete("user2")
    val user1 = loggingRepository.find("user1")
    println("찾은 사용자: $user1")
}

// 1. 인터페이스 정의
interface Printer {
    fun print(message: String)
}

// 2. 기본 구현
class BasicPrinter : Printer {
    override fun print(message: String) {
        println("출력: $message")
    }
}

// 3. 위임을 사용한 래퍼 (모든 메소드 위임)
class WrappedPrinter(printer: Printer) : Printer by printer

// 4. 선택적 오버라이드
class DecoratedPrinter(private val printer: Printer) : Printer by printer {
    override fun print(message: String) {
        println("========")
        printer.print(message)  // 원래 구현 호출
        println("========")
    }
}

// 5. Lazy 프로퍼티 위임
class Configuration {
    // 처음 접근할 때만 초기화
    val databaseUrl: String by lazy {
        println("Database URL 초기화 중...")
        Thread.sleep(100)  // 무거운 작업 시뮬레이션
        "jdbc:mysql://localhost:3306/mydb"
    }
}

// 6. Observable 프로퍼티 위임
class ObservableUser {
    var name: String by Delegates.observable("초기값") { property, oldValue, newValue ->
        println("${property.name} 변경: $oldValue → $newValue")
    }
    
    var age: Int by Delegates.observable(0) { property, oldValue, newValue ->
        println("${property.name} 변경: $oldValue → $newValue")
    }
}

// 7. Vetoable 프로퍼티 위임
class BankAccountWithVeto {
    var balance: Int by Delegates.vetoable(1000) { property, oldValue, newValue ->
        if (newValue < 0) {
            println("잔액은 음수가 될 수 없습니다")
            false  // 변경 거부
        } else {
            println("잔액 변경: $oldValue → $newValue")
            true   // 변경 승인
        }
    }
}

// 8. Map 위임
class UserData(map: Map<String, Any?>) {
    val name: String by map
    val age: Int by map
    val email: String by map
    
    override fun toString() = "UserData(name=$name, age=$age, email=$email)"
}

// 9. 실용적인 예제 - Repository 패턴
interface UserRepositoryInterface {
    fun save(user: String)
    fun find(id: String): String?
    fun delete(id: String)
}

class UserRepository : UserRepositoryInterface {
    private val users = mutableMapOf<String, String>()
    
    override fun save(user: String) {
        users[user] = user
        println("사용자 저장: $user")
    }
    
    override fun find(id: String): String? {
        return users[id]
    }
    
    override fun delete(id: String) {
        users.remove(id)
        println("사용자 삭제: $id")
    }
}

// 로깅 기능을 위임으로 추가
class LoggingUserRepository(
    private val repository: UserRepositoryInterface
) : UserRepositoryInterface by repository {
    
    override fun save(user: String) {
        println("[LOG] save 호출: $user")
        repository.save(user)
        println("[LOG] save 완료")
    }
    
    override fun delete(id: String) {
        println("[LOG] delete 호출: $id")
        repository.delete(id)
        println("[LOG] delete 완료")
    }
    // find는 위임 그대로 사용
}
