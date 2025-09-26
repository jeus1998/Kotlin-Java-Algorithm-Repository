package part5

fun main() {
    println("=== 제네릭 클래스 ===")
    
    // 제네릭 클래스 사용
    val intBox = Box(42)
    val stringBox = Box("Hello")
    val personBox = Box(Person("배제우", 28))
    
    println("Int Box: ${intBox.value}")
    println("String Box: ${stringBox.value}")
    println("Person Box: ${personBox.value}")
    
    println("\n=== 제네릭 함수 ===")
    
    // 제네릭 함수 호출
    val numbers = listOf(1, 2, 3)
    val names = listOf("배제우", "김철수")
    
    printAll(numbers)
    printAll(names)
    
    val swapped1 = swap(10, 20)
    val swapped2 = swap("Hello", "World")
    println("Swapped: $swapped1")
    println("Swapped: $swapped2")
    
    println("\n=== 타입 제약 ===")
    
    // 상한 타입 제약
    val numberBox = NumberBox(42)
    val doubleBox = NumberBox(3.14)
    // val stringNumberBox = NumberBox("Hello")  // 오류! String은 Number가 아님
    
    println("Number Box: ${numberBox.value}")
    println("Double Box: ${doubleBox.value}")
    
    val compared = maxOf(10, 20)
    println("Max: $compared")
    
    println("\n=== 변성 (Variance) ===")
    
    // out (공변) - 생산자
    val producer: Producer<Number> = Producer(42)
    val numberProducer: Producer<Any> = producer  // Number는 Any의 하위 타입
    
    // in (반공변) - 소비자
    val consumer: Consumer<Number> = Consumer()
    consumer.consume(42)
    consumer.consume(3.14)
    
    println("\n=== 스타 프로젝션 ===")
    
    val boxes: List<Box<*>> = listOf(
        Box(1),
        Box("String"),
        Box(true)
    )
    
    boxes.forEach { box ->
        println("Box contains: ${box.value}")
    }
    
    println("\n=== 실용적인 예제 ===")
    
    // 1. Generic Repository
    val userRepo = Repository<User>()
    userRepo.add(User(1, "배제우"))
    userRepo.add(User(2, "김철수"))
    
    val foundUser = userRepo.find { it.id == 1 }
    println("찾은 사용자: $foundUser")
    
    val allUsers = userRepo.getAll()
    println("모든 사용자: $allUsers")
    
    // 2. Generic Result
    val successResult: Result<String> = Result.Success("데이터 로드 성공")
    val errorResult: Result<String> = Result.Error("네트워크 오류")
    
    handleResult(successResult)
    handleResult(errorResult)
    
    // 3. Generic Extension
    val list = listOf(1, 2, 3, 4, 5)
    val middle = list.middle()
    println("\n리스트 중간값: $middle")
    
    // 4. Reified Type Parameter
    val result1 = parseJson<Person>("""{"name":"배제우","age":28}""")
    val result2 = parseJson<User>("""{"id":1,"name":"배제우"}""")
    println("Parsed Person: $result1")
    println("Parsed User: $result2")
}

// 1. 제네릭 클래스
class Box<T>(val value: T) {
    fun get(): T = value
}

// 2. 타입 제약이 있는 제네릭 클래스
class NumberBox<T : Number>(val value: T) {
    fun toDouble(): Double = value.toDouble()
}

// 3. 제네릭 함수
fun <T> printAll(items: List<T>) {
    items.forEach { println(it) }
}

fun <T> swap(a: T, b: T): Pair<T, T> = Pair(b, a)

// 4. 타입 제약이 있는 제네릭 함수
fun <T : Comparable<T>> maxOf(a: T, b: T): T {
    return if (a > b) a else b
}

// 5. 공변성 (out)
class Producer<out T>(private val value: T) {
    fun produce(): T = value
}

// 6. 반공변성 (in)
class Consumer<in T> {
    fun consume(item: T) {
        println("Consuming: $item")
    }
}

// 7. 제네릭 Repository
class Repository<T> {
    private val items = mutableListOf<T>()
    
    fun add(item: T) {
        items.add(item)
    }
    
    fun find(predicate: (T) -> Boolean): T? {
        return items.find(predicate)
    }
    
    fun getAll(): List<T> = items.toList()
}

// 8. 제네릭 Result
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
}

fun <T> handleResult(result: Result<T>) {
    when (result) {
        is Result.Success -> println("성공: ${result.data}")
        is Result.Error -> println("실패: ${result.message}")
    }
}

// 9. 제네릭 확장 함수
fun <T> List<T>.middle(): T? {
    return if (isEmpty()) null else this[size / 2]
}

// 10. Reified Type Parameter
inline fun <reified T> parseJson(json: String): T? {
    println("Parsing JSON as ${T::class.simpleName}")
    // 실제로는 JSON 파싱 로직이 들어갈 자리
    return null
}

// 데이터 클래스들
data class Person(val name: String, val age: Int)
data class User(val id: Int, val name: String)
