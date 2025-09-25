package part2

fun main() {
    println("=== 도형 추상 클래스 ===")
    // val shape = Shape("도형")  // 오류! 추상 클래스는 인스턴스화 불가

    val circle = MyCircle("원", 5.0)
    circle.draw()
    println("${circle.name} 면적: ${circle.calculateArea()}")
    println("${circle.name} 둘레: ${circle.calculatePerimeter()}")

    val rectangle = MyRectangle("직사각형", 4.0, 6.0)
    rectangle.draw()
    println("${rectangle.name} 면적: ${rectangle.calculateArea()}")
    println("${rectangle.name} 둘레: ${rectangle.calculatePerimeter()}")

    println("\n=== 직원 추상 클래스 ===")
    val programmer = Programmer("배제우", 28, "개발팀", 5000000)
    programmer.introduce()
    programmer.work()
    println("월급: ${programmer.calculateSalary()}")

    val teamManager = TeamManager("배제우", 28, "경영팀", 7000000, 2000000)
    teamManager.introduce()
    teamManager.work()
    println("월급: ${teamManager.calculateSalary()}")

    println("\n=== 게임 캐릭터 ===")
    val knight = Knight("전사", 100, 20)
    knight.displayInfo()
    knight.attack()
    knight.useSkill()
    knight.takeDamage(30)

    val mage = Mage("마법사", 70, 15, 100)
    mage.displayInfo()
    mage.attack()
    mage.useSkill()
    mage.castSpell()
}

// 1. 도형 추상 클래스
abstract class Shape(val name: String) {
    // 추상 메소드 - 자식 클래스에서 반드시 구현
    abstract fun calculateArea(): Double
    abstract fun calculatePerimeter(): Double

    // 구체 메소드 - 기본 구현 제공
    fun draw() {
        println("$name 을(를) 그립니다")
    }
}

// 2. MyCircle - Shape 구현
class MyCircle(
    name: String,
    val radius: Double
) : Shape(name) {

    override fun calculateArea(): Double {
        return Math.PI * radius * radius
    }

    override fun calculatePerimeter(): Double {
        return 2 * Math.PI * radius
    }
}

// 3. MyRectangle - Shape 구현
class MyRectangle(
    name: String,
    val width: Double,
    val height: Double
) : Shape(name) {

    override fun calculateArea(): Double {
        return width * height
    }

    override fun calculatePerimeter(): Double {
        return 2 * (width + height)
    }
}

// 4. 직원 추상 클래스
abstract class AbstractEmployee(
    val name: String,
    val age: Int,
    val department: String
) {
    // 추상 프로퍼티
    abstract val baseSalary: Int

    // 추상 메소드
    abstract fun work()
    abstract fun calculateSalary(): Int

    // 구체 메소드
    fun introduce() {
        println("안녕하세요, $department 의 $name 입니다 ($age 살)")
    }

    // open 메소드 (선택적 오버라이드)
    open fun takeBreak() {
        println("$name 이(가) 휴식을 취합니다")
    }
}

// 5. Programmer - AbstractEmployee 구현
class Programmer(
    name: String,
    age: Int,
    department: String,
    override val baseSalary: Int
) : AbstractEmployee(name, age, department) {

    override fun work() {
        println("$name 개발자가 코딩을 합니다")
    }

    override fun calculateSalary(): Int {
        return baseSalary + (baseSalary * 0.1).toInt()  // 10% 보너스
    }
}

// 6. TeamManager - AbstractEmployee 구현
class TeamManager(
    name: String,
    age: Int,
    department: String,
    override val baseSalary: Int,
    val managementAllowance: Int
) : AbstractEmployee(name, age, department) {

    override fun work() {
        println("$name 매니저가 팀을 관리합니다")
    }

    override fun calculateSalary(): Int {
        return baseSalary + managementAllowance
    }

    override fun takeBreak() {
        super.takeBreak()
        println("매니저는 회의실에서 휴식합니다")
    }
}

// 7. 게임 캐릭터 추상 클래스
abstract class GameCharacter(
    val characterName: String,
    var health: Int,
    val attackPower: Int
) {
    abstract fun attack()
    abstract fun useSkill()

    fun displayInfo() {
        println("캐릭터: $characterName, 체력: $health, 공격력: $attackPower")
    }

    open fun takeDamage(damage: Int) {
        health -= damage
        println("$characterName 이(가) $damage 피해를 입었습니다. 남은 체력: $health")
    }
}

// 8. Knight - GameCharacter 구현
class Knight(
    name: String,
    health: Int,
    attackPower: Int
) : GameCharacter(name, health, attackPower) {

    override fun attack() {
        println("$characterName 이(가) 검으로 공격! (데미지: $attackPower)")
    }

    override fun useSkill() {
        println("$characterName 이(가) 강력한 일격 사용!")
    }
}

// 9. Mage - GameCharacter 구현
class Mage(
    name: String,
    health: Int,
    attackPower: Int,
    val mana: Int
) : GameCharacter(name, health, attackPower) {

    override fun attack() {
        println("$characterName 이(가) 마법 공격! (데미지: $attackPower)")
    }

    override fun useSkill() {
        println("$characterName 이(가) 화염구 시전! (마나: $mana)")
    }

    fun castSpell() {
        println("$characterName 이(가) 특별한 주문을 시전합니다")
    }
}