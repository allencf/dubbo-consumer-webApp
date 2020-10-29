package com.allen.favorite.abstact.interfacetest;

import com.allen.favorite.abstact.InterfaceOne;

public class InterfaceOneImpl implements InterfaceOne {

	/**
	 * 接口:
	 * 1、不能够被实例化
	 * 2、只能有抽象方法和不可变常量
	 * 3、实现类必须实现其所有的抽象方法
	 */
	
	
	@Override
	public void testOne(String str) {
		// TODO Auto-generated method stub
	}

	@Override
	public void testTwo(String str, Integer i) {
		// TODO Auto-generated method stub
	}
	
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		InterfaceOneImpl test = new InterfaceOneImpl();
		System.out.println(test.str);
		
	}

}
