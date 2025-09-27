package interview.sort;

import java.util.Arrays;
/**
 * 병합 정렬 구현(Merge Sort) <br>
 * - 시간 복잡도: O(N log N) <br>
 * - 핵심: N개의 원소를 반으로 쪼개서 각각 정렬한 후 합치는 방식
 */
public class MergeSort {
	static int [] array;
	static int [] temp;
	public static void main(String[] args) {
		init();
		System.out.println(Arrays.toString(array)); // before sort
		// [3, 2, 7, 116, 62, 235, 1, 23, 55, 77]
		divide(0, array.length);
		System.out.println(Arrays.toString(array)); // after sort
		// [1, 2, 3, 7, 23, 55, 62, 77, 116, 235]
	}
	private static void init() {
		array = Elements.copy(); // 문제 배열 복사
		temp = new int [array.length];
	}
	private static void divide(int start, int end) {
		if(end - start == 1) return;
		int mid = (start + end) / 2;
		divide(start, mid);
		divide(mid, end);
		merge(start, mid, end);
	}
	private static void merge(int start, int mid, int end) {
		int idx1 = start;
		int idx2 = mid;
		for(int i = start; i < end; i++) {
			if(idx1 == mid) temp[i] = array[idx2++];
			else if(idx2 == end) temp[i] = array[idx1++];
			else if(array[idx1] <= array[idx2]) temp[i] = array[idx1++];
			else temp[i] = array[idx2++];
		}
		for(int i = start; i < end; i++) {
			array[i] = temp[i];
		}
	}
}
