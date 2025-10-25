package barkingdog;

/**
 * 백준 13300: 방 배정
 * 레벨: 브론즈2
 */
import java.io.*;
import java.util.*;
public class BJ13300 {
	static final int [][] record = new int [2][7];
	public static void main(String[] args) throws IOException {
		var br = new BufferedReader(new InputStreamReader(System.in));
		var st = new StringTokenizer(br.readLine());
		var n = Integer.parseInt(st.nextToken()); // 학생 수
		var k = Integer.parseInt(st.nextToken()); // 한 방에 배정할 수 있는 최대 인원
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			var gender = Integer.parseInt(st.nextToken()); // 성별
			var grade = Integer.parseInt(st.nextToken()); // 학년
			record[gender][grade]++;
		}
		var rooms = 0;
		for(int i = 0; i < 2; i++) {
			for(int j = 1; j <= 6; j++) {
				rooms += (record[i][j] / k) + (record[i][j] % k == 0 ? 0 : 1);
			}
		}
		System.out.println(rooms);
	}
}
/*
S(0) = 여학생 수
S(1) = 남학생 수
학년 1~6


 */
