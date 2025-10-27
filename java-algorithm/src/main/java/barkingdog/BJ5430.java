package barkingdog;

/**
 * 백준 5430: AC
 * 레벨: 골드5
 */
import java.io.*;
import java.util.*;
public class BJ5430 {
	private static final String ERROR = "error";
	private static final String NEXT_LINE = "\n";
	private static final String PREFIX = "[";
	private static final String SUFFIX = "]";
	public static void main(String[] args) throws IOException {
		var br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
		StringBuilder sb = new StringBuilder();
		while(t-->0) {
			String p = br.readLine();
			int n = Integer.parseInt(br.readLine());
			String input = br.readLine();
			if(n == 0) {
				if(p.contains("D")) sb.append(ERROR).append(NEXT_LINE);
				else sb.append("[]").append(NEXT_LINE);
				continue;
			}
			input = input.replace(PREFIX, "");
			input = input.replace(SUFFIX, "");
			String [] inputs = input.split(",");
			Deque<Integer> dq = new LinkedList<>();
			for(String in : inputs) {
				dq.add(Integer.parseInt(in));
			}
			boolean front = true;
			boolean flag = true;
			for(int i = 0; i < p.length(); i++) {
				char function = p.charAt(i);
				if(function == 'R') {
					front = !front;
					continue;
				}
				if(dq.isEmpty()) {
					flag = false;
					break;
				}
				if(front) dq.pollFirst();
				else dq.pollLast();
			}
			if(!flag) sb.append(ERROR).append(NEXT_LINE);
			else {
				sb.append(PREFIX);
				while (!dq.isEmpty()) {
					if(front) sb.append(dq.pollFirst());
					else sb.append(dq.pollLast());
					if(!dq.isEmpty()) sb.append(",");
					else break;
				}
				sb.append(SUFFIX).append(NEXT_LINE);
			}
		}
		System.out.println(sb);
	}
}
/*
함수 R: 배열에 있는 수의 순서를 뒤집는 함수
함수 D: 첫 번째 수를 버리는 함수
배열이 비어있는데 D를 사용하는 경우에는 에러가 발생

배열의 초기값과 수행할 함수가 주어졌을 때, 최종 결과를 구하는 프로그램 작성

 */
