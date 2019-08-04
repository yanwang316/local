package com.norman.sort;

/**
 * a. 稳定性：相等的元素相对顺序不变，即稳定;反之，不稳定
 * b. 
 * @author norman
 *
 */
public class Sort {

	public static void main(String[] args) {
		int[] source = new int[] {3,1,0,9,4,7,2,0};
		printArray(source);
		// 插入排序
//		insertSortMethod(source);
		// 选择排序
//		selectSortMethod(source);
		// 冒泡排序
//		bubbleSortMethod(source);
		// 快速排序
//		quickSortMethod(source, 0, source.length - 1);
		// 排序后
		printArray(source);
	}
	
	public static void printArray(int[] array) {
		if (array == null || array.length == 0) {
			System.out.println("[]");
		}
		System.out.print("[");
		for (int i : array) {
			System.out.print(i + " ");
		}
		System.out.println("]");
	}

	/**
	 * 插入排序（稳定）
	 * 	a. 数据元素[0,i-1]有序
	 * 	b. 将元素[i]插入到数组[0,i-1]的合适位置
	 * @param source
	 * @return
	 */
	public static void insertSortMethod(int[] source) {
		// 取出要插入的
		int i,j,v = 0;
		for (i = 1; i < source.length; i++) {
			v = source[i];
			for (j = i-1; j >= 0 && source[j] > v; j--) {
				source[j + 1] = source[j];
			}
			source[j+1] = v;
		}
	}
	
	/**
	 * 选择排序（不稳定）
	 * 	第i次循环
	 * 		1. 找到最小值
	 * 		2. 将它同地i个元素交换
	 * @param source
	 */
	public static void selectSortMethod(int[] source) {
		int pos,j;
		for (int i = 0; i < source.length; i++) {
			for (j = i+1, pos = i; j < source.length; j++) {
				if (source[pos] > source[j]) {
					pos = j;
				}
			}
			if (pos != i) {
				swap(source, i, pos);
			}
		}
	}
	
	/**
	 * 冒泡排序
	 * 	第i次循环
	 * 		1. 将数组中最大值移动到数组末尾
	 * 		2. 下次循环为元素为[0,n-i-1]，其中i为第i次循环
	 * @param source
	 */
	public static void bubbleSortMethod(int[] source) {
		for (int i = 0; i < source.length - 1; i++) {
			
			for (int j = 0; j < source.length - 1 - i; j++) {
				if (source[j] > source[j+1]) {
					swap(source, j, j + 1);
				}
			}
		}
	}
	
	/**
	 * 快速排序（不稳定）
	 * 	确定基准数在数组中的位置
	 * 		1. 比基准数小的在左边
	 * 		2. 比基准数大的在右边
	 * 	基准数在左右来回跃迁
	 * @param source
	 */
	public static void quickSortMethod(int[] source,int left, int right) {
		int start = left;
		int end = right;
		int pivot = source[left];
		while( start < end) {
			// 从右边找小于pivot的数
			while(start < end && source[end] > pivot) end--;
			// 从左边找大于pivot的数
			while(start < end && source[start] < pivot) start++;
			if (source[start] == source[end]) {
				start++;
			}else {
				swap(source, start, end);
			}
		}
		// 递归（缺陷，如果递归的次数太多，会导致线程栈溢出）
		if (start - 1 > left) quickSortMethod(source, left, start -1);
		if (end + 1 < right) quickSortMethod(source, end + 1, right);
	}
	
	public static void heapSortMethod(int[] source) {
		
	}
	
	public static void adjustHeap(int[] source, int i, int length) {
		
	}
	
	/**
	 * 交换元素
	 * @param source -- 数组
	 * @param i -- 元素下表i
	 * @param j -- 元素下表j
	 */
	public static void swap(int[] source, int i, int j) {
		int temp = source[i];
		source[i] = source[j];
		source[j] = temp;
	}
}
