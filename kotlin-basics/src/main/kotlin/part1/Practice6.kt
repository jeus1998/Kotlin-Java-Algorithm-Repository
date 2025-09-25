package part1

fun main() {
    // 기본 while 문
    println("=== while 문 ===")
    var count = 1
    while (count <= 5) {
        println("카운트: $count")
        count++
    }

    // 배열과 while 문
    println("\n=== 배열과 while ===")
    val items = Array(5) { i -> (i + 1) * 10 }  // [10, 20, 30, 40, 50]
    var index = 0
    while (index < items.size) {
        println("items[$index] = ${items[index]}")
        index++
    }

    // do-while 문 (최소 한 번은 실행)
    println("\n=== do-while 문 ===")
    var num = 6
    do {
        println("num = $num (최소 한 번은 실행됨)")
        num++
    } while (num <= 5)  // 조건이 false여도 한 번은 실행됨

    // 무한 루프와 break
    println("\n=== break 사용 ===")
    var counter = 0
    while (true) {
        if (counter >= 3) {
            println("3에 도달하여 종료")
            break
        }
        println("무한 루프 카운터: $counter")
        counter++
    }

    // continue 사용
    println("\n=== continue 사용 ===")
    var i = 0
    while (i < 10) {
        i++
        if (i % 2 == 0) {
            continue  // 짝수는 건너뛰기
        }
        print("$i ")  // 홀수만 출력
    }
    println()

    // 중첩 while
    println("\n=== 중첩 while (구구단 일부) ===")
    var x = 2
    while (x <= 4) {
        var y = 1
        while (y <= 3) {
            println("$x × $y = ${x * y}")
            y++
        }
        println()
        x++
    }
}