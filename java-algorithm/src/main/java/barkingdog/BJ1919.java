package barkingdog;

/**
 * 백준 1919: 애너그램 만들기
 * 레벨: 브론즈2
 */
import java.io.*;
import java.util.*;
public class BJ1919 {
	public static void main(String[] args) throws  IOException {
		var br = new BufferedReader(new InputStreamReader(System.in));
		var a = br.readLine();
		var b = br.readLine();
		var countA = new int[26];
		var countB = new int[26];
		for(int i=0; i < a.length(); i++) {
			countA[a.charAt(i) - 'a']++;
		}
		for(int i=0; i < b.length(); i++) {
			countB[b.charAt(i) - 'a']++;
		}
		var result = 0;
		for(int i=0; i < 26; i++) {
			result += Math.abs(countA[i]-countB[i]);
		}
		System.out.println(result);
	}
}
/*
두 영어 단어가 철자의 순서가 뒤바꾸어 같아질 수 있을 때,
두 단어를 서로 애너그램(anagram)관계에 있다고 한다.

예를 들면 occurs 라는 영어 단어와 succor 는 서로 애너그램 관계에 있는데,
occurs의 각 문자들의 순서를 잘 바꾸면 succor이 되기 때문이다.

두 개의 영어 단어가 주어졌을 때, 두 단어가 서로 애너그램 관계에 있도록 만들기 위해서
제거해야 하는 최소 개수의 문자 수를 구하는 프로그램을 작성하시오.
문자를 제거할 때에는 아무 위치에 있는 문자든지 제거할 수 있다.

첫째 줄과 둘째 줄에 영어 단어가 소문자로 주어진다. 각각의 길이는 1,000자를 넘지 않으며,
적어도 한 글자로 이루어진 단어가 주어진다.
 */