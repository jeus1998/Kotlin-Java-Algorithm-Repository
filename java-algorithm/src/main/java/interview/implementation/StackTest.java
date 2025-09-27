package interview.implementation;

/**
 * 라이브 코딩테스트 준비 - 스택 구현 <br>
 * 목표 <br>
 * - push(T item) : void <br>
 * - pop() : T <br>
 * - peek() : T <br>
 * - isEmpty() : boolean <br>
 * - size() : int <br>
 * - toString() : String <br>
 * comment <br>
 * - 해당 구현에서는 제네릭 타입 T를 사용하여 다양한 데이터 타입을 저장할 수 있도록 합니다. <br>
 * - 내부적으로 배열을 사용하여 스택을 구현하며, 필요에 따라 배열의 크기를 동적으로 조절할 수 있습니다. <br>
 */
import java.util.*;
class MyStack<T> {
	private int capacity;
	private int head;
	private T[] stackArray;

	@SuppressWarnings("unchecked")
	public MyStack(int initialCapacity) {
		this.stackArray = (T[]) new Object[initialCapacity];
		this.capacity = initialCapacity;
		this.head = 0;
	}
	public MyStack() {
		this(4);
	}
	public void push(T x) {
		if(head == capacity) {
			resize();
		}
		stackArray[head++] = x;
	}
	public T pop() {
		if(isEmpty()) {
			throw new NoSuchElementException("Stack is empty");
		}
		T item = stackArray[--head];
		stackArray[head] = null;      // 메모리 누수 방지
		return item;
	}
	public T peek() {
		if(isEmpty()) {
			throw new NoSuchElementException("Stack is empty");
		}
		return stackArray[head - 1];
	}
	public boolean isEmpty() {
		return head == 0;
	}
	@Override
	public String toString() {
		return Arrays.toString(Arrays.copyOf(stackArray, head));
	}
	// 동적 배열 확장 메서드
	private void resize() {
		capacity *= 2;
		stackArray = Arrays.copyOf(stackArray, capacity);
	}
}

public class StackTest {
	public static void main(String[] args) {
		test();
	}
	private static void test() {
		MyStack<Integer> stack = new MyStack<>();
		try {
			System.out.println(stack.isEmpty()); // true
			stack.push(1);
			System.out.println(stack.pop()); // 1
			System.out.println(stack.pop()); // Exception
		}
		catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
		// initial capacity 초과 push
		for (int i = 1; i <= 5; i++) {
			stack.push(i);
		}
		System.out.println(stack); // [1, 2, 3, 4, 5]
		System.out.println(stack.peek()); // 5
		System.out.println(stack.isEmpty()); // false
		while(!stack.isEmpty()) {
			System.out.print(stack.pop() + " "); // 5 4 3 2 1
		}
		try {
			System.out.println(stack.pop()); // Exception
		}
		catch (NoSuchElementException e) {
			System.out.println("\n" + e.getMessage());
		}
	}
}
