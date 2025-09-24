package flowcontrol


/**
 * for loop
 */
fun main() {
    val item = arrayOf(1, 2, 3)
    val list = listOf(1, 2, 3)

    for(index in item.indices) {
        // 0 ~ 2
        println("index: ${index}, value: ${item[index]}")
    }
    for(index in list.indices) {
        // 0 ~ 2
        println("index: ${index}, value: ${list[index]}")
    }

    println("--------------------")

    var sum = 0
    for(i in 1..100) {
        sum += i;
    }
    println("1 ~ 100 합: $sum") // 5050

    sum = 0
    for(i in 1 until 100) {
        sum += i;
    }
    println("1 ~ 99 합: $sum") // 4950

    println("--------------------")

    // 2씩 증가, 2, 4, 6, 8, 10
    for(i in 2..10 step 2) {
        println("$i")
    }

    println("--------------------")

    // 1씩 감소, 10, 9, 8, 7, 6, 5
    for(i in 10 downTo 5) {
        println("$i")
    }

}