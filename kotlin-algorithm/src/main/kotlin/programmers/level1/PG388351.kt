package programmers.level1

/**
 * 프로그래머스 LEVEL1 - 유연근무제 <br>
 * - <2025 프로그래머스 코드 챌린지 1차 예선> <br>
 * - 링크: https://school.programmers.co.kr/learn/courses/30/lessons/388351 <br>
 * - 2025-10-16
 */
class PG388351 {
    companion object {
        var answer = 0
        val weekend: BooleanArray = BooleanArray(7)
        lateinit var limit: IntArray
    }
    fun solution(schedules: IntArray, timelogs: Array<IntArray>, startday: Int): Int {
        init(startday, schedules)
        core(timelogs)
        return answer
    }
    // 핵심 로직
    private fun core(timelogs: Array<IntArray>) {
        for(i in 0 .. timelogs.lastIndex) {
            var flag = true
            for(j in 0 ..6) {
                if(weekend[j]) continue
                var log = convert(timelogs[i][j])
                if(limit[i] < log) {
                    flag = false
                    break
                }
            }
            if(flag) answer++
        }
    }
    // 시간 포맷 변경 ex) 10시13분 -> 613(10*60 + 13)
    private fun convert(time: Int): Int {
        var result = 0
        result += (time % 100)
        result += (time / 100) * 60
        return result
    }
    // 초기화: 주말 체크, 출근 가능 시간 재설정
    private fun init(startday: Int, schedules: IntArray) {
        // println(schedules.contentToString()) // Java Arrays.toString()과 동일
        var day: Int = startday
        for(i in 0 .. 6) {
            if(day++ >= 6) weekend[i] = true
            if(day > 7) day = 1
        }
        limit = IntArray(schedules.size)
        for(i in 0 .. schedules.lastIndex) {
            limit[i] = convert(schedules[i]) + 10
        }
    }
}
