package com.allen.test.sf;

import org.apache.commons.lang3.ArrayUtils;

public class FindNum {
	
	
	/**
	 * //评测题目: 
	   // 查找整数
       // 输入：一个有序数组array，一个整数n
       // 输出：如果n在array里，输出n的位置；如果n不在array中，输出n可以插入的位置，插入后的数组仍然有序
       // 例如：
       // • [1,3,5,6], 5 → 2
       // • [1,3,5,6], 2 → 1
       // • [1,3,5,6], 7 → 4
       // • [1,3,5,6], 0 → 0
	 */
	public static int findN(int[] array,int n) {
		if(ArrayUtils.isEmpty(array)) {
			return 0;
		}
		
		for (int i = 0; i < array.length; i++) {
			
			if (n <= array[i]) {
				return i +1;
			} 
			
			if (i == array.length -1 && n > array[i]) {
				return array.length + 1;
			}
		}
		return 0;
	}
	
	
	
	

	public static void main(String[] args) {
		int[] array = {1,3,6,9,11};
		System.out.println(findN(array, 0));
	}

}
