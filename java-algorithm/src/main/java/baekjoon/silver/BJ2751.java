package baekjoon.silver;

/**
 * 백준 2751 - 수 정렬하기 2 <br>
 * - 링크: https://www.acmicpc.net/problem/2751 <br>
 * - 2025-09-27 <br>
 * - 1 <= N <= 1,000,000: O(NlogN) 정렬 알고리즘 필요<br>
 * 백준 제출 결과 <br>
 * - bubble sort: O(N^2) -> 시간 초과 <br>
 * - quick sort: O(NlogN) ~ O(N^2) -> 시간 초과 (worst case 존재) <br>
 * - merge sort: O(NlogN) -> 통과 (872ms) <br>
 * - Collection.sort(): O(NlogN) -> 통과 (1464ms) <br>
 */
import java.io.*;
public class BJ2751 {
	private static int [] array;
	private static int [] temp;
	public static void main(String[] args) {
		init();
		// bubbleSort();
		mergeSort(0, array.length);
		// quickSort(0, array.length - 1);
		print();
	}
	private static void init() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			int n = Integer.parseInt(br.readLine());
			array = new int [n];
			temp = new int [n];
			for(int i = 0; i < n; i++) {
				array[i] = Integer.parseInt(br.readLine());
			}
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	private static void print() {
		StringBuilder sb = new StringBuilder();
		for(int num : array) {
			sb.append(num).append("\n");
		}
		System.out.println(sb);
	}
	// Bubble Sort: O(N^2)
	private static void bubbleSort() {
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array.length - i - 1; j++) {
				if(array[j] > array[j + 1]) {
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
	}

	// Merge Sort: O(NlogN)
	private static void mergeSort(int start, int end) {
		if(end - start == 1) return;
		int mid = (start + end) / 2;
		mergeSort(start, mid);
		mergeSort(mid, end);
		mergeSortCore(start, mid, end);
	}
	private static void mergeSortCore(int start, int mid, int end) {
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

	// Quick Sort: O(NlogN) ~ O(N^2)
	private static void quickSort(int start, int end) {
		if(start < end) {
			int pivot = quickSortCore(start, end);
			quickSort(start, pivot - 1);
			quickSort(pivot + 1, end);
		}
	}
	private static int quickSortCore(int start, int end) {
		int pivot = array[start];
		int l = start + 1;
		int r = end;
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
