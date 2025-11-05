package barkingdog;

/**
 * 백준 7569: 토마토
 * 레벨: 골드5
 */
import java.io.*;
import java.util.*;
public class BJ7569 {
	static int [] arx = {-1,1,0,0,0,0};
	static int [] ary = {0,0,-1,1,0,0};
	static int [] arz = {0,0,0,0,-1,1};
	public static void main(String[] args) throws IOException {
		var br = new BufferedReader(new InputStreamReader(System.in));
		var st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		int[][][] map = new int[n][m][h];
		Queue<int[]> q = new LinkedList<>();
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < n; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < m; k++) {
					int cur = Integer.parseInt(st.nextToken());
					if(cur == 1) {
						q.add(new int[] {j, k, i});
						continue;
					}
					if(cur == 0) {
						map[j][k][i] = -2;
						continue;
					}
					map[j][k][i] = -1;
				}
			}
		}
		while (!q.isEmpty()) {
			int [] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int z = cur[2];
			for(int k = 0; k < 6; k++) {
				int nx = x + arx[k];
				int ny = y + ary[k];
				int nz = z + arz[k];
				if(nx < 0 || ny < 0 || nz < 0) continue;
				if(nx >= n || ny >= m || nz >= h) continue;
				if(map[nx][ny][nz] == -1 || (map[x][y][z] + 1 >= map[nx][ny][nz] && map[nx][ny][nz] != -2)) continue;
				map[nx][ny][nz] = map[x][y][z] + 1;
				q.add(new int[] {nx, ny, nz});
			}
		}
		int max = 0;
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < n; j++) {
				for(int k = 0; k < m; k++) {
					if(map[j][k][i] == -2) {
						System.out.println(-1);
						return;
					}
					max = Math.max(max, map[j][k][i]);
				}
			}
		}
		System.out.println(max);
	}
}
