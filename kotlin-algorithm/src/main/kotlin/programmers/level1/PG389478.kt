package programmers.level1

/**
 * 프로그래머스 LEVEL1 - 택배 상자 꺼내기 <br>
 * - <2025 프로그래머스 코드 챌린지 2차 예선> <br>
 * - 링크: https://school.programmers.co.kr/learn/courses/30/lessons/389478 <br>
 * - 2025-09-26
 */
class PG389478 {
    private companion object {
        var answer = 0
    }
    fun reset() {
        answer = 0
    }
    fun solution(n: Int, w: Int, num: Int): Int {
        core(n, w, num)
        return answer
    }
    private fun core(n: Int, w: Int, num: Int) {
        var cur = num
        while(cur <= n) {
            answer++
            if(cur % w == 0) cur++
            else cur += (w - (cur % w)) * 2 + 1
        }
    }
}

fun main() {
    val sol = PG389478()
    println(sol.solution(22, 6, 8)) // 3
    sol.reset()
    println(sol.solution(13, 3, 6)) // 4
}