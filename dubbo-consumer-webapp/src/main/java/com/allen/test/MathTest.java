package com.allen.test;

import java.io.Serializable;

/**
 * math相关的测试信息
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 * author:      cf
 * Createdate:  2017年3月7日下午5:17:25
 */
public class MathTest implements Serializable {

	private static final long serialVersionUID = -3819811631621822462L;

	/**
	 * 四舍五入取整
	 * @param d
	 */
	public static  void mathRound(double d){
		System.out.println(Math.round(d));
	}
	
	
	/**
	 * 返回大于等于的最小整数
	 * @param d
	 */
	public static void mathCeil(double d){
		System.out.println(Math.ceil(d));
	}
	
	
	/**
	 * 返回不大于的最大整数
	 * @param d
	 */
	public static void mathFloor(double d){
		System.out.println(Math.floor(d));
	}
	
	public static void main(String[] args) {
		//mathRound(-3.6d);
		//mathFloor(-3.1d);
		//mathCeil(4.0);
		//d*Math.round(10.2);
		String a = "abc";
		String b = "def";
		String c = "abc" + "def";
		String d = a + b;
		String e = "abcdef";
		System.out.println(c == e);
	}

}
