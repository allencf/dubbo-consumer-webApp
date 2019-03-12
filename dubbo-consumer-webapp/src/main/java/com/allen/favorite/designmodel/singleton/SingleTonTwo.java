package com.allen.favorite.designmodel.singleton;


/**
 * 
 * 单例简单实现 -- 饿汉模式
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 * author:      cf
 * Createdate:  2019年3月12日下午5:36:52
 */
public class SingleTonTwo {

	private static SingleTonTwo instance = new SingleTonTwo();
	
	private SingleTonTwo() {
		
	}
	
	public static SingleTonTwo getInstance() {
		if (instance == null) {
			instance = new SingleTonTwo();
		}
		return instance;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
