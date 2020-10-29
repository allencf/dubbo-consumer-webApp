package com.allen.test.thread.banktest;

public class ThreadLocalBankAccount {
	
	
	//通过匿名内部类覆盖ThreadLocal中initialValue方法
	private static ThreadLocal<Double>  balance = new ThreadLocal<Double>(){
		public Double initialValue() {  
            return 1000d;  
        }  
	};
	
	
	public Double getBalance(){
		return balance.get();
	}
	
	public void withDraw(Double amount){
		 balance.set(balance.get()-amount);
	}
	
	
	public void deposit(Double amount){
		balance.set(balance.get() + amount);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
