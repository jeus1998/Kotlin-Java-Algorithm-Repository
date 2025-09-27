package interview.sort;

/**
 * 퀵 정렬 구현(Quick Sort) <br>
 * - 시간 복잡도: O(N log N) <br>
 * - 기준 원소(pivot)를 정하고, 기준 원소보다 작은 원소는 왼쪽, 큰 원소는 오른쪽으로 보내는 방식 <br>
 * - 평균적으로 빠르지만, 최악의 경우 O(N^2) <br>
 */
import java.util.Arrays;
public class QuickSort {
	static int [] array;
	public static void main(String[] args) {
		init();
		System.out.println(Arrays.toString(array)); // before sort
		pivotSelect(0, array.length - 1);
		System.out.println(Arrays.toString(array)); // after sort
	}
	private static void init() {
		array = Elements.copy();
	}
	private static void pivotSelect(int start, int end) {
		if(start < end) {
			int pivot = quickSort(start, end);
			pivotSelect(start, pivot - 1);
			pivotSelect(pivot + 1, end);
		}
	}
	private static  int quickSort(int start, int end) {
		// 가장 왼쪽 원소를 기준 원소로 선택
		int pivot = array[start];
		int l = start + 1; // pivot 보다 큰 원소를 찾기 위한 포인터
		int r = end; // pivot 보다 작은 원소를 찾기 위한 포인터
		while(l <= r) {
			while(l <= r && array[l] <= pivot) l++;
			while(l <= r && array[r] >= pivot) r--;
			if(l < r) swap(l, r);
		}
		swap(start, r);
		return r;
	}
	private static void swap(int l, int r) {
		int temp = array[l];
		array[l] = array[r];
		array[r] = temp;
	}
}
