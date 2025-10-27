package barkingdog;

/**
 * 백준 1874: 스택 수열
 * 레벨: 실버2
 */
import java.io.*;
import java.util.*;
public class BJ1874 {
	private static final String push = "+";
	private static final String pop = "-";
	private static final String nextLine = "\n";
	private static final String impossible = "NO";
	public static void main(String[] args) throws IOException {
		var br = new BufferedReader(new InputStreamReader(System.in));
		var n = Integer.parseInt(br.readLine());
		var stack = new Stack<Integer>();
		var q = new LinkedList<Integer>();
		for (int i = 1; i <= n; i++) {
			q.add(Integer.parseInt(br.readLine()));
		}
		var sb = new StringBuilder();
		var current = 1;
		while(!q.isEmpty()) {
			while(current <= q.peek()) {
				stack.push(current++);
				sb.append(push).append(nextLine);
			}
			var flag = false;
			if(!stack.isEmpty() && stack.peek().compareTo(q.peek()) == 0) {
				flag = true;
				stack.pop();
				q.poll();
				sb.append(pop).append(nextLine);
			}
			if(!flag) {
				System.out.println(impossible);
				return;
			}
		}
		System.out.println(sb);
	}
}
/*
1부터 n까지의 수를 스택에 넣었다가 뽑아 늘어놓음으로써, 하나의 수열을 만들 수 있다.
이때, 스택에 push하는 순서는 반드시 오름차순을 지키도록 한다고 하자

임의의 수열이 주어졌을 때 스택을 이용해 그 수열을 만들 수 있는지 없는지,
있다면 어떤 순서로 push와 pop 연산을 수행해야 하는지를 알아낼 수 있다.
이를 계산하는 프로그램을 작성하라.

4 - 3 - 6 - 8 - 7 - 5 - 2 - 1

4
3
6
8
7
5
2
1

1
2
5
7
8
6
3
4

 */