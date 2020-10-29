package com.allen.test.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Company:     HQYG.
 * author:      cf
 * Createdate:  2016年12月19日上午10:24:30
 */
public class RunnableThread1 implements Runnable{
	
	private static final Logger logger = LoggerFactory.getLogger(RunnableThread1.class);
	
	private int num = 0; 
	
	public RunnableThread1(int num) {
		this.num = num;
		//Thread.currentThread().setName("RunableThread1-"+num);
		//logger.info("create thread-"+num);
		//run();
	}

	@Override
	public void run() {
		try {
			//Thread.sleep(2000);
			String currentName = Thread.currentThread().getName();
			System.out.println("currentName:" + currentName);
			logger.info("num:"+num +"--name is:" + currentName + " is run………");
		} catch (Exception e) {
			logger.error("线程执行异常,异常信息:"+e.getMessage(),e);
		}
		
	}

	public static void main(String[] args) {
		RunnableThread1 rt = new RunnableThread1(1);
		Thread th = new Thread(rt);
		th.start();
	}

}
