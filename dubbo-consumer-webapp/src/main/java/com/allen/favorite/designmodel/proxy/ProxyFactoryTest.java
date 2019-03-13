package com.allen.favorite.designmodel.proxy;

import com.allen.favorite.designmodel.proxy.staticproxy.NetworkAip;
import com.allen.favorite.designmodel.proxy.staticproxy.StaticProxyFactory;

/**
 * 工厂的测试类
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 * author:      allen
 * Createdate:  2019年3月13日下午2:09:55
 */
public class ProxyFactoryTest {

	public static void main(String[] args) {
		
		//静态工厂
		NetworkAip newwork = new NetworkAip();
		StaticProxyFactory proxy = new StaticProxyFactory(newwork);
		proxy.browse();
		
		//动态工厂
		

	}

}
