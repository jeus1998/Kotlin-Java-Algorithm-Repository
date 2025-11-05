package barkingdog;

/**
 * 백준 1012: 유기농 배추
 * 레벨: 실버2
 */
import java.io.*;
import java.util.*;
public class BJ1012 {
	static final int [] arx = {-1,1,0,0};
	static final int [] ary = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		var br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
		StringBuilder sb = new StringBuilder();
		while (t-- > 0) {
			var st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken()); // 배추 위치
			int[][] map = new int[n][m];
			while(k-- > 0) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				map[x][y] = 1;
			}
			int count = 0;
			boolean [][] visited = new boolean[n][m];
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					if(visited[i][j] || map[i][j] == 0) continue;
					visited[i][j] = true;
					count++;
					Queue<int[]> q = new LinkedList<>();
					q.add(new int[] { i, j });
					while(!q.isEmpty()) {
						int [] cur =  q.poll();
						int x = cur[0];
						int y = cur[1];
						for(int l = 0; l < 4; l++) {
							int nx = x + arx[l];
							int ny = y + ary[l];
							if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
							if(visited[nx][ny] || map[nx][ny] == 0) continue;
							visited[nx][ny] = true;
							q.add(new int[] { nx, ny });
						}
					}
				}
			}
			sb.append(count).append("\n");
		}
		System.out.println(sb);
	}
}
