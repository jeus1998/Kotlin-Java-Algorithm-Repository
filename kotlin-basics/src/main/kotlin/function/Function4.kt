package function

/**
 * "가변 인자 함수" (Function with Vararg)
 * - You can pass a variable number of arguments to a function
 */
fun main() {
    println(sum(1, 2, 3, 4, 5))    // 15
    val numbers = listOf(100, 100, 100)
    // '*' -> Spread Operator: 배열을 개별 요소로 변환
    println(sum(*numbers.toIntArray()))  // 300

    println("------")
    val list = newList(1, 2, 3, 4, 5)
    println(list)   // [1, 2, 3, 4, 5]
    val strList = newList("A", "B", "C")
    println(strList)  // [A, B, C]
}

fun sum(vararg ints: Int): Int{
    return ints.sum()
}

fun <T> newList(vararg elements: T): List<T> {
    val result = ArrayList<T>()
    for(item in elements) {
        result.add(item)
    }
    return result
}