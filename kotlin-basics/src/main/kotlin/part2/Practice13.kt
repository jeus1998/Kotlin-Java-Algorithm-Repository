package part2

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