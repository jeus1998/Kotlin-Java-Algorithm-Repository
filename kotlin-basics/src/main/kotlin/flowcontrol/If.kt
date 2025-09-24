package flowcontrol

/**
 * If expression
 */
fun main () {
    val length = 10
    val msg = if (length > 5) "안녕" else "잘가"
    println(msg)  // 안녕
}

fun getMsg(length: Int) = if (length > 5) "안녕" else "잘가"
fun getMsg2(length: Int): String {
    return if (length > 5) {
        "안녕"
    }
    else {
        "잘가"
    }
}