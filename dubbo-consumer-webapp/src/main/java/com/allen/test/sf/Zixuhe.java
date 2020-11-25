package com.allen.test.sf;

import org.apache.commons.lang3.ArrayUtils;

public class Zixuhe {
	
	
	public static int subLation(int[] array) {
		if (ArrayUtils.isEmpty(array)) {
			return 0;
		}
		
		int currentSum = array[0];
		int maxSum = 0;
		for (int i = 1; i < array.length; i++) {
			currentSum = Math.max(array[i], currentSum + array[i]);
			maxSum = Math.max(maxSum, currentSum);
		}
		return maxSum;
	}
	
	public static void main(String[] args) {
		int[] array = {-3,1,2,-2,4,-5,3};
		System.out.println(subLation(array));
	}

}
