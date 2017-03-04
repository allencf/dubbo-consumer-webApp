package com.allen.test.thread;

public class ThreadLocalTest extends Thread{
	
	private SequenceNumber seqNum;
	
	
	public ThreadLocalTest(SequenceNumber seqNum){
		this.seqNum = seqNum;
	}

	
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println("threadName:"+Thread.currentThread().getName() + "--sn:" + seqNum.getNextNum());
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
