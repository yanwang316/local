package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import sort.support.BST;

/**
 * a. 稳定性：相等的元素相对顺序不变，即稳定;反之，不稳定
 * b. 
 * @author norman
 *
 */
public class CompareSort {

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
		// 堆排序
//		heapSortMethod(source);
		// 希尔排序
//		shellSort(source);
		// 归并排序
//		source = mergeSort(source, 0, source.length - 1);
		// 二叉排序算法
//		source = binaryTreeSort(source);
		// 计数排序
//		countSort(source);
		// 桶排序 
		List<Integer> result = bucketSort(Arrays.stream(source).boxed().collect(Collectors.toList()), 3);
		System.out.println(result);
		// 排序后	
//		printArray(source);
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
	
	/**
	 * 堆排序
	 * 	1. 构架初始最大堆
	 * 	2. 每次将source[0]与末尾的元素交换，并调整以末尾元素前一个为界堆
	 * @param source
	 */
	public static void heapSortMethod(int[] source) {
		if (source == null || source.length == 0) {
			return;
		}
		// 构建初始大堆
		buildMaxHeap(source);
		// 堆顶与最后一个元素交换，并调整
		for (int i = source.length - 1; i >= 0; i--) {
			swap(source, 0, i);
			adjustHeap(source, 0, i);
		}
	}
	
	private static void buildMaxHeap(int[] source) {
		for (int i = source.length / 2 - 1; i >= 0; i--) {
			adjustHeap(source, i, source.length);
		}
	}
	
	private static void adjustHeap(int[] source, int i, int length) {
		// 在父节点，左子节点，右子节点中找最大值
		// 大顶下标
		int maxIndex = i;
		// 左子树2×i+1
		// 如果左子树存在，并大于前最大值节点，设置最大值节点为左子树
		if (i * 2 + 1 < length && source[maxIndex] < source[2 * i + 1] )
			maxIndex = i * 2 + 1;
		// 如果右子树存在，并大于前最大值节点，设置最大值节点为右子树
		if (i * 2 + 2 < length && source[maxIndex] < source[2 * i + 2] )
			maxIndex = i * 2 + 2;
		// 右子树2×i+2
		if (maxIndex != i) {
			swap(source, i, maxIndex);
			// 调整交换的节点的子节点
			adjustHeap(source, maxIndex, length);
		}
	}
	
	/**
	 * 希尔排序
	 * 	1. 排序增量选择希尔增量
	 * @param source
	 */
	public static void shellSort(int[] source) {
		int len = source.length;
		int temp, gap = len / 2;
		while (gap > 0) {
			// 多序列排序
			for (int i = gap; i < len; i++) {
				temp = source[i];
				int preIndex = i - gap;
				while (preIndex >= 0 && source[preIndex] > temp) {
					source[preIndex + gap] = source[preIndex];
					preIndex -= gap;
				}
				source[preIndex + gap] = temp;
			}
			gap /= 2;
		}
	}
	
	/**
	 * 归并排序
	 * 1. 二分法分割数组
	 * 2. 合并并排序两个数组
	 * @param source
	 */
	public static int[] mergeSort(int[] source, int start, int end) {
		// 将数组分成两部分
		if (start == end) {
			return new int[] {source[start]};
		}
		// 算中间索引值
		int mid = start + (end - start) / 2;
		int[] leftPart = mergeSort(source, start, mid);
		int[] rightPart = mergeSort(source, mid + 1, end);
		// 合并两个有序数据
		return mergeTwoSortedArray(leftPart, rightPart);
	}
	
	private static int[] mergeTwoSortedArray(int[] a, int[] b) {
		int aIndex = 0, bIndex = 0, sIndex = 0;
		int[] num = new int[a.length + b.length];
		// 此时子两个序列中合并
		while(aIndex < a.length && bIndex < b.length)
			num[sIndex++] = a[aIndex]< b[bIndex] ? a[aIndex++] : b[bIndex++];
		// 若其中有一个序列合并完成
		while(aIndex < a.length)
			num[sIndex++] = a[aIndex++];
		while (bIndex < b.length) 
			num[sIndex++] = b[bIndex++];
		return num;
	}
	
	/**
	 * 二叉树排序
	 * @param source
	 */
	public static int[] binaryTreeSort(int[] source) {
		BST bst = new BST();
		for (int i : source) {
			bst.add(i);
		}
		return bst.collect();
	}
	
	/**
	 * 计数排序
	 * 	1. 适合排序有限长度的整数
	 * @param source
	 */
	public static void countSort(int[] source) {
		// 找到最大值与最小值
		int max = source[0],min = source[0];
		for (int i = 0; i < source.length; i++) {
			if (max < source[i]) 
				max = source[i];
			if (min > source[i])
				min = source[i];
		}
		int bias = 0 - min;
		int[] bucket = new int[max - min + 1];
		// 构建计数数组
		for (int i = 0; i < source.length; i++) {
			bucket[source[i] + bias]++;
		}
		// 根据计数数组生成排序序列
		int index = 0, bucketIndex = 0;
		while (index < source.length) {
			if (bucket[bucketIndex] != 0) {
				source[index++] = bucketIndex - bias;
				bucket[bucketIndex]--;
			}else {
				bucketIndex++;
			}
		}
	}
	
	/**
	 * 桶排序
	 * @param source
	 */
	public static List<Integer> bucketSort(List<Integer> source, int bucketSize) {
		// 递归返回条件
		if (source == null || source.size() < 2 || bucketSize == 0) {
			return source;
		}
		// 找到最大值与最小值
		int max = source.get(0),min = source.get(0);
		for (Integer integer : source) {
			if (max < integer) 
				max = integer;
			if (min > integer)
				min = integer;
		}
		// 确定桶的数量
		int bucketCount = (max - min) / bucketSize + 1;
		
		// 构建桶排序数据结构
		List<List<Integer>> bucketStruct = new ArrayList<List<Integer>>();
		// 初始化桶数据结构
		for (int i = 0; i < bucketCount; i++) {
			bucketStruct.add(new ArrayList<Integer>());
		}
		// 数据入桶
		for (int i = 0; i < source.size(); i++) {
			bucketStruct.get((source.get(i) - min) / bucketSize).add(source.get(i));
		}
		// 数据出桶

		List<Integer> bucket = null;
		List<Integer> result = new ArrayList<Integer>();
		for (List<Integer> list : bucketStruct) {
			if (bucketCount == 1) {
				bucketSize--;
			}
			//排序桶内数据排序
			bucket = bucketSort(list, bucketSize);
			for (Integer integer : bucket) {
				result.add(integer);
			}
		}
		return result;
	}
	
	/**
	 * 交换元素
	 * @param source -- 数组
	 * @param i -- 元素下表i
	 * @param j -- 元素下表j
	 */
	private static void swap(int[] source, int i, int j) {
		int temp = source[i];
		source[i] = source[j];
		source[j] = temp;
	}
}
