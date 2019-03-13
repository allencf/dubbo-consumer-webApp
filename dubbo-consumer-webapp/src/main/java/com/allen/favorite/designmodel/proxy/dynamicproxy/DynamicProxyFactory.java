package com.allen.favorite.designmodel.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理工厂类
 *
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 * author:      allen
 * Createdate:  2019年3月13日上午11:58:28
 */
public class DynamicProxyFactory {

	private Object target;
	
	public DynamicProxyFactory(Object target) {
		this.target = target;
	}
	
	public Object getProxyInstance () {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), 
			new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				Object resultObject = method.invoke(proxy, args);
				return resultObject;
			}
		});
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
