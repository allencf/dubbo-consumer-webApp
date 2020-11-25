package com.allen.test;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

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
		//test3();
		/*int i = 1;
		try {
			int a = 100 / 1;
			System.out.println("a");
			return;
		} catch (Exception e) {
			i = i + 1;
			System.out.println("b");
		} finally {
			i = i + 1;
			System.out.println("c");
		}
		System.out.println("d");*/
		new Thread(new Runnable() {
			@Override public void run() {
				/*try {
					//Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				System.out.println("a");
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override public void run() {
				/*try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				System.out.println("b");
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override public void run() {
				/*try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				System.out.println("c");
			}
		}).start();
	}

}
