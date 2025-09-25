package part1

fun main() {
    // Nullable 변수 선언
    var nullableName: String? = null
    var nonNullName: String = "배제우"

    // 안전 호출 연산자 (?.)
    println("nullable 길이: ${nullableName?.length}")  // null 반환
    println("non-null 길이: ${nonNullName.length}")    // 3 반환

    // 엘비스 연산자 (?:)
    val length = nullableName?.length ?: 0
    println("길이 (기본값 0): $length")

    // non-null 단언 연산자 (!!) - 주의해서 사용
    nullableName = "배제우"
    val forceLength = nullableName!!.length  // null이면 NPE 발생
    println("강제 길이: $forceLength")

    // 안전한 타입 캐스팅
    val obj: Any = "문자열"
    val str: String? = obj as? String
    val num: Int? = obj as? Int

    println("문자열 캐스팅: $str")   // "문자열"
    println("숫자 캐스팅: $num")    // null
}