package part5

fun main() {
    println("=== 정규 표현식 기본 ===")
    
    // Regex 생성
    val regex1 = Regex("[0-9]+")
    val regex2 = "[a-z]+".toRegex()
    
    // matches - 전체 문자열 매칭
    println("123".matches(regex1))  // true
    println("123abc".matches(regex1))  // false
    println("abc".matches(regex2))  // true
    
    println("\n=== 패턴 매칭 ===")
    
    // containsMatchIn - 부분 매칭
    val text = "My phone number is 010-1234-5678"
    val phoneRegex = Regex("\\d{3}-\\d{4}-\\d{4}")
    
    if (phoneRegex.containsMatchIn(text)) {
        println("전화번호 패턴 발견!")
    }
    
    // find - 첫 번째 매칭 찾기
    val match = phoneRegex.find(text)
    match?.let {
        println("찾은 번호: ${it.value}")
        println("시작 위치: ${it.range.first}")
    }
    
    println("\n=== 모든 매칭 찾기 ===")
    
    val text2 = "가격: 1000원, 할인가: 800원, 배송비: 3000원"
    val priceRegex = Regex("\\d+원")
    
    val matches = priceRegex.findAll(text2)
    matches.forEach { match ->
        println("찾은 가격: ${match.value}")
    }
    
    println("\n=== 그룹 캡처 ===")
    
    val dateText = "오늘은 2024-03-15입니다"
    val dateRegex = Regex("(\\d{4})-(\\d{2})-(\\d{2})")
    
    dateRegex.find(dateText)?.let { match ->
        println("전체 매칭: ${match.value}")
        println("연도: ${match.groupValues[1]}")
        println("월: ${match.groupValues[2]}")
        println("일: ${match.groupValues[3]}")
    }
    
    // Named groups
    val emailRegex = Regex("(?<user>[^@]+)@(?<domain>[^.]+)\\.(?<tld>\\w+)")
    val email = "baejewo@example.com"
    
    emailRegex.find(email)?.let { match ->
        println("\n이메일 분석:")
        println("사용자: ${match.groups["user"]?.value}")
        println("도메인: ${match.groups["domain"]?.value}")
        println("TLD: ${match.groups["tld"]?.value}")
    }
    
    println("\n=== 치환 ===")
    
    // replace
    val text3 = "Hello World! Hello Kotlin!"
    val replaced = text3.replace(Regex("Hello"), "Hi")
    println("치환 결과: $replaced")
    
    // replaceFirst
    val replacedFirst = text3.replaceFirst(Regex("Hello"), "Hi")
    println("첫 번째만 치환: $replacedFirst")
    
    // 복잡한 치환 (람다 사용)
    val text4 = "item1: 100, item2: 200, item3: 300"
    val result = text4.replace(Regex("\\d+")) { match ->
        (match.value.toInt() * 1.1).toInt().toString()
    }
    println("10% 인상: $result")
    
    println("\n=== 분할 ===")
    
    val csv = "배제우,28,서울"
    val fields = csv.split(Regex(","))
    println("CSV 분할: $fields")
    
    val text5 = "apple  banana   orange"
    val words = text5.split(Regex("\\s+"))  // 하나 이상의 공백
    println("단어 분할: $words")
    
    println("\n=== 실용적인 예제 ===")
    
    // 1. 이메일 검증
    fun isValidEmail(email: String): Boolean {
        val emailPattern = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
        return emailPattern.matches(email)
    }
    
    println("이메일 검증:")
    println("bae@example.com: ${isValidEmail("bae@example.com")}")
    println("invalid.email: ${isValidEmail("invalid.email")}")
    
    // 2. 전화번호 포맷팅
    fun formatPhoneNumber(phone: String): String? {
        val cleaned = phone.replace(Regex("[^0-9]"), "")
        val phonePattern = Regex("(\\d{3})(\\d{4})(\\d{4})")
        return phonePattern.find(cleaned)?.let {
            "${it.groupValues[1]}-${it.groupValues[2]}-${it.groupValues[3]}"
        }
    }
    
    println("\n전화번호 포맷팅:")
    println("01012345678 → ${formatPhoneNumber("01012345678")}")
    println("010 1234 5678 → ${formatPhoneNumber("010 1234 5678")}")
    
    // 3. HTML 태그 제거
    fun stripHtmlTags(html: String): String {
        return html.replace(Regex("<[^>]+>"), "")
    }
    
    val html = "<h1>제목</h1><p>본문 내용</p>"
    println("\nHTML 태그 제거:")
    println("원본: $html")
    println("제거 후: ${stripHtmlTags(html)}")
    
    // 4. 비밀번호 강도 체크
    fun checkPasswordStrength(password: String): String {
        val hasUpperCase = Regex("[A-Z]").containsMatchIn(password)
        val hasLowerCase = Regex("[a-z]").containsMatchIn(password)
        val hasDigit = Regex("\\d").containsMatchIn(password)
        val hasSpecialChar = Regex("[!@#$%^&*(),.?\":{}|<>]").containsMatchIn(password)
        val isLongEnough = password.length >= 8
        
        val score = listOf(hasUpperCase, hasLowerCase, hasDigit, hasSpecialChar, isLongEnough)
            .count { it }
        
        return when (score) {
            5 -> "매우 강함"
            4 -> "강함"
            3 -> "보통"
            2 -> "약함"
            else -> "매우 약함"
        }
    }
    
    println("\n비밀번호 강도:")
    println("password: ${checkPasswordStrength("password")}")
    println("Password1: ${checkPasswordStrength("Password1")}")
    println("P@ssw0rd!: ${checkPasswordStrength("P@ssw0rd!")}")
}
