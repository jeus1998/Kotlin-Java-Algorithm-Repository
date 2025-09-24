package variable

/**
 * Nullable variable
 * - By default, variables cannot hold null values.
 * - To allow null values, append '?' to the type.
 */
fun main() {
    // nullable variable
    var name:String? = null

    // null 값을 허용하지 않는 변수에 null 선언은 컴파일 에러 발생
    // var age:Int = null
}