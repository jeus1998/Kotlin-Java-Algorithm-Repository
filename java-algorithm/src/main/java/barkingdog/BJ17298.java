package barkingdog;

/**
 * 백준 17298: 오큰수
 * 레벨: 골드4
 */
import java.io.*;
import java.util.*;
public class BJ17298 {
	public static void main(String[] args) throws IOException {
		var br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int[] answer = new int[n];
		Arrays.fill(answer, -1);
		Stack<Integer> stack = new Stack<>();
		for(int i = n - 1; i >= 0; i--) {
			while(!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
				stack.pop();
			}
			if(!stack.isEmpty()) {
				answer[i] = arr[stack.peek()];
			}
			stack.push(i);
		}
		var sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			sb.append(answer[i]).append(' ');
		}
		System.out.println(sb);
	}
}
/*
Ai의 오큰수는 오른쪽에 있으면서 Ai보다 큰 수 중에서 가장 왼쪽에 있는 수이다.
그러한 수가 없는 경우에는 -1이다.

예를 들어, A = [3, 5, 2, 7]인 경우에
A1(=3)의 오큰수는 A2(=5)이고,
A2(=5)의 오큰수는 A4(=7)이며,
A3(=2)의 오큰수는 A4(=7)이고,
A4(=7)의 오큰수는 없으므로 -1이다.

 */