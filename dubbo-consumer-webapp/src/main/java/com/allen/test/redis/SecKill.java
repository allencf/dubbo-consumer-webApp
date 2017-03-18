package com.allen.test.redis;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.allen.test.redis.reidsLock.RedisCacheLock;

/**
 * 模拟秒杀的一个测试类
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 * author:      cf
 * Createdate:  2017年3月18日下午4:39:17
 */
public class SecKill {

	private static final Logger logger = LoggerFactory.getLogger(SecKill.class);
	
	/**
	 * 模拟商品库存 key、商品id  value、商品数量
	 */
	static Map<String, Integer> inventory = new HashMap<>();
	
	
	/**
	 * 初始化赋默认值
	 */
	static {
		inventory.put("100001", 20);
		inventory.put("100002", 5000);
	}
	
	
	/**
	 * 模拟减商品库存的一个方法
	 * @param commodityId
	 * @return
	 */
	public static Integer reduceInventory(String commodityId){
		Integer inventoryCount = inventory.get(commodityId);
		if(inventoryCount > 0) {
			inventory.put(commodityId, inventory.get(commodityId)-1);
		} else {
			logger.info("商品已被抢完!!!");
		}
		return inventoryCount;
	}
	
	
	/**
	 * 测试加锁模拟秒杀场景
	 * 开启1000个线程模拟秒杀
	 */
	public static void testLockSecKill(){
		int threadCount = 1000;
		Thread[] treads = new Thread[threadCount];
		String commondityId = "100001";
		for (Thread thread : treads) {
			thread = new Thread(new Runnable() {
				@Override
				public void run() {
					RedisCacheLock lock = new RedisCacheLock("100001", null, 0, 0);
					//调用代码(模拟redis分布式锁)
					try {
			            if(lock.lock()) {
			               //需要加锁的代码
			               Integer count = reduceInventory(commondityId);
			               System.out.println(count);
			            }
				    } 
					catch (InterruptedException e) {
				        e.printStackTrace();
				    }
					finally {
			           //为了让分布式锁的算法更稳键些，持有锁的客户端在解锁之前应该再检查一次自己的锁是否已经超时，再去做DEL操作，因为可能客户端因为某个耗时的操作而挂起，
			           //操作完的时候锁因为超时已经被别人获得，这时就不必解锁了。 ————这里没有做
			           lock.unlock();
				   }
				}
			});
			thread.start();
		}
		
		Integer inventoryCount = inventory.get(commondityId);
		logger.info("剩余商品数量:{}" , inventoryCount);
	}
	
	
	public static void testSecKill() {
		String commondityId = "100001";
		int threadCount = 1000;
		Thread[] treads = new Thread[threadCount];
		for (Thread thread : treads) {
			thread = new Thread(new Runnable() {
				@Override
				public void run() {
					 try {
						Thread.sleep(1000*3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Integer count = reduceInventory(commondityId);
		            System.out.println(count);
				}
			});
			thread.start();
		}
		Integer inventoryCount = inventory.get(commondityId);
		logger.info("剩余商品数量:{}" , inventoryCount);
	}
	
	
	
	
	public static void main(String[] args) {
		testSecKill();

	}

}
