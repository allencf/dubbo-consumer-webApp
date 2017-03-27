package com.allen.test.DataStructure.ListImpl;

/**
 * 模拟ArrayList的实现原理(基于数组实现)
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 * author:      cf
 * Createdate:  2017年3月27日下午5:24:39
 */
public class ArrayListImpl {

	//定义一个默认长度为10的数组
	private static Object[] array = new Object[5];
	
	//定义一个计数器，用于计算数组中元素的个数
	private int count;
	
	//添加元素
	public void addVal(Object obj) {
		//当数组满时重新定义一个新的数组
		if(count == array.length) {
			//重新定义一个新的数组,数组长度是原来的2倍(这个分配有点不合理)
			Object[] newArray = new Object[array.length*2];
			//将原数组中的数据加入到新数组中,再将要加的元素添加到新数组中
			System.arraycopy(array, 0, newArray, 0, array.length);
			array = newArray;
		}
		//将需要添加的元素放到数组中
		array[count] = obj;
		count ++ ;
	}
	
	
	public void getList(){
		for (Object obj : array) {
			if(obj != null)
			System.out.println(obj);
		}
	}
	
	
	
	
	/**
	 * 返回计数器的值
	 * @return
	 */
	public int size(){
		return count;
	}
	
	public static void main(String[] args) {
		/*String[] src = {"1","3","5","6"};
		for (String i : src) {
			System.out.println(i);
		}
		System.out.println("src length :" + src.length);
		
		String[] newSrc = new String[10];
		System.arraycopy(src, 0, newSrc, 0, src.length);
		for (String str : newSrc) {
			System.out.println(str);
		}
		System.out.println("newSrc length:" + newSrc.length);*/
		ArrayListImpl list = new ArrayListImpl();
		list.addVal(1);
		list.addVal(2);
		list.addVal(5);
		list.addVal(4);
		list.addVal(5);
		list.addVal(6);
		
		list.getList();
		
	}
	
}

