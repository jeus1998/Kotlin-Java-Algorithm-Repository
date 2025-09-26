package part4

fun main() {
    println("=== let 함수 ===")
    // let: null 체크와 변수 스코프 제한
    
    val name: String? = "배제우"
    val length = name?.let {
        println("이름: $it")
        println("길이 계산 중...")
        it.length  // 반환값
    }
    println("이름 길이: $length")
    
    // null인 경우
    val nullName: String? = null
    val nullLength = nullName?.let {
        it.length
    } ?: 0
    println("null 이름 길이: $nullLength")
    
    // 체이닝
    val result = "hello"
        .let { it.uppercase() }
        .let { "$it WORLD" }
        .let { it.length }
    println("체이닝 결과: $result")
    
    println("\n=== run 함수 ===")
    // run: 객체 초기화와 계산
    
    val person = Person2("배제우", 28)
    val bio = person.run {
        age += 1  // this 생략 가능
        "이름: $name, 나이: $age"  // 반환값
    }
    println("소개: $bio")
    
    // null이 아닐 때만 실행
    val message: String? = "Hello"
    message?.run {
        println("메시지 길이: ${length}")
        println("대문자: ${uppercase()}")
    }
    
    println("\n=== with 함수 ===")
    // with: 여러 메소드 호출
    
    val numbers = mutableListOf(1, 2, 3)
    val result2 = with(numbers) {
        add(4)
        add(5)
        remove(1)
        println("수정된 리스트: $this")
        sum()  // 반환값
    }
    println("합계: $result2")
    
    // StringBuilder 예제
    val stringResult = with(StringBuilder()) {
        append("Hello")
        append(" ")
        append("World")
        toString()
    }
    println("문자열: $stringResult")
    
    println("\n=== apply 함수 ===")
    // apply: 객체 설정 (빌더 패턴)
    
    val user = User2().apply {
        username = "배제우"
        email = "bae@email.com"
        age = 28
        isActive = true
    }
    println("생성된 사용자: $user")
    
    // 체이닝에 유용
    val config = DatabaseConfig().apply {
        host = "localhost"
        port = 3306
        database = "mydb"
    }.apply {
        // 추가 설정
        username = "admin"
        password = "1234"
    }
    println("DB 설정: $config")
    
    println("\n=== also 함수 ===")
    // also: 부수 효과(로깅, 검증 등)
    
    val numbers2 = mutableListOf(1, 2, 3)
        .also { println("원본 리스트: $it") }
        .map { it * 2 }
        .also { println("2배 후: $it") }
        .filter { it > 2 }
        .also { println("필터 후: $it") }
    
    // 검증과 로깅
    val validatedAge = 28
        .also { require(it >= 0) { "나이는 음수일 수 없습니다" } }
        .also { println("유효한 나이: $it") }
    
    println("\n=== 실제 사용 예제 ===")
    
    // 1. null 처리와 변환 (let)
    fun processNullableString(str: String?) {
        str?.let { nonNullStr ->
            println("처리: ${nonNullStr.uppercase()}")
        } ?: println("null 값입니다")
    }
    
    processNullableString("hello")
    processNullableString(null)
    
    // 2. 객체 초기화 (apply)
    data class Car(
        var brand: String = "",
        var model: String = "",
        var year: Int = 0
    )
    
    val myCar = Car().apply {
        brand = "현대"
        model = "소나타"
        year = 2024
    }
    println("\n내 차: $myCar")
    
    // 3. 조건부 실행 (run)
    val score = 85
    val grade = run {
        when {
            score >= 90 -> "A"
            score >= 80 -> "B"
            score >= 70 -> "C"
            else -> "F"
        }
    }
    println("등급: $grade")
    
    // 4. 스코프 함수 조합
    val processedData = getData()
        ?.let { data ->  // null 체크
            println("데이터 수신: $data")
            data
        }
        ?.run {  // 변환
            this.uppercase()
        }
        ?.also { result ->  // 로깅
            println("변환 결과: $result")
        }
    
    println("최종 데이터: $processedData")
}

// 헬퍼 클래스들
data class Person2(var name: String, var age: Int)

class User2 {
    var username: String = ""
    var email: String = ""
    var age: Int = 0
    var isActive: Boolean = false
    
    override fun toString() = "User(name=$username, email=$email, age=$age, active=$isActive)"
}

class DatabaseConfig {
    var host: String = ""
    var port: Int = 0
    var database: String = ""
    var username: String = ""
    var password: String = ""
    
    override fun toString() = "DB($host:$port/$database, user=$username)"
}

fun getData(): String? = "sample data"
