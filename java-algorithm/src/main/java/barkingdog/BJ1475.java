package barkingdog;

/**
 * 백준 1475: 방 번호
 * 레벨: 실버5
 */
import java.io.*;
import java.util.*;
public class BJ1475 {
	public static void main(String[] args) throws IOException {
		var br = new BufferedReader(new InputStreamReader(System.in));
		var roomNumber = br.readLine(); // 방 번호
		var record = new int [10];
		for (int i = 0; i < roomNumber.length(); i++) {
			record[roomNumber.charAt(i) - '0']++;
		}
		var max = 0;
		for(int i = 0; i < 10; i++) {
			if(i == 6 || i == 9) continue;
			if(record[i] > max) {
				max = record[i];
			}
		}
		var sixNineCount = record[6] + record[9];
		var sixNineSet = sixNineCount / 2 + (sixNineCount % 2 == 0 ? 0 : 1);
		System.out.println(Math.max(max, sixNineSet));
	}
}
/*
다솜이는 자기 방 번호를 예쁜 플라스틱 숫자로 문에 붙이려고 한다.
다솜이의 옆집에서는 플라스틱 숫자를 한 세트로 판다. 한 세트에는 0번부터 9번까지 숫자가 하나씩 들어있다.
다솜이의 방 번호가 주어졌을 때, 필요한 세트의 개수의 최솟값을 출력하시오.
(6은 9를 뒤집어서 이용할 수 있고, 9는 6을 뒤집어서 이용할 수 있다.)
 */
