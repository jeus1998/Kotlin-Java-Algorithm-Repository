package programmers.level1;

/**
 * 프로그래머스 LEVEL1 - 유연근무제 <br>
 * - <2025 프로그래머스 코드 챌린지 1차 예선> <br>
 * - 링크: https://school.programmers.co.kr/learn/courses/30/lessons/388351 <br>
 * - 2025-10-16
 */
public class PG388351 {
	static int answer = 0;
	static boolean [] weekend = new boolean [7];
	static int [] limit;

	public int solution(int[] schedules, int[][] timelogs, int startday) {
		init(startday, schedules);
		core(timelogs);
		return answer;
	}

	private static void core(int[][] logs) {
		for(int i = 0; i < logs.length; i++) {
			boolean flag = true;
			for(int j = 0; j < 7; j++) {
				if(weekend[j]) continue;
				int log = convert(logs[i][j]);
				if(limit[i] < log) {
					flag = false;
					break;
				}
			}
			if(flag) answer++;
		}
	}

	// "시간 포맷 변경 함수" ex: 10시 13분(1013) -> 60 * 10 + 13 = 613(분)
	private static int convert(int time) {
		int result = 0;
		result += time % 100;
		result += (time / 100) * 60;
		return result;
	}

	// 초기화
	private void init(int day, int[] schedules) {
		for(int i = 0; i < 7; i++) {
			if(day++ >= 6) weekend[i] = true;
			if(day > 7) day = 1;
		}
		limit = new int [schedules.length];
		for(int i = 0; i < limit.length; i++) {
			// 출근 희망 시각 + 10
			limit[i] = convert(schedules[i]) + 10;
		}
	}
}
