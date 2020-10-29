package com.allen.favorite.designmodel.proxy.cglibproxy;

import java.lang.reflect.Method;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;


/**
 * 基于cglib(子类代理)实现的一个子类代理工厂
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 * author:      cf
 * Createdate:  2019年3月13日下午3:23:41
 */
public class CglibProxyFactory implements MethodInterceptor{

	private Object target;
	
	public CglibProxyFactory(Object target) {
		this.target = target;
	}  
	
	public Object getProxyInstance() {
		//1、工具类
		Enhancer en = new Enhancer();
		//2、设置父类
		en.setSuperclass(target.getClass());
		//3、设置回调函数
		en.setCallback(this);
		//4、创建子类代理对象
		return en.create();
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("执行cglib代理方法!");
		Object result = method.invoke(target, args);
		System.out.println(method.getName());
		return result;
	}

	public static void main(String[] args) {
		

	}

}
