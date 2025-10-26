package barkingdog;

/**
 * 백준 5397: 키로거
 * 레벨: 실버2
 */
import java.io.*;
import java.util.*;
public class BJ5397 {
	public static void main(String[] args) throws  IOException {
		var br = new BufferedReader(new InputStreamReader(System.in));
		var t = Integer.parseInt(br.readLine());
		var sb = new StringBuilder();
		LinkedList<Character> q1 = new LinkedList<>();
		LinkedList<Character> q2 = new LinkedList<>();
		while(t --> 0) {
			var input = br.readLine();
			for (int i = 0; i < input.length(); i++) {
				var cur = input.charAt(i);
				switch (cur) {
					case '<' -> {
						if(!q1.isEmpty()) {
							q2.addFirst(q1.pollLast());
						}
					}
					case '>' -> {
						if(!q2.isEmpty()) {
							q1.addLast(q2.pollFirst());
						}
					}
					case '-' -> {
						if(!q1.isEmpty()) {
							q1.pollLast();
						}
					}
					default -> q1.addLast(cur);
				}
			}
			while (!q1.isEmpty()) sb.append(q1.pollFirst());
			while (!q2.isEmpty()) sb.append(q2.pollFirst());
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
/*
창영이는 강산이의 비밀번호를 훔치기 위해서 강산이가 사용하는 컴퓨터에 키로거를 설치했다.
며칠을 기다린 끝에 창영이는 강산이가 비밀번호 창에 입력하는 글자를 얻어냈다.
키로거는 사용자가 키보드를 누른 명령을 모두 기록한다.
따라서, 강산이가 비밀번호를 입력할 때, 화살표나 백스페이스를 입력해도 정확한 비밀번호를 알아낼 수 있다.
강산이가 비밀번호 창에서 입력한 키가 주어졌을 때, 강산이의 비밀번호를 알아내는 프로그램을 작성하시오.
강산이는 키보드로 입력한 키는 알파벳 대문자, 소문자, 숫자, 백스페이스, 화살표이다.
 */