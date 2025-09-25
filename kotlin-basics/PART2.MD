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

### 13. 상속(Inheritance)

```text
Kotlin에서는 open 키워드를 사용해 상속 가능한 클래스를 만듭니다. 기본적으로 모든 클래스는 final입니다.
```
- `open` 키워드로 상속 허용
- `override` 키워드로 메소드/프로퍼티 재정의
- `super` 키워드로 부모 클래스 참조

```kotlin
// Practice13.kt 파일
fun main() {
    println("=== 기본 상속 ===")
    val animal = Animal("동물", 5)
    animal.makeSound()
    animal.eat()

    val dog = Dog("멍멍이", 3, "진돗개")
    dog.makeSound()  // 오버라이드된 메소드
    dog.eat()        // 상속받은 메소드
    dog.wagTail()    // Dog만의 메소드

    val cat = Cat("야옹이", 2)
    cat.makeSound()
    cat.purr()

    println("\n=== 사람 상속 예시 ===")
    val human = Human("배제우", 28)
    human.introduce()

    val collegeStudent = CollegeStudent("배제우", 28, "대학교", "컴퓨터공학")
    collegeStudent.introduce()  // 오버라이드
    collegeStudent.study()

    val companyEmployee = CompanyEmployee("배제우", 28, "AWS", 5000000)
    companyEmployee.introduce()  // 오버라이드
    companyEmployee.work()

    println("\n=== super 사용 ===")
    val softwareDeveloper = SoftwareDeveloper("배제우", 28, "AWS", 7000000, "Kotlin")
    softwareDeveloper.introduce()
    softwareDeveloper.work()
    softwareDeveloper.coding()
}

// 1. 부모 클래스 (open 필수)
open class Animal(
    val name: String,
    var age: Int
) {
    // 오버라이드 가능한 메소드 (open 필수)
    open fun makeSound() {
        println("$name 이(가) 소리를 냅니다")
    }

    // final 메소드 (오버라이드 불가)
    fun eat() {
        println("$name 이(가) 먹이를 먹습니다")
    }
}

// 2. 자식 클래스 - Dog
class Dog(
    name: String,
    age: Int,
    val breed: String  // Dog만의 프로퍼티
) : Animal(name, age) {

    // 메소드 오버라이드
    override fun makeSound() {
        println("$name: 멍멍! (${breed})")
    }

    // Dog만의 메소드
    fun wagTail() {
        println("$name 이(가) 꼬리를 흔듭니다")
    }
}

// 3. 자식 클래스 - Cat
class Cat(
    name: String,
    age: Int
) : Animal(name, age) {

    override fun makeSound() {
        println("$name: 야옹~")
    }

    fun purr() {
        println("$name 이(가) 그르렁거립니다")
    }
}

// 4. 사람 예시 - 부모 클래스
open class Human(
    val name: String,
    val age: Int
) {
    open fun introduce() {
        println("안녕하세요, $name 입니다. $age 살입니다.")
    }
}

// 5. 학생 - 자식 클래스
class CollegeStudent(
    name: String,
    age: Int,
    val university: String,
    val major: String
) : Human(name, age) {

    override fun introduce() {
        super.introduce()  // 부모 메소드 호출
        println("$university $major 전공입니다.")
    }

    fun study() {
        println("$name 이(가) 공부 중입니다.")
    }
}

// 6. 직원 - 자식 클래스
open class CompanyEmployee(
    name: String,
    age: Int,
    val company: String,
    var salary: Int
) : Human(name, age) {

    override fun introduce() {
        println("안녕하세요, $company 에서 일하는 $name 입니다.")
    }

    open fun work() {
        println("$name 이(가) 일하고 있습니다. (월급: $salary)")
    }
}

// 7. 개발자 - CompanyEmployee의 자식 (다단계 상속)
class SoftwareDeveloper(
    name: String,
    age: Int,
    company: String,
    salary: Int,
    val language: String
) : CompanyEmployee(name, age, company, salary) {

    override fun work() {
        super.work()
        println("주 사용 언어: $language")
    }

    fun coding() {
        println("$name 이(가) $language 로 코딩 중...")
    }
}
```

### 14. 인터페이스(Interface)

```text
인터페이스는 구현해야 할 메소드와 프로퍼티를 정의합니다. 다중 구현이 가능합니다.
```
- `interface` 키워드로 선언
- `open` 없이 메소드/프로퍼티 선언 가능
- `override` 키워드로 구현 클래스에서 재정의
- 디폴트 구현 제공 가능
- 추상 프로퍼티의 경우 반드시 구현 클래스에서 초기화 필요

