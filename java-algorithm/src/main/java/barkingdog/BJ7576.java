package barkingdog;
/**
 * 백준 7576: 토마토
 * 레벨: 골드5
 */
import java.io.*;
import java.util.*;
public class BJ7576 {
	static final int [] arx = {-1,1,0,0};
	static final int [] ary = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		var br = new BufferedReader(new InputStreamReader(System.in));
		var st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int[][] map = new int [n][m];
		int[][] memo = new int [n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				memo[i][j] = Integer.MAX_VALUE;
			}
		}
		Queue<int[]> q = new LinkedList<>();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] == 1) {
					q.add(new int [] {i, j});
					memo[i][j] = 0;
				}
			}
		}
		while (!q.isEmpty()) {
			int [] cur = q.poll();
			for(int i = 0; i < 4; i++) {
				int nx = cur[0] + arx[i];
				int ny = cur[1] + ary[i];
				if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
				if(map[nx][ny] == -1) continue;
				if(memo[cur[0]][cur[1]] + 1 >= memo[nx][ny]) continue;
				memo[nx][ny] = memo[cur[0]][cur[1]] + 1;
				q.add(new int[] {nx, ny});
			}
		}
		int max = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(memo[i][j] == Integer.MAX_VALUE && map[i][j] != -1) {
					System.out.println(-1);
					return;
				}
				if(memo[i][j] == Integer.MAX_VALUE) continue;
				max = Math.max(max, memo[i][j]);
			}
		}
		System.out.println(max);
	}
}
