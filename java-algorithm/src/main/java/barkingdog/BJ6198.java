package barkingdog;

/**
 * 백준 6198: 옥상 정원 꾸미기
 * 레벨: 골드5
 */
import java.io.*;
import java.util.*;
public class BJ6198 {
	public static void main(String[] args) throws IOException {
		var br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int [] buildings = new int[n];
		for(int i = 0; i < n; i++) {
			buildings[i] = Integer.parseInt(br.readLine());
		}
		long result = 0;
		Stack<Integer> stack = new Stack<>();
		for(int i = 0; i < n; i++) {
			while(!stack.isEmpty() && stack.peek() <= buildings[i]) {
				stack.pop();
			}
			result += stack.size();
			stack.push(buildings[i]);
		}
		System.out.println(result);
	}
}
/*
각 관리인들이 벤치마킹이 가능한 빌딩의 수의 합을 출력
 */