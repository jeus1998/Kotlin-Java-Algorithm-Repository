package part2

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