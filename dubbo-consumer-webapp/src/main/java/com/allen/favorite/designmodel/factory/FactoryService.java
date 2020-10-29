package com.allen.favorite.designmodel.factory;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 定义一个简单的工厂实现
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 * author:      cf
 * Createdate:  2019年3月12日下午6:02:31
 */
public class FactoryService {

	public static String getAnimal(String animal) {
		if (StringUtils.isBlank(animal)) {
			return null;
		}
		if ("Dog".equalsIgnoreCase(animal)) {
			return new DogApiImpl().say();
		}
		if ("Cat".equalsIgnoreCase(animal)) {
			return new CatApiImpl().say();
		}
		return null;
	}
	
	
	public static void main(String[] args) {
		String result = FactoryService.getAnimal("Dog");
		System.out.println(result);
	}
}
