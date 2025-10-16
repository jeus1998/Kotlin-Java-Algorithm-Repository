package programmers.level1;

/**
 * 프로그래머스 LEVEL1 - 택배 상자 꺼내기 <br>
 * - <2025 프로그래머스 코드 챌린지 2차 예선> <br>
 * - 링크: https://school.programmers.co.kr/learn/courses/30/lessons/389478 <br>
 * - 2025-09-26
 */
public class PG389478 {
	private static int answer = 0;
	public int solution(int n, int w, int num) {
		core(n, w, num);
		return answer;
	}
	private static void core(int n, int w, int num) {
		while (num <= n) {
			answer++;
			if(num % w == 0) num++;
			else num += (w - num % w) * 2 + 1;
		}
	}
}
