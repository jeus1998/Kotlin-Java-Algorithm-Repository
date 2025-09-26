package programmers.level1;

/**
 * 프로그래머스 LEVEL1 - 택배 상자 꺼내기 <br>
 * - <2025 프로그래머스 코드 챌린지 2차 예선> <br>
 * - 링크: https://school.programmers.co.kr/learn/courses/30/lessons/389478 <br>
 * - 2025-09-26
 */
public class PG389478 {
	static int answer = 1;
	static int size;
	static int [][] map;
	static int x, y;
	public int solution(int n, int w, int num) {
		init(n, w, num);
		core(n);
		return answer;
	}
	// 상자 개수 찾기
	static void core(int n) {
		for(int i = x + 1; i < size; i++) {
			if(map[i][y] <= n) answer++;
		}
	}
	static void init(int n, int w, int num) {
		// 높이 계산
		size = (n / w) + (n % w == 0 ? 0 : 1);
		map = new int [size][w];
		// 상자 채우기
		int index = 1;
		for(int i = 0; i < size; i++) {
			if(i % 2 == 0) {
				for(int j = 0; j < w; j++) {
					map[i][j] = index++;
					find(i, j, num);
				}
				continue;
			}
			for(int j = w - 1; j >= 0; j--) {
				map[i][j] = index++;
				find(i, j, num);
			}
		}
	}
	static void find(int i, int j, int num) {
		if(map[i][j] != num) return;
		x = i;
		y = j;
	}
}
