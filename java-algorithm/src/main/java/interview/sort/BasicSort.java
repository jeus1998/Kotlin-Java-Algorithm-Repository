package interview.sort;

/**
 * 선택 정렬 구현(Selection Sort)
 * 시간 복잡도: O(N^2)
 */
import java.util.*;
public class BasicSort {
	public static void main(String[] args) {
		int[] array = Elements.copy();
		for(int i = 0; i < array.length; i++) {
			int idx = i;
			for(int j = i + 1; j < array.length; j++) {
				if(array[idx] > array[j]) idx = j;
			}
			if(idx == i) continue;
			int temp = array[i];
			array[i] = array[idx];
			array[idx] = temp;
		}
		System.out.println(Arrays.toString(array));
	}
}
