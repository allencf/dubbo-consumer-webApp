package com.allen.test.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * RedisClient 
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 * author:      cf
 * Createdate:  2017年3月18日上午10:22:30
 */
public class RedisClient {
	
private static final Logger logger = LoggerFactory.getLogger(RedisClient.class);
	
	private static RedisClient redisClient;

    private static JedisPoolConfig jedisPoolConfig;
	
	private static JedisPool jedisPool;
	
	
	/**
	 * 最大连接数(可用连接实列的最大数目,为负值时没有限制)
	 * 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；  
     * 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	 */
	private static Integer maxActive = 2000;
	
	/**
	 * 最大空闲数(空闲连接实列的最大数目,为负值时没有限制)
	 * 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。  
	 */
	private static Integer maxIdle = 20;
	
	/**
	 * 超时时间
	 * 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；  
	 */
	private static Integer maxWait = 5000;
	
	/**
	 * 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	 */
	private static Boolean isBorrow=true;
	
	/**
	 * 主机IP
	 */
	private static String hostIp = "10.33.3.225";
	
	/**
	 * 端口
	 */
	private static Integer port = 6379;
	
	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate;
	
	private RedisClient(){
		
	}
	
	public static RedisClient getRedisClient(){
		Object obj = new Object();
		if(redisClient == null) {
			synchronized (obj) {
				if(redisClient == null) {
					redisClient = new RedisClient();
				}
			}
		}
		return redisClient;
	}
	
	
	/**
	 * 获取jedis对象
	 * @return
	 */
	public static synchronized Jedis getJedis(){
		if(jedisPoolConfig == null){
			jedisPoolConfig = new JedisPoolConfig();
			jedisPoolConfig.setMaxTotal(maxActive);
			jedisPoolConfig.setMaxIdle(maxIdle);
			jedisPoolConfig.setMaxWaitMillis(maxWait);
			jedisPoolConfig.setTestOnBorrow(isBorrow);
		}
		if(jedisPool == null) {
			jedisPool = new JedisPool(jedisPoolConfig, hostIp, port , maxWait);
		}
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
		} catch (Exception e) {
			logger.error("获取jedis异常:",e);
		}
		return jedis;
	}
	
	
	public static void set(String key,Object value){
		Jedis jedis = null;
		try{
			jedis = getJedis();
			jedis.set(key.getBytes(), serialize(value));
			logger.info("操作成功!!!");
		}
		catch (Exception e) {
			logger.error("操作失败,异常信息:"+e.getMessage(),e);
		}
		finally{
			closeResource(jedis);
		}
	} 
	
	
	@SuppressWarnings("unchecked")
	public String saveTestBean(TestBean1 test){
		return (String) redisTemplate.execute(new RedisCallback<String>(){
		
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] key = getKey(String.valueOf(test.getId()));
				byte[] value = com.alibaba.fastjson.JSON.toJSONString(test).getBytes();
				connection.set(key, value);
				return null;
			}
	   });
	}

	
	public static Long expire(String key,int expireTime){
		return getJedis().expire(key, expireTime);
	}
	
	
	public static Long expire(byte[] key,int expireTime){
		return getJedis().expire(key, expireTime);
 	}
	
	
	/**
	 * 
	 * @param key
	 * @param value
	 * @return true、success  、fail
	 */
	public static boolean setnx(String key,String value){
		Jedis jedis = null;
		try {
			jedis = getJedis();
			long result = jedis.setnx(key, value);
			if(result == 1)
				return true;
			else
				return false;
		} catch (Exception e) {
			logger.info("操作失败" , e);
			return false;
		} finally {
			if(jedis != null){
				closeResource(jedis);
			}
		}
	}
	
	public Long setnx(byte[] key,byte[] value){
		return getJedis().setnx(key, value);
	}
	
	public Long delKey(String key){
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.del(key);
		} catch (Exception e) {
			logger.error("Redis delKey 异常",e);
			return null;
		} finally {
			if(jedis != null) {
				closeResource(jedis);
			}
		}
	}
	
	
	public static String getString(String key){
		Jedis jedis = null;
		try {
			jedis =  getJedis();
			return jedis.get(key);
		} catch (Exception e) {
			logger.info("操作Jedis异常",e);
			return null;
		} finally {
			if(jedis != null){
				closeResource(jedis);
			}
		}
	}
	
	public static void closeResource(Jedis jedis){
		if(jedis == null || jedisPool == null)
			return ;
		jedis.close(); 
		//jedisPool.returnResource(jedis);
		//jedisPool.returnBrokenResource(jedis);
		//jedisPool.destroy();
	}
	
	/**
	 * set key 返回旧值
	 * @param key
	 * @param value
	 * @return
	 */
	public static String getSet(String key,String value){
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.getSet(key, value);
		} catch (Exception e) {
			logger.error("Redis getSet异常" , e);
			return null;
		} finally {
			if(jedis != null) {
				closeResource(jedis);
			}
		}
	}
	

	
	/**
	 * 序列化
	 * @param object
	 * @return
	 */
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
         // 序列化
             baos = new ByteArrayOutputStream();
             oos = new ObjectOutputStream(baos);
             oos.writeObject(object);
             byte[] bytes = baos.toByteArray();
              return bytes;
          } catch (Exception e) {
  
         }
          return null;
    }
		  
    /**
     * 反序列化
     * @param bytes
     * @return
     */
    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
          // 反序列化
              bais = new ByteArrayInputStream(bytes);
              ObjectInputStream ois = new ObjectInputStream(bais);
              return ois.readObject();
          } catch (Exception e) {
  
          }
          return null;
    }
	
	public TestBean1 getTestBean(Integer id,String name){
		return TestBean1.getInstance(id, name);
	}
	
	
	public static byte[] getKey(String id){
		return ("HQYG:Allen:" + id).getBytes();
	}
    
    
	

	public static void main(String[] args) {
		//set("viney", "allen");
		//System.out.println(setStr("", "allen"));
		
		//System.out.println(setnx("allen1", "viney"));
		//System.out.println(getString("allen"));
		//System.out.println(getSet("allen12", "1"));
		//set("allen1", "alllen");
		//expire("allen1", 1000*10);
		//System.out.println(get("allen1"));
		//setStr("HQYG:ALLEN:aa", "aaa");
		//setnx("SECKILL:commontityId:100001", "12");
		
	}

}
