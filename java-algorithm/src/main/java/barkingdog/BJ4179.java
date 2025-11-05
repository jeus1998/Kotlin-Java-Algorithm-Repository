package barkingdog;


/**
 * 백준 4179: 불!
 * 레벨: 골드3
 */
import java.io.*;
import java.util.*;
public class BJ4179 {
	private static int sx, sy = 0; // 지훈이의 시작 지점
	static int[] arx = {-1,1,0,0};
	static int[] ary = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		var br = new BufferedReader(new InputStreamReader(System.in));
		var st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[][] map = new int[R][C];   // 불이 번지는 시간을 기록
		int[][] memo = new int[R][C];  // 지훈이 이동을 기록
		Queue<int[]> q = new LinkedList<>();
 		for(int i = 0; i < R; i++) {
			String line = br.readLine();
			for(int j = 0; j < C; j++) {
				memo[i][j] = Integer.MAX_VALUE;
				char cur =  line.charAt(j);
				if(cur == '#') {
					map[i][j] = -1;
					memo[i][j] = -1;
					continue;
				}
				if(cur == 'F') {
					q.add(new int[] {i, j});
					map[i][j] = 1;
					continue;
				}
				if(cur == 'J') {
					sx = i;
					sy = j;
				}
				map[i][j] = Integer.MAX_VALUE;
			}
		}
		// 1. BFS 불이 번지는 시간을 기록
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0];
			int cy = cur[1];
			for(int i = 0; i < 4; i++) {
				int nx = cx + arx[i];
				int ny = cy + ary[i];
				if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
				if(map[nx][ny] == -1) continue;
				if(map[cx][cy] + 1 >= map[nx][ny]) continue;
				map[nx][ny] = map[cx][cy] + 1;
				q.add(new int[] {nx, ny});
			}
		}
		// 2. 지훈이 BFS
		q.add(new int[] {sx, sy});
		memo[sx][sy] = 1;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0];
			int cy = cur[1];
			for(int i = 0; i < 4; i++) {
				int nx = cx + arx[i];
				int ny = cy + ary[i];
				if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
				if(memo[nx][ny] == -1 || memo[cx][cy] + 1 >= map[nx][ny]) continue;
				if(memo[cx][cy] + 1 >= memo[nx][ny]) continue;
				memo[nx][ny] = memo[cx][cy] + 1;
				q.add(new int[] {nx, ny});
			}
		}
		int answer = Integer.MAX_VALUE;
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(i != 0 && i != R - 1 && j != 0 && j != C - 1) continue;
				if(memo[i][j] == -1 || memo[i][j] == Integer.MAX_VALUE) continue;
				answer = Math.min(answer, memo[i][j]);
			}
		}
		if(answer == Integer.MAX_VALUE) {
			System.out.println("IMPOSSIBLE");
			return;
		}
		System.out.println(answer);
	}
}
/*
[-1, -1, -1, -1]
[-1, 2, 1, -1]
[-1, 3, 2, -1]
[-1, 4, 3, -1]

[-1, -1, -1, -1]
[-1, 1, 2147483647, -1]
[-1, 2, 2147483647, -1]
[-1, 3, 2147483647, -1]
 */