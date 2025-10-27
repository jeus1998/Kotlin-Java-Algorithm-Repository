package barkingdog;

/**
 * 백준 2493: 탑
 * 레벨: 골드5
 */
import java.io.*;
import java.util.*;
public class BJ2493 {
	public static void main(String[] args) throws IOException {
		var br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		var st = new StringTokenizer(br.readLine());
		int [] towers = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			towers[i] = Integer.parseInt(st.nextToken());
		}
		Stack<Integer> stack = new Stack<>();
		int [] answer = new int[n + 1];
		for(int i = n; i >= 1; i--) {
			while(!stack.isEmpty() && towers[stack.peek()] <= towers[i]) {
				answer[stack.pop()] = i;
			}
			stack.push(i);
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= n; i++) {
			sb.append(answer[i]).append(' ');
		}
		System.out.println(sb);
	}
}
/*
입력
5
6 9 5 7 4

출력
0 0 2 2 4





 */