package function

/**
 * "기본 매개변수"(Function with Default Parameters)
 */
fun main() {
    defaultParam(age = 28)  // name: zeus, age: 28
    defaultParam("홍길동", 30)  // name: 홍길동, age: 30
}

// 기본 매개변수
fun defaultParam(name: String = "zeus", age: Int) {
    println("name: $name, age: $age")
}