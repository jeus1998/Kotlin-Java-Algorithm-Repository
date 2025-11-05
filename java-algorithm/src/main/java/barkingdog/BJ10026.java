package barkingdog;

/**
 * 백준 10026: 적록색약
 * 레벨: 골드5
 */
import java.io.*;
import java.util.*;
public class BJ10026 {
	private final static int[] arx = {-1,1,0,0};
	private final static int[] ary = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		var br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int [][] map = new int [n][n];
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for(int j = 0; j < n; j++) {
				char cur = line.charAt(j);
				if(cur == 'R') continue;
				if(cur == 'G') map[i][j] = 1;
				else if(cur == 'B') map[i][j] = 2;
			}
		}
		int cnt1 = 0;
		int cnt2 = 0;
		// 1. R/G/B 구분이 가능한 보통 사람 BFS
		cnt1 = BFS(map, n);
		// 2. 적록색약을 위한 map 세팅
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				// green -> red (통일)
				if(map[i][j] == 1) map[i][j] = 0;
			}
		}
		// 3. 적록색약인 사람 BFS
		cnt2 = BFS(map, n);
		StringBuilder sb =  new StringBuilder();
		sb.append(cnt1).append(" ").append(cnt2);
		System.out.println(sb);
	}
	private static int BFS(int[][] map, int n) {
		int result = 0;
		Queue<int[]> q = new LinkedList<>();
		boolean [][] visited = new boolean[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(visited[i][j]) continue;
				visited[i][j] = true;
				int target = map[i][j];
				result++;
				q.add(new int[] {i, j});
				while(!q.isEmpty()) {
					int cur[] = q.poll();
					int x = cur[0];
					int y = cur[1];
					for(int k = 0; k < 4; k++) {
						int nx = x + arx[k];
						int ny = y + ary[k];
						if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
						if(visited[nx][ny] || target != map[nx][ny]) continue;
						visited[nx][ny] = true;
						q.add(new int[] {nx, ny});
					}
				}
			}
		}
		return result;
	}
}
