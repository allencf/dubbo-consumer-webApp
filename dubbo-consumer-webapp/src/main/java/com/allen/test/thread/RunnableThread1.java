package com.allen.test.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Company:     HQYG.
 * author:      cf
 * Createdate:  2016年12月19日上午10:24:30
 */
public class RunnableThread1 implements Runnable{
	
	private static final Logger logger = LoggerFactory.getLogger(RunnableThread1.class);
	
	private static int num = 0;
	
	private static AtomicInteger acount = new AtomicInteger(0);
	
	public RunnableThread1(int num) {
		//this.num = num;
		//Thread.currentThread().setName("RunableThread1-"+num);
		//logger.info("create thread-"+num);
		//run();
	}

	@Override
	public void run() {
		try {
			//Thread.sleep(2000);
			String currentName = Thread.currentThread().getName();
			//System.out.println("currentName:" + currentName);
			//logger.info("num:"+num +"--name is:" + currentName + " is run………");
			//num ++;
			//acount.addAndGet(1);
			getCount();
		} catch (Exception e) {
			logger.error("线程执行异常,异常信息:"+e.getMessage(),e);
		}
		
	}
	
	
	public void getCount () {
		for (int i = 0; i < 5000; i++) {
			Thread thred = new Thread(new Runnable() {
				@Override
				public void run() {
					++ num ;
					acount.getAndIncrement();
					//System.out.println("Thread:"+ Thread.currentThread().getName() + "==num:" + num +",===acount:" + acount);
					//System.out.println(acount);
				}
			 }
			);
			thred.start();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		RunnableThread1 rt = new RunnableThread1(0);
		rt.getCount();
		CountDownLatch down = new CountDownLatch(1);
		down.await(1000, TimeUnit.MILLISECONDS);
		System.out.println("num:" + num);
		System.out.println("acount:" + acount);
	}

}
