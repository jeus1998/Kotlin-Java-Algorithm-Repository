package function

/**
 * "네임드 파라미터"(Function with Named Parameters)
 * - You can specify arguments by their parameter names
 */
fun main() {
    namedParam(1, 2, 3)    // 20 -> 2 + 6 + 12
    namedParam(c = 3, b = 2, a = 1) // 20 -> 2 + 6 + 12
}

fun namedParam(a: Int, b: Int, c:Int) = (a * 2) + (b * 3) + (c * 4)

