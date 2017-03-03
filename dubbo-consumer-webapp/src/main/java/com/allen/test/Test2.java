package com.allen.test;


public abstract class Test2 extends Test {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return super.getName();
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		super.setName(name);
	}
	
	public abstract  void  test1();
	
	
	
	public abstract void test();
	
	public static void main(String[] args) {
		synchronized (args) {
			
		}
	}

}
