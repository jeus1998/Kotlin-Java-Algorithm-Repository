package interview.tree;

/**
 * 백준 1991 - 트리 순회 <br>
 * - 링크: https://www.acmicpc.net/problem/1991 <br>
 * - 2025-09-27 <br>
 * - 이진트리의 전위 순회, 중위 순회, 후위 순회 결과를 출력하는 문제
 */
import java.io.*;
import java.util.*;
public class BOJ1991 {
	static final int [][] binaryTree = new int [26][2];
	static final StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		init();
		preOrder(0);
		sb.append("\n");
		inOrder(0);
		sb.append("\n");
		postOrder(0);
		System.out.println(sb);
	}
	private static void init() {
		for (int i = 0; i < 26; i++) {
			Arrays.fill(binaryTree[i], -1);
		}
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			int n = Integer.parseInt(br.readLine());
			while(n --> 0) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int parent = st.nextToken().charAt(0) - 'A';
				int inputLeft = st.nextToken().charAt(0);
				int inputRight = st.nextToken().charAt(0);
				binaryTree[parent][0] = inputLeft == '.' ? -1 : inputLeft - 'A';
				binaryTree[parent][1] = inputRight == '.' ? -1 : inputRight - 'A';
			}
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	private static void preOrder(int node) {
		if(node == -1) return;
		sb.append((char)(node + 'A'));
		preOrder(binaryTree[node][0]);
		preOrder(binaryTree[node][1]);
	}
	private static void inOrder(int node) {
		if(node == -1) return;
		inOrder(binaryTree[node][0]);
		sb.append((char)(node + 'A'));
		inOrder(binaryTree[node][1]);
	}
	private static void postOrder(int node) {
		if(node == -1) return;
		postOrder(binaryTree[node][0]);
		postOrder(binaryTree[node][1]);
		sb.append((char)(node + 'A'));
	}
}
/*
입력
첫째 줄에 이진 트리 노드의 개수 N(1 ≤ N ≤ 26)이 주어진다.
둘째 줄부터 N개의 줄에 걸쳐 노드와 그의 왼쪽 자식, 오른쪽 자식이 주어진다.
자식 노드가 없는 경우에는 .으로 표현한다. 루트 노드는 항상 A이다.

출력
첫째 줄에 전위 순회한 결과, 둘째 줄에 중위 순회한 결과, 셋째 줄에 후위 순회한 결과를 출력한다.

즉, 노드는 A~Z까지 최대 26개, 자식 노드가 없으면 .으로 표현
이는 배열로 트리를 표현하기에 용이
index 0 = A
index 25 = Z

example
int [][] tree = new int [26][2];
tree[0][0] = 1;  // A의 왼쪽 자식 B
tree[0][1] = 2;  // A의 오른쪽 자식 C
 */
