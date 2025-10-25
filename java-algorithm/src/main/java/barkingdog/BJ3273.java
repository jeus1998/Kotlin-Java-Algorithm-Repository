package barkingdog;

/**
 * 백준 3273: 두 수의 합
 * 레벨: 실버3
 */
import java.io.*;
import java.util.*;
public class BJ3273 {
	static final int INF = 2000001;
	public static void main(String[] args) throws  IOException {
		var br = new BufferedReader(new InputStreamReader(System.in));
		var n = Integer.parseInt(br.readLine());
		var elements = new boolean[INF];
		var record = new int [n];
		var inputTokens = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			var cur = Integer.parseInt(inputTokens.nextToken());
			elements[cur] = true;
			record[i] = cur;
		}
		var x = Integer.parseInt(br.readLine());
		var answer = 0;
		for(int i = 0; i < n; i++) {
			if(x - record[i] < 0) continue;
			if(elements[x - record[i]]) answer++;
		}
		System.out.println(answer / 2);
	}
}
/*
n개의 서로 다른 양의 정수 a1, a2, ..., an으로 이루어진 수열

첫째 줄에 수열의 크기 n이 주어진다. 다음 줄에는 수열에 포함되는 수가 주어진다. 셋째 줄에는 x가 주어진다.
문제의 조건을 만족하는 쌍의 개수를 출력
1 <= n <= 100,000, 1 <= x <= 2,000,000, 1 <= 수열의 원소 <= 1,000,000
ai + aj = x(1 <= i < j <= n)인 쌍 (i, j)의 개수를 구하는 프로그램을 작성
 */
