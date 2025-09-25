package part1

fun main() {
    // String 확장 함수 사용
    val text = "Hello Kotlin"
    println("원본: $text")
    println("첫 글자 대문자: ${text.capitalizeFirst()}")
    println("마지막 3글자: ${text.lastChars(3)}")
    println("회문 체크: ${text.isPalindrome()}")

    val palindrome = "level"
    println("'$palindrome' 회문 체크: ${palindrome.isPalindrome()}")

    // Int 확장 함수 사용
    val number = 5
    println("\n$number 제곱: ${number.square()}")
    println("$number 짝수?: ${number.isEven()}")
    println("$number 홀수?: ${number.isOdd()}")

    // List 확장 함수 사용
    val numbers = listOf(1, 2, 3, 4, 5)
    println("\n리스트: $numbers")
    println("두 번째와 마지막 두 번째: ${numbers.secondAndPenultimate()}")

    // 제네릭 확장 함수
    val items = listOf("A", "B", "C")
    items.printWithIndex()

    // Array 확장 함수
    val array = arrayOf(10, 20, 30, 40, 50)
    println("\n배열 중간값: ${array.middle()}")

    // nullable 타입 확장
    val nullableString: String? = null
    val nonNullString: String? = "Kotlin"

    println("\nnull 체크:")
    println("null일 때: ${nullableString.isNullOrEmpty()}")
    println("값이 있을 때: ${nonNullString.isNullOrEmpty()}")
}

// String 확장 함수들
fun String.capitalizeFirst(): String {
    return if (this.isEmpty()) this
    else this[0].uppercaseChar() + this.substring(1)
}

fun String.lastChars(n: Int): String {
    return if (this.length >= n) this.substring(this.length - n)
    else this
}

fun String.isPalindrome(): Boolean {
    return this == this.reversed()
}

// Int 확장 함수들
fun Int.square(): Int = this * this

fun Int.isEven(): Boolean = this % 2 == 0

fun Int.isOdd(): Boolean = !this.isEven()

// List 확장 함수
fun <T> List<T>.secondAndPenultimate(): Pair<T?, T?> {
    val second = if (this.size >= 2) this[1] else null
    val penultimate = if (this.size >= 2) this[this.size - 2] else null
    return Pair(second, penultimate)
}

// 제네릭 확장 함수
fun <T> List<T>.printWithIndex() {
    println("리스트 출력:")
    this.forEachIndexed { index, item ->
        println("  [$index]: $item")
    }
}

// Array 확장 함수
fun <T> Array<T>.middle(): T? {
    return if (this.isEmpty()) null
    else this[this.size / 2]
}

// nullable 타입 확장
fun String?.isNullOrEmpty(): Boolean {
    return this == null || this.isEmpty()
}