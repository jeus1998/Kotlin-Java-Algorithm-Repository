package barkingdog;

/**
 * 백준 10773: 제로
 * 레벨: 실버4
 */
import java.io.*;
import java.util.*;
public class BJ10773 {
	public static void main(String[] args) throws IOException{
		var br = new BufferedReader(new InputStreamReader(System.in));
		var stack = new Stack<Integer>();
		var k = Integer.parseInt(br.readLine());
		while (k-->0) {
			var input = Integer.parseInt(br.readLine());
			if (input == 0) stack.pop();
			else stack.push(input);
		}
		var result = 0;
		for (var num : stack) {
			result += num;
		}
		System.out.println(result);
	}
}