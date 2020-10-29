package com.allen.favorite.designmodel.singleton;


/**
 * 单列简单实现--懒汉模式
 * 
 * 单例三要素:
 * 1、私有的静态的成员变量
 * 2、私有的构造方法
 * 3、静态的工厂方法，供外部获取类的实列
 * 
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 * author:      allen
 * Createdate:  2019年3月12日下午5:32:04
 */
public class SingleTonOne {

	private static SingleTonOne instance;
	
	private SingleTonOne(){
		
	}
	
	public synchronized static SingleTonOne getInstatnce() {
		if (instance == null) {
			instance = new SingleTonOne();
		}
		return instance;
	}
	
	public static void main(String[] args) {
		

	}

}
