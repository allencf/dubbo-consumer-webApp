package com.allen.favorite.designmodel.factory;

/**
 * 
 * 定义一个cat的接口实现
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 * author:      allen
 * Createdate:  2019年3月12日下午6:05:37
 */
public class CatApiImpl implements AnimalApi {

	@Override
	public String say() {
		return "hi , this is cat !";
	}
	
	

}
