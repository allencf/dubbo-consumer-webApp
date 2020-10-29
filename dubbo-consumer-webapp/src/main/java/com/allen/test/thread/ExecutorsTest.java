package com.allen.test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsTest {
	
	private static ExecutorService service = Executors.newCachedThreadPool();
	
	//private static ExecutorService poolService = Executors.new
	
	
	
	
	public static void main(String[] args) {
		
		service.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

}
