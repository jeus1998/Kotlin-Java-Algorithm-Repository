package interview.sort;

/**
 * 버블 정렬(Bubble Sort) <br>
 * - 시간 복잡도: O(N^2) <br>
 * - 인접한 두 원소를 비교하여 정렬하는 알고리즘
 */
public class BubbleSort {
	public static void main(String[] args) {
		int[] array = Elements.copy();
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array.length - i - 1; j++) {
				if(array[j] > array[j + 1]) {
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
		System.out.println(java.util.Arrays.toString(array));
	}
}
