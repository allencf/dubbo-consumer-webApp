package com.allen.test.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
	
	public void test1() {
		ReentrantLock lock = new ReentrantLock(false);
		
		Semaphore avilable = new Semaphore(100);
		try {
			avilable.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testSemaphore() {
		
		Semaphore semaphore = new Semaphore(3);
		System.out.println("-----------start------------");
		for (int i = 0; i < 10; i++) {
			new Runnable() {
				@Override
				public void run() {
					try {
						semaphore.acquire();
						System.out.println(Thread.currentThread().getName() + "acquire");
						semaphore.release();
						System.out.println(Thread.currentThread().getName() + "release");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			};
		}
		
		
		
	}
	
	
	static class TaskThread extends Thread {
		
		Semaphore semaphore;
		
		public TaskThread(Semaphore semaphore) {
			this.semaphore = semaphore;
		}
		
		
		@Override
		public void run() {
			try {
				semaphore.acquire();
				System.out.println(getName() + "---- acquire");
				Thread.sleep(500);
				semaphore.release();
				System.out.println(getName() + "---- release");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	public static void main(String[] args) {
		//testSemaphore();
		Semaphore semphare = new Semaphore(2);
		for (int i = 0; i < 5; i++) {
			new TaskThread(semphare).start();
		}

	}

}
