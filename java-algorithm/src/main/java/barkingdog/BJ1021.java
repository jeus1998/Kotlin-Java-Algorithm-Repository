package barkingdog;

/**
 * 백준 1021: 회전하는 큐
 * 레벨: 실버3
 */
import java.io.*;
import java.util.*;
public class BJ1021 {
	public static void main(String[] args) throws  IOException {
		var br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		LinkedList<Integer> q = new LinkedList<>();
		for(int i = 1; i <= n; i++) {
			q.addLast(i);
		}
		st = new StringTokenizer(br.readLine());
		int answer = 0;
		while(m --> 0) {
			int target = Integer.parseInt(st.nextToken());
			int leftMove = q.indexOf(target);    // 왼쪽으로 움직이는 경우
			int rightMove = q.size() - leftMove; // 오른쪽으로 움직이는 경우
			answer += Math.min(leftMove, rightMove);
			if(leftMove <= rightMove) {
				for(int i = 1; i <= leftMove; i++) {
					q.addLast(q.pollFirst());
				}
				q.pollFirst();
				continue;
			}
			for(int i = 1; i <= rightMove; i++) {
				q.addFirst(q.pollLast());
			}
			q.pollFirst();
		}
		System.out.println(answer);
	}
}
/*
1. 첫 번째 원소를 뽑아낸다. 이 연산을 수행하면, 원래 큐의 원소가 a1, ..., ak이었던 것이 a2, ..., ak와 같이 된다.
2. 왼쪽으로 한 칸 이동시킨다. 이 연산을 수행하면, a1, ..., ak가 a2, ..., ak, a1이 된다.
3. 오른쪽으로 한 칸 이동시킨다. 이 연산을 수행하면, a1, ..., ak가 ak, a1, ..., ak-1이 된다.

2번, 3번 연산의 최솟값을 출력하는 프로그램 작성


n = 10 m = 3
1 2 3
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

n = 10 m = 3
2 9 5
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

[2, 3, 4, 5, 6, 7, 8, 9, 10, 1] -> 1

[3, 4, 5, 6, 7, 8, 9, 10, 1]

[9, 10, 1, 3, 4, 5, 6, 7, 8] -> 3

[10, 1, 3, 4, 5, 6, 7, 8] -> 4

 */