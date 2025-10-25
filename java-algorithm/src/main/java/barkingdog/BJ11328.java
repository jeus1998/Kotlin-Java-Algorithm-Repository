package barkingdog;

/**
 * 백준 11328: Strfry
 * 레벨: 브론즈2
 */
import java.io.*;
import java.util.*;
public class BJ11328 {
	private final static String YES = "Possible";
	private final static String NO = "Impossible";
	public static void main(String[] args) throws IOException {
		var br = new BufferedReader(new InputStreamReader(System.in));
		var n = Integer.parseInt(br.readLine());
		var sb = new StringBuilder();
		while (n--> 0) {
			var st = new StringTokenizer(br.readLine());
			var a = st.nextToken();
			var b = st.nextToken();
			if(strfry(a, b)) {
				sb.append(YES).append("\n");
				continue;
			}
			sb.append(NO).append("\n");
		}
		System.out.print(sb);
	}
	private static boolean strfry(String a, String b) {
		var record1 = new int[26];
		var record2 = new int[26];
		if(a.length() != b.length()) return false;
		for(int i = 0; i < a.length(); i++) {
			record1[a.charAt(i) - 'a']++;
			record2[b.charAt(i) - 'a']++;
		}
		for (int i = 0; i < 26; i++) {
			if(record1[i] != record2[i]) return false;
		}
		return true;
	}
}
/*
두 개의 문자열에 대해, 2번째 문자열이 1번째 문자열에 strfry 함수를 적용하여
얻어질 수 있는지 판단하라
 */