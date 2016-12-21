package com.allen.test.thread.banktest;

public class ThreadLocalManager implements Runnable{
	
	private ThreadLocalBankAccount bankAccount = new ThreadLocalBankAccount();
	
	
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			makeWithDraw(200d);
			if(bankAccount.getBalance() < 0){
				System.out.println("☆ 当前余额:" + bankAccount.getBalance() + "---当前线程:"+Thread.currentThread().getName());
			}
		}
	}
	
	
	private void makeWithDraw(Double amount){
		if(bankAccount.getBalance() >= amount){
			System.out.println(Thread.currentThread().getName() +"  准备操作---------------");
			try {
				//让线程等待0.5秒,模拟真实场景
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			bankAccount.withDraw(amount);
			System.out.println(Thread.currentThread().getName() + " 操作完成,余额:"+bankAccount.getBalance());
			
		}else{
			System.out.println(Thread.currentThread().getName() + " 余额不足,当前线程:" + Thread.currentThread().getName());
		}
		
		
	}

	public static void main(String[] args) {
		ThreadLocalManager manager = new ThreadLocalManager();
		
		Thread thread1 = new Thread(manager);
		thread1.setName("allen");
		Thread thread2 = new Thread(manager);
		thread2.setName("viney");
		
		thread1.start();
		thread2.start();

	}

}
