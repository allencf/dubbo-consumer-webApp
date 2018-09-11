package com.allen.test.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CachedThreadPool {
	
	//https://www.cnblogs.com/aaron911/p/6213808.html
	//https://blog.csdn.net/mine_song/article/details/70948223
	
	private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	
	private static ExecutorService fixCachedThreadPool = Executors.newFixedThreadPool(3);
	
	private static ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
	
	
	/*在前面我们多次提到了任务缓存队列，即workQueue，它用来存放等待执行的任务。
	workQueue的类型为BlockingQueue<Runnable>，通常可以取下面三种类型：
	1）ArrayBlockingQueue：基于数组的先进先出队列，此队列创建时必须指定大小；
	2）LinkedBlockingQueue：基于链表的先进先出队列，如果创建时没有指定此队列大小，则默认为Integer.MAX_VALUE；
	3）synchronousQueue：这个队列比较特殊，它不会保存提交的任务，而是将直接新建一个线程来执行新来的任务。 */
	
	//private static ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
	
	//获取CPU数
	//private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2; // 核心线程数为 CPU 数＊2
	
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
	
	
	public CachedThreadPool() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static void threadPoolTest() {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(5));
		for (int i = 1; i <= 20; i++) {
			RunnableThread1 thread = new RunnableThread1(i);
			executor.execute(thread);
			System.out.println(
					"线程池中线程数目:" + executor.getPoolSize() + 
					"  线程池中核心池数目:" + executor.getCorePoolSize() + 
					"  队列中等待执行的任务数目:" + executor.getQueue().size() + 
					"  队列中已执行完的任务数目:" + executor.getCompletedTaskCount() + 
					"  ------------------------------------------------------------");
			try {
				//Thread.sleep(1500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		executor.shutdown();
	}
	

	public static void main(String[] args) {
		//cachedThreadPoolMethod();
		//fixCachedThreadPoolMethod();
		//singleThreadPoolMethod();
		//打印服务器CPU核心数
		//System.out.println("服务器CPU数：" + Runtime.getRuntime().availableProcessors());
		threadPoolTest();
	}

}
