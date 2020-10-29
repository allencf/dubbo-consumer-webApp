package com.allen.favorite.designmodel.singleton;


/**
 * 
 * 单例简单实现 -- 优化版
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 * author:      allen
 * Createdate:  2019年3月12日下午5:36:52
 */
public class SingleTonThree {

	private static SingleTonThree instance = new SingleTonThree();
	
	private SingleTonThree() {
		
	}
	
	public static SingleTonThree getInstance() {
		if (instance == null) {
			synchronized (SingleTonThree.class){
				if (instance == null) {
					instance = new SingleTonThree();
				}
			}
		}
		return instance;
	}
	
	public static void main(String[] args) {
		

	}

}
