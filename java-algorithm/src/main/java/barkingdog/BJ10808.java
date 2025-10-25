package barkingdog;

/**
 * 백준 10808: 알파벳 개수
 * 레벨: 브론즈2
 */
import java.util.*;
import java.io.*;
public class BJ10808 {
	private static final int[] RECORD = new int[26];
	public static void main(String[] args) throws IOException {
		var br = new BufferedReader(new InputStreamReader(System.in));
		var input = br.readLine();
		for(int i = 0; i < input.length(); i++) {
			RECORD[input.charAt(i) - 'a']++;
		}
		var sb = new StringBuilder();
		for (int e : RECORD) {
			sb.append(e).append(" ");
		}
		System.out.println(sb);
	}
}
/*
알파벳 소문자로만 이루어진 단어 S가 주어진다.
각 알파벳이 단어에 몇 개가 포함되어 있는지 구하는 프로그램을 작성하시오.
첫째 줄에 단어 S가 주어진다. 단어의 길이는 100을 넘지 않으며, 알파벳 소문자로만 이루어져 있다.

예제 입력
baekjoon

예제 출력
1 1 0 0 1 0 0 0 0 1 1 0 0 1 2 0 0 0 0 0 0 0 0 0 0 0
 */