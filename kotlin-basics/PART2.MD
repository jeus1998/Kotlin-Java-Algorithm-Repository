# Kotlin 기본 문법 정복 Part2

## 11. 클래스 기본 선언

```text
Kotlin의 클래스는 간결한 문법으로 생성자와 프로퍼티를 동시에 선언할 수 있습니다.
프로퍼티(property)는 클래스의 속성(상태)를 표현하는 언어 요소로,
자바의 필드 + getter/setter를 합친 개념입니다.
```
- 기본 생성자는 클래스명 옆에 선언
- `val`/`var`로 프로퍼티와 생성자 매개변수 동시 선언이 가능
```kotlin
// Practice11.kt 파일
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
```

## 12. 생성자 오버로딩

```text
Kotlin에서는 주 생성자(primary constructor)와 부 생성자(secondary constructor)를 통해
다양한 방식으로 객체를 생성할 수 있습니다.
```
- 기본(주) 생성자: 클래스명 옆 선언
- 보조(부) 생성자: `constructor` 키워드 사용
- `this()`로 다른 생성자 호출 가능
- `lateinit`: 나중에 초기화할 프로퍼티에 사용

```kotlin
// Practice12.kt 파일
fun main() {
    // 다양한 생성자 사용
    val user1 = User("배제우")
    val user2 = User("배제우", "baejewo@email.com")
    val user3 = User("배제우", "baejewo@email.com", 28)

    user1.printInfo()
    user2.printInfo()
    user3.printInfo()

    println("\n=== Student 클래스 ===")
    val student1 = Student("배제우", 28)
    val student2 = Student("배제우", 28, "컴퓨터공학", 3.8)

    student1.printInfo()
    student2.printInfo()

    println("\n=== Product 클래스 ===")
    val product1 = Product("노트북")
    val product2 = Product("노트북", 1500000)
    val product3 = Product("노트북", 1500000, "최신형 게이밍 노트북")

    product1.display()
    product2.display()
    product3.display()

    println("\n=== lateinit 사용 ===")
    val config = Configuration()
    // config.printConfig()  // 초기화 전 접근시 오류!
    config.initialize("배제우", "서버1")
    config.printConfig()
}

// 1. 보조 생성자를 사용한 오버로딩
class User(val name: String) {
    var email: String = ""
    var age: Int = 0

    // 보조 생성자 1
    constructor(name: String, email: String) : this(name) {
        this.email = email
        println("User 2개 매개변수 생성자 호출")
    }

    // 보조 생성자 2
    constructor(name: String, email: String, age: Int) : this(name, email) {
        this.age = age
        println("User 3개 매개변수 생성자 호출")
    }

    fun printInfo() {
        println("User: $name, $email, ${age}살")
    }
}

// 2. 기본 생성자 + 보조 생성자 조합
class Student(
    val name: String,
    val age: Int
) {
    var major: String = "미정"
    var gpa: Double = 0.0

    init {
        println("Student 기본 생성자 호출: $name")
    }

    // 보조 생성자
    constructor(name: String, age: Int, major: String, gpa: Double) : this(name, age) {
        this.major = major
        this.gpa = gpa
        println("Student 보조 생성자 호출")
    }

    fun printInfo() {
        println("학생: $name(${age}살), 전공: $major, 학점: $gpa")
    }
}

// 3. 기본값과 보조 생성자 활용
class Product {
    val name: String
    var price: Int
    var description: String

    // 기본 생성자 대신 보조 생성자만 사용
    constructor(name: String) {
        this.name = name
        this.price = 0
        this.description = "설명 없음"
    }

    constructor(name: String, price: Int) {
        this.name = name
        this.price = price
        this.description = "설명 없음"
    }

    constructor(name: String, price: Int, description: String) {
        this.name = name
        this.price = price
        this.description = description
    }

    fun display() {
        println("상품: $name, 가격: $price 원, 설명: $description")
    }
}

// 4. lateinit 사용한 지연 초기화
class Configuration {
    lateinit var username: String
    lateinit var serverName: String
    var port: Int = 8080  // 기본값이 있는 프로퍼티

    fun initialize(username: String, serverName: String) {
        this.username = username
        this.serverName = serverName
        println("Configuration 초기화 완료")
    }

    fun printConfig() {
        // lateinit 프로퍼티가 초기화되었는지 확인
        if (::username.isInitialized && ::serverName.isInitialized) {
            println("설정: $username@$serverName:$port")
        } else {
            println("아직 초기화되지 않음")
        }
    }
}
```


