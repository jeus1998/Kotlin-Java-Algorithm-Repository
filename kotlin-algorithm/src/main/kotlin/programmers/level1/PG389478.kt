package programmers.level1

/**
 * 프로그래머스 LEVEL1 - 택배 상자 꺼내기 <br>
 * - <2025 프로그래머스 코드 챌린지 2차 예선> <br>
 * - 링크: https://school.programmers.co.kr/learn/courses/30/lessons/389478 <br>
 * - 2025-09-26
 */
class PG389478 {
    companion object {
        // primitive type cant late init
        var answer = 1
        var size = 0
        lateinit var map: Array<IntArray>
        var x = 0
        var y = 0
    }
    fun solution(n: Int, w: Int, num: Int): Int {
        init(n = n, w = w, num = num)
        core(n)
        return answer
    }
    private fun core(n: Int) {
        for(i in x + 1 until size) {
            if(map[i][y] <= n) answer++
        }
    }
    private fun init(n: Int, w: Int, num: Int) {
        size = (n / w) + if (n % w == 0) 0 else 1
        map = Array(size) { IntArray(w) }
        var index = 1
        for(i in 0 until size) {
            if(i % 2 == 0) {
                for(j in 0 until w) {
                    map[i][j] = index++
                    find(i, j, num)
                }
                continue
            }
            for(j in w - 1 downTo 0) {
                map[i][j] = index++
                find(i, j, num)
            }
        }
    }
    private fun find(i: Int, j: Int, num: Int) {
        if(map[i][j] != num) return
        x = i
        y = j
    }
}

fun main() {
    val sol = PG389478()
    println(sol.solution(22, 6, 8)) // 3
    println(sol.solution(13, 3, 6)) // 4
}