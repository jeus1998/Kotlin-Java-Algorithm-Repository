package variable

/**
 * Null Checking
 * - Nullable type: Type?
 * - Safe call operator: ?.
 * - Non-null assertion operator: !! -> NullPointerException 발생 가능
 * - Safe cast operator: as? -> 실패 시 null 반환
 */
fun main() {
    val name:String? = null
    // name이 null이 아니면 length 반환, null이면 null 반환
    val nameLength = name?.length
    // nameLength: null
    println("nameLength: $nameLength")

    // name이 null이면 NullPointerException 발생
    try {
        val nameLength2 = name!!.length
    }
    catch (e: NullPointerException) {
        println("NullPointerException 발생")
    }

    // name을 Int로 캐스팅 시도, 실패하면 null 반환
    val age:Int? = name as? Int
    println("age: $age") // age: null
}