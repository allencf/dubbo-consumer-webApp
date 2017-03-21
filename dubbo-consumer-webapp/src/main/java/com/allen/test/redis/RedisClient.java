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
	 * jedis对象
	 */
	private static Jedis jedis;
	
	/**
	 * 最大连接数(可用连接实列的最大数目,为负值时没有限制)
	 */
	private static Integer maxActive = 2000;
	
	/**
	 * 最大空闲数(空闲连接实列的最大数目,为负值时没有限制)
	 */
	private static Integer maxIdle = 20;
	
	/**
	 * 超时时间
	 */
	private static Integer maxWait = 5000;
	
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
		if(redisClient == null) {
			redisClient = new RedisClient();
		}
		return redisClient;
	}
	
	
	
	/**
	 * 获取jedis对象
	 * @return
	 */
	public static Jedis getJedis(){
		if(jedisPoolConfig == null){
			jedisPoolConfig = new JedisPoolConfig();
			jedisPoolConfig.setMaxActive(maxActive);
			jedisPoolConfig.setMaxIdle(maxIdle);
			jedisPoolConfig.setMaxWait(maxWait);
		}
		jedisPool = new JedisPool(jedisPoolConfig, hostIp, port , maxWait);
		long startTime = System.currentTimeMillis();
		try {
			jedis = jedisPool.getResource();
		} catch (Exception e) {
			logger.error("获取jedis异常:",e);
		}
		long endTime = System.currentTimeMillis();
		long time = endTime - startTime;
		logger.info("获取jedis时间:{}ms",time);
		return jedis;
	}
	
	
	public static void set(String key,Object value){
		try{
			Jedis jedis = getJedis();
			jedis.set(key.getBytes(), serialize(value));
			logger.info("操作成功!!!");
		}
		catch (Exception e) {
			logger.error("操作失败,异常信息:"+e.getMessage(),e);
		}
		finally{
			jedisPool.returnResource(jedis);
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
			long result = getJedis().setnx(key, value);
			if(result == 1)
				return true;
			else
				return false;
		} catch (Exception e) {
			logger.info("操作失败" , e);
			return false;
		} finally {
			if(jedis != null){
				jedisPool.returnResource(jedis);
			}
		}
	}
	
	public Long setnx(byte[] key,byte[] value){
		return getJedis().setnx(key, value);
	}
	
	public Long delKey(String key){
		return getJedis().del(key);
	}
	
	public Long delKey(byte[] key){
		return getJedis().del(key);
	}
	
	public static String setStr(String key,String value){
		logger.info("");
		return getJedis().set(key, value);
	}
	
	public static String getString(String key){
		Jedis jedis = null;
		try {
			jedis =  getJedis();
			return getJedis().get(key);
		} catch (Exception e) {
			logger.info("操作Jedis异常",e);
			return null;
		} finally {
			if(jedis != null){
				jedisPool.returnResource(jedis);
			}
		}
	}
	
	
	/**
	 * set key 返回旧值
	 * @param key
	 * @param value
	 * @return
	 */
	public static String getSet(String key,String value){
		return getJedis().getSet(key, value);
	}
	

	public static Object get(String key){
		//Jedis jedis = getJedis();
		return null;
	}
	
	/*104      * 根据key 获取内容
	105      *
	106      * @param key
	107      * @return
	108      
	109     public static Object get(String key) {
	110 
	111         Jedis jedis = null;
	112         try {
	113             jedis = jedisPool.getResource();
	114             byte[] value = jedis.get(key.getBytes());
	115             return SerializeUtil.unserialize(value);
	116         } catch (Exception e) {
	117             e.printStackTrace();
	118             return false;
	119         } finally {
	120             jedisPool.returnResource(jedis);
	121         }
	122     }
	*/
	
	
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
		setnx("SECKILL:commontityId:100001", "12");
		
	}

}
