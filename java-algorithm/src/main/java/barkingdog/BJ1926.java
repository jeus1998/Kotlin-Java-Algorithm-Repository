package barkingdog;

/**
 * 백준 1926: 그림
 * 레벨: 실버1
 */
import java.io.*;
import java.util.*;
public class BJ1926 {
	static final int [] arx = {-1, 1, 0, 0};
	static final int [] ary = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		var br = new BufferedReader(new InputStreamReader(System.in));
		var st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 행
		int m = Integer.parseInt(st.nextToken()); // 열
		int[][] map = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int cnt = 0; // 그림의 개수
		int max = 0; // 가장 넓은 그림의 넓이, 없는 경우 0
		boolean [][] visited = new boolean[n][m];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(visited[i][j] || map[i][j] == 0) continue;
				visited[i][j] = true;
				cnt++;
				int count = 1;
				Queue<int[]> q = new LinkedList<>();
				q.add(new int[] {i, j});
				while (!q.isEmpty()) {
					int [] cur = q.poll();
					for(int k = 0; k < 4; k++) {
						int nx = cur[0] + arx[k];
						int ny = cur[1] + ary[k];
						if(nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny] || map[nx][ny] == 0) continue;
						visited[nx][ny] = true;
						q.add(new int[] {nx, ny});
						count++;
					}
				}
				max = Math.max(max, count);
			}
		}
		System.out.println(cnt);
		System.out.println(max);
	}
}
