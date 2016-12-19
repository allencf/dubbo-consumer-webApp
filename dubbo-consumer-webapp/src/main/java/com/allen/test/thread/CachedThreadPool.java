package com.allen.test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {
	
	private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	
	private static ExecutorService fixCachedThreadPool = Executors.newFixedThreadPool(3);
	
	private static ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
	
	
	public static void cachedThreadPoolMethod(){
		for (int i = 1; i <= 5; i++) {
			RunnableThread1 thread = new RunnableThread1(i);
			//thread.run();
			
			//用CachedThreadPool启动线程
			cachedThreadPool.execute(thread);
			
			//Thread t = new Thread(thread);
			//t.start();
		}
		//任务执行完毕,关闭线程池
		cachedThreadPool.shutdown();
	}
	
	
	public static void fixCachedThreadPoolMethod(){
		for (int i = 1; i <= 10; i++) {
			RunnableThread1 thread = new RunnableThread1(i);
			fixCachedThreadPool.execute(thread);
		}
		fixCachedThreadPool.shutdown();
	}
	
	public static void singleThreadPoolMethod(){
		for (int i = 1; i <= 5; i++) {
			RunnableThread1 thread = new RunnableThread1(i);
			singleThreadPool.execute(thread);
		}
		singleThreadPool.shutdown();
	}
	
	
	public static void main(String[] args) {
		//cachedThreadPoolMethod();
		//fixCachedThreadPoolMethod();
		singleThreadPoolMethod();
	}

}
