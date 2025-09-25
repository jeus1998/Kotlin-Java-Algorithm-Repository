package part2

fun main() {
    // 간단한 클래스 생성
    val person1 = SimpleClass("배제우", "서울", 28)
    println("이름: ${person1.name}, 주소: ${person1.address}, 나이: ${person1.age}")

    // 기본값이 있는 클래스
    val person2 = SimpleClass("배제우", "부산")  // age는 기본값 28 사용
    println("이름: ${person2.name}, 나이: ${person2.age}")

    // getter/setter override
    val person3 = PersonWithGetterSetter("배제우", "서울", 28)
    println("주소 getter: ${person3.address}") // "서울 특별시" 출력
    person3.address = "부산"  // setter 호출
    println("변경된 주소: ${person3.address}")   // "부산 특별시" 출력

    // init 블록이 있는 클래스
    val person4 = PersonWithInit("배제우", "대전", 28)

    // 메소드가 있는 클래스
    val person5 = PersonWithMethods("배제우", 28)
    println("\n성인입니까? ${person5.isAdult()}")
    person5.printInfo()
    person5.haveBirthday()

    // private setter
    val account = BankAccount("배제우", 1000000)
    println("\n계좌 잔액: ${account.balance}")
    account.deposit(50000)
    account.withdraw(30000)
    // account.balance = 2000000  // 컴파일 오류! private setter
}

// 1. 가장 간단한 클래스 (프로퍼티와 생성자 동시 선언)
class SimpleClass(
    val name: String,
    var address: String,
    var age: Int = 28  // 기본값
)

// 2. getter/setter 오버라이드
class PersonWithGetterSetter(
    val name: String,
    address: String,
    var age: Int
) {
    var address: String = address
        get() = "$field 특별시"  // field는 실제 저장된 값
        set(value) {
            field = "$value"
            println("주소가 '$value 특별시'로 변경됨")
        }
}

// 3. init 블록 사용
class PersonWithInit(name: String, address: String, age: Int) {
    val name: String
    var address: String
    var age: Int

    init {
        println("PersonWithInit 객체 생성중...")
        this.name = name
        this.address = address
        this.age = age
        println("초기화 완료: $name, $address, $age")
    }
}

// 4. 메소드가 있는 클래스
class PersonWithMethods(
    private val name: String,
    private var age: Int
) {
    fun isAdult(): Boolean = age >= 20

    fun printInfo() {
        println("정보: $name(${age}살)")
    }

    fun haveBirthday() {
        age++
        println("${name}님 생일 축하! 이제 ${age}살")
    }
}

// 5. private setter (캡슐화)
class BankAccount(
    val owner: String,
    initialBalance: Int
) {
    var balance: Int = initialBalance
        private set  // 외부에서 직접 변경 불가

    fun deposit(amount: Int) {
        if (amount > 0) {
            balance += amount
            println("$amount 원 입금. 잔액: $balance")
        }
    }

    fun withdraw(amount: Int): Boolean {
        return if (amount <= balance) {
            balance -= amount
            println("$amount 원 출금. 잔액: $balance")
            true
        } else {
            println("잔액 부족!")
            false
        }
    }
}