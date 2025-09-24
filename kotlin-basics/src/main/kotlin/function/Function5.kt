package function

/**
 * 확장 함수(Extension Function)
 * - 기존 클래스에 새로운 함수를 확장할 수 있다.
 * - 기존 클래스의 소스 코드를 수정하지 않고도 기능을 확장할 수 있다.
 */
fun main() {
    val array = arrayOf(10, 20, 30)
    println(array.length()) // Int.MAX_VALUE
    println(array.size())  // 1
}

fun <T> Array<T>.length(): Int {
    return Int.MAX_VALUE
}

fun <T> Array<T>.size(): Int {
    return 1
}
