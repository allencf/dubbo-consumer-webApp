package com.allen.test.redis.reidsLock;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.allen.test.redis.RedisClient;

public class RedisCacheLock {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisCacheLock.class);
	
	private String lockKey;
	 
	private static final int DEFAULT_ACQUIRY_RESOLUTION_MILLIS = 100;
	
	//锁超时时间，防止线程在入锁以后，无限的执行等待
    private int expireMsecs = 60 * 1000;
    
    //锁等待时间，防止线程饥饿
    private int timeoutMsecs = 10 * 1000;

    private volatile boolean locked = false;
    
    //redisClient 操作Redis
    private RedisClient redisClient;

	
	/**
     * 加锁
     * 使用方式为：
     * lock();
     * try{
     *    executeMethod();
     * }finally{
     *   unlock();
     * }
     * @param timeout timeout的时间范围内轮询锁
     * @param expire 设置锁超时时间
     * @return 成功 or 失败
     */
   /* public boolean lock(long timeout,int expire){
        long nanoTime = System.nanoTime();
        timeout *= MILLI_NANO_TIME;
        try {
            //在timeout的时间范围内不断轮询锁
            while (System.nanoTime() - nanoTime < timeout) {
                //锁不存在的话，设置锁并设置锁过期时间，即加锁
                if (this.redisClient.setnx(this.key, LOCKED) == 1) {
                    this.redisClient.expire(key, expire);//设置锁过期时间是为了在没有释放
                    //锁的情况下锁过期后消失，不会造成永久阻塞
                    this.lock = true;
                    return this.lock;
                }
                System.out.println("出现锁等待");
                //短暂休眠，避免可能的活锁
                //Thread.sleep(3, Random.nextInt(30));
            } 
        } catch (Exception e) {
            throw new RuntimeException("locking error",e);
        }
        return false;
    }*/
	
	
    /**
     * 获得 lock.
     * 实现思路:主要是使用了redis的setnx命令,缓存了锁.
     * reids缓存的key是锁的key,所有的共享,value是锁的到期时间(注意:这里把过期时间放在value了,没有时间上设置其超时时间)
     * 执行过程:
     * 1.通过setnx尝试设置某个key的值,成功(当前没有这个锁)则返回,成功获得锁
     * 2.锁已经存在则获取锁的到期时间,和当前时间比较,超时的话,则设置新的值
     * @return true if lock is acquired, false acquire timeouted
     * @throws InterruptedException in case of thread interruption
     */
    @SuppressWarnings("static-access")
	public synchronized boolean lock() throws InterruptedException {
        int timeout = timeoutMsecs;
        while (timeout >= 0) {
            long expires = System.currentTimeMillis() + expireMsecs + 1;
            String expiresStr = String.valueOf(expires); //锁到期时间
            if (redisClient.setnx(lockKey, expiresStr)) {
                //lock acquired
                locked = true;
                return true;
            }

            String currentValueStr = redisClient.getString(lockKey); //redis里的时间
            if (StringUtils.isNotBlank(currentValueStr) && Long.parseLong(currentValueStr) < System.currentTimeMillis()) {
                //判断是否为空，不为空的情况下，如果被其他线程设置了值，则第二个条件判断是过不去的
                // lock is expired
                String oldValueStr = redisClient.getSet(lockKey, expiresStr);
                //获取上一个锁到期时间，并设置现在的锁到期时间，
                //只有一个线程才能获取上一个线上的设置时间，因为jedis.getSet是同步的
                if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
                    //防止误删（覆盖，因为key是相同的）了他人的锁——这里达不到效果，这里值会被覆盖，但是因为什么相差了很少的时间，所以可以接受
                    //[分布式的情况下]:如过这个时候，多个线程恰好都到了这里，但是只有一个线程的设置值和当前值相同，他才有权利获取锁
                    // lock acquired
                    locked = true;
                    return true;
                }
            }
            timeout -= DEFAULT_ACQUIRY_RESOLUTION_MILLIS;
            logger.info("timeout : {}" , timeout);

            /**
                        延迟100 毫秒,  这里使用随机时间可能会好一点,可以防止饥饿进程的出现,即,当同时到达多个进程,
                       只会有一个进程获得锁,其他的都用同样的频率进行尝试,后面有来了一些进行,也以同样的频率申请锁,这将可能导致前面来的锁得不到满足.
                        使用随机的等待时间可以一定程度上保证公平性
            */
            Thread.sleep(DEFAULT_ACQUIRY_RESOLUTION_MILLIS);

        }
        return false;
    }
	
    
    /**
     * Acqurired lock release.
     */
    public synchronized void unlock() {
        if (locked) {
            redisClient.delKey(lockKey);
            locked = false;
        }
    }
    
    
    /*public  void unlock(String key) {
        try {
            if(this.locked){
                redisClient.delKey(key);//直接删除
            }
        } catch (Throwable e) {

        }
    }*/

    
    
    
    
    

	public RedisCacheLock(String lockKey,RedisClient redisClient,int timeoutMsecs,int expireMsecs){
		this.lockKey      = lockKey;
		this.redisClient  = redisClient;
		this.timeoutMsecs = timeoutMsecs;
		this.expireMsecs  = expireMsecs;
	}


	public static void main(String[] args) throws Exception{
		/*System.out.println(1000*1000);
		long time1 = System.nanoTime();
		Thread.sleep(1);
		long time2 = System.nanoTime();
		System.out.println((time2-time1));*/
		
		RedisClient redisClient = new RedisClient();
		int timeoutMsecs = 10000;
		int expireMsecs  = 20000;
		String key = null;
		
		//调用代码
		RedisCacheLock lock = new RedisCacheLock(key, redisClient , timeoutMsecs ,expireMsecs);
		try {
            if(lock.lock()) {
               //需要加锁的代码
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

}
