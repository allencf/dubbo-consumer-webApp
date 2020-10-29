package com.allen.test.equal;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class EqualsTest {
	
	
	public void testE(){
		
		String strOne = "abc";
		String strTwo = "abc";
		
		System.out.println(strOne.equals(strTwo));
		
	}
	
	class A {

		/*@Override
		public boolean equals(Object obj) {
			return true;
		}*/
		
		
	}
	
	class B {
		
	}
	
	public void test() {
		A a = new A();
		B b = new B();
		System.out.println(a.equals(b));
		System.out.println(a.hashCode() + "---" + b.hashCode());
	}
	
	
	public void test2() {
		Integer i = new Integer(10);
		Integer j = new Integer(10);
		System.out.println(i == j);
		System.out.println(j.equals(j));
		System.out.println(i.hashCode() + "," + j.hashCode());
	}
	
	public void testHashMap () {
		Map<String, Object> testMap = new HashMap<String, Object>();
		testMap.put("key1", "v1");
		testMap.put("key2", "v2");
		
		for (Map.Entry<String, Object> entry : testMap.entrySet()) {
			System.out.println("key:" + entry.getKey() + ",value:" + entry.getValue());
		}
	}
	
	public void testCon() {
		ReentrantLock lock = new ReentrantLock();
	}

	public static void main(String[] args) {
		EqualsTest test = new EqualsTest();
		test.test();
		test.test2();
		test.testHashMap();

	}

}
