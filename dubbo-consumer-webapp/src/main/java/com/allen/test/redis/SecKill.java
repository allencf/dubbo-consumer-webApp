package com.allen.test.redis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
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
	private static Map<String, Integer> inventory = new HashMap<>();
	
	
	private static Integer sellCount = 0;
	
	private static Integer noLockCount = 0;
	
	private static Integer getLockCount = 0;
	
	
	/**
	 * 初始化赋默认值
	 */
	static {
		inventory.put("100001", 100);
		inventory.put("100002", 5000);
	}
	
	
	/**
	 * 模拟减商品库存的一个方法
	 * @param commodityId
	 * @return true、成功  false、失败
	 */
	public static boolean reduceInventory(String commodityId){
		Integer inventoryCount = inventory.get(commodityId);
		boolean flag = true;
		try {
			if(inventoryCount > 0) {
				inventory.put(commodityId, inventory.get(commodityId)-1);
				sellCount += 1;
				
			} else {
				if(inventoryCount < 0){
					Integer count = inventory.get(commodityId);
					logger.info(Thread.currentThread().getName() + "剩余商品数：{},",count);
				}
				flag = false;
			}
		} catch (Exception e) {
			logger.error("线程异常",e);
			
		}
		return flag;
	}
	
	
	/**
	 * 测试加锁模拟秒杀场景
	 * 开启1000个线程模拟秒杀
	 * @param threadCount 需要模拟的线程数(默认100个线程)
	 */
	public static void testLockSecKill(Integer threadCount){
		if (threadCount == null) {
			threadCount = 100;
		}
		Thread[] treads = new Thread[threadCount];
		CountDownLatch latch = new CountDownLatch(threadCount);
		String commondityId = "100001";
		RedisCacheLock lock = new RedisCacheLock("seckill:commontityId:100001", RedisClient.getRedisClient(), 1, 1);
		for (Thread thread : treads) {
			thread = new Thread(new Runnable() {
				@Override
				public void run() {
					//调用代码(模拟redis分布式锁)
					try {
			            if(lock.lock()) { //获得锁
			              Integer goodsCount = inventory.get(commondityId);
			              if(goodsCount >0 ){
			            	  getLockCount += 1;
				              //需要加锁的代码
				              reduceInventory(commondityId);
			              } else {
			            	  noLockCount += 1;
			              }
			            } else {
			               //等待获取锁后重新操作
			            	recursion(lock, commondityId);
			            }
					}
					catch (InterruptedException e) {
				        logger.error("执行操作异常",e);
						//e.printStackTrace();
				    }
					finally {
			           //为了让分布式锁的算法更稳键些，持有锁的客户端在解锁之前应该再检查一次自己的锁是否已经超时，再去做DEL操作，因为可能客户端因为某个耗时的操作而挂起，
			           //操作完的时候锁因为超时已经被别人获得，这时就不必解锁了。 ————这里没有做
			           if(lock != null){
			        	   lock.unlock();
			           }
				   }
				   latch.countDown();
				}
			});
			thread.start();
		}
		try {
			latch.await();
		} catch (Exception e) {
			logger.error("countDownLatch 异常:",e);
			//e.printStackTrace();
		}
		logger.info("goods count ------------:{}",inventory.get(commondityId));
		logger.info("sellCount --------:{}",sellCount);
		logger.info("noLockCount ------:{}",noLockCount);
		logger.info("getLockCount -----:{}",getLockCount);
	}
	
	private static void recursion(RedisCacheLock lock,String commondityId){
		try {
			if(!lock.lock()) {
				recursion(lock , commondityId);
			} else {
				Integer count = inventory.get(commondityId);
				if(count == 0 ){
					//logger.info("goods count is 0");
					noLockCount += 1;
					return;
				}
				//需要加锁的代码
				getLockCount += 1;
	            reduceInventory(commondityId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void testSecKill(Integer threadCount) {
		String commondityId = "100001";
		CountDownLatch latch = new CountDownLatch(threadCount);
		Thread[] treads = new Thread[threadCount];
		for (Thread thread : treads) {
			thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						//Thread.sleep(1000*2);
					} catch (Exception e) {
						e.printStackTrace();
					}
					boolean flag = reduceInventory(commondityId);
		            if(flag){
		            	//System.out.println("操作成功!!!");
		            }
		            latch.countDown();
				}
			});
			thread.start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("count ------------- : {}" , inventory.get(commondityId));
		logger.info("sellCount --------- : {} " , sellCount);
	}
	
	
	
	static boolean flag = true;
	
	public static void testLock(){
		Object obj = new Object();
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (obj) {
					if(!flag){
						obj.notify();
					}
					try {
						Thread.sleep(2000);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.print("abc");
					flag = false;
				}
			}
		});
		
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (obj) {
					try {
						if(flag) {
							flag = false;
							obj.wait();
						}
						System.out.print("def");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread1.start();
		thread2.start();
	}
	
	
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		//testSecKill();
		testLockSecKill(1000);
		//testLock();
		long endTime = System.currentTimeMillis();
		long time = endTime - startTime;
		logger.info("execute time :{}ms",time);
	}

}
