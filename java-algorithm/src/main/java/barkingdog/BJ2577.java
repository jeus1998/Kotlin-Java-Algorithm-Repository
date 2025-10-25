package barkingdog;

/**
 * 백준 2577: 숫자의 개수
 * 레벨: 브론즈2
 */
import java.io.*;
import java.util.*;
public class BJ2577 {
	public static void main(String[] args) throws IOException {
		var br = new BufferedReader(new InputStreamReader(System.in));
		var result = 1;
		for(int i = 1; i <= 3; i++)
			result *= Integer.parseInt(br.readLine());
		var str = Integer.toString(result);
		var record = new int[10];
		for (int i = 0; i < str.length(); i++) {
			record[str.charAt(i) - '0']++;
		}
		var sb = new StringBuilder();
		for (int e : record) {
			sb.append(e).append("\n");
		}
		System.out.println(sb);
	}
}
/*
세 개의 자연수 A, B, C가 주어질 때 A × B × C를 계산한 결과에
0부터 9까지 각각의 숫자가 몇 번씩 쓰였는지를 구하는 프로그램을 작성
예를 들어 A = 150, B = 266, C = 427 이라면 A × B × C = 150 × 266 × 427 = 17037300 이 되고,
계산한 결과 17037300 에는 0이 3번, 1이 1번, 3이 2번, 7이 2번 쓰였다.

입력
첫째 줄에 A, 둘째 줄에 B, 셋째 줄에 C가 주어진다. A, B, C는 모두 100보다 크거나 같고,
1,000보다 작은 자연수이다.

예제 입력
150
266
427

예제 출력
3
1
0
2
0
0
0
2
0
0
 */
