package com.allen.test.thread;

public class ThreadLocalTest extends Thread{
	
	private SequenceNumber seqNum;
	
	
	public ThreadLocalTest(SequenceNumber seqNum){
		this.seqNum = seqNum;
	}

	
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println("threadName:"+Thread.currentThread().getName() + "--sn:" + seqNum.getNextNum());
		}
		
	}

	public static void main(String[] args) {
		SequenceNumber se = new SequenceNumber();
		
		ThreadLocalTest thread = new ThreadLocalTest(se);
		thread.start();
	}

}
