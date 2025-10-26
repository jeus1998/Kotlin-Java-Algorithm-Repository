package barkingdog;

/**
 * 백준 1406: 에디터
 * 레벨: 실버2
 */
import java.io.*;
import java.util.*;
public class BJ1406 {
	public static void main(String[] args) throws IOException{
		var br = new BufferedReader(new InputStreamReader(System.in));
		var init = br.readLine();
		var m = Integer.parseInt(br.readLine());
		LinkedList<String> q1 = new LinkedList<>();
		LinkedList<String> q2 = new LinkedList<>();
		for(int i = 0; i < init.length(); i++) {
			q1.add(Character.toString(init.charAt(i)));
		}
		while(m-->0) {
			var st = new StringTokenizer(br.readLine());
			switch (st.nextToken()) {
				case "L" -> {
					if(q1.isEmpty()) break;
					q2.addFirst(q1.pollLast());
				}
				case "D" -> {
					if(q2.isEmpty()) break;
					q1.addLast(q2.pollFirst());
				}
				case "B" -> {
					if(q1.isEmpty()) break;
					q1.pollLast();
				}
				default -> {
					var token = st.nextToken();
					q1.addLast(token);
				}
			}
		}
		var sb = new StringBuilder();
		while(!q1.isEmpty()) sb.append(q1.pollFirst());
		while(!q2.isEmpty()) sb.append(q2.pollFirst());
		System.out.println(sb);
	}
}
/*
이 편집기에는 '커서'라는 것이 있는데, 커서는 문장의 맨 앞(첫 번째 문자의 왼쪽),
문장의 맨 뒤(마지막 문자의 오른쪽), 또는 문장 중간 임의의 곳(모든 연속된 두 문자 사이)에 위치할 수 있다.
즉 길이가 L인 문자열이 현재 편집기에 입력되어 있으면,
커서가 위치할 수 있는 곳은 L+1가지 경우가 있다.

이 편집기가 지원하는 명령어는 다음과 같다.

L: 커서를 왼쪽으로 한 칸 옮김 (커서가 문장의 맨 앞이면 무시됨)
D: 커서를 오른쪽으로 한 칸 옮김 (커서가 문장의 맨 뒤이면 무시됨)
B: 커서 왼쪽에 있는 문자를 삭제함 (커서가 문장의 맨 앞이면 무시됨)
P $: $라는 문자를 커서 왼쪽에 추가함

초기에 편집기에 입력되어 있는 문자열이 주어지고,
그 이후 입력한 명령어가 차례로 주어졌을 때,
모든 명령어를 수행하고 난 후 편집기에 입력되어 있는 문자열을 구하는 프로그램을 작성하시오.
단, 명령어가 수행되기 전에 커서는 문장의 맨 뒤에 위치하고 있다고 한다.
 */
