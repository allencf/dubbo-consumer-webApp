package com.allen.test;

import java.util.HashMap;
import java.util.Map;

public class Test {
	
	private String name;
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}



	public static void main(String[] args) {
		int i = 0x00000001;
		System.out.println(0 & i);
		//boolean flag = 1 & i;
		
		String s ="aa";
		int code = s.hashCode();
		System.out.println(code);
		Map<String, Object> map = new HashMap<>();
		String value1 = "value1";
		String v = (String) map.put("key1", value1);
		System.out.println(v);
		
		Test test = new Test();
		test.setName("allen");
		
		System.out.println(test.getName());
		
		Test test2 = test;
		test2.setName("viney");
		
		System.out.println(test.getName());
		
	}

}
