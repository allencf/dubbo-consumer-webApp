package com.allen.favorite.designmodel.factory;

/**
 * 
 * 定义一个Dog的实现
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 * author:      allen
 * Createdate:  2019年3月12日下午6:06:53
 */
public class DogApiImpl implements AnimalApi {

	@Override
	public String say() {
		return "hi , this is Dog !";
	}
	
	

}
