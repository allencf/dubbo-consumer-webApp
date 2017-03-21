package com.allen.test.redis;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.lang.StringUtils;
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
				//sendCountToRedis();
				//logger.info("已成功购买数:{}",getCountToRedis());
				
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
	 */
	public static void testLockSecKill(){
		int threadCount = 100;
		Thread[] treads = new Thread[threadCount];
		CountDownLatch latch = new CountDownLatch(threadCount);
		String commondityId = "100001";
		for (Thread thread : treads) {
			thread = new Thread(new Runnable() {
				@Override
				public void run() {
					RedisCacheLock lock = null; 
					//调用代码(模拟redis分布式锁)
					try {
						lock = new RedisCacheLock("seckill:commontityId:100001", null, 1, 1);
			            if(lock.lock()) {
			               //需要加锁的代码
			               reduceInventory(commondityId);
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
			           latch.countDown();
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
	}
	
	
	public static void testSecKill() {
		String commondityId = "100001";
		int threadCount = 500;
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
	
	
	@SuppressWarnings("static-access")
	public static void sendCountToRedis(){
		RedisClient redisClient = RedisClient.getRedisClient();
		String count = redisClient.getString("secKill:count:test");
		Integer sellCount = 1;
		if(StringUtils.isNotBlank(count)) {
			sellCount = Integer.parseInt(count) + 1;
		}
		redisClient.setStr("secKill:count:test", String.valueOf(sellCount));
	}
	
	
	@SuppressWarnings("static-access")
	public static String getCountToRedis(){
		RedisClient redisClient = RedisClient.getRedisClient();
		return redisClient.getString("secKill:count:test");
	}
	
	
	public static void main(String[] args) {
		//testSecKill();
		testLockSecKill();
	}

}
