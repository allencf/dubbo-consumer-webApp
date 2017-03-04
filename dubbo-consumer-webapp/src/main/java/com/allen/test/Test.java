package com.allen.test;


public class Test {
	
	private String name;
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}



	public Test() {
	}
	
	public String getaValue(){
		String value = "";
		try {
			value ="test1";
			return value;
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
			value = "test12";
		}
		return value;
	}


	public static void main(String[] args) {
		/*int i = 0x00000001;
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
		*/
		int a = (int)3.9;
		
		//System.out.println(a);
		
		
		long temp = (int)3.9;
		
		temp%=2;
		//System.out.println(temp);
		
		//System.out.println(4&7);
		
		System.out.println(Math.round(10.2));
		
		//Math.round(12.376*Math.round(10.2))/Math.round(10.2);	
		
		
	}

}
