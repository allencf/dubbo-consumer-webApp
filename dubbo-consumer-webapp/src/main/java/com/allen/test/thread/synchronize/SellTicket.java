package com.allen.test.thread.synchronize;


/**
 * 模拟售票的一个线程测试类
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 * author:      cf
 * Createdate:  2017年3月3日下午2:22:27
 */
public class SellTicket implements Runnable{

	//剩余余票数
	private int tickets = 100;
	
	@Override
	public void run() {
		sellTicket();
	}
	
	private synchronized void sellTicket(){
		while(true){
			if(tickets > 0){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "-剩余余票数:" + tickets--);
			}else{
				//终止线程
				Thread.currentThread().interrupt();
			}
		}
		
		
	}
	

	public static void main(String[] args) {
		SellTicket ticket = new SellTicket();
		Thread t1 = new Thread(ticket);
		t1.setName("窗口1");
		Thread t2 = new Thread(ticket);
		t2.setName("窗口2");
		Thread t3 = new Thread(ticket);
		t3.setName("窗口3");
		Thread t4 = new Thread(ticket);
		t4.setName("窗口4");
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}

}
