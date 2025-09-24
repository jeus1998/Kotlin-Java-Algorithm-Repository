package variable

/**
 * Variable declaration
 * - val: Immutable variable (cannot be reassigned)
 * - var: Mutable variable (can be reassigned)
 */
fun main() {
    val name:String = "배제우"
    var age:Int = 28
    var address = "서울" // 타입 추론(생략)

    address = "경기"   // var -> 재할당 가능
    // name = "홍길동"  val -> 재할당 불가 (컴파일 에러) 'val' cannot be reassigned.
}