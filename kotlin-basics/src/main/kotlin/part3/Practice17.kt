package part3

fun main() {
    println("=== Sealed 클래스 기본 ===")
    // API 응답 처리
    val responses = listOf(
        ApiResponse.Success("데이터 로드 완료", listOf("item1", "item2")),
        ApiResponse.Error(404, "페이지를 찾을 수 없습니다"),
        ApiResponse.Loading
    )
    responses.forEach { response ->
        handleResponse(response)
    }
    
    println("\n=== UI 상태 관리 ===")
    var uiState: UiState = UiState.Idle
    
    // 상태 변경 시뮬레이션
    val states = listOf(
        UiState.Idle,
        UiState.Loading("데이터 가져오는 중..."),
        UiState.Success(UserInfo(1, "배제우", 28)),
        UiState.Error(Exception("네트워크 오류"))
    )
    
    states.forEach { state ->
        renderUi(state)
    }
    
    println("\n=== 계산기 연산 ===")
    val operations = listOf(
        MathOperation.Add(10.0, 5.0),
        MathOperation.Subtract(10.0, 3.0),
        MathOperation.Multiply(4.0, 7.0),
        MathOperation.Divide(20.0, 4.0),
        MathOperation.Divide(10.0, 0.0)  // 0으로 나누기
    )
    
    operations.forEach { op ->
        val result = calculate(op)
        println("결과: $result")
    }
}

// 1. API 응답을 표현하는 Sealed 클래스
sealed class ApiResponse {
    data class Success(val message: String, val data: List<String>) : ApiResponse()
    data class Error(val code: Int, val message: String) : ApiResponse()
    object Loading : ApiResponse()
}

fun handleResponse(response: ApiResponse) {
    when (response) {
        is ApiResponse.Success -> {
            println("성공: ${response.message}")
            println("데이터: ${response.data}")
        }
        is ApiResponse.Error -> {
            println("에러 ${response.code}: ${response.message}")
        }
        ApiResponse.Loading -> {
            println("로딩 중...")
        }
        // else 불필요 - 모든 경우를 처리했음
    }
}

// 2. UI 상태 관리
sealed class UiState {
    object Idle : UiState()
    data class Loading(val message: String) : UiState()
    data class Success(val user: UserInfo) : UiState()
    data class Error(val exception: Exception) : UiState()
}

data class UserInfo(val id: Int, val name: String, val age: Int)

fun renderUi(state: UiState) {
    when (state) {
        is UiState.Idle -> println("대기 상태")
        is UiState.Loading -> println("${state.message}")
        is UiState.Success -> println("사용자: ${state.user.name} (${state.user.age}살)")
        is UiState.Error -> println("오류: ${state.exception.message}")
    }
}

// 3. 수학 연산
sealed class MathOperation {
    abstract val a: Double
    abstract val b: Double
    
    data class Add(override val a: Double, override val b: Double) : MathOperation()
    data class Subtract(override val a: Double, override val b: Double) : MathOperation()
    data class Multiply(override val a: Double, override val b: Double) : MathOperation()
    data class Divide(override val a: Double, override val b: Double) : MathOperation()
}

fun calculate(operation: MathOperation): Double {
    return when (operation) {
        is MathOperation.Add -> {
            println("${operation.a} + ${operation.b}")
            operation.a + operation.b
        }
        is MathOperation.Subtract -> {
            println("${operation.a} - ${operation.b}")
            operation.a - operation.b
        }
        is MathOperation.Multiply -> {
            println("${operation.a} × ${operation.b}")
            operation.a * operation.b
        }
        is MathOperation.Divide -> {
            println("${operation.a} ÷ ${operation.b}")
            if (operation.b != 0.0) {
                operation.a / operation.b
            }
            else {
                println("경고: 0으로 나눌 수 없습니다")
                Double.NaN
            }
        }
    }
}
