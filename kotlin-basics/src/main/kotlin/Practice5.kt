fun main() {
    // 배열 순회
    val items = arrayOf("사과", "바나나", "오렌지")
    println("=== 배열 순회 ===")
    for (item in items) {
        println(item)
    }

    // 인덱스와 함께 순회
    println("\n=== 인덱스와 함께 ===")
    for (index in items.indices) {
        println("$index: ${items[index]}")
    }

    // withIndex() 사용
    println("\n=== withIndex() ===")
    for ((index, value) in items.withIndex()) {
        println("$index: $value")
    }

    // 범위 순회 (1부터 5까지)
    println("\n=== 1..5 범위 ===")
    for (i in 1..5) {
        print("$i ")
    }
    println()

    // until 사용 (1부터 4까지, 5 제외)
    println("\n=== 1 until 5 ===")
    for (i in 1 until 5) {
        print("$i ")
    }
    println()

    // step 사용 (2씩 증가)
    println("\n=== 0..10 step 2 ===")
    for (i in 0..10 step 2) {
        print("$i ")
    }
    println()

    // downTo 사용 (감소)
    println("\n=== 10 downTo 1 ===")
    for (i in 10 downTo 1) {
        print("$i ")
    }
    println()

    // 감소하면서 step
    println("\n=== 10 downTo 1 step 2 ===")
    for (i in 10 downTo 1 step 2) {
        print("$i ")
    }
    println()

    // List 순회
    val list = listOf("월", "화", "수", "목", "금")
    println("\n=== List 순회 ===")
    for (day in list) {
        println("$day 요일")
    }

    // 문자열 순회
    println("\n=== 문자열 순회 ===")
    for (char in "Kotlin") {
        print("$char ")
    }
    println()
}