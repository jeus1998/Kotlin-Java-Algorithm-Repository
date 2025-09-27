package interview.sort;

import java.util.*;
public final class Elements {
	private Elements() {}
	private static final int [] ARRAY = {3, 2, 7, 116, 62, 235, 1, 23, 55, 77};
	public static int [] copy() {
		return Arrays.copyOf(ARRAY, ARRAY.length);
	}
}
