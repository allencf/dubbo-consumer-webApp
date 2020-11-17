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
	
	
	public static void test3() {
		int a = 1000;
		int b = 1000;
		System.out.println(a == b);
		
	}
	
	
	public abstract void test();
	
	public static void main(String[] args) {
		test3();
	}

}
