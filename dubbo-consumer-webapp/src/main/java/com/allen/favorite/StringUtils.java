package com.allen.favorite;

/**
 * String 相关
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 * author:      allen
 * Createdate:  2019年3月2日上午10:14:05
 */
public class StringUtils {
	
	/**
	 * String、StringBuilder、StringBuffer三者的主要区别
	 * 1、从运行速度上分析 StringBuilder>StringBuffer>String
	 * 2、从线程安全分析  StringBuffer是线程安全的 、
	 *    StringBuilder和String是线程非安全的
	 * 3、String是字符串常量、需要不停的创建对象来给赋值，所以执行
	 *    效率较差
	 *    StringBuffer和StringBuilder是字符串变量,可以在原值的基
	 *    础之上修改，所以执行效率较高
	 */
	
	
	public void String(){
		String str = "abc";
		str = str + "bcd";
		//实际上是创建了2个对象，先创建了一个对象abc，然后再创建一个新的对象把abc和bdc
		//赋值给新的对象，同事GC回收了老的str对象
		
		@SuppressWarnings("unused")
		String s = "abc" + "bcd";
		//这个实际上只创建了一个对象
		
		String a1 = "abc";
		String b1 = "bcd";
		String c1 = "def";
		@SuppressWarnings("unused")
		String abc = a1 + b1 + c1;
		//实际上创建了4个对象
	}
	
	
	public void StringBuffer() {
		
	}
	
	
	public void StringBuilder() {
		
	}

}
