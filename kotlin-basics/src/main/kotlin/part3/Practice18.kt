package part3

fun main() {
    println("=== Object (싱글톤) ===")
    // 싱글톤 객체 사용
    DatabaseManager.connect()
    DatabaseManager.addUser("배제우")
    DatabaseManager.addUser("김철수")
    DatabaseManager.printUsers()
    DatabaseManager.disconnect()
    
    // 같은 인스턴스인지 확인
    val db1 = DatabaseManager
    val db2 = DatabaseManager
    println("같은 객체? ${db1 === db2}")  // true
    
    println("\n=== Object Expression (익명 객체) ===")
    
    // 익명 객체 생성
    val clickListener = object : ClickListener {
        override fun onClick() {
            println("버튼이 클릭되었습니다!")
        }
    }
    clickListener.onClick()
    
    println("\n=== Companion Object ===")
    
    // Companion object의 멤버 접근
    val user1 = MyUser.create("배제우", 28)
    val user2 = MyUser.createWithEmail("김철수", 30, "kim@email.com")
    
    println("생성된 사용자 수: ${MyUser.userCount}")
    println("앱 버전: ${MyUser.APP_VERSION}")
    
    // Factory 패턴
    val circle = ShapeFactory.createCircle(5.0)
    val rect = ShapeFactory.createRectangle(4.0, 6.0)
    
    println("원 면적: ${circle.area()}")
    println("사각형 면적: ${rect.area()}")
    
    // Named Companion Object
    val logger = Logger.Factory.create("Main")
    logger.log("프로그램 시작")
    logger.log("처리 중...")
}

// 1. Singleton Object
object DatabaseManager {
    private val users = mutableListOf<String>()
    private var isConnected = false
    
    fun connect() {
        isConnected = true
        println("데이터베이스 연결됨")
    }
    
    fun disconnect() {
        isConnected = false
        println("데이터베이스 연결 해제됨")
    }
    
    fun addUser(name: String) {
        if (isConnected) {
            users.add(name)
            println("사용자 '$name' 추가됨")
        } else {
            println("연결이 필요합니다")
        }
    }
    
    fun printUsers() {
        println("등록된 사용자: $users")
    }
}

// 2. Interface for Object Expression
interface ClickListener {
    fun onClick()
}

// 3. Companion Object를 가진 클래스
class MyUser private constructor(
    val name: String,
    val age: Int,
    val email: String? = null
) {
    companion object {
        var userCount = 0
        const val APP_VERSION = "1.0.0"
        
        fun create(name: String, age: Int): MyUser {
            userCount++
            return MyUser(name, age)
        }
        
        fun createWithEmail(name: String, age: Int, email: String): MyUser {
            userCount++
            return MyUser(name, age, email)
        }
    }
    
    override fun toString() = "User(name=$name, age=$age, email=$email)"
}

// 4. Factory Pattern with Object
interface IShape {
    fun area(): Double
}

class Circle(val radius: Double) : IShape {
    override fun area() = Math.PI * radius * radius
}

class Rectangle(val width: Double, val height: Double) : IShape {
    override fun area() = width * height
}

object ShapeFactory {
    fun createCircle(radius: Double): IShape = Circle(radius)
    fun createRectangle(width: Double, height: Double): IShape = Rectangle(width, height)
}

// 5. Named Companion Object
class Logger private constructor(private val tag: String) {
    companion object Factory {
        fun create(tag: String): Logger {
            println("Logger 생성: $tag")
            return Logger(tag)
        }
    }
    
    fun log(message: String) {
        println("[$tag] $message")
    }
}
