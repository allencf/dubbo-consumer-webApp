package com.allen.favorite.designmodel.proxy.staticproxy;


/**
 * 
 * 网络对象的静态代理类 -- 静态代理
 * 优点：
 * 1、可以做到不修改目标对象的情况下，通过代理对目标对象做扩展
 * 缺点:
 * 1、代理类和接口实现类一样需要实现接口方法、类会非常多
 * 2、如果接口方法有增加，实现类和代理类都需要改动，增加了维护成本
 * 
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 * author:      allen
 * Createdate:  2019年3月13日上午11:27:00
 */
public class StaticProxyFactory implements Network {

	private Network network;
	
	public StaticProxyFactory(Network network) {
		this.network = network;
	}
	
	@Override
	public void browse() {
		System.out.println("使用代理类访问代理目标!");
		network.browse();
	}

	@Override
	public void close() {
		System.out.println("使用代理类访问代理目标!");
		network.close();
	}
	
	

}