```kotlin
// Practice14.kt 파일
fun main() {
    println("=== 기본 인터페이스 ===")
    val myCar = MyCar("소나타")
    myCar.start()
    myCar.stop()
    myCar.honk()

    val myBike = MyBike("자전거")
    myBike.start()
    myBike.stop()
    myBike.ringBell()

    println("\n=== 다중 인터페이스 구현 ===")
    val smartphone = MySmartPhone("갤럭시", "010-1234-5678")
    smartphone.turnOn()
    smartphone.turnOff()
    smartphone.call("010-9876-5432")
    smartphone.sendMessage("안녕하세요!")
    smartphone.takePicture()
    smartphone.recordVideo()  // 디폴트 구현 사용

    println("\n=== 프로퍼티가 있는 인터페이스 ===")
    val pupil = Pupil("배제우", 28)
    println("이름: ${pupil.name}")
    println("나이: ${pupil.age}")
    pupil.study()
    pupil.introduce()
    println("성인 여부: ${if (pupil.isAdult) "성인" else "미성년자"}")

    val instructor = Instructor("배제우", 28, "수학")
    instructor.teach()
    instructor.introduce()

    println("\n=== 디폴트 메소드 ===")
    val sparrow = Sparrow("참새")
    sparrow.move()  // 디폴트 구현 사용
    sparrow.fly()

    val goldfish = Goldfish("금붕어")
    goldfish.move()  // 오버라이드한 구현 사용
    goldfish.swim()
}

// 1. 기본 인터페이스
interface Vehicle {
    fun start()
    fun stop()
}

// 2. Vehicle 구현 - MyCar
class MyCar(val model: String) : Vehicle {
    override fun start() {
        println("$model 시동을 켭니다")
    }

    override fun stop() {
        println("$model 시동을 끕니다")
    }

    fun honk() {
        println("빵빵!")
    }
}

// 3. Vehicle 구현 - MyBike
class MyBike(val type: String) : Vehicle {
    override fun start() {
        println("$type 페달을 밟기 시작합니다")
    }

    override fun stop() {
        println("$type 브레이크를 잡습니다")
    }

    fun ringBell() {
        println("따르릉!")
    }
}

// 4. 다중 인터페이스
interface Device {
    fun turnOn()
    fun turnOff()
}

interface Phone {
    fun call(number: String)
    fun sendMessage(message: String)
}

interface Camera {
    fun takePicture()
    fun recordVideo() {
        // 디폴트 구현
        println("비디오 녹화를 시작합니다")
    }
}

// 5. 다중 인터페이스 구현
class MySmartPhone(
    val model: String,
    val phoneNumber: String
) : Device, Phone, Camera {

    override fun turnOn() {
        println("$model 전원을 켭니다")
    }

    override fun turnOff() {
        println("$model 전원을 끕니다")
    }

    override fun call(number: String) {
        println("$phoneNumber 에서 $number 로 전화 중...")
    }

    override fun sendMessage(message: String) {
        println("메시지 전송: $message")
    }

    override fun takePicture() {
        println("$model 로 사진 촬영!")
    }
    // recordVideo는 디폴트 구현 사용
}

// 6. 프로퍼티가 있는 인터페이스
interface IPerson {
    val name: String  // 추상 프로퍼티
    val age: Int      // 추상 프로퍼티
    val isAdult: Boolean
        get() = age >= 18  // 커스텀 getter

    fun introduce() {
        // 프로퍼티를 사용하는 디폴트 구현
        println("저는 $name 이고, $age 살입니다")
    }
}

interface ILearner {
    fun study()
}

// 7. IPerson과 ILearner 구현
class Pupil(
    override val name: String,
    override val age: Int
) : IPerson, ILearner {
    override fun study() {
        println("$name 학생이 공부합니다")
    }
}

class Instructor(
    override val name: String,
    override val age: Int,
    val subject: String
) : IPerson {
    fun teach() {
        println("$name 선생님이 $subject 을(를) 가르칩니다")
    }
}

// 8. 디폴트 메소드가 있는 인터페이스
interface IAnimal {
    fun move() {
        println("동물이 움직입니다")
    }
}

class Sparrow(val species: String) : IAnimal {
    // move()는 디폴트 구현 사용
    fun fly() {
        println("$species 이(가) 날아갑니다")
    }
}

class Goldfish(val species: String) : IAnimal {
    override fun move() {
        println("$species 이(가) 헤엄칩니다")
    }

    fun swim() {
        println("$species 이(가) 물속을 누빕니다")
    }
}
```
