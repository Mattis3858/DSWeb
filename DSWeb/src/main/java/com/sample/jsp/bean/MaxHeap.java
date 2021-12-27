package com.sample.jsp.bean;
import java.util.ArrayList;
public class MaxHeap {

	private static int N;

	    public static void sort(ArrayList<WebNode> root) {
	        // build the heap
	        heapify(root);

	        for (int i = N; i > 0; i--) {
	            
	            swap(root, 0, i);
	            N = N - 1;
	            maxheap(root, 0);
	        }
	    }

	    public static void heapify(ArrayList<WebNode> root) {
	        N = root.size() - 1;
	        for (int i = N / 2; i >= 0; i--)
	            maxheap(root, i);//bottom-up
	    }

	    public static void maxheap(ArrayList<WebNode> root, int i) {

	        int left = 2 * i; // left child of i which is 2i
	        int right = 2 * i + 1; // right child of i which is 2i + 1
	        int max = i;


	        if (left <= N && root.get(left).getScore() > root.get(i).getScore())
	            max = left;
	        if (right <= N && root.get(right).getScore() > root.get(max).getScore())
	            max = right;

	        if (max != i) {
	            swap(root, i, max);//���之���銝�撅斗��
	            maxheap(root, max);
	        }
	    }

	    public static void swap(ArrayList<WebNode> root, int i, int j) {
	        WebNode temp = root.get(i);
	        root.set(i, root.get(j));
	        root.set(j, temp);
	    }
/*
	    public static void main(String[] args) {
	        int[] array = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
	        sort(array);
	        System.out.println("Heap Sort: " + Arrays.toString(array));
	    }
*/
}
