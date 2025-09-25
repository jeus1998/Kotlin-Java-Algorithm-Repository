package part1

fun main() {
    // val - 읽기 전용 변수
    val name: String = "배제우"
    val age = 28  // 타입 추론

    // var - 읽기/쓰기 가능 변수
    var address: String = "서울"
    var count = 0

    println("이름: $name")
    println("나이: $age")
    println("주소: $address")

    // val은 재할당 불가
    // name = "홍길동"  // 컴파일 오류!

    // var는 재할당 가능
    address = "부산"
    count = count + 1

    println("변경된 주소: $address")
    println("카운트: $count")
}
