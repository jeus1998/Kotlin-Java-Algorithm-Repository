package barkingdog;

/**
 * 백준 2178: 미로 탐색
 * 레벨: 실버1
 */
import java.io.*;
import java.util.*;
public class BJ2178 {
	static final int [] arx = {-1,1,0,0};
	static final int [] ary = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		var br = new BufferedReader(new InputStreamReader(System.in));
		var st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][m];
		int[][] memo = new int[n][m];
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = line.charAt(j) - '0';
				memo[i][j] = Integer.MAX_VALUE;
			}
		}
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0, 0});
		memo[0][0] = 1;
		while (!q.isEmpty()) {
			int [] cur = q.poll();
			for(int i = 0; i < 4; i++) {
				int nx = cur[0] + arx[i];
				int ny = cur[1] + ary[i];
				if(nx < 0 || ny < 0 || nx >= n || ny >= m || map[nx][ny] == 0) continue;
				if(memo[cur[0]][cur[1]] + 1 >= memo[nx][ny]) continue;
				memo[nx][ny] = memo[cur[0]][cur[1]] + 1;
				q.add(new int[] {nx, ny});
			}
		}
		System.out.println(memo[n-1][m-1]);
	}
}
