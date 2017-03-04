package com.allen.test.thread;


public class SequenceNumber {
	
	/*private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {
		
		public Integer initialValue() {
	        return 0;
	    }
	};*/
	
	private static Integer seqNum = 0;
	
	
	public int getNextNum(){
		//seqNum.set(seqNum.get()+1);
		return seqNum++; //seqNum.get();
	}


	public static void main(String[] args) {
		SequenceNumber seq = new SequenceNumber();
		ThreadLocalTest t1 = new ThreadLocalTest(seq);
		t1.setName("t1");
		ThreadLocalTest t2 = new ThreadLocalTest(seq);
		t2.setName("t2");
		ThreadLocalTest t3 = new ThreadLocalTest(seq);
		t3.setName("t3");
		
		t1.start();
		t2.start();
		t3.start();
		
	}

}