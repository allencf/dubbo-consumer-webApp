package com.allen.favorite.abstact;

public abstract class AbstactOne {
	
	public String str;
	
	public final Integer i = 5;
	
	public abstract void testOne(String str);
	
	public abstract void testTwo(String str,Integer i);
	
	public void testThree(String str) {
		System.out.println(str);
	}
	

}
