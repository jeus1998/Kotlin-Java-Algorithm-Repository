package barkingdog;

/**
 * 백준 1679: 숨바꼭질
 * 레벨: 실버1
 */
import java.io.*;
import java.util.*;
public class BJ1679 {
	static final int INF = 100001;
	public static void main(String[] args) throws IOException {
		var br = new BufferedReader(new InputStreamReader(System.in));
		int [] memo = new int [INF];
		Arrays.fill(memo, Integer.MAX_VALUE);
		var st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 수빈이 위치
		int K = Integer.parseInt(st.nextToken()); // 동생 위치
		memo[N] = 0;
		Queue<Integer> q = new LinkedList<>();
		q.add(N);
		while (!q.isEmpty()) {
			int cur = q.poll();
			if(cur + 1 < INF && memo[cur] + 1 < memo[cur + 1]) {
				memo[cur + 1] = memo[cur] + 1;
				q.add(cur + 1);
			}
			if(cur - 1 >= 0 && memo[cur] + 1 < memo[cur - 1]) {
				memo[cur - 1] = memo[cur] + 1;
				q.add(cur - 1);
			}
			if(cur * 2 < INF && memo[cur] + 1 < memo[cur * 2]) {
				memo[cur * 2] = memo[cur] + 1;
				q.add(cur * 2);
			}
		}
		System.out.println(memo[K]);
	}
}
