package barkingdog;

/**
 * 백준 1158: 요세푸스 문제
 * 레벨: 실버4
 */
import java.io.*;
import java.util.*;
public class BJ1158 {
	public static void main(String[] args) throws IOException{
		var br = new BufferedReader(new InputStreamReader(System.in));
		var st = new StringTokenizer(br.readLine());
		var n = Integer.parseInt(st.nextToken());
		var k = Integer.parseInt(st.nextToken());
		var q = new LinkedList<Integer>();
		for(int i = 1; i <= n; i++) {
			q.add(i);
		}
		var sb = new StringBuilder();
		sb.append("<");
		while(!q.isEmpty()) {
			for(int i = 0; i < k - 1; i++) {
				q.addLast(q.pollFirst());
			}
			sb.append(q.pollFirst());
			if(!q.isEmpty()) {
				sb.append(", ");
			}
		}
		sb.append(">");
		System.out.println(sb);
	}
}
/*
1번부터 N번까지 N명의 사람이 원을 이루면서 앉아있고,
양의 정수 K(≤ N)가 주어진다. 이제 순서대로 K번째 사람을 제거한다.

한 사람이 제거되면 남은 사람들로 이루어진 원을 따라 이 과정을 계속해 나간다.
이 과정은 N명의 사람이 모두 제거될 때까지 계속된다.
원에서 사람들이 제거되는 순서를 (N, K) -요세푸스 순열이라고 한다.
예를 들어 (7, 3) -요세푸스 순열은 <3, 6, 2, 7, 5, 1, 4>이다.
 */
